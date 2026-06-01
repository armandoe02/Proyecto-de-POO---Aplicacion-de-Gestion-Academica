package org.unisheduler.backend.domain.model.academic_catalog.entity;

import java.util.ArrayList;
import java.util.List;

public class Course {

    //================// Atributos //================//
    private String courseId;
    private String name;
    private String code;
    private int credits;

    private List<Course> prerequisites;


    public Course(String courseId, String name, String code, int credits, ArrayList<Course> prerequisites) {
        this.courseId = courseId;
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.prerequisites = prerequisites != null? prerequisites : new ArrayList<>();
    }

    //================// Setters y Getters //================//

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
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

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ArrayList<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }


    //================// Funciones Adicionales //================//
}
