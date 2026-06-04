package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.enrollment;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.backend.infrastructure.out.entity.enrollment.StudentEntity;
import org.unischeduler.backend.infrastructure.out.persistence.excel.core.ExcelIdGenerator;

import java.io.File;
import java.util.Optional;

public class ExcelStudentRepository {
    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public StudentEntity save(StudentEntity entity) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));

            Table studentTable = doc.getTableByName("Students");

            String newStudentId = ExcelIdGenerator.generateNextId(studentTable, 0);
            entity.setStudentId(newStudentId);

            int studentRow = studentTable.getRowCount();
            studentTable.appendRow();

            studentTable.getCellByPosition(0, studentRow).setStringValue(entity.getStudentId());
            studentTable.getCellByPosition(1, studentRow).setStringValue(entity.getStudentCode());
            studentTable.getCellByPosition(2, studentRow).setStringValue(entity.getUserId());

            doc.save(FILE_PATH);

            return entity;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<StudentEntity> findById(String id) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table studentTable = doc.getTableByName("Students");

            for (int i = 0; i < studentTable.getRowCount(); i++) {

                String studentId = studentTable.getCellByPosition(0, i).getStringValue();

                if (studentId.equals(id)) {

                    StudentEntity student = new StudentEntity();
                    student.setStudentId(studentId);
                    student.setStudentCode(studentTable.getCellByPosition(1, i).getStringValue());
                    student.setUserId(studentTable.getCellByPosition(2, i).getStringValue());

                    return Optional.of(student);
                }
            }

            return Optional.empty();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsByStudentCode(String code) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table studentTable = doc.getTableByName("Students");

            for (int i = 0; i < studentTable.getRowCount(); i++) {

                String currentStudentCode = studentTable.getCellByPosition(1, i).getStringValue();

                if (code.equals(currentStudentCode)) {
                    return true;
                }
            }

            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
