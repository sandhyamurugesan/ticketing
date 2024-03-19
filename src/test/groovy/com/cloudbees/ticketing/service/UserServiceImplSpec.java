package com.cloudbees.ticketing.service;
import spock.lang.Specification
import spock.lang.Subject
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.beans.factory.annotation.Autowired
import com.cloudbees.ticketing.model.UserEntity
import com.cloudbees.ticketing.model.TicketEntity
import com.cloudbees.ticketing.repository.UserRepository
import com.cloudbees.ticketing.repository.TicketRepository

@SpringBootTest
class UserServiceImplSpec extends Specification {

    @Autowired
    UserService userService

    @Autowired
    UserRepository userRepository

    @Subject
    UserServiceImpl subject = new UserServiceImpl()

    def setup() {
        // Setup initial data if needed
    }

    def "getUserSeat method should return the correct seat for a user"() {
        given:
        def user = new UserEntity(firstName: "John", lastName: "Doe", email: "john.doe@example.com", seat: "Section A1")
        userRepository.save(user)

        when:
        def seat = subject.getUserSeat(user.id)

        then:
        seat == "Section A1"
    }

    def "removeUser method should remove the user and update the seats occupied count"() {
        given:
        def user = new UserEntity(firstName: "Jane", lastName: "Smith", email: "jane.smith@example.com", seat: "Section A2")
        userRepository.save(user)
        def initialSeatsOccupied = subject.train.seatsOccupied

        when:
        def result = subject.removeUser(user.id)

        then:
        result == "User ${user.firstName} ${user.lastName} removed from the train."
        userRepository.findById(user.id).isEmpty()
        subject.train.seatsOccupied == initialSeatsOccupied - 1
    }

    def "modifyUserSeat method should modify the user's seat and update the seat in tickets"() {
        given:
        def user = new UserEntity(firstName: "Alice", lastName: "Brown", email: "alice.brown@example.com", seat: "Section A3")
        userRepository.save(user)

        when:
        def result = subject.modifyUserSeat(user.id, "Section A10")

        then:
        result == "User ${user.firstName} ${user.lastName}'s seat modified to Section A10."
        user = userRepository.findById(user.id).orElse(null)
        user.seat == "Section A10"
        user.tickets.every { it.seat == "Section A10" }
    }

   
}
