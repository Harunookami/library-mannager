package com.library.mng.controller;


import com.library.mng.DTO.RecordUser;
import com.library.mng.model.UserModel;
import com.library.mng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public UserModel addUser(@RequestBody RecordUser user) {
        return service.save(user);
    }

    @GetMapping
    public List<UserModel> listAllUsers() {
        return service.listAllUsers();
    }

    @GetMapping("/{id}")
    public UserModel findUserById(@PathVariable long id) {
        UserModel user = service.getUserById(id);
        if (user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }

        return service.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserModel updateUser(@PathVariable long id, @RequestBody RecordUser user) {
        return service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        service.deleteUserById(id);
    }
}
