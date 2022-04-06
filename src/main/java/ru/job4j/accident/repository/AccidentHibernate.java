package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Repository
public class AccidentHibernate implements Store {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Accident save(Accident accident) {
        return this.tx(
                session -> {
                    session.saveOrUpdate(accident);
                    return accident;
                }
        );
    }
    @Override
    public List<Accident> getAll() {
        return this.tx(
                session -> session.createQuery("select distinct a from Accident a "
                            + "join fetch a.type t "
                            + "join fetch a.rules").list()
        );
    }
    @Override
    public List<Rule> findByRuleAll() {
        return this.tx(
              session -> session.createQuery("select distinct r from Rule r", Rule.class)
                    .list()
        );
    }
    @Override
    public List<AccidentType> findByTypeAll() {
        return this.tx(
                session -> session.createQuery("select distinct r from AccidentType r", AccidentType.class)
                    .list()
        );
    }
    @Override
    public AccidentType findByAccidentTypeId(int id) {
        return this.tx(
                session -> session.get(AccidentType.class, id)
        );
    }
    @Override
    public Accident findByAccidentId(int id) {
        return this.tx(
                session -> session.get(Accident.class, id)
        );
    }
    @Override
    public Rule findByIdRule(int id) {
        return this.tx(
                session -> session.get(Rule.class, id)
        );
    }
    @Override
    public Set<Rule> findRulesByIds(String[] ids) {
        Set<Rule> rsl = new HashSet<>();
        for (String id : ids) {
            rsl.add(this.findByIdRule(Integer.parseInt(id)));
        }
        return rsl;
    }

}
