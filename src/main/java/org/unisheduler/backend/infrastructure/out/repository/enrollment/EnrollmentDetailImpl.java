package org.unisheduler.backend.infrastructure.out.repository.enrollment;

import org.unisheduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unisheduler.backend.domain.model.academic_programming.entity.Group;
import org.unisheduler.backend.domain.model.enrollment.entity.EnrollmentDetail;
import org.unisheduler.backend.domain.port.out.academic_programming.GroupRepository;
import org.unisheduler.backend.domain.port.out.enrollment.repository.EnrollmentDetailRepository;
import org.unisheduler.backend.infrastructure.out.entity.enrollment.EnrollmentDetailEntity;
import org.unisheduler.backend.infrastructure.out.mapper.enrollment.EnrollmentDetailMapper;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.enrollment.ExcelEnrollmentDetailRepository;

import java.util.ArrayList;

public class EnrollmentDetailImpl implements EnrollmentDetailRepository {
    private final ExcelEnrollmentDetailRepository detailRepository;
    private final GroupRepository groupRepository;

    public EnrollmentDetailImpl(ExcelEnrollmentDetailRepository detailRepository, GroupRepository groupRepository) {
        this.detailRepository = detailRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public EnrollmentDetail save(EnrollmentDetail enrollmentDetail, String enrollmentId) {
        EnrollmentDetailEntity entity = EnrollmentDetailMapper.toEntity(enrollmentDetail, enrollmentId);

        EnrollmentDetailEntity entitySaved = detailRepository.save(entity);

        Group group = groupRepository.findById(entitySaved.getGroupId())
                .orElseThrow(() -> new EntityNotFoundException("No existe un grupo con id: " + entitySaved.getGroupId()));
        return EnrollmentDetailMapper.toDomain(entitySaved, group);
    }

    @Override
    public ArrayList<EnrollmentDetail> findByEnrollmentId(String enrollmentId) {

        ArrayList<EnrollmentDetailEntity> entities =
                detailRepository.findByEnrollmentId(enrollmentId);

        ArrayList<EnrollmentDetail> result = new ArrayList<>();

        for (EnrollmentDetailEntity entity : entities) {

            Group group = groupRepository.findById(entity.getGroupId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "No existe un grupo con id: " + entity.getGroupId()
                    ));

            result.add(EnrollmentDetailMapper.toDomain(entity, group));
        }

        return result;
    }
}
