Train Ticketing System
This project is a train ticketing system that allows users to purchase tickets for a train journey from London to France.

+----------------+        +----------------+        +----------------+
|     Train      |        |     Ticket     |        |      User      |
+----------------+        +----------------+        +----------------+
| - id: Long     |        | - id: Long     |        | - id: Long     |
| - name: String |        | - fromStation: |        | - firstName:   |
| - totalSeats:  |        |   String       |        | - lastName:    |
| - seatsOccupied|        | - toStation:   |        | - email:       |
+----------------+        |   String       |        | - seat: String |
                           | - price:       |        +----------------+
                           |   double       |
                           | - seat: String |
                           +----------------+
