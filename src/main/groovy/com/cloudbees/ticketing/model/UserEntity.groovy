package com.cloudbees.ticketing.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    String firstName
    String lastName
    String email
    String seat
	List<Ticket> tickets = []
	
	static hasMany = [tickets: Ticket]
}
