package com.cloudbees.ticketing.repository

import com.cloudbees.ticketing.model.Train
import com.cloudbees.ticketing.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface TrainRepository extends JpaRepository<Train, Long> {
    Train findByTrainNo(int trainNo)
}