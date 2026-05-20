package repository;

import module.academic_catalog.model.Course;
import module.enrollment.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private List<Student> students = new ArrayList<>();

    public void save(Student student) {
        students.add(student);
    }

    public List<Student> findAll() {
        return students;
    }

    public Student findById(Long id) {
        return students.stream()
                .filter(s -> s.getStudentId() == id)
                .findFirst()
                .orElse(null);
    }

    public Student findByCode(String code) {
        return students.stream()
                .filter(c -> c.getStudentCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}