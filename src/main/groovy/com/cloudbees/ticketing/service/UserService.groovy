package com.cloudbees.ticketing.service;

import com.cloudbees.ticketing.dto.ReceiptDTO
import com.cloudbees.ticketing.dto.TicketDTO

interface UserService {
    String getUserSeat(Long userId)
    String removeUser(Long userId)
	String modifyUserSeat(Long userId, String newSeat)
}
