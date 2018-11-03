package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.Util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class, userId);
        meal.setUser(ref);
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        }

        if (em.createNamedQuery(Meal.UPDATE)
                .setParameter("id", meal.getId())
                .setParameter("userId", userId)
                .setParameter("description", meal.getDescription())
                .setParameter("dateTime", meal.getDateTime())
                .setParameter("calories", meal.getCalories())
                .executeUpdate() != 0) {
            return meal;
        }
        return null;

    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE).setParameter("id", id)
                .setParameter("user_id", userId).executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {

        return (Meal) em.createNamedQuery(Meal.GET).setParameter("id", id)
                .setParameter("user_id", userId)
                .getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.GET_ALL).setParameter("user_id", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        List<Meal> meals = em.createNamedQuery(Meal.GET_BETWEEN).setParameter("user_id", userId)
                .setParameter("startDate", startDate).setParameter("endDate", endDate)
                .getResultList();
        return meals.stream().filter(m -> Util.isBetween(m.getDateTime().toLocalTime(), startDate.toLocalTime(), endDate.toLocalTime()))
                .collect(Collectors.toList());

    }
}