CREATE TABLE DC_Appointment(
    Appointment_ID int NOT NULL PRIMARY KEY,
    Appointment_Date_Time TIMESTAMP NOT NULL,
    Patient_ID int NOT NULL,
    Dentist_ID int NOT NULL,
    Appointment_Note VARCHAR(250) NOT NULL,
    FOREIGN KEY (Patient_ID) REFERENCES DC_Patient(Patient_ID),
    FOREIGN KEY (Dentist_ID) REFERENCES DC_Dentist(Dentist_ID)
);