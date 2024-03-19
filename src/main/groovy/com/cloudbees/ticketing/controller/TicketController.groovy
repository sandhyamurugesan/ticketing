package com.cloudbees.ticketing.controller;

import groovy.transform.CompileStatic
import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*

import com.cloudbees.ticketing.dto.ReceiptDTO
import com.cloudbees.ticketing.dto.TicketDTO
import com.cloudbees.ticketing.service.TicketService

@CompileStatic
@RestController
@RequestMapping("/api/ticket")
class TicketController {
    TicketService ticketService
	
	UserService userService

    @PostMapping("/purchase")
    ReceiptDTO purchaseTicket(@RequestBody TicketDTO ticketDTO) {
        ticketService.purchaseTicket(ticketDTO)
    }

    @GetMapping("/receipt")
    ReceiptDTO getReceipt(
        @RequestParam String user
    ) {
        ticketService.getReceipt(user)
    }

    @GetMapping("/seat")
    String getUserSeat(
        @RequestParam Long userId
    ) {
        userService.getUserSeat(userId)
    }

    @DeleteMapping("/remove")
    String removeUser(
        @RequestParam Long userId
    ) {
        userService.removeUser(userId)
    }

    @PutMapping("/modify-seat")
    String modifyUserSeat(
        @RequestParam Long userId,
        @RequestParam String newSeat
    ) {
        userService.modifyUserSeat(user, newSeat)
    }
}
