package com.example.codefellowship;

import com.example.codefellowship.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser,Integer> {
    public ApplicationUser findByUsername(String username);
}
