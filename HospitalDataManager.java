import java.util.*;

public class HospitalDataManager {
    private ArrayList<patient> patients;
    private ArrayList<doctor> doctors;
    private ArrayList<appointment> appointments;
    private ArrayList<bill> bills;
    
    private HashMap<Integer, patient> patientMap;
    private HashMap<Integer, doctor> doctorMap;
    private HashMap<Integer, appointment> appointmentMap;
    
    private HashSet<String> uniqueEmails;
    private HashSet<Integer> uniqueIDs;

    public HospitalDataManager() {
        patients = new ArrayList<>();
        doctors = new ArrayList<>();
        appointments = new ArrayList<>();
        bills = new ArrayList<>();
        
        patientMap = new HashMap<>();
        doctorMap = new HashMap<>();
        appointmentMap = new HashMap<>();
        
        uniqueEmails = new HashSet<>();
        uniqueIDs = new HashSet<>();
    }


    public boolean addPatient(patient p) {
        if (uniqueIDs.contains(p.getPersonID())) {
            System.out.println("Error: Patient with ID " + p.getPersonID() + " already exists.");
            return false;
        }
        patients.add(p);
        patientMap.put(p.getPersonID(), p);
        uniqueIDs.add(p.getPersonID());
        uniqueEmails.add(p.getEmail());
        return true;
    }

    public patient getPatientByID(int personID) {
        return patientMap.get(personID);
    }

    public ArrayList<patient> getAllPatients() {
        return new ArrayList<>(patients);
    }

    public boolean removePatient(int personID) {
        patient p = patientMap.remove(personID);
        if (p != null) {
            patients.remove(p);
            uniqueIDs.remove(personID);
            return true;
        }
        return false;
    }

    public int getPatientCount() {
        return patients.size();
    }


    public boolean addDoctor(doctor d) {
        if (uniqueIDs.contains(d.getPersonID())) {
            System.out.println("Error: Doctor with ID " + d.getPersonID() + " already exists.");
            return false;
        }
        doctors.add(d);
        doctorMap.put(d.getPersonID(), d);
        uniqueIDs.add(d.getPersonID());
        uniqueEmails.add(d.getEmail());
        return true;
    }

    public doctor getDoctorByID(int personID) {
        return doctorMap.get(personID);
    }

    public ArrayList<doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }

    public boolean removeDoctor(int personID) {
        doctor d = doctorMap.remove(personID);
        if (d != null) {
            doctors.remove(d);
            uniqueIDs.remove(personID);
            return true;
        }
        return false;
    }

    public int getDoctorCount() {
        return doctors.size();
    }

    public boolean addAppointment(appointment a) {
        appointments.add(a);
        appointmentMap.put(a.getAppointmentID(), a);
        return true;
    }

    public appointment getAppointmentByID(int appointmentID) {
        return appointmentMap.get(appointmentID);
    }

    public ArrayList<appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    public boolean removeAppointment(int appointmentID) {
        appointment a = appointmentMap.remove(appointmentID);
        if (a != null) {
            appointments.remove(a);
            return true;
        }
        return false;
    }

    public int getAppointmentCount() {
        return appointments.size();
    }

    public boolean addBill(bill b) {
        bills.add(b);
        return true;
    }

    public ArrayList<bill> getAllBills() {
        return new ArrayList<>(bills);
    }

    public int getBillCount() {
        return bills.size();
    }

    public void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients in the system.");
            return;
        }
        System.out.println("\n========== ALL PATIENTS ==========");
        for (patient p : patients) {
            p.displayInfo();
            System.out.println();
        }
    }

    public void displayAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors in the system.");
            return;
        }
        System.out.println("\n========== ALL DOCTORS ==========");
        for (doctor d : doctors) {
            d.displayInfo();
            System.out.println();
        }
    }

    public void displayAllAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments in the system.");
            return;
        }
        System.out.println("\n========== ALL APPOINTMENTS ==========");
        for (appointment a : appointments) {
            a.displayInfo();
            System.out.println();
        }
    }

    public void displayAllBills() {
        if (bills.isEmpty()) {
            System.out.println("No bills in the system.");
            return;
        }
        System.out.println("\n========== ALL BILLS ==========");
        for (bill b : bills) {
            b.generateBill();
            System.out.println();
        }
    }

    public void displaySummary() {
        System.out.println("\n========== SYSTEM SUMMARY ==========");
        System.out.println("Total Patients: " + getPatientCount());
        System.out.println("Total Doctors: " + getDoctorCount());
        System.out.println("Total Appointments: " + getAppointmentCount());
        System.out.println("Total Bills: " + getBillCount());
        System.out.println("====================================");
    }

    public void clearAll() {
        patients.clear();
        doctors.clear();
        appointments.clear();
        bills.clear();
        patientMap.clear();
        doctorMap.clear();
        appointmentMap.clear();
        uniqueEmails.clear();
        uniqueIDs.clear();
    }
}
