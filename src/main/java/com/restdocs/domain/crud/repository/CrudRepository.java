package com.restdocs.domain.crud.repository;

import com.restdocs.domain.crud.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * {@link CrudRepository} 기본 crud
 * @author hansaem.song @EDP LAB
 * @version 1.00
 * @comment *
 * @since 2024.03.26
 */

@Repository
public interface CrudRepository extends JpaRepository<Example, Integer> {
}
