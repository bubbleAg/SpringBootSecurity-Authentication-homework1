package com.koro.firstloginspringsecurity.service;

import com.koro.firstloginspringsecurity.model.AppUser;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;

@Service
public class AuthenticationCounterService {

    public static HashSet<AppUser> userList = new HashSet<>();

    @EventListener
    public void authSuccessEvent(AuthenticationSuccessEvent e){
         String username = ((User)e.getAuthentication().getPrincipal()).getUsername();
         userList.stream().filter(user -> user.getNick().equals(username)).findFirst().ifPresentOrElse(
                 (u) -> {
                    int count = u.getAuthCount();
                    u.setAuthCount(++count);
                    },
                 () -> {
                    userList.add(new AppUser(username, 1));
                 });
    }

    public int getNumberOfAuthentication(Principal principal){
        AppUser actualUser = userList
                .stream()
                .filter(user -> user.getNick().equals(principal.getName()))
                .findFirst()
                .get();
        return actualUser.getAuthCount();
    }
}
