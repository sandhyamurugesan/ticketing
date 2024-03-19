package com.cloudbees.ticketing.controller

import com.cloudbees.ticketing.service.UserService;
import groovy.transform.CompileStatic
import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*

import com.cloudbees.ticketing.dto.ReceiptDTO
import com.cloudbees.ticketing.dto.TicketDTO
import com.cloudbees.ticketing.service.TicketService

@CompileStatic
@RestController
@RequestMapping("/user")
class UserController {

    UserService userService

    @GetMapping("/seat")
    String getUserSeat(@RequestParam Long userId) {
        userService.getUserSeat(userId)
    }

    @DeleteMapping("/remove")
    String removeUser(@RequestParam Long userId) {
        userService.removeUser(userId)
    }

    @PutMapping("/modify-seat")
    String modifyUserSeat(
            @RequestParam Long userId,
            @RequestParam String newSeat
    ) {
        userService.modifyUserSeat(userId, newSeat)
    }

}

