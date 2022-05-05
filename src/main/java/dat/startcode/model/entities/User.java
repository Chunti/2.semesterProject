package dat.startcode.model.entities;


public class User
{
    private int userId;
    private String name;
    private String email;
    private int role;
    private int phoneNumber;

    public User(int userId,String name,String email, int role, int phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }



    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getRole() {
        return role;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

}
