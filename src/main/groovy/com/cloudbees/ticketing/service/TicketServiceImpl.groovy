package com.cloudbees.ticketing.service

import com.cloudbees.ticketing.model.Ticket
import com.cloudbees.ticketing.model.Train;
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import com.cloudbees.ticketing.dto.ReceiptDTO
import com.cloudbees.ticketing.dto.TicketDTO
import com.cloudbees.ticketing.exception.TicketServiceException
import com.cloudbees.ticketing.model.UserEntity
import com.cloudbees.ticketing.repository.UserRepository
import com.cloudbees.ticketing.repository.TicketRepository

@Service
class TicketServiceImpl implements TicketService {
   

    UserRepository userRepository
	TicketRepository ticketRepository
	Train train = new Train(id: 1, name: "London to France", totalSeats: 100, seatsOccupied: 0)
	

    @Transactional
    @Override
    ReceiptDTO purchaseTicket(TicketDTO ticketDTO) {
		
		def seatNumber = train.seatsOccupied + 1
		train.seatsOccupied++
		
        def user = userRepository.findByEmail(ticketDTO.email)
        
		user.seat="Section A${seatNumber}"
		def ticket = new Ticket(fromStation: ticketDTO.from, toStation: ticketDTO.to, price: ticketDTO.price, seat: user.seat)
		user.addToTickets(ticket)
		
        userRepository.save(user)
		//ticketRepository.save(ticket)
        

        def receipt = new ReceiptDTO(
            from: ticketDTO.from,
            to: ticketDTO.to,
            user: "${ticketDTO.firstName} ${ticketDTO.lastName}",
            pricePaid: ticketDTO.price,
			seat: user.seat
			
        )

        return receipt
    }

    @Transactional(readOnly = true)
    @Override
    ReceiptDTO getReceiptByUserId(Long userId) {
        def userDetails = userRepository.findById(userId).orElseThrow { new TicketServiceException("User not found with ID: $userId") }
		Ticket ticket = userDetails.tickets.find { it.user.id == userId }
       

        return new ReceiptDTO(
            from: userDetails.from,
            to: userDetails.to,
            user: userDetails,
            pricePaid: ticket.price
        )
    }
	
	@Transactional(readOnly = true)
	@Override
	ReceiptDTO getReceiptById(Long recId) {
		def ticket = ticketRepository.findById(recId).orElseThrow { new TicketServiceException("Ticket not found with ID: $recId") }

		return new ReceiptDTO(
			from: ticket.fromStation,
			to: ticket.toStation,
			user: ticket.user,
			pricePaid: ticket.price
		)
	}

	@Override
	List<ReceiptDTO> getAllReceipts() {
		def tickets = ticketRepository.findAll()
		if(tickets.isEmpty()){
			new TicketServiceException("No tickets added")
		}

		def receiptDTOs = tickets.collect { ticket ->
			new ReceiptDTO(
					from: ticket.fromStation,
					to: ticket.toStation,
					user: ticket.user,
					pricePaid: ticket.price
			)
		}

		return receiptDTOs
	}
}
