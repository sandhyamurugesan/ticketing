package com.cloudbees.ticketing.repository

import com.cloudbees.ticketing.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository

interface TicketRepository extends JpaRepository<Ticket, Long> {

}