package com.cloudbees.ticketing.service

import com.cloudbees.ticketing.controller.TicketController
import com.cloudbees.ticketing.dto.ReceiptDTO
import com.cloudbees.ticketing.dto.TicketDTO
import com.cloudbees.ticketing.dto.UserDTO
import com.cloudbees.ticketing.repository.TicketRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSON
import org.mockito.Mock
import org.spockframework.spring.SpringSpy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import spock.lang.Specification
import spock.lang.Subject
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import javax.transaction.Transactional

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.mockito.Mockito.*


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TicketControllerSpec extends Specification {

    @SpringSpy
    TicketService ticketService

    @Autowired
    MockMvc mockMvc

    ObjectMapper objectMapper = new ObjectMapper()

    def userId

    def setup() {
        def userDTO= new UserDTO(firstName: "Test", lastName: "test", email: "test@example.com", seat:"SectionA1" )
        def userResponse=mockMvc.perform(post("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andReturn()
       userId=userResponse.response.contentAsString
    }

    void "purchaseTicket should return receipt"() {
        given:
        def ticketDTO = new TicketDTO(from: "London", to: "Paris", email: "test@example.com", price: 100)
        String expectedResponse = "{\"from\":\"London\",\"to\":\"Paris\",\"user\":\"com.cloudbees.ticketing.model.UserEntity(${userId}, Test, test, test@example.com, Section A1)\",\"pricePaid\":100.0,\"seat\":\"Section A1\"}";
        when:
        def ticketPurchasedResponse = mockMvc.perform(post("/tickets/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content( objectMapper.writeValueAsString(ticketDTO)))
                .andExpect(status().isOk())
                .andReturn()
        then:
        ticketPurchasedResponse.response.contentAsString ==expectedResponse

    }

    def "getReceiptAll should return all receipts"() {
       given:
        def ticketDTO = new TicketDTO(from: "London", to: "Paris", email: "test@example.com", price: 100)
        def ticketDTO1 = new TicketDTO(from: "Chennai", to: "Delhi", email: "test@example.com", price: 500)
        def expectedResponse="[{\"from\":\"London\",\"to\":\"Paris\",\"user\":\"com.cloudbees.ticketing.model.UserEntity(${userId}, Test, test, test@example.com, Section A1)\",\"pricePaid\":100.0,\"seat\":\"Section A1\"},{\"from\":\"Chennai\",\"to\":\"Delhi\",\"user\":\"com.cloudbees.ticketing.model.UserEntity(${userId}, Test, test, test@example.com, Section A1)\",\"pricePaid\":500.0,\"seat\":\"Section A1\"}]"
      when:
       mockMvc.perform(post("/tickets/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticketDTO)))
                .andExpect(status().isOk())
                .andReturn()
        mockMvc.perform(post("/tickets/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticketDTO1)))
                .andExpect(status().isOk())
                .andReturn()

        def result = mockMvc.perform(get("/tickets")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()

        then:
        result.response.contentAsString == expectedResponse
    }

    void "getReceiptById should return receipt by id"() {
        given:
        def receiptId = 4L
        def ticketDTO = new TicketDTO(from: "London", to: "Paris", email: "test@example.com", price: 100)
        def expectedResponse= "{\"from\":\"London\",\"to\":\"Paris\",\"user\":\"com.cloudbees.ticketing.model.UserEntity(${userId}, Test, test, test@example.com, Section A1)\",\"pricePaid\":100.0,\"seat\":\"Section A1\"}"
        when:
        mockMvc.perform(post("/tickets/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticketDTO)))
                .andExpect(status().isOk())
                .andReturn()
        def result = mockMvc.perform(get("/tickets/${receiptId}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
        then:
        result.response.contentAsString == expectedResponse
    }

    void "getReceiptByUser should return receipts by user id"() {
        given:
        def ticketDTO = new TicketDTO(from: "Chennai", to: "Delhi", email: "test@example.com", price: 100)
        def expectedResponse= "[{\"from\":\"Chennai\",\"to\":\"Delhi\",\"user\":\"com.cloudbees.ticketing.model.UserEntity(${userId}, Test, test, test@example.com, Section A1)\",\"pricePaid\":100.0,\"seat\":\"Section A1\"}]"
        when:
        mockMvc.perform(post("/tickets/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticketDTO)))
                .andExpect(status().isOk())
                .andReturn()
        def result = mockMvc.perform(get("/tickets/users/${userId}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
        then:
        result.response.contentAsString == expectedResponse
    }

    void "removeReceiptByUser should remove receipts by user and train id"() {
        given:
        def userId = 1L
        def trainId = 1L
        def ticketDTO = new TicketDTO(from: "Chennai", to: "Delhi", email: "test@example.com", price: 100)
        when:
        mockMvc.perform(post("/tickets/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticketDTO)))
                .andExpect(status().isOk())
                .andReturn()
        def result = mockMvc.perform(delete("/tickets/trains/${trainId}/users/${userId}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
        then:
        result.response.contentAsString == "User $userId removed from the train."
    }
}
