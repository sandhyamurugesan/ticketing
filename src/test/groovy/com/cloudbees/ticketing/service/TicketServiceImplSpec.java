package com.cloudbees.ticketing.service;
import spock.lang.Specification
import spock.lang.Subject
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import com.cloudbees.ticketing.dto.TicketDTO
import com.cloudbees.ticketing.dto.ReceiptDTO
import com.cloudbees.ticketing.model.UserEntity
import com.cloudbees.ticketing.model.TicketEntity
import com.cloudbees.ticketing.repository.UserRepository
import com.cloudbees.ticketing.repository.TicketRepository

@SpringBootTest
class TicketServiceImplSpec extends Specification {

    @Autowired
    TicketService ticketService

    @Autowired
    UserRepository userRepository

    @Autowired
    TicketRepository ticketRepository

    @Subject
    TicketServiceImpl subject = new TicketServiceImpl()

    def setup() {
        // Setup initial data if needed
    }

    def "purchaseTicket method should create a new user and ticket"() {
        given:
        def ticketDTO = new TicketDTO(
            from: "London",
            to: "France",
            firstName: "John",
            lastName: "Doe",
            email: "john.doe@example.com",
            price: 5.0
        )

        when:
        def receipt = subject.purchaseTicket(ticketDTO)

        then:
        receipt != null
        receipt.from == "London"
        receipt.to == "France"
        receipt.user == "John Doe"
        receipt.pricePaid == 5.0
        receipt.seat != null
    }

    def "getReceiptByUserId method should return the correct receipt for a user"() {
        given:
        def ticketDTO = new TicketDTO(
            from: "London",
            to: "France",
            firstName: "Jane",
            lastName: "Smith",
            email: "jane.smith@example.com",
            price: 5.0
        )
        def receipt = subject.purchaseTicket(ticketDTO)

        when:
        def userId = receipt.userId
        def receiptByUserId = subject.getReceiptByUserId(userId)

        then:
        receiptByUserId != null
        receiptByUserId.from == "London"
        receiptByUserId.to == "France"
        receiptByUserId.user == "Jane Smith"
        receiptByUserId.pricePaid == 5.0
    }

    def "getReceiptById method should return the correct receipt for a ticket"() {
        given:
        def ticketDTO = new TicketDTO(
            from: "London",
            to: "France",
            firstName: "Alice",
            lastName: "Brown",
            email: "alice.brown@
