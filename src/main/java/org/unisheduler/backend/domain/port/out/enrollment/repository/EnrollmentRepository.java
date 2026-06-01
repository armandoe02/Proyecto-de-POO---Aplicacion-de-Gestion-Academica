package org.unisheduler.backend.domain.port.out.enrollment.repository;

import org.unisheduler.backend.domain.model.enrollment.entity.Enrollment;

public interface EnrollmentRepository {
    Enrollment save(Enrollment enrollment);
}
