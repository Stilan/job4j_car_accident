package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

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

    public void save(Accident accident) {
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

}
