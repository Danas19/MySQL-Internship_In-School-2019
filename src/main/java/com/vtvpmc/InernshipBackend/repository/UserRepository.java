package com.vtvpmc.InernshipBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vtvpmc.InernshipBackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
