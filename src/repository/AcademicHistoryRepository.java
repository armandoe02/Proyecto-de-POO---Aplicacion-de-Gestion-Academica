package repository;

import module.academic_history.model.AcademicHistory;

import java.util.ArrayList;
import java.util.List;

public class AcademicHistoryRepository {
    private List<AcademicHistory> academicHistorys = new ArrayList<>();

    public void save(AcademicHistory student) {
        academicHistorys.add(student);
    }

    public List<AcademicHistory> findAll() {
        return academicHistorys;
    }

    public AcademicHistory findById(Long id) {
        return academicHistorys.stream()
                .filter(s -> s.getHistoryId() == id)
                .findFirst()
                .orElse(null);
    }
}
