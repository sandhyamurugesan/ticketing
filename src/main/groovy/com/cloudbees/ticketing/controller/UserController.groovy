package com.cloudbees.ticketing.controller

import com.cloudbees.ticketing.dto.UserDTO
import com.cloudbees.ticketing.service.UserService;
import groovy.transform.CompileStatic
import io.swagger.annotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import com.cloudbees.ticketing.dto.ReceiptDTO
import com.cloudbees.ticketing.dto.TicketDTO
import com.cloudbees.ticketing.service.TicketService

@CompileStatic
@RestController
@RequestMapping("/users")
class UserController {
    @Autowired
    UserService userService

    @GetMapping("/seat/{userId}")
    String getUserSeat(@PathVariable Long userId) {
        userService.getUserSeat(userId)
    }

    @GetMapping("/seatsection/{sectionName}")
    List<UserDTO> getUserSeat(@PathVariable String sectionName) {
        userService.getUsersSeatBySection(sectionName)
    }

    @DeleteMapping("/{userId}")
    String removeUser(@PathVariable Long userId) {
        userService.removeUser(userId)
    }

    @PutMapping("/{userId}/modifySeat")
    String modifyUserSeat(
            @PathVariable Long userId,
            @RequestParam String newSeat
    ) {
        userService.modifyUserSeat(userId, newSeat)
    }



}

