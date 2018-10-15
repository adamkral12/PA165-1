package cz.muni.fi.pa165;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import javax.inject.Named;

@Named
@Aspect
public class LoggingAspect {
    @Around("execution(public * cz.muni.fi.pa165.currency..*(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {

        System.err.println("Calling method: "
                + joinPoint.getSignature());
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        System.err.println("Method finished: "
                + joinPoint.getSignature() + ", start: " + start + ", end: " + end);

        return result;
    }
}
