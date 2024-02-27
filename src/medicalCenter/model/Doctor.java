package medicalCenter.model;

import java.util.Objects;

public class Doctor extends Person{


    private String profession;

    public Doctor(String id, String name, String surname, String email, String phoneNumber, String profession) {
        super(id, name, surname, email, phoneNumber);
        this.profession = profession;
    }

    public Doctor() {
    }
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(profession, doctor.profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profession);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "profession='" + profession + '\'' +
                "} " + super.toString();
    }

}
