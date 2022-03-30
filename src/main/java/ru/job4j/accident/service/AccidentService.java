package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccidentService {

    @Autowired
    private AccidentMem accidentMem;

    public Collection<Accident> findByAll() {
        return accidentMem.findByAll();
    }

    public void save(Accident accident, String[] ids) {
        accidentMem.save(accident, ids);
    }

    public Accident findById(int id) {
      return accidentMem.findById(id);
    }

    public List<AccidentType> findByTypeAll() {
        return accidentMem.findByTypeAll();
    }
    public AccidentType findByTypeId(int id) {
        return accidentMem.findByTypeId(id);
    }

    public List<Rule> findByRuleAll() {
        return accidentMem.findByRuleAll();
    }
}
