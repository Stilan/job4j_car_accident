package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private static final AccidentMem INST = new AccidentMem();

    private static final AtomicInteger ACCIDENTS_ID = new AtomicInteger();

    HashMap<Integer, Accident> accidents = new HashMap<>();

    public static AccidentMem instOf() {
        return INST;
    }

    public void save(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(ACCIDENTS_ID.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    public Collection<Accident> findByAll() {
        return accidents.values();
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }
}
