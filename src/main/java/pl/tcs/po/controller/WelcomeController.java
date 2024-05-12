package pl.tcs.po.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    // When this is uncommented, Vaadin will not work, no idea why

    // @GetMapping("/")
    // public String welcome(){
    //     return "Welcome to out website";
    // }
}
