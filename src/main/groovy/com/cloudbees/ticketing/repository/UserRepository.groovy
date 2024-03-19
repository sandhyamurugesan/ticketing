package com.cloudbees.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository

import com.cloudbees.ticketing.model.UserEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email)

    @Query("SELECT u FROM UserEntity u WHERE u.seat LIKE %:section%")
    List<UserEntity> findBySection(@Param("section") String section);

}