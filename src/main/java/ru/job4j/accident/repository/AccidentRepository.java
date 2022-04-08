package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

@Repository
public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Override
    @Query("select distinct a from Accident a join fetch a.type t join fetch a.rules")
     Iterable<Accident> findAll();

}
