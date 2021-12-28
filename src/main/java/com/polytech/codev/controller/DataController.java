package com.polytech.codev.controller;

import com.polytech.codev.appels.AppelApi;
import com.polytech.codev.payload.response.MessageResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;


@RestController
@CrossOrigin
@RequestMapping("/data")
public class DataController {

    @GetMapping()
    public Object listConsumption(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken))
            return new MessageResponse("coucou " + auth.getName());
        return new MessageResponse("fuck off");
    }

    @GetMapping("/{id}")
    public Object getConsumption(@PathVariable long id){
        return null;
    }

    @GetMapping("/period/{id}")
    public Object getConsumptionPeriod(@PathVariable long id){
        return null;
    }













    @Deprecated
    @GetMapping("/test")
    public Object findAllData()
    {
        Object data = null;

        try {
            AppelApi appelApi = new AppelApi();
            String str = appelApi.getData();
            Gson gson = new Gson();
            Type listType = new TypeToken<Object>(){}.getType();
            data = gson.fromJson(str, listType);
            //LinkedTreeMap<Object, Object> test = (LinkedTreeMap<Object, Object>) data;
            //data = test.get("records");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return data;
    }


}
