CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(50) NOT NULL,
                       email VARCHAR(50) NOT NULL
);

CREATE TABLE parking_lots (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              name VARCHAR(50) NOT NULL,
                              location VARCHAR(100) NOT NULL,
                              capacity INT NOT NULL
);

CREATE TABLE reservations (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              user_id BIGINT NOT NULL,
                              parking_lot_id BIGINT NOT NULL,
                              reserved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (user_id) REFERENCES users(id),
                              FOREIGN KEY (parking_lot_id) REFERENCES parking_lots(id)
);