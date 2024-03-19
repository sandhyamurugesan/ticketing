package com.cloudbees.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository

import com.cloudbees.ticketing.model.UserEntity

interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByFirstNameAndLastName(String firstName, String lastName)
}