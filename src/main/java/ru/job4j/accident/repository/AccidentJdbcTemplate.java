package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.*;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public void save(Accident accident) {

        if (accident.getId() == 0) {
           saveAccident(accident);
        } else {
            updateAccident(accident);
        }
    }

    public void updateAccident(Accident accident) {
        jdbc.update("update accident SET name = ?, text = ?, address = ?, type_id = ? WHERE id = ? ",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getAccidentType().getId(),
                accident.getId());
        jdbc.update("delete from accident_rules where accident_id = ?", accident.getId());
        saveRulesInAccident(accident, accident.getId());
    }



    public Optional<AccidentType> findByAccidentTypeId(int id) {
        return jdbc.queryForObject(
                "select * from accident_type where id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(AccidentType.of(
                                rs.getInt("id"),
                                rs.getString("name")
                                )
                        ));
    }


    public List<AccidentType> findByTypeAll() {
        return jdbc.query("select id, name from accident_type",
                (rs, row) -> {
                return AccidentType.of(
                         rs.getInt("id"),
                          rs.getString("name")
                 );
                });
    }



    private void saveAccident(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "INSERT INTO accident (name, text, address, type_id) VALUES (?, ?, ?, ?)",
                            new String[]{"id"}
                    );
                    ps.setString(1, accident.getName());
                    ps.setString(2, accident.getText());
                    ps.setString(3, accident.getAddress());
                    ps.setInt(4, accident.getAccidentType().getId());
                    return ps;
                },
                keyHolder
        );
        saveRulesInAccident(accident, keyHolder.getKey().intValue());
    }

    public Accident findByAccidentId(int id) {
        List<Accident> accidentList = getAll();
        Accident accident = null;
        for (Accident acc : accidentList) {
           if (acc.getId() == id) {
               accident = acc;
           }
        }
        return accident;
    }

    private void saveRulesInAccident(Accident accident, int id) {
        for (Rule rule : accident.getRules()) {
            jdbc.update("INSERT INTO accident_rules(accident_id, rule_id) VALUES (?, ?);",
                    id,
                    rule.getId());
        }
    }


    public List<Accident> getAll() {
        return jdbc.query("select id, name, text, address,type_id from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    AccidentType accidentType =  findByAccidentTypeId(rs.getInt("type_id")).get();
                    accident.setAccidentType(accidentType);
                    accident.setRules(getSetRule(rs.getInt("id")));
                    return accident;
                });
    }

    public List<Rule> findByRuleAll() {
        return jdbc.query("select id, name from rules",
                (rs, row) -> {
                     return Rule.of(
                             rs.getInt("id"),
                             rs.getString("name")
                     );
                });
    }

    public Rule findByIdRule(int id) {
        return jdbc.queryForObject(
                "select id, name from rules where id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Rule.of(
                                        rs.getInt("id"),
                                        rs.getString("name")
                                )
        );
    }

    public Set<Rule> getSetRule(int id) {
        List<Integer> list = jdbc.query("select  rule_id from accident_rules where accident_id = ?",
                (rs, row) -> rs.getInt("rule_id"), id);
        Set<Rule> ruleSet = new HashSet<>();
        for (Integer idList: list) {
            ruleSet.add(findByIdRule(idList));
        }
        return ruleSet;
    }

    public Set<Rule> findRulesByIds(String[] ids) {
        Set<Rule> rsl = new HashSet<>();
        for (String id : ids) {
            rsl.add(this.findByIdRule(Integer.parseInt(id)));
        }
        return rsl;
    }
}
