package com.polytech.codev.controller;

import com.polytech.codev.appels.AppelApi;
import com.polytech.codev.model.Data;
import com.polytech.codev.payload.response.DataResponse;
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
import java.util.ArrayList;
import java.util.Date;
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
    public DataResponse listConsumption(){
        DataResponse response = new DataResponse();
        List<Data> data = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            data = this.service.listConsumption();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        if (!(auth instanceof AnonymousAuthenticationToken)){
            //TODO
        }

        // set up most recent consumption
        Date recentDate = data.get(0).getDate_hour();
        for(Data d: new ArrayList<>(data)){
            if (d.getDate_hour().equals(recentDate)){
                response.addRecentData(d);
                data.remove(d);
            }
        }
        // set up other recent consumption
        response.setOtherData(data);

        return response;
    }

    @GetMapping("/{id}")
    public Data getConsumption(@PathVariable long id){
        Data data = null;
        try {
            data = this.service.getConsumption(String.valueOf(id));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @GetMapping("/period/{id}")
    public Object getConsumptionPeriod(@PathVariable long id) {
        return null;
    }
}
