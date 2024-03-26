-- noinspection SqlNoDataSourceInspectionForFile

DROP TABLE IF EXISTS Cdr;
DROP TABLE IF EXISTS Abonents;

CREATE TABLE Abonents (
    msisdn VARCHAR(11) PRIMARY KEY
);

CREATE TABLE Cdr (
    id INT PRIMARY KEY AUTO_INCREMENT,
    call_type INT,
    msisdn VARCHAR(11),
    start_time LONG,
    end_time LONG,
    FOREIGN KEY (msisdn) REFERENCES Abonents(msisdn)
);
commit;