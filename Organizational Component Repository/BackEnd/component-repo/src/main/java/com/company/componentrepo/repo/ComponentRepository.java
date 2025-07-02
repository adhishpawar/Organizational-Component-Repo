package com.company.componentrepo.repo;

import com.company.componentrepo.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {


    //Search by name
    @Query("Select c FROM Component c where LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) AND c.enabled = true")
    List<Component> searchByName(String name);

    //Search By Desc
    @Query("Select c from Component c where lower(c.description) like lower(concat('%', :desc, '%')) AND c.enabled = true")
    List<Component> searchByDescription(@Param("desc") String desc);

    //Search By Technology
    @Query("SELECT DISTINCT c FROM Component c JOIN c.technologies t WHERE LOWER(t) LIKE LOWER(CONCAT('%', :tech, '%')) AND c.enabled = true")
    List<Component> searchByTechnology(@Param("tech") String tech);


    //By Tag
    @Query("Select DISTINCT c FROM Component c JOIN c.tags t where LOWER(t.name) LIKE LOWER(CONCAT('%', :tag, '%')) AND c.enabled = true")
    List<Component> searchByTag(@Param("tag") String tag);



    @Query("SELECT DISTINCT c FROM Component c LEFT JOIN c.tags t LEFT JOIN c.technologies tech " +
            "WHERE c.enabled = true AND (" +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(c.description) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(t.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(tech) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<Component> globalSearch(@Param("query") String query);







}
