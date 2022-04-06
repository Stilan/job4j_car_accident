package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Set;

public interface Store {
    public Accident save(Accident accident);
    public List<Accident> getAll();
    public List<Rule> findByRuleAll();
    public List<AccidentType> findByTypeAll();
    public AccidentType findByAccidentTypeId(int id);
    public Accident findByAccidentId(int id);
    public Rule findByIdRule(int id);
    public Set<Rule> findRulesByIds(String[] ids);
}
