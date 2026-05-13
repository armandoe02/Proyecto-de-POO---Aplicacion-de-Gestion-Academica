package model.matricula;

import model.gestion_academica.AcademicProgram;

import java.util.ArrayList;

public class Enrollment {

    //================// Atributos //================//
    private long enrollmentId;
    private Student student;
    private AcademicProgram academicProgram;
    private String enrollmentDate;
    private ArrayList<Enrollment> details;


    //================// Constructores //================//

    public Enrollment() {
    }

    public Enrollment(long enrollmentId, Student student, AcademicProgram academicProgram, String enrollmentDate, ArrayList<Enrollment> details) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.academicProgram = academicProgram;
        this.enrollmentDate = enrollmentDate;
        this.details = details;
    }

    //================// Setters y Getters //================//

    public long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AcademicProgram getAcademicProgram() {
        return academicProgram;
    }

    public void setAcademicProgram(AcademicProgram academicProgram) {
        this.academicProgram = academicProgram;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public ArrayList<Enrollment> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Enrollment> details) {
        this.details = details;
    }


    //================// Funciones Adicionales //================//


}
