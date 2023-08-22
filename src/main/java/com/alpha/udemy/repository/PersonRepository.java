package com.alpha.udemy.repository;

import com.alpha.udemy.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
