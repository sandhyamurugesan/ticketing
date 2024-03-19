CREATE TABLE Train (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       total_seats INT NOT NULL,
                       seats_occupied INT NOT NULL
);

CREATE TABLE User (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      first_name VARCHAR(255) NOT NULL,
                      last_name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      seat VARCHAR(255)
);

CREATE TABLE Ticket (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT NOT NULL,
                        train_id BIGINT NOT NULL,
                        price DECIMAL(10, 2) NOT NULL,
                        seat VARCHAR(255) NOT NULL,
                        from_station VARCHAR(255) NOT NULL,
                        to_station VARCHAR(255) NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES User(id),
                        FOREIGN KEY (train_id) REFERENCES Train(id)
);

INSERT INTO train (name, total_seats, seats_occupied) VALUES ('London to Paris', 100, 0);
INSERT INTO train (name, total_seats, seats_occupied) VALUES ('Paris to Amsterdam', 150, 0);


INSERT INTO user (first_name, last_name, email, seat) VALUES ('John', 'Doe', 'john.doe@example.com', 'SectionA1');
INSERT INTO user (first_name, last_name, email, seat) VALUES ('Jane', 'Smith', 'jane.smith@example.com', 'SectionB1');


INSERT INTO ticket (user_id, train_id, price, seat, from_station, to_station) VALUES (1, 1, 50.0, 'SectionA1', 'London', 'Paris');
INSERT INTO ticket (user_id, train_id, price, seat, from_station, to_station) VALUES (2, 2, 75.0, 'SectionB1', 'Paris', 'Amsterdam');