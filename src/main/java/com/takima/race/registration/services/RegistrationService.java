package com.takima.race.registration.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.race.entities.Race;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.repositories.RegistrationRepository;
import com.takima.race.runner.repositories.RunnerRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    private final RaceRepository raceRepository;
    private final RegistrationRepository registrationRepository;
    private final RunnerRepository runnerRepository;

    public RegistrationService(RaceRepository raceRepository, RegistrationRepository registrationRepository, RunnerRepository runnerRepository) {
        this.raceRepository = raceRepository;
        this.registrationRepository = registrationRepository;
        this.runnerRepository = runnerRepository;
    }

    public List<Registration> getByRaceId(Long raceId) {
        raceRepository.findById(raceId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Race " + raceId + " not found"));
        return registrationRepository.findAllByRaceId(raceId);
    }

    public List<Race> getRacesByRunnerId(Long runnerId) {
        runnerRepository.findById(runnerId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Runner " + runnerId + " not found"));
        return registrationRepository.findAllByRunnerId(runnerId).stream()
                .map(r -> raceRepository.findById(r.getRaceId()).orElse(null))
                .filter(r -> r != null)
                .collect(Collectors.toList());
    }

    public Registration createRegistration(Long raceId, Long runnerId) {
        runnerRepository.findById(runnerId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Runner " + runnerId + " not found"));

        Race race = raceRepository.findById(raceId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Race " + raceId + " not found"));

        if (registrationRepository.existsByRunnerIdAndRaceId(runnerId, raceId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Runner already registered to this race");
        }

        Integer count = registrationRepository.countRunnersByRaceId(raceId);
        if (count >= race.getMaxParticipants()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Race is full");
        }

        Registration registration = new Registration();
        registration.setRunnerId(runnerId);
        registration.setRaceId(raceId);
        registration.setRegistrationDate(new Date());
        return registrationRepository.save(registration);
    }
}
