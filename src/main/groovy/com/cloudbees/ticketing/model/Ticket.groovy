package com.cloudbees.ticketing.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "ticket")
class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id

    @ManyToOne
    @JoinColumn(name = "train_id")
    Train train

    double price
    String seat
	String fromStation
	String toStation
    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user
}
