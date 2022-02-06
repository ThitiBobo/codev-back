package com.polytech.codev.controller;

import com.polytech.codev.model.Data;
import com.polytech.codev.model.DataDetail;
import com.polytech.codev.model.Metropolis;
import com.polytech.codev.payload.response.DataResponse;
import com.polytech.codev.security.services.UserDetailsImpl;
import com.polytech.codev.service.DataService;
import com.polytech.codev.service.ProfileService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@RestController
@CrossOrigin
@RequestMapping("/data")
public class DataController {

    private final DataService service;
    private final ProfileService profileService;

    @Autowired
    public DataController(DataService service, ProfileService profileService) {
        this.service = service;
        this.profileService = profileService;
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
            Long user_id = getAuthUserId();
            if (user_id != null){
                List<Metropolis> preferences = this.profileService.listPreferences(user_id);
                for (Metropolis preference: preferences){
                    Data d = data.stream().filter(o -> o.getCode().equals(preference.getCode())).findFirst().orElse(null);
                    if (d != null){
                        response.addPreferenceData(d);
                        data.remove(d);
                    }
                }
            }
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
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    @GetMapping("/{id}/period")
    public DataDetail getConsumptionPeriod(
            @PathVariable long id,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String end,
            @RequestParam(required = false) int number) {

        LocalDateTime defaultStartDate = LocalDateTime.now().minusDays(7);
        LocalDateTime startDate = (start != null) ? LocalDateTime.parse(start) : defaultStartDate;
        LocalDateTime endDate = (end != null) ? LocalDateTime.parse(end) : LocalDateTime.now();

        String f = startDate.toString();

        Data data = this.getConsumption(id);

        DataDetail dataDetail = (endDate != null) ?
                this.service.getConsumptionPeriod(id, number, startDate.toString(), endDate.toString()) :
                this.service.getConsumptionPeriod(id, number, startDate.toString());

        dataDetail.setCode(data.getCode());
        dataDetail.setConsumption(data.getConsumption());
        dataDetail.setDate_hour(data.getDate_hour());
        dataDetail.setMetropolis(data.getMetropolis());
        return dataDetail;
    }

    private Long getAuthUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()){
            Object principal = auth.getPrincipal();
            return (principal instanceof UserDetailsImpl)? ((UserDetailsImpl)principal).getId() : null;
        }
        return null;
    }
}
