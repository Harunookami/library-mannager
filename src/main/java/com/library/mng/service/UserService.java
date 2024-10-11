package com.library.mng.service;

import com.library.mng.DTO.RecordUser;
import com.library.mng.model.UserModel;
import com.library.mng.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserModel save(RecordUser body) {
        UserModel userExist = repository.findFirstByCpf(body.cpf());
        if (userExist != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }
        UserModel user = new UserModel();
        user.setFullName(body.fullName());
        user.setCpf(body.cpf());
        user.setPhone(body.phone());
        user.setEmail(body.email());
        user.setPassword(body.password());

        return repository.save(user);
    }

    public List<UserModel> listAllUsers(){
        return repository.findAvailableUsers();
    }

    public UserModel getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public UserModel updateUser (Long id, RecordUser body) {
        UserModel user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setFullName(body.fullName());
        user.setCpf(body.cpf());
        user.setPhone(body.phone());
        user.setEmail(body.email());
        user.setPassword(body.password());

        return repository.save(user);
    }

    public void deleteUserById(Long id) {
        UserModel user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setDeletedAt(timestamp);
        repository.save(user);
    }
    
}
