package org.unisheduler.backend.domain.port.out.enrollment.repository;

import org.unisheduler.backend.domain.model.enrollment.entity.EnrollmentDetail;

import java.util.ArrayList;

public interface EnrollmentDetailRepository {
    EnrollmentDetail save(EnrollmentDetail enrollmentDetail, String enrollmentId);

    ArrayList<EnrollmentDetail> findByEnrollmentId(String enrollmentId);
}
