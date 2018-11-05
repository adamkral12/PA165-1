<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="users">
<jsp:attribute name="body">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Given name</th>
                <th>Surname</th>
                <th>Email</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Joined date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user" varStatus="ic">

                <tr>
                    <td><c:out value="${user.givenName}"/></td>
                    <td><c:out value="${user.surname}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.address}"/></td>
                    <td><c:out value="${user.phone}"/></td>
                    <td><c:out value="${user.joinedDate}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
</jsp:attribute>
</my:pagetemplate>