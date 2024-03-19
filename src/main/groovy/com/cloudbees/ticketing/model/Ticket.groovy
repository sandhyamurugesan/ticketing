package com.cloudbees.ticketing.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    Long userId
    Long trainId
    double price
    String seat
	String fromStation
	String toStation
	static belongsTo = [user: UserEntity]
}
