package com.example.codefellowship;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String body;
    private LocalDateTime createAt = LocalDateTime.now();

    @ManyToOne
    private ApplicationUser user;

    public Post() {

    }

    public Post(String body, ApplicationUser user) {
        this.body = body;
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public ApplicationUser getApplicationUser() {
        return user;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.user = applicationUser;
    }
}
