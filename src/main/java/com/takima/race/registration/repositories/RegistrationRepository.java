package com.takima.race.registration.repositories;

import com.takima.race.registration.entities.Registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findAllByRaceId(Long raceId);
    List<Registration> findAllByRunnerId(Long runnerId);
    boolean existsByRunnerIdAndRaceId(Long runnerId, Long raceId);
    @Query("SELECT COUNT(r) FROM Registration r WHERE r.raceId = :raceId")
    Integer countRunnersByRaceId(Long raceId);
}
