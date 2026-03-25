package com.takima.race.registration.controllers;

import com.takima.race.registration.entities.Registration;
import com.takima.race.registration.services.RegistrationService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/races")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/{raceId}/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    public Registration createRegistration(@PathVariable Long raceId, @RequestBody Map<String, Long> body) {
        return registrationService.createRegistration(raceId, body.get("runnerId"));
    }

    @GetMapping("/{raceId}/registrations")
    public List<Registration> getByRaceId(@PathVariable Long raceId) {
        return registrationService.getByRaceId(raceId);
    }
}
