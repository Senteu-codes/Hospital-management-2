public class doctor extends person {
    private int doctorID;
    private String specialization;
    private String licenseNumber;

    public doctor(int personID, String name, int phone, String email, String address, int doctorID, String specialization, String licenseNumber) {
        super(personID, name, phone, email, address);
        this.doctorID = doctorID;
        this.specialization = specialization;
        this.licenseNumber = licenseNumber;
    }

    @Override
    public void displayInfo() {
        System.out.println("Doctor Information:");
        System.out.println("Person ID: " + this.personID);
        System.out.println("Name: " + this.name);
        System.out.println("Phone: " + this.phone);
        System.out.println("Email: " + this.email);
        System.out.println("Address: " + this.address);
        System.out.println("Doctor ID: " + this.doctorID);
        System.out.println("Specialization: " + this.specialization);
        System.out.println("License Number: " + this.licenseNumber);
    }
    
    public int getDoctorID() {
        return this.doctorID;
    }
    
    public String getSpecialization() {
        return this.specialization;
    }
    
    public String getLicenseNumber() {
        return this.licenseNumber;
    }
}