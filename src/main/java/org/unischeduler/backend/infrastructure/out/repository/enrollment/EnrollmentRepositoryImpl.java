package org.unischeduler.backend.infrastructure.out.repository.enrollment;

import org.unischeduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicProgram;
import org.unischeduler.backend.domain.model.enrollment.entity.Enrollment;
import org.unischeduler.backend.domain.model.enrollment.entity.EnrollmentDetail;
import org.unischeduler.backend.domain.model.enrollment.entity.Student;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicProgramRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentDetailRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.EnrollmentRepository;
import org.unischeduler.backend.domain.port.out.enrollment.repository.StudentRepository;
import org.unischeduler.backend.infrastructure.out.entity.enrollment.EnrollmentEntity;
import org.unischeduler.backend.infrastructure.out.mapper.enrollment.EnrollmentMapper;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelEnrollmentRepository;

import java.util.ArrayList;

public class EnrollmentRepositoryImpl implements EnrollmentRepository {
    private final ExcelEnrollmentRepository enrollmentRepository;
    private final AcademicProgramRepository programRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentDetailRepository enrollmentDetailRepository;

    public EnrollmentRepositoryImpl(ExcelEnrollmentRepository enrollmentRepository, AcademicProgramRepository programRepository, StudentRepository studentRepository, EnrollmentDetailRepository enrollmentDetailRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.programRepository = programRepository;
        this.studentRepository = studentRepository;
        this.enrollmentDetailRepository = enrollmentDetailRepository;
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        EnrollmentEntity entity = EnrollmentMapper.toEntity(enrollment);

        EnrollmentEntity entitySaved = enrollmentRepository.save(entity);
        AcademicProgram program = programRepository.findById(entitySaved.getAcademicProgramId())
                .orElseThrow(() -> new EntityNotFoundException("No existe un programa academico con el id: " + entitySaved.getAcademicProgramId()));
        Student student = studentRepository.findById(entitySaved.getStudentId())
                .orElseThrow(() -> new EntityNotFoundException("No existe un estudiante con el id: " + entitySaved.getStudentId()));
        ArrayList<EnrollmentDetail> details = enrollmentDetailRepository.findByEnrollmentId(entitySaved.getEnrollmentId());

        return EnrollmentMapper.toDomain(entitySaved, student, program, details);
    }
}
