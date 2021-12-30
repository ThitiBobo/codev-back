package com.polytech.codev.controller;

import com.polytech.codev.model.Metropolis;
import com.polytech.codev.service.MetropolisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/metropolis")
public class MetropolisController {

    private final MetropolisService service;

    @Autowired
    public MetropolisController(MetropolisService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Metropolis> listMetropolises(){
        List<Metropolis> list = null;
        try{
            list = this.service.list();
        }catch (Exception e){
            ResponseEntity.notFound().build();
        }
        return list;
    }
}
