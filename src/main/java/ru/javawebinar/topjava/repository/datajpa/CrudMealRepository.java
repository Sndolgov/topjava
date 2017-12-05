package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Modifying
    @Transactional
    @Query (name = Meal.DELETE)
    int delete(@Param("id") int id, @Param("userId") int userId);


    @Override
    @Transactional
    Meal save(Meal meal);

    /*@Modifying
    @Query ("SELECT m FROM Meal m WHERE m.user.id=:userId AND m.id=:id")
    Meal get(@Param("id") int id, @Param("userId") int userId);*/

    @Override
    Optional<Meal> findById(Integer integer);

    @Modifying
    @Query(name = Meal.ALL_SORTED)
    List<Meal> getAll(@Param("userId") int userId);

    @Modifying
    @Query(name = Meal.GET_BETWEEN)
    List<Meal> getBetween(@Param("userId") int userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);



}
