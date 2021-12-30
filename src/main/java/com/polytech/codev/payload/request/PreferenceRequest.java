package com.polytech.codev.payload.request;

import javax.validation.constraints.NotBlank;

public class PreferenceRequest {

    private Long profileId;

    private Long metropolisId;

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
