package com.cloudbees.ticketing.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id

    Long userId
    Long trainId
    double price
    String seat
	String fromStation
	String toStation
	static belongsTo = [user: UserEntity]
}
