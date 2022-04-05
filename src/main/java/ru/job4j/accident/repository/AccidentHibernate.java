package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class AccidentHibernate {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Accident save(Accident accident) {
        try (Session session = sf.openSession()) {
            session.saveOrUpdate(accident);
            return accident;
        }
    }

    public List<Accident> getAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("select distinct a from Accident a "
                            + "join fetch a.type t "
                            + "join fetch a.rules", Accident.class)
                    .list();
        }
    }

    public List<Rule> findByRuleAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("select distinct r from Rule r", Rule.class)
                    .list();
        }
    }

    public List<AccidentType> findByTypeAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("select distinct r from AccidentType r", AccidentType.class)
                    .list();
        }
    }

    public AccidentType findByAccidentTypeId(int id) {
        try (Session session = sf.openSession()) {
            return session.get(AccidentType.class, id);
        }
    }

    public Accident findByAccidentId(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Accident.class, id);
        }
    }

    public Rule findByIdRule(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Rule.class, id);
        }
    }

    public Set<Rule> findRulesByIds(String[] ids) {
        Set<Rule> rsl = new HashSet<>();
        for (String id : ids) {
            rsl.add(this.findByIdRule(Integer.parseInt(id)));
        }
        return rsl;
    }

}
