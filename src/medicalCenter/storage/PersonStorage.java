package medicalCenter.storage;

import medicalCenter.dateUtil.DateUtil;
import medicalCenter.exception.PersonNotFoundException;
import medicalCenter.model.Doctor;
import medicalCenter.model.Patient;
import medicalCenter.model.Person;

import java.util.Calendar;
import java.util.Date;

public class PersonStorage {

    private Person[] persons = new Person[10];

    private int size = 0;

    public void add(Person person) {
        if (size == persons.length) {
            extend();
        }
        persons[size++] = person;
    }


    public void printDoctors() {
        for (int i = 0; i < size; i++) {
            if (persons[i] instanceof Doctor) {
                System.out.println(persons[i]);
            }
        }
    }

    public void searchDoctorByProfession(String profession) {
        for (int i = 0; i < size; i++) {
            if (persons[i] instanceof Doctor) {
                Doctor doctor = (Doctor) persons[i];
                if (doctor.getProfession().equals(profession)) {
                    System.out.println(doctor);
                }
            }
        }
    }

    public void deleteDoctorById(String id) {
        for (int i = 0; i < size; i++) {
            Person person = persons[i];
            if (person.getId().equals(id) && person instanceof Doctor) {
                deleteByIndex(i);
            }
        }
    }

    public Doctor getDoctorById(String id) throws PersonNotFoundException {
        for (int i = 0; i < size; i++) {
            if (persons[i].getId().equals(id) && persons[i] instanceof Doctor){
                return (Doctor) persons[i];
            }
        }
        throw new PersonNotFoundException("Person with " + id + " doesn't exist");
    }

    public void printAllPatientByDoctor(Doctor doctor){
        for (int i = 0; i < size; i++) {
            if (persons[i] instanceof Patient){
                Patient patient = (Patient) persons[i];
                if (patient.getDoctor().equals(doctor)){
                    System.out.println(patient);
                }
            }
        }
    }

    public void printTodayPatients(){
        Date today = new Date();
        for (int i = 0; i < size; i++) {
            if (persons[i] instanceof Patient){
                Patient patient = (Patient) persons[i];
               if (DateUtil.isSameDay(today,patient.getRegisterDate())){
                   System.out.println(patient);
               }
          }
        }
    }

    private void deleteByIndex(int i) {
        for (int j = i; j < size; j++) {

            persons[j] = persons[j + 1];
        }
        size--;
    }

    private void extend() {
        if (size == persons.length) {
            Person[] tmp = new Person[persons.length + 10];
            System.arraycopy(persons, 0, tmp, 0, persons.length);
            persons = tmp;
        }
    }

    public boolean isDoctorAvailable(Doctor doctor, Date registerDateTime) {
        for (int i = 0; i < size; i++) {
            if (persons[i] instanceof  Patient){
                Patient patient = (Patient) persons[i];
                if (patient.getDoctor().equals(doctor) && patient.getRegisterDate().equals(registerDateTime)){
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(patient.getRegisterDate());
                    calendar.add(Calendar.MINUTE, 29);
                    Date registerTime = calendar.getTime();
                    if (registerDateTime.before(registerTime)){
                        return false;
                    }
                }
            }
        }
       return true;
    }
}
