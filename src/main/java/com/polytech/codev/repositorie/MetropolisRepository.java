package com.polytech.codev.repositorie;

import com.polytech.codev.model.Metropolis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetropolisRepository extends JpaRepository<Metropolis, Long> {

}

