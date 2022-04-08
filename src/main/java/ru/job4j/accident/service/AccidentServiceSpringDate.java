package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentRuleRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccidentServiceSpringDate implements AccidentInterface {

    private AccidentRepository accidentRepository;
    private AccidentTypeRepository accidentType;
    private AccidentRuleRepository accidentRule;

    public AccidentServiceSpringDate(AccidentRepository accidentRepository,
                                     AccidentTypeRepository accidentType, AccidentRuleRepository accidentRule) {
        this.accidentRepository = accidentRepository;
        this.accidentType = accidentType;
        this.accidentRule = accidentRule;
    }

    @Override
    public void save(Accident accident) {
        accidentRepository.save(accident);
    }

    @Override
    public Accident getAccidentById(int id) {
        return accidentRepository.findById(id).get();
    }

    @Override
    public List<Rule> getRulesBy() {
        List<Rule> list = new ArrayList<>();
        accidentRule.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Rule getRuleById(int id) {
        return accidentRule.findById(id).get();
    }

    @Override
    public List<AccidentType> getAccidentTypesBy() {
        List<AccidentType> list = new ArrayList<>();
        accidentType.findAll().forEach(list::add);
        return list;
    }

    @Override
    public AccidentType getAccidentTypeById(int id) {
        return accidentType.findById(id).get();
    }

    @Override
    public Set<Rule> findRulesByIds(String[] ids) {
        Set<Rule> rsl = new HashSet<>();
        for (String id : ids) {
            rsl.add(this.getRuleById(Integer.parseInt(id)));
        }
        return rsl;
    }

}
