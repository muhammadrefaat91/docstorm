package com.docstorm.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.docstorm.repository.entities.Body;

@Repository
public interface BodyRepository extends JpaRepository<Body, Integer> {

}
