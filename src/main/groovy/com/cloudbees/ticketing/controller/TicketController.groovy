package com.cloudbees.ticketing.controller

import groovy.transform.CompileStatic
import io.swagger.annotations.*
import org.springframework.web.bind.annotation.*

import com.cloudbees.ticketing.dto.ReceiptDTO
import com.cloudbees.ticketing.dto.TicketDTO
import com.cloudbees.ticketing.service.TicketService

@CompileStatic
@RestController
@RequestMapping("/ticket")
class TicketController {
    TicketService ticketService

    @PostMapping("/purchase")
    ReceiptDTO purchaseTicket(@RequestBody TicketDTO ticketDTO) {
        ticketService.purchaseTicket(ticketDTO)
    }

    @GetMapping("")
    List<ReceiptDTO> getReceiptAll() {
        ticketService.getAllReceipts()
    }

    @GetMapping("/user")
    ReceiptDTO getReceiptByUser(
            @RequestParam Long userId
    ) {
        ticketService.getReceiptByUserId(userId)
    }

}
