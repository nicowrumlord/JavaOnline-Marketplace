package com.example.marketplace.repos;

import com.example.marketplace.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepo{
    @Autowired
    private UserCrudRepo userCrudRepo;
    public Optional<User> getUser(Integer idUser){
        return userCrudRepo.findById(idUser);
    }
    public User save(User usr) {
        return userCrudRepo.save(usr);
    }
}
