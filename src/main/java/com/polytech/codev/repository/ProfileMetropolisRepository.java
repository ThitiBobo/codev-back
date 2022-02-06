package com.polytech.codev.repository;

import com.polytech.codev.model.ProfileMetropolise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ProfileMetropolisRepository extends JpaRepository<ProfileMetropolise, Long> {

    @Query("SELECT profileMetropolises FROM ProfileMetropolise profileMetropolises WHERE profileMetropolises.metropolisId = :metropolisId AND profileMetropolises.profileId = :profileId")
    public Optional<ProfileMetropolise> findById(@Param("metropolisId")Long metropolisId, @Param("profileId")Long profileId);
}
