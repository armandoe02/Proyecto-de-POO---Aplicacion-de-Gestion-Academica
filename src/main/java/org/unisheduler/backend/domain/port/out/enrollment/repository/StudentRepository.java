package org.unisheduler.backend.domain.port.out.enrollment.repository;

import org.unisheduler.backend.domain.model.enrollment.entity.Student;

import java.util.Optional;

public interface StudentRepository {
    Student save(Student student);
    Optional<Student> findById(String id);
    boolean existsByStudentCode(String code);
}
