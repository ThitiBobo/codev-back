package com.polytech.codev.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(	name = "profile_metropolises")
@IdClass(ProfileMetropolise.ProfileMetropolisId.class)
public class ProfileMetropolise {

    public static class ProfileMetropolisId implements Serializable{
        @Id
        @Column(name="profile_id")
        private Long profileId;

        @Id
        @Column(name="metropolis_id")
        private Long metropolisId;

        public ProfileMetropolisId(){}

        public ProfileMetropolisId(Long profileId, Long metropolisId) {
            this.profileId = profileId;
            this.metropolisId = metropolisId;
        }

        public Long getProfileId() {
            return profileId;
        }

        public void setProfileId(Long profileId) {
            this.profileId = profileId;
        }

        public Long getMetropolisId() {
            return metropolisId;
        }

        public void setMetropolisId(Long metropolisId) {
            this.metropolisId = metropolisId;
        }
    }

    @Id
    @Column(name="profile_id")
    private Long profileId;

    @Id
    @Column(name="metropolis_id", insertable = false, updatable = false)
    private Long metropolisId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = true, insertable = false, updatable = false)
    private Profile profile;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "metropolis_id", referencedColumnName = "id", nullable = true, insertable = false, updatable = false)
    private Metropolis metropolis;

    public ProfileMetropolise(){}

    public ProfileMetropolise(Long profileId, Long metropolisId) {
        this.profileId = profileId;
        this.metropolisId = metropolisId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getMetropolisId() {
        return metropolisId;
    }

    public void setMetropolisId(Long metropolisId) {
        this.metropolisId = metropolisId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Metropolis getMetropolis() {
        return metropolis;
    }

    public void setMetropolis(Metropolis metropolis) {
        this.metropolis = metropolis;
    }
}
