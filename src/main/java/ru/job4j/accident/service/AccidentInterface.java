package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Set;

public interface AccidentInterface {

    void save(Accident accident);
    Accident getAccidentById(int id);
    List<Rule> getRulesBy();
    Rule getRuleById(int id);
    List<AccidentType> getAccidentTypesBy();
    AccidentType getAccidentTypeById(int id);
    Set<Rule> findRulesByIds(String[] ids);

}
