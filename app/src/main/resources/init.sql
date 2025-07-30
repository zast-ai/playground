CREATE DATABASE IF NOT EXISTS testpath;
USE testpath;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Default admin user with password: admin
-- Password is MD5 hashed
INSERT INTO users (username, password, role) 
VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 'ADMIN')
ON DUPLICATE KEY UPDATE username=username; 