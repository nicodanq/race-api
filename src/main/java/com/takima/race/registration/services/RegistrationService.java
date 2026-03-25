package com.takima.race.registration.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.registration.entities.Registration;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.registration.repositories.RegistrationRepository;

import java.util.List;

@Service
public class RegistrationService {
    private final RaceRepository raceRepository;
    private final RegistrationRepository registrationRepository;

    public RegistrationService(RaceRepository raceRepository, RegistrationRepository registrationRepository) {
        this.raceRepository = raceRepository;
        this.registrationRepository = registrationRepository;
    }

    public List<Registration> getAll() {
        return registrationRepository.findAll();
    }

    public Registration getById(Long id) {
        return registrationRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Registration %s not found", id)
                )
        );
    }

    public Registration createRegistration(Registration registration) {
        return registrationRepository.save(registration);
    }

}
