public class bill {
    
    private patient patient;
    private double amount;
    private boolean isPaid;
    
    public bill(patient patient, double amount) {
        this.patient = patient;
        this.amount = amount;
        this.isPaid = false;
    }
    
    public void generateBill() {
        System.out.println("Generating bill for patient: " + patient.getName());
        System.out.println("Phone: " + patient.getPhone());
        System.out.println("Amount: $" + amount);
    }
    
    public void makePayment() {
        if (!isPaid) {
            isPaid = true;
            System.out.println("Payment of $" + amount + " made for patient: " + patient.getName());
        } else {
            System.out.println("Bill already paid for patient: " + patient.getName());
        }
    }
    
    public patient getPatient() {
        return this.patient;
    }
    
    public double getAmount() {
        return this.amount;
    }
    
    public boolean isPaid() {
        return this.isPaid;
    }
}
