package com.takima.race.runner.services;

import org.postgresql.translation.messages_bg;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.runner.entities.Race;
import com.takima.race.runner.entities.Registration;
import com.takima.race.runner.repositories.RaceRepository;
import com.takima.race.runner.repositories.RegistrationRepository;

import java.util.List;

@Service
public class RaceService {
    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> getAll() {
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

    public Integer countRegisterInRace(Long id){
        //logique métier pour compter le nombre de runner inscrit à une course
        //recuperer la course, 
        Race raceToCount = raceRepository.findById(id).orElseThrow(()->new RuntimeException("race not found"));
        //aller voir dans la table d'inscription combien de runner sont inscrit à cette course


    }
} 
