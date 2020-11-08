package MainClass;

import PatientClass.Patient;

import java.util.ArrayList;
import java.util.Random;

public class main {

    static Random rnd = new Random();
    static String names[] = {"Gale", "Walter", "Jessie", "Kale", "Bob", "Jason", "Greg"};
    static String forenames[] = {"Webber", "Wong", "White", "Black", "Yellowman", "Ivanenko", "Hobbs"};
    static String surnames[] = {"Abbot", "Dane", "Johnson", "Abraham", "Huntson", "Ron", "Zun"};
    static String diagnoses[] = {"covid", "influienza", "cold", "cancer", "schizophrenia"};
    static ArrayList<Patient> Patients = new ArrayList<Patient>();
    static ArrayList<Patient> PatientsList = new ArrayList<Patient>();

    static void fillPatients() {

        for (int i = 0; i < 15; i++) {
            Patient patient = new Patient(i, 1 + rnd.nextInt(10), names[rnd.nextInt(6)],
                    surnames[rnd.nextInt(6)], forenames[rnd.nextInt(6)], "V. Velukogo " + Integer.toString(rnd.nextInt(100)),
                    diagnoses[rnd.nextInt(4)], 100000000 + rnd.nextInt(1000000000));

            Patients.add(patient);
        }
    }

    static void printPatientsInfo(ArrayList<Patient> Patients) {

        for (Patient patient : Patients) {
            System.out.println(patient);
        }
    }

    static void cleaAfterPrint() {
        if (PatientsList.size() == 0) {
            System.out.println("Patients list is empty.");
        } else {
            printPatientsInfo(PatientsList);
            PatientsList.clear();
        }

    }

    static void showDiagnosed(int diagnoseIndex) {
        for (Patient patient : Patients) {
            if (patient.getDiagnose() == diagnoses[diagnoseIndex]) {
                PatientsList.add(patient);
            }
        }
        System.out.println("\nPatients diagnose with " + diagnoses[diagnoseIndex] + " :");
        cleaAfterPrint();
    }

    static void showByCardNumber(int lwb, int upb) {
        for (Patient patient : Patients) {
            if ((patient.getMedicalCardNumber() >= lwb) && (patient.getMedicalCardNumber() <= upb)) {
                PatientsList.add(patient);
            }
        }
        System.out.println("\nPatients with card numbers between " + lwb + " and " + upb + " :");
        cleaAfterPrint();
    }

    static void showByPhoneNumber(int numb) {
        long refrence;
        for (Patient patient : Patients) {
            refrence = patient.getPhoneNumber();
            while (refrence > 10) {
                refrence /= 10;
            }

            if (refrence == numb) {
                PatientsList.add(patient);
            }
        }
        System.out.println("\nPatients with phone number starting on " + numb + " :");
        cleaAfterPrint();
    }

    public static void main(String[] args) {

        fillPatients();
        showDiagnosed(0);
        showByCardNumber(4, 6);
        showByPhoneNumber(5);

    }
}
