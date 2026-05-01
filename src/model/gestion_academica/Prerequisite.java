package model.gestion_academica;

public class Prerequisite {

    //================// Atributos //================//
    private long prerequisite;
    private long courseId;
    private long requiredCourseId;


    //================// Constructores //================//

    public Prerequisite() {
    }

    public Prerequisite(long prerequisite, long courseId, long requiredCourseId) {
        this.prerequisite = prerequisite;
        this.courseId = courseId;
        this.requiredCourseId = requiredCourseId;
    }

    //================// Setters y Getters //================//

    public long getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(long prerequisite) {
        this.prerequisite = prerequisite;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getRequiredCourseId() {
        return requiredCourseId;
    }

    public void setRequiredCourseId(long requiredCourseId) {
        this.requiredCourseId = requiredCourseId;
    }


    //================// Funciones Adicionales //================//
}
