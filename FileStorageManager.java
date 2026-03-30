import java.io.*;
import java.util.*;

public class FileStorageManager {
    private static final String DATA_DIR = "hospital_data";
    private static final String PATIENTS_FILE = "patients.txt";
    private static final String DOCTORS_FILE = "doctors.txt";
    private static final String APPOINTMENTS_FILE = "appointments.txt";
    private static final String BILLS_FILE = "bills.txt";
    private static final String DELIMITER = "|";

    public FileStorageManager() {
        createDataDirectory();
    }

    private void createDataDirectory() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                System.out.println("Created data directory: " + DATA_DIR);
            }
        }
    }

    

    public boolean savePatients(ArrayList<patient> patients) {
        try (FileWriter writer = new FileWriter(DATA_DIR + File.separator + PATIENTS_FILE)) {
            for (patient p : patients) {
                String line = p.getPersonID() + DELIMITER +
                        p.getName() + DELIMITER +
                        p.getPhone() + DELIMITER +
                        p.getEmail() + DELIMITER +
                        p.getAddress() + DELIMITER +
                        p.getPatientID() + DELIMITER +
                        p.getMedicalHistory() + DELIMITER +
                        p.getInsuranceProvider();
                writer.write(line + "\n");
            }
            System.out.println("Successfully saved " + patients.size() + " patients to file.");
            return true;
        } catch (IOException e) {
            System.out.println("Error saving patients: " + e.getMessage());
            return false;
        }
    }

    public boolean saveDoctors(ArrayList<doctor> doctors) {
        try (FileWriter writer = new FileWriter(DATA_DIR + File.separator + DOCTORS_FILE)) {
            for (doctor d : doctors) {
                String line = d.getPersonID() + DELIMITER +
                        d.getName() + DELIMITER +
                        d.getPhone() + DELIMITER +
                        d.getEmail() + DELIMITER +
                        d.getAddress() + DELIMITER +
                        d.getDoctorID() + DELIMITER +
                        d.getSpecialization() + DELIMITER +
                        d.getLicenseNumber();
                writer.write(line + "\n");
            }
            System.out.println("Successfully saved " + doctors.size() + " doctors to file.");
            return true;
        } catch (IOException e) {
            System.out.println("Error saving doctors: " + e.getMessage());
            return false;
        }
    }

    public boolean saveAppointments(ArrayList<appointment> appointments) {
        try (FileWriter writer = new FileWriter(DATA_DIR + File.separator + APPOINTMENTS_FILE)) {
            for (appointment a : appointments) {
                String line = a.getAppointmentID() + DELIMITER +
                        a.getDate() + DELIMITER +
                        a.getTime() + DELIMITER +
                        a.getDescription();
                writer.write(line + "\n");
            }
            System.out.println("Successfully saved " + appointments.size() + " appointments to file.");
            return true;
        } catch (IOException e) {
            System.out.println("Error saving appointments: " + e.getMessage());
            return false;
        }
    }

    public boolean saveBills(ArrayList<bill> bills) {
        try (FileWriter writer = new FileWriter(DATA_DIR + File.separator + BILLS_FILE)) {
            for (bill b : bills) {
                String line = b.getPatient().getPersonID() + DELIMITER +
                        b.getAmount() + DELIMITER +
                        b.isPaid();
                writer.write(line + "\n");
            }
            System.out.println("Successfully saved " + bills.size() + " bills to file.");
            return true;
        } catch (IOException e) {
            System.out.println("Error saving bills: " + e.getMessage());
            return false;
        }
    }

    public boolean saveAllData(HospitalDataManager manager) {
        return savePatients(manager.getAllPatients()) &&
               saveDoctors(manager.getAllDoctors()) &&
               saveAppointments(manager.getAllAppointments()) &&
               saveBills(manager.getAllBills());
    }

    

    public ArrayList<patient> loadPatients() {
        ArrayList<patient> patients = new ArrayList<>();
        File file = new File(DATA_DIR + File.separator + PATIENTS_FILE);

        if (!file.exists()) {
            System.out.println("Patients file not found. Starting with empty patient list.");
            return patients;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 8) {
                    int personID = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int phone = Integer.parseInt(parts[2]);
                    String email = parts[3];
                    String address = parts[4];
                    int patientID = Integer.parseInt(parts[5]);
                    String medicalHistory = parts[6];
                    String insuranceProvider = parts[7];

                    patient p = new patient(personID, name, phone, email, address, patientID, medicalHistory, insuranceProvider);
                    patients.add(p);
                }
            }
            System.out.println("Successfully loaded " + patients.size() + " patients from file.");
        } catch (IOException e) {
            System.out.println("Error loading patients: " + e.getMessage());
        }

        return patients;
    }

    public ArrayList<doctor> loadDoctors() {
        ArrayList<doctor> doctors = new ArrayList<>();
        File file = new File(DATA_DIR + File.separator + DOCTORS_FILE);

        if (!file.exists()) {
            System.out.println("Doctors file not found. Starting with empty doctor list.");
            return doctors;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 8) {
                    int personID = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int phone = Integer.parseInt(parts[2]);
                    String email = parts[3];
                    String address = parts[4];
                    int doctorID = Integer.parseInt(parts[5]);
                    String specialization = parts[6];
                    String licenseNumber = parts[7];

                    doctor d = new doctor(personID, name, phone, email, address, doctorID, specialization, licenseNumber);
                    doctors.add(d);
                }
            }
            System.out.println("Successfully loaded " + doctors.size() + " doctors from file.");
        } catch (IOException e) {
            System.out.println("Error loading doctors: " + e.getMessage());
        }

        return doctors;
    }

    public ArrayList<appointment> loadAppointments() {
        ArrayList<appointment> appointments = new ArrayList<>();
        File file = new File(DATA_DIR + File.separator + APPOINTMENTS_FILE);

        if (!file.exists()) {
            System.out.println("Appointments file not found. Starting with empty appointment list.");
            return appointments;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    int appointmentID = Integer.parseInt(parts[0]);
                    String date = parts[1];
                    String time = parts[2];
                    String description = parts[3];

                    appointment a = new appointment(appointmentID, date, time, description);
                    appointments.add(a);
                }
            }
            System.out.println("Successfully loaded " + appointments.size() + " appointments from file.");
        } catch (IOException e) {
            System.out.println("Error loading appointments: " + e.getMessage());
        }

        return appointments;
    }

    public boolean loadAllData(HospitalDataManager manager) {
        manager.clearAll();

        
        ArrayList<patient> patients = loadPatients();
        for (patient p : patients) {
            manager.addPatient(p);
        }

        
        ArrayList<doctor> doctors = loadDoctors();
        for (doctor d : doctors) {
            manager.addDoctor(d);
        }

        
        ArrayList<appointment> appointments = loadAppointments();
        for (appointment a : appointments) {
            manager.addAppointment(a);
        }

        return true;
    }

    

    public boolean filesExist() {
        File patientsFile = new File(DATA_DIR + File.separator + PATIENTS_FILE);
        File doctorsFile = new File(DATA_DIR + File.separator + DOCTORS_FILE);
        File appointmentsFile = new File(DATA_DIR + File.separator + APPOINTMENTS_FILE);

        return patientsFile.exists() || doctorsFile.exists() || appointmentsFile.exists();
    }

    public void deleteAllData() {
        File[] files = {
            new File(DATA_DIR + File.separator + PATIENTS_FILE),
            new File(DATA_DIR + File.separator + DOCTORS_FILE),
            new File(DATA_DIR + File.separator + APPOINTMENTS_FILE),
            new File(DATA_DIR + File.separator + BILLS_FILE)
        };

        for (File file : files) {
            if (file.exists() && file.delete()) {
                System.out.println("Deleted: " + file.getName());
            }
        }
    }
}
