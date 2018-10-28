package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {

    private static final MealRowMapper MEAL_ROW_MAPPER = new MealRowMapper();

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert mealInsert;



    @Autowired
    public JdbcMealRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.mealInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("meals")
                                .usingGeneratedKeyColumns("id");
    }


    @Override
    public Meal save(Meal meal, int userId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", meal.getId())
                .addValue("userId", userId)
                .addValue("description", meal.getDescription())
                .addValue("datetime", meal.getDateTime())
                .addValue("calories", meal.getCalories());

        if (meal.isNew()) {
            Number key = mealInsert.executeAndReturnKey(parameters);
            meal.setId(key.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE meals SET description=:description, datetime=:datetime, " +
                        "calories=:calories WHERE id=:id AND user_id=:userId", parameters) == 0) {
            return null;
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM meals WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> meals =  jdbcTemplate.query("SELECT * FROM meals WHERE id=? AND user_id=?", MEAL_ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM meals WHERE user_id=?", MEAL_ROW_MAPPER, userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        List<Meal> betweenDates = jdbcTemplate.query("SELECT * FROM meals WHERE datetime BETWEEN ? AND ? AND user_id=?",
                MEAL_ROW_MAPPER, startDate, endDate, userId);
        return betweenDates.stream().filter(m -> Util.isBetween( m.getDateTime().toLocalTime(),
                startDate.toLocalTime(), endDate.toLocalTime()))
                .collect(Collectors.toList());
    }

    private static class MealRowMapper implements RowMapper<Meal> {

        @Override
        public Meal mapRow(ResultSet rs, int rowNum) throws SQLException {
            Meal meal = new Meal();
            meal.setId(rs.getInt("id"));
            meal.setDescription(rs.getString("description"));
            meal.setDateTime(LocalDateTime.parse(rs.getString("datetime"), DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss")));
            meal.setCalories(rs.getInt("calories"));
            return meal;
        }
    }
}
