package com.cloudbees.ticketing.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id
    String name
    int totalSeats
    int seatsOccupied
	
}
