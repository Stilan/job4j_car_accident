package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private static final AtomicInteger ACCIDENTS_ID = new AtomicInteger();

   private HashMap<Integer, Accident> accidents = new HashMap<>();

   private Map<Integer, AccidentType> accidentTypeHashMap = Map.of(
          1, AccidentType.of(1, "Две машины"),
        2, AccidentType.of(2, "Машина и человек"),
       3, AccidentType.of(3, "Машина и велосипед")
   );

   private Map<Integer, Rule>  accidentRuleMap = Map.of(
           1, Rule.of(1, "Статья. 1"),
           2, Rule.of(2, "Статья. 2"),
           3, Rule.of(3, "Статья. 3")
   );

    public void save(Accident accident, String[] ids) {
        Set<Rule> ruleSet = new HashSet<>();
        for (int i = 0; i < ids.length; i++) {
            ruleSet.add(findByRuleId(Integer.parseInt(ids[i])));
        }
        accident.setRules(ruleSet);
        if (accident.getId() == 0) {
            accident.setId(ACCIDENTS_ID.incrementAndGet());
            accidents.put(accident.getId(), accident);
        } else {
            accidents.replace(accident.getId(), accident);
        }
    }

    public AccidentType findByTypeId(int id) {
        return accidentTypeHashMap.get(id);
    }

    public Collection<Accident> findByAll() {
        return accidents.values();
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public List<AccidentType> findByTypeAll() {
        return new ArrayList<>(accidentTypeHashMap.values());
    }

    public List<Rule> findByRuleAll() {
        return new ArrayList<>(accidentRuleMap.values());
    }

    public Rule findByRuleId(int id) {
        return accidentRuleMap.get(id);
    }

}
