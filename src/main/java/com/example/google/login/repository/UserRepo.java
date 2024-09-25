package com.example.google.login.repository;
import com.example.google.login.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, Integer> {

    User findByUseremail(String username);
}