package com.cloudbees.ticketing.controller

import com.cloudbees.ticketing.dto.TrainDTO
import com.cloudbees.ticketing.dto.UserDTO
import com.cloudbees.ticketing.service.TrainService
import com.cloudbees.ticketing.service.UserService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CompileStatic
@RestController
@RequestMapping("/trains")
class TrainController {
    @Autowired
    TrainService trainService


    @PostMapping("/")
    String addTrains(@RequestBody TrainDTO trainDTO) {
        trainService.addTrain(trainDTO)
    }

}

