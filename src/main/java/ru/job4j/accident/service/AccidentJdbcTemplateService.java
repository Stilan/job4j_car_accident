package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.List;
import java.util.Set;

@Service
public class AccidentJdbcTemplateService {

    @Autowired
    private AccidentJdbcTemplate accidentJdbcTemplate;

    public void save(Accident accident) {
        accidentJdbcTemplate.save(accident);
    }

    public AccidentType findByAccidentTypeId(int id) {
      return accidentJdbcTemplate.findByAccidentTypeId(id).get();
    }

    public Set<Rule> findRulesByIds(String[] ids) {
        return accidentJdbcTemplate.findRulesByIds(ids);
    }

    public List<Rule> findByRuleAll() {
        return accidentJdbcTemplate.findByRuleAll();
    }

    public List<AccidentType> findByTypeAll() {
        return accidentJdbcTemplate.findByTypeAll();
    }

    public Accident findByAccidentId(int id) {
        return accidentJdbcTemplate.findByAccidentId(id);
    }
}
