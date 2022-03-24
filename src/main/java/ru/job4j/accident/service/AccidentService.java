package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {

    private AccidentMem accidentMem;

    public AccidentService() {
        this.accidentMem = new AccidentMem();
    }

    public Collection<Accident> findByAll() {
        return accidentMem.findByAll();
    }
}
