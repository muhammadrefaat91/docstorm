package com.docstorm.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.docstorm.repository.entities.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

}
