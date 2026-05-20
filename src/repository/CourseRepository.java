package repository;


import module.academic_catalog.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private List<Course> courses = new ArrayList<>();

    public void save(Course student) {
        courses.add(student);
    }

    public List<Course> findAll() {
        return courses;
    }

    public Course findById(Long id) {
        return courses.stream()
                .filter(c -> c.getCourseId() == id)
                .findFirst()
                .orElse(null);
    }

    public Course findByCode(String code) {
        return courses.stream()
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
