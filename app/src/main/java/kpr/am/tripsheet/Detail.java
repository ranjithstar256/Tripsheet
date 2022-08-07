package kpr.am.tripsheet;

public class Detail {

    // variables for storing our data.
    private String Name;    private String Loc;    private String Phone;    private String Password;
    String cabname;

    public String getCabname() {
        return cabname;
    }

    public void setCabname(String cabname) {
        this.cabname = cabname;
    }

    public String getCabnum() {
        return cabnum;
    }

    public void setCabnum(String cabnum) {
        this.cabnum = cabnum;
    }

    String cabnum;
    public Detail() {
        // empty constructor
        // required for Firebase.
    }

    // Constructor for all variables.
    public Detail(String Name, String Loc, String Phone, String Password,String cbnam, String cbnum) {
        this.Name = Name;        this.Loc = Loc;
        this.Phone = Phone;        this.Password = Password;
        this.cabname = cbnam;        this.cabnum = cbnum;
    }

    // getter methods for all variables.
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLoc() {
        return Loc;
    }

    // setter method for all variables.
    public void setLoc(String loc) {
        this.Loc = Loc;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String courseDuration) {
        this.Phone = Phone;
    }
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}