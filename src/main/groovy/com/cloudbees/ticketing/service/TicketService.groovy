package com.cloudbees.ticketing.service;

import com.cloudbees.ticketing.dto.ReceiptDTO
import com.cloudbees.ticketing.dto.TicketDTO

interface TicketService {
    ReceiptDTO purchaseTicket(TicketDTO ticketDTO)
    List<ReceiptDTO> getReceiptByUserId(Long userId)
	ReceiptDTO getReceiptById(Long recId)
    List<ReceiptDTO>  getAllReceipts()
    String removeReceiptByUserId(Long userId, Long trainId)
}
