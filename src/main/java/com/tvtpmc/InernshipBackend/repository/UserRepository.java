package com.tvtpmc.InernshipBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tvtpmc.InernshipBackend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
