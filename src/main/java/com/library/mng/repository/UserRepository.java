package com.library.mng.repository;

import com.library.mng.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    @Query(value = "SELECT * FROM users WHERE deleted_at IS NULL", nativeQuery = true)
    List<UserModel> findAvailableUsers();

    UserModel findFirstByCpf(String cpf);
}
