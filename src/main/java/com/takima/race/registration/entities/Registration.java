package com.takima.race.registration.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long runnerId;
    private Long raceId;
    private LocalDate registrationDate;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Registration registration = (Registration) o;
        return Objects.equals(id, registration.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Getters
    public Long getId() { return id; }
    public Long getRunnerId() { return runnerId; }
    public Long getRaceId() { return raceId; }
    public LocalDate getRegistrationDate() { return registrationDate; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setRunnerId(Long runnerId) { this.runnerId = runnerId; }
    public void setRaceId(Long raceId) { this.raceId = raceId; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }
}
