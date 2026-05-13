package model.historial_academico;

import model.matricula.Student;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class AcademicHistory {

    //================// Atributos //================//
    private long historyId;
    private Student student;
    private ArrayList<CompletedCourse> completedCourses;


    //================// Constructores //================//

    public AcademicHistory() {
    }

    public AcademicHistory(long historyId, Student student, ArrayList<CompletedCourse> completedCourses) {
        this.historyId = historyId;
        this.student = student;
        this.completedCourses = completedCourses;
    }

    //================// Setters y Getters //================//

    public long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(long historyId) {
        this.historyId = historyId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ArrayList<CompletedCourse> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(ArrayList<CompletedCourse> completedCourses) {
        this.completedCourses = completedCourses;
    }


    //================// Funciones Adicionales //================//

}
