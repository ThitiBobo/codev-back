package com.polytech.codev.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.polytech.codev.appels.AppelApi;
import com.polytech.codev.model.Metropolis;
import com.polytech.codev.model.Profile;
import com.polytech.codev.model.ProfileMetropolise;
import com.polytech.codev.model.User;
import com.polytech.codev.payload.request.PreferenceRequest;
import com.polytech.codev.payload.request.SignupRequest;
import com.polytech.codev.payload.response.MessageResponse;
import com.polytech.codev.repositorie.MetropolisRepository;
import com.polytech.codev.repositorie.ProfileRepository;
import com.polytech.codev.security.services.UserDetailsImpl;
import com.polytech.codev.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService service;

    @Autowired
    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping("/metropolises")
    @ResponseStatus( HttpStatus.OK )
    public List<Metropolis> list() {
        Long user_id = getAuthUser();
        // TODO return 401 error if no auth user
        return (user_id != null)? this.service.listPreferences(user_id): null;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/metropolises")
    public List<Metropolis> add(@RequestBody PreferenceRequest preference){
        Long user_id = getAuthUser();
        ProfileMetropolise pref = new ProfileMetropolise(preference.getProfileId(), preference.getMetropolisId());
        return (user_id != null)? this.service.createPreference(user_id, pref): null;
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/metropolises/{id}")
    public List<Metropolis> update(@PathVariable long id, @RequestBody PreferenceRequest preference){
        Long user_id = getAuthUser();
        ProfileMetropolise pref = new ProfileMetropolise(preference.getProfileId(), preference.getMetropolisId());
        return (user_id != null)? this.service.updatePreference(user_id, id, pref): null;
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/metropolises/{id}")
    public List<Metropolis> remove(@PathVariable long id){
        Long user_id = getAuthUser();
        return (user_id != null)? this.service.deletePreference(user_id, id): null;
    }

    private Long getAuthUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()){
            Object principal = auth.getPrincipal();
            return (principal instanceof UserDetailsImpl)? ((UserDetailsImpl)principal).getId() : null;
        }
        return null;
    }
}
