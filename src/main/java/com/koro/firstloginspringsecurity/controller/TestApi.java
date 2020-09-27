package com.koro.firstloginspringsecurity.controller;

import com.koro.firstloginspringsecurity.service.AuthenticationCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestApi {

    @Autowired
    AuthenticationCounterService authCounter;

    @GetMapping("/forGuest")
    public String forGuest(Principal principal) {
        if(principal != null) {
            return "Czesc " + principal.getName() +
                    ". Ilosc uwierzytelnien: " + authCounter.getNumberOfAuthentication(principal);
        }
        else {
            return "Czesc nieznajomy";
        }
    }

    @GetMapping("/forAdmin")
    public String forAdmin(Principal principal) {
        return "Czesc admin " + principal.getName() +
                ". Ilosc uwierzytelnien: " + authCounter.getNumberOfAuthentication(principal);
    }

    @GetMapping("/forUser")
    public String forUser(Principal principal){
        return "Czesc user " + principal.getName() +
                ". Ilosc uwierzytelnien: " + authCounter.getNumberOfAuthentication(principal);
    }

    @GetMapping("/endPage")
    public String endPage(){
            return "Papa";
    }
}
