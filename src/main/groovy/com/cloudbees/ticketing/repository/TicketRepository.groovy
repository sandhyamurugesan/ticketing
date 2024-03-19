package com.cloudbees.ticketing.repository

import com.cloudbees.ticketing.model.Ticket
import com.cloudbees.ticketing.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUserIdAndTrainId(Long userId, Long trainId)

}