package com.cloudbees.ticketing.service

import com.cloudbees.ticketing.model.Ticket
import com.cloudbees.ticketing.model.Train
import com.cloudbees.ticketing.repository.TrainRepository
import org.springframework.beans.factory.annotation.Autowired;
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
   
	@Autowired
    UserRepository userRepository
	@Autowired
	TicketRepository ticketRepository
	@Autowired
	TrainRepository trainRepository

    @Transactional
    @Override
    ReceiptDTO purchaseTicket(TicketDTO ticketDTO) {
		def train = trainRepository.findByTrainNo(ticketDTO.trainNo)

		def seatNumber = train.seatsOccupied + 1
		train.seatsOccupied++
		
        def user = userRepository.findByEmail(ticketDTO.email)
		user.seat="Section A${seatNumber}"
		def ticket = new Ticket(fromStation: ticketDTO.from, toStation: ticketDTO.to, price: ticketDTO.price, seat: user.seat)
		user.tickets=Arrays.asList(ticket)
		ticket.user=user
		ticket.train=train
		ticketRepository.save(ticket)
        

        def receipt = new ReceiptDTO(
            from: ticketDTO.from,
            to: ticketDTO.to,
            user: user,
            pricePaid: ticketDTO.price,
			seat: user.seat,
			trainName: train.name
			
        )

        return receipt
    }

    @Transactional(readOnly = true)
    @Override
    List<ReceiptDTO> getReceiptByUserId(Long userId) {
        def userDetails = userRepository.findById(userId).orElseThrow { new TicketServiceException("User not found with ID: $userId") }

		def receiptDTOs = userDetails.tickets.collect { ticket ->
			new ReceiptDTO(
					from: ticket.fromStation,
					to: ticket.toStation,
					user: userDetails,
					pricePaid: ticket.price,
					seat: ticket.seat,
					trainName: ticket.train.name
			)
		}

		return receiptDTOs
    }
	
	@Transactional(readOnly = true)
	@Override
	ReceiptDTO getReceiptById(Long recId) {
		def ticket = ticketRepository.findById(recId).orElseThrow { new TicketServiceException("Ticket not found with ID: $recId") }

		return new ReceiptDTO(
			from: ticket.fromStation,
			to: ticket.toStation,
			user: ticket.user,
			pricePaid: ticket.price,
			seat: ticket.seat,
			trainName: ticket.train.name
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
					pricePaid: ticket.price,
					seat: ticket.seat,
					trainName: ticket.train.name
			)
		}

		return receiptDTOs
	}

	@Transactional
	@Override
	String removeReceiptByUserId(Long userId, Long trainId) {
		def tickets = ticketRepository.findByUserIdAndTrainId(userId,trainId)
		List<Long> ticketIds = tickets.collect { it.id }
		//train.seatsOccupied -= user.tickets.size()
		ticketRepository.deleteAllById(ticketIds)

		return "User $userId removed from the train."
	}
}
