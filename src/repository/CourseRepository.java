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
                .filter(s -> s.getCourseId() == id)
                .findFirst()
                .orElse(null);
    }
}
