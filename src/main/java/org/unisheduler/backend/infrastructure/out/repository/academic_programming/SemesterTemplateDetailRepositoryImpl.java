package org.unisheduler.backend.infrastructure.out.repository.academic_programming;

import org.unisheduler.backend.domain.exceptions.shared.EntityNotFoundException;
import org.unisheduler.backend.domain.model.academic_programming.entity.Group;
import org.unisheduler.backend.domain.model.academic_programming.entity.SemesterTemplateDetail;
import org.unisheduler.backend.domain.port.out.academic_programming.GroupRepository;
import org.unisheduler.backend.domain.port.out.academic_programming.SemesterTemplateDetailRepository;
import org.unisheduler.backend.infrastructure.out.entity.academic_programming.SemesterTemplateDetailEntity;
import org.unisheduler.backend.infrastructure.out.mapper.academic_programming.SemesterTemplateDetailMapper;
import org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_program.ExcelSemesterTemplateDetailRepository;

import java.util.ArrayList;

public class SemesterTemplateDetailRepositoryImpl implements SemesterTemplateDetailRepository {
    private final ExcelSemesterTemplateDetailRepository templateDetailRepository;
    private final GroupRepository groupRepository;

    public SemesterTemplateDetailRepositoryImpl(ExcelSemesterTemplateDetailRepository templateDetailRepository, GroupRepository groupRepository) {
        this.templateDetailRepository = templateDetailRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public ArrayList<SemesterTemplateDetail> findAllWhereTemplateId(String templateId) {
        ArrayList<SemesterTemplateDetailEntity> entities = templateDetailRepository.findAllWhereTemplateId(templateId);

        ArrayList<SemesterTemplateDetail> details = new ArrayList<>();
        for(SemesterTemplateDetailEntity e : entities) {
            Group group = groupRepository.findById(e.getGroupId())
                    .orElseThrow(() -> new EntityNotFoundException("No existe el grupo con id: " + e.getGroupId()));;
            details.add(
                    SemesterTemplateDetailMapper.toDomain(e, group)
            );
        }

        return details;
    }
}
