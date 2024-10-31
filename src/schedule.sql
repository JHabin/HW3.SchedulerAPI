CREATE TABLE USERS {
    userId INT NOT NULL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(50),
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP
    };

CREATE TABLE SCHEDULES {
    scheduleId INT NOT NULL,
    userId INT NOT NULL,
    todo VARCHAR(30) NOT NULL,
    content VARCHAR(80),
    createdAt TIMESTAMP NOT NULL,
    updatedAt TIMESTAMP,
    password VARCHAR(50) NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE,
    FOREIGN KEY (userId) REFERENCES USERS (userId)
    };

INSERT INTO USERS (userId, name, email, createdAt) VALUES (1, "HABIN", "example@gmail.com", current_timestamp());

INSERT INTO SCHEDULES (userId, todo, content, createdAt, startDate, password) VALUES (1, "과제중간점검", "14:00까지", current_timestamp(), '2024-11-01' "abcd1234");
INSERT INTO SCHEDULES (userId, todo, content, createdAt, startDate, password) VALUES (1, "과제제출", "12:00까지", current_timestamp(), '2024-11-08', "abcd1234");

SELECT * FROM SCHEDULES;

SELECT * FROM SCHEDULES WHERE scheduleId = 1;

UPDATE SCHEDULES SET content = "14:00까지", updatedAt = current_timestamp() WHERE scheduleId = 2;

DELETE * FROM SCHEDULES WHERE scheduleId = 1;