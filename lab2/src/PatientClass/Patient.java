package PatientClass;

public class Patient {
    private int id, medicalCardNumber;
    private String name, surname, forename, address, diagnose;
    private long phoneNumber;

    public Patient(int id, int medicalCardNumber, String name, String surename, String forename, String address, String diagnose, long phoneNumber) {
        this.id = id;
        this.medicalCardNumber = medicalCardNumber;
        this.name = name;
        this.surname = surename;
        this.forename = forename;
        this.address = address;
        this.diagnose = diagnose;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Patient #" +
                id +
                ", medical card number:" + medicalCardNumber +
                ", " + name +
                " " + surname +
                " " + forename +
                ", lives on " + address + " street" +
                ", diagnosed with " + diagnose +
                ", contact phone: " + phoneNumber +
                '.';
    }

    //region Getters & setters
    public int getMedicalCardNumber() {
        return medicalCardNumber;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMedicalCardNumber(int medicalCardNumber) {
        this.medicalCardNumber = medicalCardNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    //endregion

}
