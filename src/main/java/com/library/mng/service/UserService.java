package com.library.mng.service;

import com.library.mng.DTO.RecordUser;
import com.library.mng.model.UserModel;
import com.library.mng.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserModel save(RecordUser body) {
        UserModel user = new UserModel();
        user.setFullName(body.name());
        user.setCpf(body.cpf());
        user.setTel(body.tel());
        user.setEmail(body.email());
        user.setPassword(body.password());

        return repository.save(user);
    }

    public List<UserModel> listAllUsers(){
        return repository.findAll();
    }

    public UserModel getUserById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public UserModel updateUser (Long id, RecordUser body) {
        UserModel user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        user.setFullName(body.name());
        user.setCpf(body.cpf());
        user.setTel(body.tel());
        user.setEmail(body.email());
        user.setPassword(body.password());

        return repository.save(user);
    }

    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }
    
}
