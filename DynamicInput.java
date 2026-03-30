import java.util.Scanner;
import java.util.ArrayList;

public class DynamicInput {
    private static HospitalDataManager dataManager = new HospitalDataManager();
    private static FileStorageManager fileManager = new FileStorageManager();
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        try {
            System.out.println("=== Hospital Management System (Enhanced with Collections & File Storage) ===\n");

            if (fileManager.filesExist()) {
                System.out.println("Existing data found. Loading from files...");
                fileManager.loadAllData(dataManager);
                System.out.println();
            }

            mainMenu();

        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void mainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Add Appointment");
            System.out.println("4. Add Bill");
            System.out.println("5. View All Patients");
            System.out.println("6. View All Doctors");
            System.out.println("7. View All Appointments");
            System.out.println("8. View All Bills");
            System.out.println("9. System Summary");
            System.out.println("10. Save All Data to Files");
            System.out.println("11. Load Data from Files");
            System.out.println("12. Exit");
            System.out.println("==============================");
            System.out.print("Enter your choice (1-12): ");

            int choice = readInt(scanner, "");
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addNewPatient();
                    break;
                case 2:
                    addNewDoctor();
                    break;
                case 3:
                    addNewAppointment();
                    break;
                case 4:
                    addNewBill();
                    break;
                case 5:
                    dataManager.displayAllPatients();
                    break;
                case 6:
                    dataManager.displayAllDoctors();
                    break;
                case 7:
                    dataManager.displayAllAppointments();
                    break;
                case 8:
                    dataManager.displayAllBills();
                    break;
                case 9:
                    dataManager.displaySummary();
                    break;
                case 10:
                    saveAllData();
                    break;
                case 11:
                    loadAllData();
                    break;
                case 12:
                    System.out.println("Exiting application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addNewPatient() {
        System.out.println("\n--- Add New Patient ---");
        patient p = readPatient(scanner);
        if (dataManager.addPatient(p)) {
            System.out.println("Patient added successfully!");
        } else {
            System.out.println("Failed to add patient.");
        }
    }

    private static void addNewDoctor() {
        System.out.println("\n--- Add New Doctor ---");
        doctor d = readDoctor(scanner);
        if (dataManager.addDoctor(d)) {
            System.out.println("Doctor added successfully!");
        } else {
            System.out.println("Failed to add doctor.");
        }
    }

    private static void addNewAppointment() {
        System.out.println("\n--- Add New Appointment ---");
        appointment a = readAppointment(scanner);
        if (dataManager.addAppointment(a)) {
            System.out.println("Appointment added successfully!");
        } else {
            System.out.println("Failed to add appointment.");
        }
    }

    private static void addNewBill() {
        System.out.println("\n--- Add New Bill ---");

        if (dataManager.getPatientCount() == 0) {
            System.out.println("No patients in system. Please add a patient first.");
            return;
        }

        ArrayList<patient> patients = dataManager.getAllPatients();
        System.out.println("Available Patients:");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println((i + 1) + ". " + patients.get(i).getName() + " (ID: " + patients.get(i).getPersonID() + ")");
        }

        System.out.print("Select patient number: ");
        int patientIndex = readInt(scanner, "");
        scanner.nextLine();

        if (patientIndex > 0 && patientIndex <= patients.size()) {
            patient selectedPatient = patients.get(patientIndex - 1);
            System.out.print("Enter bill amount: $");
            double amount = readDouble(scanner, "");
            scanner.nextLine();

            bill b = new bill(selectedPatient, amount);
            if (dataManager.addBill(b)) {
                System.out.println("Bill added successfully!");
            }
        } else {
            System.out.println("Invalid patient selection.");
        }
    }

    private static void saveAllData() {
        System.out.println("\n--- Saving All Data ---");
        if (fileManager.saveAllData(dataManager)) {
            System.out.println("All data saved successfully!");
        } else {
            System.out.println("Error saving data. Please try again.");
        }
    }

    private static void loadAllData() {
        System.out.println("\n--- Loading Data from Files ---");
        if (fileManager.loadAllData(dataManager)) {
            System.out.println("Data loaded successfully!");
            dataManager.displaySummary();
        } else {
            System.out.println("Error loading data.");
        }
    }

    private static patient readPatient(Scanner scanner) {
        System.out.println("Enter patient details:");
        int personId = readIntWithPrompt(scanner, "  Person ID: ");
        String name = readNonEmptyString(scanner, "  Name: ");
        int phone = readIntWithPrompt(scanner, "  Phone (digits only): ");
        String email = readNonEmptyString(scanner, "  Email: ");
        String address = readNonEmptyString(scanner, "  Address: ");
        int patientId = readIntWithPrompt(scanner, "  Patient ID: ");
        String medicalHistory = readNonEmptyString(scanner, "  Medical history: ");
        String insuranceProvider = readNonEmptyString(scanner, "  Insurance provider: ");

        return new patient(personId, name, phone, email, address, patientId, medicalHistory, insuranceProvider);
    }

    private static doctor readDoctor(Scanner scanner) {
        System.out.println("Enter doctor details:");
        int personId = readIntWithPrompt(scanner, "  Person ID: ");
        String name = readNonEmptyString(scanner, "  Name: ");
        int phone = readIntWithPrompt(scanner, "  Phone (digits only): ");
        String email = readNonEmptyString(scanner, "  Email: ");
        String address = readNonEmptyString(scanner, "  Address: ");
        int doctorId = readIntWithPrompt(scanner, "  Doctor ID: ");
        String specialization = readNonEmptyString(scanner, "  Specialization: ");
        String licenseNumber = readNonEmptyString(scanner, "  License number: ");

        return new doctor(personId, name, phone, email, address, doctorId, specialization, licenseNumber);
    }

    private static appointment readAppointment(Scanner scanner) {
        System.out.println("Enter appointment details:");
        int appointmentId = readIntWithPrompt(scanner, "  Appointment ID: ");
        String date = readNonEmptyString(scanner, "  Date (e.g. 2024-07-01): ");
        String time = readNonEmptyString(scanner, "  Time (e.g. 10:00 AM): ");
        String description = readNonEmptyString(scanner, "  Description: ");

        return new appointment(appointmentId, date, time, description);
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    System.out.println("  Input cannot be empty. Please try again.");
                    continue;
                }
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a valid whole number.");
            }
        }
    }

    private static int readIntWithPrompt(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    System.out.println("  Input cannot be empty. Please try again.");
                    continue;
                }
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    System.out.println("  Input cannot be empty. Please try again.");
                    continue;
                }
                double value = Double.parseDouble(line);
                if (value < 0) {
                    System.out.println("  Amount cannot be negative. Please try again.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("  Please enter a valid number (e.g., 123.45).");
            }
        }
    }

    private static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("  Input cannot be empty. Please try again.");
        }
    }
}
