package com.polytech.codev.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.polytech.codev.appels.AppelApi;
import com.polytech.codev.payload.response.MessageResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;

@RestController
@CrossOrigin
@RequestMapping("/profile")
public class ProfileController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/metropolises")
    public Object list() {
        // TODO retourne la liste des métropoles préférés de l'utilisateur courrant
        return new MessageResponse("coucou");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/metropolises")
    public Object add(@RequestBody Object o){
        // TODO ajoute une nouvelle préf
        return null;
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/metropolises/{id}")
    public Object update(@PathVariable long id, @RequestBody Object o){
        // TODO modifie une préf
        return null;
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/metropolises/{id}")
    public Object remove(@PathVariable long id){
        // TODO supprime une préf
        return null;
    }
}
