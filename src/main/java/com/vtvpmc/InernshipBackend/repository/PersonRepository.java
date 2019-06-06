package com.vtvpmc.InernshipBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vtvpmc.InernshipBackend.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
