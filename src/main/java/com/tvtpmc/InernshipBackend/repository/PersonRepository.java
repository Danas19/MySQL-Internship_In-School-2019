package com.tvtpmc.InernshipBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvtpmc.InernshipBackend.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
