public class patient extends person {
    private int patientID;
    private String medicalHistory;
    private String insuranceProvider;

    public patient(int personID, String name, int phone, String email, String address, int patientID, String medicalHistory, String insuranceProvider) {
        super(personID, name, phone, email, address);
        this.patientID = patientID;
        this.medicalHistory = medicalHistory;
        this.insuranceProvider = insuranceProvider;
    }

    @Override
    public void displayInfo() {
        System.out.println("Patient Information:");
        System.out.println("Person ID: " + this.personID);
        System.out.println("Name: " + this.name);
        System.out.println("Phone: " + this.phone);
        System.out.println("Email: " + this.email);
        System.out.println("Address: " + this.address);
        System.out.println("Patient ID: " + this.patientID);
        System.out.println("Medical History: " + this.medicalHistory);
        System.out.println("Insurance Provider: " + this.insuranceProvider);
    }
    
    public int getPatientID() {
        return this.patientID;
    }
    
    public String getMedicalHistory() {
        return this.medicalHistory;
    }
    
    public String getInsuranceProvider() {
        return this.insuranceProvider;
    }
}