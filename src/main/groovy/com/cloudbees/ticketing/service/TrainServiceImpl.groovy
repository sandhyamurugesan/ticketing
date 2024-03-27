package com.cloudbees.ticketing.service

import com.cloudbees.ticketing.dto.TrainDTO
import com.cloudbees.ticketing.model.Train
import com.cloudbees.ticketing.repository.TrainRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TrainServiceImpl implements TrainService {
   
    @Autowired
    TrainRepository trainRepository

    @Transactional(readOnly = true)
    @Override
    String addTrain(TrainDTO trainDTO) {
        Train train = new Train(trainNo:trainDTO.trainNo,name: trainDTO.name, totalSeats: trainDTO.totalSeats, seatsOccupied: 0)

        trainRepository.save(train)

        return train.id
    }

}
