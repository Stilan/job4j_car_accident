package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private static final AtomicInteger ACCIDENTS_ID = new AtomicInteger();

   private HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        accidents.put(1, new Accident("Вася", "Дтп", "Ленина 12"));
        accidents.put(2, new Accident("Федя", "Дтп", "Ленина 13"));
        accidents.put(3, new Accident("Коля", "Дтп", "Ленина 14"));
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
