package org.unisheduler.backend.domain.model.enrollment.entity;

import org.unisheduler.backend.domain.model.academic_programming.entity.Group;
import org.unisheduler.backend.domain.model.enrollment.enums.EnrollmentStatus;

import java.time.LocalDate;

public class EnrollmentDetail {
    private final String enrollmentDetailId;
    private Group group;

    private EnrollmentStatus status;
    private Double finalGrade;

    //================// Constructores //================//

    public EnrollmentDetail(String enrollmentDetailId, Group group, EnrollmentStatus status, Double finalGrade) {
        this.enrollmentDetailId = enrollmentDetailId;
        this.group = group;
        this.status = status;
        this.finalGrade = finalGrade;
    }

    public String getEnrollmentDetailId() {
        return enrollmentDetailId;
    }

    public Group getGroup() {
        return group;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public Double getFinalGrade() {
        return finalGrade;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public void setFinalGrade(Double finalGrade) {
        this.finalGrade = finalGrade;
    }
}
