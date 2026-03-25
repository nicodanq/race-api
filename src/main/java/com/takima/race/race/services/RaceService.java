package com.takima.race.race.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.race.entities.Race;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.registration.repositories.RegistrationRepository;

import java.util.List;

@Service
public class RaceService {
    private final RaceRepository raceRepository;
    private final RegistrationRepository registrationRepository;

    public RaceService(RaceRepository raceRepository, RegistrationRepository registrationRepository) {
        this.raceRepository = raceRepository;
        this.registrationRepository = registrationRepository;
    }

    public List<Race> getAll(String location) {
        if (location != null) {
            return raceRepository.findAllByLocation(location);
        }
        return raceRepository.findAll();
    }

    public Race getById(Long id) {
        return raceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Runner %s not found", id)
                )
        );
    }

    public Race createRace(Race race) {
        return raceRepository.save(race);
    }

    public Race updateRace(Race race, Long id) {
        Race raceToUpdate = raceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Race " + id + " not found"));
        raceToUpdate.setName(race.getName());
        raceToUpdate.setDate(race.getDate());
        raceToUpdate.setLocation(race.getLocation());
        raceToUpdate.setMaxParticipants(race.getMaxParticipants());
        return raceRepository.save(raceToUpdate);
    }

    public Integer countRegisterInRace(Long valraceId){
        return registrationRepository.countRunnersByRaceId(valraceId);
    }
}
