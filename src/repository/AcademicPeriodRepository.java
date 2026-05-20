package repository;

import module.academic_catalog.model.AcademicPeriod;

import java.util.ArrayList;
import java.util.List;

public class AcademicPeriodRepository {
    private List<AcademicPeriod> academicPeriods = new ArrayList<>();

    public void save(AcademicPeriod student) {
        academicPeriods.add(student);
    }

    public List<AcademicPeriod> findAll() {
        return academicPeriods;
    }

    public AcademicPeriod findById(Long id) {
        return academicPeriods.stream()
                .filter(s -> s.getAcademicPeriodId() == id)
                .findFirst()
                .orElse(null);
    }
}
