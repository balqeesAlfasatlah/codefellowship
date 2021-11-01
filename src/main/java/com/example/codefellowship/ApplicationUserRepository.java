package com.example.codefellowship;

import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<Integer,ApplicationUser> {
    public ApplicationUser findByUsername(String username);
}
