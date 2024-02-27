package medicalCenter;

import medicalCenter.dateUtil.DateUtil;
import medicalCenter.exception.PersonNotFoundException;
import medicalCenter.model.Doctor;
import medicalCenter.model.Patient;
import medicalCenter.storage.PersonStorage;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;


public class MedicalCenterMain implements Commands {
    private static PersonStorage personStorage = new PersonStorage();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
            String commands = scanner.nextLine();
            switch (commands) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_DOCTOR:
                    addDoctor();
                    break;
                case SEARCH_DOCTOR_BY_PROFESSION:
                    searchDoctorByProfession();
                    break;
                case DELETE_DOCTOR_BY_ID:
                    deleteDoctorById();
                    break;
                case CHANGE_DOCTOR_BY_ID:
                    changeDoctorById();
                    break;
                case ADD_PATIENT:
                    addPatient();
                    break;
                case PRINT_ALL_PATIENTS_BY_DOCTOR:
                    printAllPatientsByDoctor();
                    break;
                case PRINT_TODAY_PATIENTS:
                    personStorage.printTodayPatients();
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }

    }

    private static void printAllPatientsByDoctor() {
        personStorage.printDoctors();
        System.out.println("Please choose doctor");
        String doctorId = scanner.nextLine();

        Doctor doctorById = null;
        try {
            doctorById = personStorage.getDoctorById(doctorId);
        } catch (PersonNotFoundException e) {
            System.out.println(e.getMessage());
        }
        if (doctorById != null) {
            personStorage.printAllPatientByDoctor(doctorById);
        } else {
            System.out.println("Doctor doesn't exist");
        }
    }

    private static void addPatient() {
        personStorage.printDoctors();
        System.out.println("Please choose doctor id");
        String doctorDataStr = scanner.nextLine();

        String[] doctorData = doctorDataStr.split(",");
        if (doctorData.length != 1) {
            System.out.println("Invalid input for doctor id");
            return;
        }

        String doctorId = doctorData[0];

        Doctor doctorById = null;
        try {
            doctorById = personStorage.getDoctorById(doctorId);
        } catch (PersonNotFoundException e) {
            System.out.println(e.getMessage());
        }

        if (doctorById != null) {
            System.out.println("Please input id, name, surname, email, phone, registerTime(26-02-2024 14:00)");
            String patientDataStr = scanner.nextLine();

            String[] patientData = patientDataStr.split(",");
            if (patientData.length != 6) {
                System.out.println("Invalid input for patient data");
                return;
            }

            try {
                Date registerDateTime = DateUtil.stringToDateTime(patientData[5]);

                if (personStorage.isDoctorAvailable(doctorById, registerDateTime)) {
                    Patient patient = new Patient();
                    patient.setId(patientData[0]);
                    patient.setName(patientData[1]);
                    patient.setSurname(patientData[2]);
                    patient.setEmail(patientData[3]);
                    patient.setPhoneNumber(patientData[4]);
                    patient.setDoctor(doctorById);
                    patient.setRegisterDate(registerDateTime);

                    personStorage.add(patient);
                    System.out.println("Patient registered");
                } else {
                    System.out.println("Doctor is unavailable at that time, please choose another time");
                }
            } catch (ParseException e) {
                System.out.println("Error parsing date. Please use the format: dd-MM-yyyy HH:mm");
            }
        } else {
            System.out.println("Doctor with ID " + doctorId + " doesn't exist");
        }
    }


    private static void changeDoctorById() {
        personStorage.printDoctors();
        System.out.println("Please choose doctor id");
        String doctorId = scanner.nextLine();
        Doctor doctorById = null;
        try {
            doctorById = personStorage.getDoctorById(doctorId);
        } catch (PersonNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (doctorById != null) {
            System.out.println("Please input doctor name,surname, email,phone,profession");
            String doctorDataStr = scanner.nextLine();
            String[] doctorData = doctorDataStr.split(",");
            doctorById.setName(doctorData[0]);
            doctorById.setSurname(doctorData[1]);
            doctorById.setEmail(doctorData[2]);
            doctorById.setPhoneNumber(doctorData[3]);
            doctorById.setProfession(doctorData[4]);
            System.out.println("Doctor changed!");
        } else {
            System.out.println("Doctor with " + doctorId + " doesn't exist");
        }
    }

    private static void deleteDoctorById() {
        personStorage.printDoctors();
        System.out.println("Please input doctor id");
        String doctorId = scanner.nextLine();
        Doctor doctorByID = null;
        try {
            doctorByID = personStorage.getDoctorById(doctorId);
        } catch (PersonNotFoundException e) {
            System.out.println(e.getMessage());
        }
//        if (doctorByID != null) {
//            personStorage.deleteDoctorById(doctorId);
//        } else {
//            System.out.println("Doctor with " + doctorId + " doesn't exist");
//        }

    }

    private static void searchDoctorByProfession() {
        System.out.println("Please input profession");
        String profession = scanner.nextLine();
        personStorage.searchDoctorByProfession(profession);

    }

    private static void addDoctor() {
        System.out.println("Please input doctor id, name,surname, email,phone,profession");
        String doctorDataStr = scanner.nextLine();
        String[] doctorData = doctorDataStr.split(",");
        String doctorId = doctorData[0];
        Doctor doctorById = null;
        try {
            doctorById = personStorage.getDoctorById(doctorId);
        } catch (PersonNotFoundException e) {
            System.out.println(e.getMessage());
        }
        if (doctorById == null) {
            Doctor doctor = new Doctor();
            doctor.setId(doctorId);
            doctor.setName(doctorData[1]);
            doctor.setSurname(doctorData[2]);
            doctor.setEmail(doctorData[3]);
            doctor.setPhoneNumber(doctorData[4]);
            doctor.setProfession(doctorData[5]);
            personStorage.add(doctor);
            System.out.println("Doctor added");
        } else {
            System.out.println("Doctor with " + doctorId + " already exist");
        }

    }


}
