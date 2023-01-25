package com.kheffache.usersjwt.restController;


import com.kheffache.usersjwt.entities.User;
import com.kheffache.usersjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class UserRESTController {
    @Autowired
    UserService userService;

    @RequestMapping(path = "all", method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return  userService.fidAllUsers();
 }

}
