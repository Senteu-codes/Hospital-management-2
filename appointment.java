public class appointment {
    private int appointmentID;
    private String date;
    private String time;
    private String description;

    public appointment(int appointmentID, String date, String time, String description) {
        this.appointmentID = appointmentID;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public void displayInfo() {
        System.out.println("Appointment Information:");
        System.out.println("Appointment ID: " + this.appointmentID);
        System.out.println("Date: " + this.date);
        System.out.println("Time: " + this.time);
        System.out.println("Description: " + this.description);
    }
    
    public int getAppointmentID() {
        return this.appointmentID;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public String getDescription() {
        return this.description;
    }
}