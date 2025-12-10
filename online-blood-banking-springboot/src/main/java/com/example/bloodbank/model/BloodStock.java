package com.example.bloodbank.model;

import jakarta.persistence.*;

@Entity
@Table(name = "blood_stock")
public class BloodStock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bloodGroup;
    private Integer unitsAvailable;

    public BloodStock() {}
    public BloodStock(String bloodGroup, Integer unitsAvailable) {
        this.bloodGroup = bloodGroup; this.unitsAvailable = unitsAvailable;
    }

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    public Integer getUnitsAvailable() { return unitsAvailable; }
    public void setUnitsAvailable(Integer unitsAvailable) { this.unitsAvailable = unitsAvailable; }
}
