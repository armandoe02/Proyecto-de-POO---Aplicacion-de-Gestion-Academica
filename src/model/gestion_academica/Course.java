package model.gestion_academica;

import java.util.ArrayList;

public class Course {

    //================// Atributos //================//
    private long courseId;
    private String name;
    private String code;
    private int credits;
    private AcademicProgram academicProgramId;
    private ArrayList<Course> prerequisites;


    //================// Constructores //================//

    public Course() {
    }

    public Course(long courseId, String name, String code, int credits, AcademicProgram academicProgramId, ArrayList<Course> prerequisites) {
        this.courseId = courseId;
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.academicProgramId = academicProgramId;
        this.prerequisites = prerequisites;
    }

    //================// Setters y Getters //================//

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public AcademicProgram getAcademicProgramId() {
        return academicProgramId;
    }

    public void setAcademicProgramId(AcademicProgram academicProgramId) {
        this.academicProgramId = academicProgramId;
    }

    public ArrayList<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ArrayList<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }


    //================// Funciones Adicionales //================//
}
