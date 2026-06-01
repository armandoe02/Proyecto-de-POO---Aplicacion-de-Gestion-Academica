package org.unisheduler.backend.infrastructure.out.persistence.excel.repository.enrollment;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unisheduler.backend.domain.model.enrollment.entity.Enrollment;
import org.unisheduler.backend.infrastructure.out.entity.enrollment.EnrollmentEntity;
import org.unisheduler.backend.infrastructure.out.persistence.excel.core.ExcelIdGenerator;

import java.io.File;

public class ExcelEnrollmentRepository {
    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public EnrollmentEntity save(EnrollmentEntity entity) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));

            Table enrollmentTable = doc.getTableByName("Enrollments");

            String newEnrollmentId = ExcelIdGenerator.generateNextId(enrollmentTable, 0);
            entity.setEnrollmentId(newEnrollmentId);

            int enrollmentRow = enrollmentTable.getRowCount();
            enrollmentTable.appendRow();

            enrollmentTable.getCellByPosition(0, enrollmentRow).setStringValue(entity.getEnrollmentId());
            enrollmentTable.getCellByPosition(1, enrollmentRow).setStringValue(entity.getStudentId());
            enrollmentTable.getCellByPosition(2, enrollmentRow).setStringValue(entity.getAcademicProgramId());
            enrollmentTable.getCellByPosition(3, enrollmentRow).setStringValue(entity.getEnrollmentDate().toString());

            doc.save(FILE_PATH);

            return entity;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
