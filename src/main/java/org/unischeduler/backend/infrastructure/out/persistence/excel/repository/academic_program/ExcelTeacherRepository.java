package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_program;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.backend.infrastructure.out.entity.academic_programming.TeacherEntity;

import java.io.File;
import java.util.Optional;

public class ExcelTeacherRepository {
    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public Optional<TeacherEntity> findById(String id) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table teacherTable = doc.getTableByName("Teachers");

            for (int i = 0; i < teacherTable.getRowCount(); i++) {

                String teacherId = teacherTable.getCellByPosition(0, i).getStringValue();

                if (teacherId.equals(id)) {

                    TeacherEntity teacher = new TeacherEntity();
                    teacher.setTeacherId(teacherId);
                    teacher.setName(teacherTable.getCellByPosition(1, i).getStringValue());

                    return Optional.of(teacher);
                }
            }

            return Optional.empty();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
