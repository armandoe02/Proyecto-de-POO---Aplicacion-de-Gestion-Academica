package model.matricula;

import model.autenticacion_y_autorizacion.User;
import model.gestion_academica.AcademicProgram;

public class Student {

    //================// Atributos //================//
    private long studentId;
    private String studentCode;
    private User user;
    private AcademicProgram academicProgram;


    //================// Constructores //================//

    public Student() {
    }

    public Student(long studentId, String studentCode, User user, AcademicProgram academicProgram) {
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.user = user;
        this.academicProgram = academicProgram;
    }

    //================// Setters y Getters //================//

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AcademicProgram getAcademicProgram() {
        return academicProgram;
    }

    public void setAcademicProgram(AcademicProgram academicProgram) {
        this.academicProgram = academicProgram;
    }


    //================// Funciones Adicionales //================//
}
