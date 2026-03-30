public abstract class person {
    protected int personID;
    protected String name;
    protected int phone;
    protected String email;
    protected String address;
    
    public person(int personID, String name, int phone, String email, String address) {
        this.personID = personID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
    public abstract void displayInfo();
    
    public int getPersonID() {
        return this.personID;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getPhone() {
        return String.valueOf(this.phone);
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getAddress() {
        return this.address;
    }
}
