package cz.muni.fi.pa165.mvc.controllers;

import cz.fi.muni.pa165.dto.UserDTO;
import cz.fi.muni.pa165.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    final static Logger log = LoggerFactory.getLogger(ShoppingController.class);

    @Autowired
    private UserFacade userFacade;

    public void setUserFacade(UserFacade userFacade) { this.userFacade = userFacade; }

    @RequestMapping("/list")
    public String list(
            Model model
    ) {
        log.debug("show()");
        Collection<UserDTO> users = userFacade.getAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }
}
