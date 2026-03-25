package com.takima.race.runner.services;

import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.repositories.RunnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
        if (runner.getEmail() == null || !runner.getEmail().contains("@")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email address");
        }
        return runnerRepository.save(runner);
    }

    // DELETE /runners/{id}
    public void deleteRunner(Long id) {
        runnerRepository.deleteById(id);
    }

    // PUT /runners/{id}
    public Runner updateRunner(Runner runner, Long id) {
        if (runner.getEmail() == null || !runner.getEmail().contains("@")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email address");
        }
        //logique métier pour mettre a jour le runner avec les nouvelles données
        //recuperer le runner
        Runner runner_to_update = runnerRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Runner id not found"));
        runner_to_update.setAge(runner.getAge());
        runner_to_update.setFirstName(runner.getFirstName());
        runner_to_update.setLastName(runner.getLastName());
        runner_to_update.setEmail(runner.getEmail());


        //enregistrer les nouvelles données du runner
        return runnerRepository.save(runner_to_update);
    }
}