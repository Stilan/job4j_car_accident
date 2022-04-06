package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.List;
import java.util.Set;

@Service
public class AccidentServiceHibernate {

    @Autowired
   private AccidentHibernate accidentHibernate;

    public Accident save(Accident accident) {
        return accidentHibernate.save(accident);
    }
    public List<Accident> getAll() {
      return accidentHibernate.getAll();
    }

    public List<Rule> findByRuleAll() {
        return accidentHibernate.findByRuleAll();
    }

    public List<AccidentType> findByTypeAll() {
       return accidentHibernate.findByTypeAll();
    }

    public AccidentType findByAccidentTypeId(int id) {
       return accidentHibernate.findByAccidentTypeId(id);
    }

    public Accident findByAccidentId(int id) {
      return accidentHibernate.findByAccidentId(id);
    }

    public Rule findByIdRule(int id) {
       return accidentHibernate.findByIdRule(id);
    }

    public Set<Rule> findRulesByIds(String[] ids) {
       return accidentHibernate.findRulesByIds(ids);
    }
}
