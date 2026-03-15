package com.takima.race.runner.services;

import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;

    public RunnerService(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    public List<Runner> getAll() {
        return runnerRepository.findAll();
    }

    public Runner getById(Long id) {
        return runnerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Runner %s not found", id)
                )
        );
    }

    // POST /runners
    public Runner createRunner(Runner runner) {
        return runnerRepository.save(runner);
    }

    // DELETE /runners/{id}
    public void deleteRunner(Long id) {
        runnerRepository.deleteById(id);
    }

    // PUT /runners/{id}
    public Runner updateRunner(Runner runner, Long id){
        //logique métier pour mettre a jour le runner avec les nouvelles données
        //recuperer le runner
        Runner runner_to_update = runnerRepository.findById(id).orElseThrow(()->new RuntimeException("Runner id not found"));
        runner_to_update.setAge(runner.getAge());
        runner_to_update.setFirstName(runner.getFirstName());
        runner_to_update.setLastName(runner.getLastName());
        runner_to_update.setEmail(runner.getEmail());


        //enregistrer les nouvelles données du runner
        return runnerRepository.save(runner_to_update);
    }
}