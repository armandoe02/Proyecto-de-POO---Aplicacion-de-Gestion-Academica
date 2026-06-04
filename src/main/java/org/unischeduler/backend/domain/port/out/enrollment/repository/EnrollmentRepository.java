package org.unischeduler.backend.domain.port.out.enrollment.repository;

import org.unischeduler.backend.domain.model.enrollment.entity.Enrollment;

public interface EnrollmentRepository {
    Enrollment save(Enrollment enrollment);
}
