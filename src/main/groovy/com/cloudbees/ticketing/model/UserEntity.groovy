package com.cloudbees.ticketing.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "user")
class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String firstName
    String lastName
    String email
    String seat
    @OneToMany(mappedBy = "user")
    List<Ticket> tickets

    @Override
    String toString(){
        return firstName
    }
}
