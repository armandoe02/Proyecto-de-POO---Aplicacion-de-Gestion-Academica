package org.unischeduler.backend.domain.model.enrollment.entity;

import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;

import java.time.LocalDate;
import java.util.ArrayList;

public class Enrollment {

    //================// Atributos //================//
    private final String enrollmentId;
    private final Student student;
    private  AcademicProgram academicProgram;
    private final LocalDate enrollmentDate;
    private ArrayList<EnrollmentDetail> details;


    public Enrollment(String enrollmentId, Student student, AcademicProgram academicProgram, LocalDate enrollmentDate, ArrayList<EnrollmentDetail> details) {
        this.enrollmentId = enrollmentId;
        this.student = student;
        this.academicProgram = academicProgram;
        this.enrollmentDate = enrollmentDate;
        this.details = details;
    }

    //================// Setters y Getters //================//

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public Student getStudent() {
        return student;
    }


    public AcademicProgram getAcademicProgram() {
        return academicProgram;
    }

    public void setAcademicProgram(AcademicProgram academicProgram) {
        this.academicProgram = academicProgram;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public ArrayList<EnrollmentDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<EnrollmentDetail> details) {
        this.details = details;
    }


    //================// Funciones Adicionales //================//


}
