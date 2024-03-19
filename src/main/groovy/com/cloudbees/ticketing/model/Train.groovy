package com.cloudbees.ticketing.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id
    String name
    int totalSeats
    int seatsOccupied
	
}
