package com.polytech.codev.payload.response;

public class JwtResponse {
    private Long id;
    private Long profileId;
    private String email;
    private String firstname;
    private String lastname;

    private String token;
    private String tokenType = "Bearer";

    public JwtResponse(Long id, Long profileId, String email, String firstname, String lastname, String token, String tokenType) {
        this.id = id;
        this.profileId = profileId;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.token = token;
        this.tokenType = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }
}
