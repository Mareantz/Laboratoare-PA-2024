CREATE TABLE IF NOT EXISTS users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(50) NOT NULL UNIQUE,
                       role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS parking_lots (
                              parking_lot_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              name VARCHAR(255) NOT NULL,
                              address VARCHAR(255) NOT NULL,
                              capacity INT NOT NULL
);

CREATE TABLE IF NOT EXISTS reservations (
                              reservation_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              user_id BIGINT NOT NULL,
                              parking_lot_id BIGINT NOT NULL,
                              parking_space_id BIGINT,
                              start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              end_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (user_id) REFERENCES users(user_id),
                              FOREIGN KEY (parking_lot_id) REFERENCES parking_lots(parking_lot_id),
                              FOREIGN KEY (parking_space_id) REFERENCES parking_spaces(parking_space_id)
);

CREATE TABLE IF NOT EXISTS parking_spaces (
                                parking_space_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                space_number VARCHAR(255) NOT NULL,
                                parking_lot_id BIGINT,
                                is_reserved BOOLEAN DEFAULT TRUE,
                                FOREIGN KEY (parking_lot_id) REFERENCES parking_lots(parking_lot_id)
);
