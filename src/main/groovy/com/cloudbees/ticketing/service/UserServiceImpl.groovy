package com.cloudbees.ticketing.service;

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import com.cloudbees.ticketing.dto.ReceiptDTO
import com.cloudbees.ticketing.dto.TicketDTO
import com.cloudbees.ticketing.exception.TicketServiceException
import com.cloudbees.ticketing.model.UserEntity
import com.cloudbees.ticketing.repository.UserRepository

@Service
class UserServiceImpl implements UserService {
   

    UserRepository userRepository
	Train train = new Train(id: 1, name: "London to France", totalSeats: 100, seatsOccupied: 0)
	

    @Transactional(readOnly = true)
    @Override
    String getUserSeat(String userId) {
        def userDetails = userRepository.findById(userId).orElseThrow { new TicketServiceException("User not found with ID: $userId") }
        if (!userDetails) {
            throw new TicketServiceException("User $user not found.")
        }

        return userDetails.seat
    }

    @Transactional
    @Override
    String removeUser(Long userId) {
        def user = userRepository.findById(userId).orElseThrow { new TicketServiceException("User not found with ID: $userId") }

        userRepository.delete(userDetails)
		train.seatsOccupied -= user.tickets.size()

        return "User $user removed from the train."
    }

    @Transactional
    @Override
    String modifyUserSeat(Long userId, String newSeat) {
        def user = userRepository.findById(userId).orElseThrow { new TicketServiceException("User not found with ID: $userId") }

        user.seat = newSeat
		user.tickets.each { ticket ->
			ticket.seat = newSeat
		}
		userRepository.save(user)

        return "User $user's seat modified to $newSeat."
    }
}
