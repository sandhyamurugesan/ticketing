package com.cloudbees.ticketing.exception;

class TicketServiceException extends RuntimeException {
    TicketServiceException(String message) {
        super(message)
    }
}
