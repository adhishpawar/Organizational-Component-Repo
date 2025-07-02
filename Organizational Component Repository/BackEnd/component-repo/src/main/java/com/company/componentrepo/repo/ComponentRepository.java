package com.company.componentrepo.repo;

import com.company.componentrepo.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentRepository extends JpaRepository<Component, Long> {

    List<Component> findByIsDisabledFalse();

    @Query("SELECT c FROM Component c WHERE c.isDisabled = false AND (" +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.useDescription) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            ":keyword IN elements(c.technologiesUsed) OR " +
            ":keyword IN elements(c.tags))")
    List<Component> search(@Param("keyword") String keyword);

}
