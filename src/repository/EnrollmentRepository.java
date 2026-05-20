package repository;

import module.enrollment.model.Enrollment;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {
    private List<Enrollment> enrollments = new ArrayList<>();

    public void save(Enrollment student) {
        enrollments.add(student);
    }

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public Enrollment findById(Long id) {
        return enrollments.stream()
                .filter(s -> s.getEnrollmentId() == id)
                .findFirst()
                .orElse(null);
    }
}
