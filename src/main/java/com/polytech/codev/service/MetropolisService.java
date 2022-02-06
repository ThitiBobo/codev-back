package com.polytech.codev.service;

import com.polytech.codev.model.Metropolis;
import com.polytech.codev.repository.MetropolisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MetropolisService {

    private final MetropolisRepository repository;

    @Autowired
    public MetropolisService(MetropolisRepository repository) {
        this.repository = repository;
    }

    public List<Metropolis> list(){
        return this.repository.findAll();
    }

    public Metropolis get(Long id){
        return this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

}
