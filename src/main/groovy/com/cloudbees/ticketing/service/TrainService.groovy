package com.cloudbees.ticketing.service

import com.cloudbees.ticketing.dto.TrainDTO


interface TrainService {
    String addTrain(TrainDTO trainDTO)
}
