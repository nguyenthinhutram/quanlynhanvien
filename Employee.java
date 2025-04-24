
import java.sql.Date;

public class Employee {
    private int Employee_id;
    private String Full_name;
    private Date Date_of_birth;
    private String Gender;
    private String Phone_number;
    private String Email;
    private String Address;
    private String Position;
    private String Department;
    private long Salary;
    private Date Start_date;
    private String Status;
    private String CCCD;
    private String Education;
    private String Notes;
    public Employee(int Employee_id, String Full_name, Date Date_of_birth, String Gender, String Phone_number,
                    String Email, String Address, String Position, String Department, long Salary,
                    Date Start_date, String Status, String CCCD, String Education, String Notes) {
        this.Employee_id = Employee_id;
        this.Full_name = Full_name;
        this.Date_of_birth = Date_of_birth;
        this.Gender = Gender;
        this.Phone_number = Phone_number;
        this.Email = Email;
        this.Address = Address;
        this.Position = Position;
        this.Department = Department;
        this.Salary = Salary;
        this.Start_date = Start_date;
        this.Status = Status;
        this.CCCD = CCCD;
        this.Education = Education;
        this.Notes = Notes;
    }

    public void setEmployee_id(int Employee_id) {
        this.Employee_id = Employee_id;
    }

    public void setFull_name(String Full_name) {
        this.Full_name = Full_name;
    }

    public void setDate_of_birth(Date Date_of_birth) {
        this.Date_of_birth = Date_of_birth;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setPhone_number(String Phone_number) {
        this.Phone_number = Phone_number;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public void setSalary(long Salary) {
        this.Salary = Salary;
    }

    public void setStart_date(Date Start_date) {
        this.Start_date = Start_date;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public void setEducation(String Education) {
        this.Education = Education;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public int getEmployee_id() {
        return Employee_id;
    }

    public String getFull_name() {
        return Full_name;
    }

    public Date getDate_of_birth() {
        return Date_of_birth;
    }

    public String getGender() {
        return Gender;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public String getEmail() {
        return Email;
    }

    public String getAddress() {
        return Address;
    }

    public String getPosition() {
        return Position;
    }

    public String getDepartment() {
        return Department;
    }

    public long getSalary() {
        return Salary;
    }

    public Date getStart_date() {
        return Start_date;
    }

    public String getStatus() {
        return Status;
    }

    public String getCCCD() {
        return CCCD;
    }

    public String getEducation() {
        return Education;
    }

    public String getNotes() {
        return Notes;
    }

    @Override
    public String toString() {
        return "Employee [Employee_id=" + Employee_id + ", Full_name=" + Full_name + ", Date_of_birth=" + Date_of_birth
                + ", Gender=" + Gender + ", Phone_number=" + Phone_number + ", Email=" + Email + ", Address=" + Address
                + ", Position=" + Position + ", Department=" + Department + ", Salary=" + Salary + ", Start_date="
                + Start_date + ", Status=" + Status + ", CCCD=" + CCCD + ", Education=" + Education + ", Notes=" + Notes
                + "]";
    }


}
