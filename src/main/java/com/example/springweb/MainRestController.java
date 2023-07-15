package com.example.springweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/1.1")
@CrossOrigin
public class MainRestController {

    @Autowired
    UserdetailRepository userdetailRepository;
    @GetMapping("getdummyuser")
    Userdetail getDummyUser(){
        Userdetail userdetail = new Userdetail();
        userdetail.setUsername("user100");
        userdetail.setFname("robin");
        userdetail.setLname("shetty");
        userdetail.setPhone("70546561");
        userdetail.setEmail("harea@gmail.com");
        userdetail.setType("Buyer");

        return userdetail;
    }


    @PostMapping("getuserbyemail")
    public ResponseEntity<Userdetail> getUserDetailsByEmail(@RequestParam("email") String email)
    {
        if (userdetailRepository.findByEmail(email).isPresent()){
            return new ResponseEntity<>(userdetailRepository.findByEmail(email).get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
