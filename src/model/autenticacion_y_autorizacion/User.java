package model.autenticacion_y_autorizacion;

public class User {

    enum Role{Admin, student}

    //================// Atributos //================//
    private long userId;
    private String firstName;
    private String lastName;
    private Password password;
    private Role role;

    //================// Constructores //================//


    public User() {
    }

    public User(long userId, String firstName, String lastName, Password password, Role role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    //================// Setters y Getters //================//


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    //================// Funciones Adicionales //================//


}
