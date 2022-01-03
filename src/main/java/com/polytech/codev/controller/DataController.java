package com.polytech.codev.controller;

import com.polytech.codev.appels.AppelApi;
import com.polytech.codev.model.Data;
import com.polytech.codev.payload.response.MessageResponse;
import com.polytech.codev.service.DataService;
import com.polytech.codev.service.MetropolisService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.List;

import com.google.gson.reflect.TypeToken;


@RestController
@CrossOrigin
@RequestMapping("/data")
public class DataController {

    private final DataService service;

    @Autowired
    public DataController(DataService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Data> listConsumption(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)){

        }
        try {
            return this.service.listConsumption();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
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
