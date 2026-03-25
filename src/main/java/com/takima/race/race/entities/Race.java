package com.takima.race.race.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    /**
     * The date of the race.
     * <p>
     * Use {@link java.util.Date} for general date/time handling unless you need SQL-specific features,
     * in which case use {@link java.sql.Date}. For entity fields in JPA/Hibernate, {@link java.util.Date} is commonly used.
     */
    private Date date;
    private String location;
    private Integer maxParticipants;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return Objects.equals(id, race.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public Date getDate() { return date; }
    public String getLocation() { return location; }
    public int getMaxParticipants() { return maxParticipants; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDate(Date date) { this.date = date; }
    public void setLocation(String location) { this.location = location; }
    public void setMaxParticipants(int maxParticipants) { this.maxParticipants = maxParticipants; }
}
