package medicalCenter.model;

import javax.print.Doc;
import java.util.Date;
import java.util.Objects;

public class Patient extends Person{


    private Doctor doctor;
    private Date registerDateTime;

    public Patient(String id, String name, String surname, String email, String phoneNumber,  Doctor doctor, Date registerDateTime) {
        super(id, name, surname, email, phoneNumber);
        this.doctor = doctor;
        this.registerDateTime = registerDateTime;
    }

    public Patient() {
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getRegisterDate() {
        return registerDateTime;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDateTime = registerDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(doctor, patient.doctor) && Objects.equals(registerDateTime, patient.registerDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctor, registerDateTime);
    }
}
