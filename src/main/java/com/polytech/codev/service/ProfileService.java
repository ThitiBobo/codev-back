package com.polytech.codev.service;

import com.polytech.codev.model.Metropolis;
import com.polytech.codev.model.Profile;
import com.polytech.codev.model.ProfileMetropolise;
import com.polytech.codev.repositorie.MetropolisRepository;
import com.polytech.codev.repositorie.ProfileMetropolisRepository;
import com.polytech.codev.repositorie.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class ProfileService {

    private final ProfileRepository repository;
    private final ProfileMetropolisRepository profileMetropolisRepository;
    private final MetropolisRepository metropolisRepository;

    @Autowired
    public ProfileService(ProfileRepository repository,
                          ProfileMetropolisRepository profileMetropolisRepository,
                          MetropolisRepository metropolisRepository){
        this.repository = repository;
        this.profileMetropolisRepository = profileMetropolisRepository;
        this.metropolisRepository = metropolisRepository;
    }

    public List<Metropolis> listPreferences(Long id){
        return this.repository.listPreferences(id).orElseThrow( () -> new EntityNotFoundException());
    }

    public List<Metropolis> createPreference(Long user_id, ProfileMetropolise preference) {
        this.hasRightProfile(user_id, preference);
        this.profileMetropolisRepository.save(preference);
        return listPreferences(user_id);
    }

    public List<Metropolis> updatePreference(Long user_id, long id, ProfileMetropolise preference) {
        this.deletePreference(user_id, id);
        return this.createPreference(user_id, preference);
    }

    public List<Metropolis> deletePreference(Long user_id, long id) {
        Long profileId = this.repository.getProfileByUser(user_id)
                .orElseThrow( () -> new EntityNotFoundException())
                .get(0).getId();
        ProfileMetropolise preference = this.profileMetropolisRepository.findById(id, profileId)
                .orElseThrow( () -> new EntityNotFoundException());
        if (this.hasRightProfile(user_id, preference)){
            this.profileMetropolisRepository.delete(preference);
        }
        return listPreferences(user_id);
    }

    private boolean hasRightProfile(Long user_id, ProfileMetropolise preference){
        List<Profile> profiles = this.repository.getProfileByUser(user_id).orElseThrow(() -> new EntityNotFoundException());
        if (profiles == null || profiles.size() == 0)
            throw new EntityNotFoundException();
        if (!Objects.equals(preference.getProfileId(), profiles.get(0).getId()))
            throw new EntityNotFoundException();
        return true;
    }
}
