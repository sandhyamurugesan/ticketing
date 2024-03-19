package com.cloudbees.ticketing.controller

import groovy.transform.CompileStatic
import io.swagger.annotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import com.cloudbees.ticketing.dto.ReceiptDTO
import com.cloudbees.ticketing.dto.TicketDTO
import com.cloudbees.ticketing.service.TicketService

@CompileStatic
@RestController
@RequestMapping("/tickets")
class TicketController {
    @Autowired
    TicketService ticketService

    @PostMapping("/purchase")
    ReceiptDTO purchaseTicket(@RequestBody TicketDTO ticketDTO) {
        ticketService.purchaseTicket(ticketDTO)
    }

    @GetMapping("")
    List<ReceiptDTO> getReceiptAll() {
        ticketService.getAllReceipts()
    }

    @GetMapping("/{ticketId}")
    ReceiptDTO getReceipById(@PathVariable Long ticketId) {
        ticketService.getReceiptById(ticketId)
    }

    @GetMapping("/users/{userId}")
    List<ReceiptDTO> getReceiptByUser(
            @PathVariable Long userId
    ) {
        ticketService.getReceiptByUserId(userId)
    }

    @DeleteMapping("train/{trainId}/users/{userId}")
    String getReceiptByUser(
            @PathVariable Long userId,
            @PathVariable Long trainId
    ) {
        ticketService.removeReceiptByUserId(userId,trainId)
    }

}
