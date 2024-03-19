package com.cloudbees.ticketing.repository;

import org.springframework.data.jpa.repository.JpaRepository

import com.cloudbees.ticketing.model.UserEntity

interface TicketRepository extends JpaRepository<Ticket, Long> {

}