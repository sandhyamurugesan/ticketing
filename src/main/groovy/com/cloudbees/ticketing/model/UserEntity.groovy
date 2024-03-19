package com.cloudbees.ticketing.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String firstName
    String lastName
    String email
    String seat
	
	static hasMany = [tickets: Ticket]
}
