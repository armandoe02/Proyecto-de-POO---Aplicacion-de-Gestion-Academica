package org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unisheduler.backend.infrastructure.out.entity.academic_catalog.CourseEntity;

import java.io.File;
import java.util.Optional;

public class ExcelCourseRepository {
    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public Optional<CourseEntity> findById(String id) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table courseTable = doc.getTableByName("Course");

            for (int i = 0; i < courseTable.getRowCount(); i++) {

                String courseId = courseTable.getCellByPosition(0, i).getStringValue();

                if (courseId.equals(id)) {

                    CourseEntity course = new CourseEntity();
                    course.setCourseId(courseId);
                    course.setName(courseTable.getCellByPosition(1, i).getStringValue());
                    course.setCode(courseTable.getCellByPosition(2, i).getStringValue());
                    course.setCredits(Integer.parseInt(courseTable.getCellByPosition(3, i).getStringValue()));

                    return Optional.of(course);
                }
            }

            return Optional.empty();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
