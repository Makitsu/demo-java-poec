CREATE TABLE IF NOT EXISTS wilders
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    category     VARCHAR(255) NULL,
    wilder_email VARCHAR(255) NULL,
    name         VARCHAR(255) NULL
    );
INSERT INTO wilders (id,category, wilder_email, name) VALUES (1,'TEST', 'test@gmail.com', 'TEST');