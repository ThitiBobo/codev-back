package com.polytech.codev.repository;

import com.polytech.codev.model.Metropolis;
import com.polytech.codev.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query("SELECT profiles.metropolises FROM Profile profiles WHERE profiles.user.id = :id")
    public Optional<List<Metropolis>> listPreferences(@Param("id") Long id);

    @Query("SELECT profiles FROM Profile profiles WHERE profiles.user.id = :id")
    public Optional<List<Profile>> getProfileByUser(@Param("id") Long user_id);
}
