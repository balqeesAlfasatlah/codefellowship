package com.example.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;


@Controller
public class ApplicationUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/")
    public String getHome(){
        return "index.html";
    }


    @GetMapping("/signup")
    public String getSignPage(){
        return "signup.html";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login.html";
    }

    @PostMapping("/signup")
    public RedirectView signUpUser(@RequestParam String username , @RequestParam String password, String firstName , String lastName ,
                                   String dateOfBirth , String bio){

        ApplicationUser applicationUser = new ApplicationUser(username,encoder.encode(password),firstName , lastName, dateOfBirth , bio);
       applicationUserRepository.save(applicationUser);
        return  new RedirectView("/login");
    }


    @GetMapping("/profile")
    public String myprofile(Model model , Principal principal){
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(principal.getName());
        model.addAttribute("username", applicationUser.getUsername());
        model.addAttribute("firstName", applicationUser.getFirstName());
        model.addAttribute("lastName", applicationUser.getLastName());
        model.addAttribute("dateOfBirth", applicationUser.getDateOfBirth());
        model.addAttribute("bio", applicationUser.getBio());
        model.addAttribute("posts", applicationUser.getPosts());
        return "profile.html";
    }

    @PostMapping("/addpost")
    public RedirectView addPost(@AuthenticationPrincipal ApplicationUser user , @RequestParam String body){
        ApplicationUser curUser = applicationUserRepository.findByUsername(user.getUsername());
        Post newPost = new Post(body,curUser);
        postRepository.save(newPost);
        return new RedirectView("/profile");
    }


}
