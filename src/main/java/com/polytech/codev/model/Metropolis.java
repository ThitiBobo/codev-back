package com.polytech.codev.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(	name = "metropolises")
public class Metropolis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String code;

    private Double latitude;

    private Double longitude;

    @NotBlank
    private String name;

    public Metropolis() {}

    public Metropolis(Long id, String code, String name, Double latitude, Double longitude) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
