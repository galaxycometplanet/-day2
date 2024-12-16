CREATE TABLE IF NOT EXISTS STUDENT_FOLDER (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(50) NOT NULL,
        namemini VARCHAR(50) NOT NULL,
        penname VARCHAR(50),
        maleaddress VARCHAR(50) NOT NULL,
        address VARCHAR(50),
        age INT,
        gender VARCHAR(10),
        remark TEXT,
        isDeleted BOOLEAN
);

CREATE TABLE IF NOT EXISTS studentCourse (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    datamineID INT NOT NULL,
    coursename VARCHAR(36) NOT NULL,
    start TIMESTAMP,
    `end` TIMESTAMP
);
