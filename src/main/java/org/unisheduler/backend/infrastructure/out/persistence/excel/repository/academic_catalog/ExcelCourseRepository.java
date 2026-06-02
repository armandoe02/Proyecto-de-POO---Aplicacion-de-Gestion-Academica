package org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unisheduler.backend.infrastructure.out.entity.academic_catalog.CourseEntity;
import org.unisheduler.backend.infrastructure.out.persistence.excel.core.ExcelIdGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExcelCourseRepository {
    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public Optional<CourseEntity> findById(String id) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table courseTable = doc.getTableByName("Course");

            for (int i = 1; i < courseTable.getRowCount(); i++) {

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

    public List<CourseEntity> findAll() {

        List<CourseEntity> courses = new ArrayList<>();

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table courseTable = doc.getTableByName("Course");

            for (int i = 1; i < courseTable.getRowCount(); i++) {

                String courseId = courseTable.getCellByPosition(0, i).getStringValue();

                CourseEntity course = new CourseEntity();

                course.setCourseId(courseId);
                course.setName(courseTable.getCellByPosition(1, i).getStringValue());
                course.setCode(courseTable.getCellByPosition(2, i).getStringValue());
                course.setCredits(
                        Integer.parseInt(
                                courseTable.getCellByPosition(3, i).getStringValue()
                        )
                );

                courses.add(course);
            }

            return courses;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsByCode(String code) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table courseTable = doc.getTableByName("Course");

            for (int i = 1; i < courseTable.getRowCount(); i++) {

                String currentId = courseTable.getCellByPosition(2, i).getStringValue();

                if (code.equals(currentId)) {
                    return true;
                }
            }

            return false;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<CourseEntity> findByCode(String code) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table courseTable = doc.getTableByName("Course");

            for (int i = 1; i < courseTable.getRowCount(); i++) {

                String courseCode = courseTable.getCellByPosition(2, i).getStringValue();

                if (courseCode.equals(code)) {

                    CourseEntity course = new CourseEntity();
                    course.setCourseId(courseTable.getCellByPosition(0, i).getStringValue());
                    course.setName(courseTable.getCellByPosition(1, i).getStringValue());
                    course.setCode(code);
                    course.setCredits(Integer.parseInt(courseTable.getCellByPosition(3, i).getStringValue()));
                    course.setDescription(courseTable.getCellByPosition(4, i).getStringValue());

                    return Optional.of(course);
                }
            }

            return Optional.empty();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CourseEntity save(CourseEntity entity) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));

            Table courseTable = doc.getTableByName("Course");

            String newCourseId = ExcelIdGenerator.generateNextId(courseTable, 0);
            entity.setCourseId(newCourseId);

            int courseRow = courseTable.getRowCount();
            courseTable.appendRow();

            courseTable.getCellByPosition(0, courseRow).setStringValue(entity.getCourseId());
            courseTable.getCellByPosition(1, courseRow).setStringValue(entity.getName());
            courseTable.getCellByPosition(2, courseRow).setStringValue(entity.getCode());
            courseTable.getCellByPosition(3, courseRow).setStringValue(String.valueOf(entity.getCredits()));
            courseTable.getCellByPosition(4, courseRow).setStringValue(entity.getDescription());

            doc.save(FILE_PATH);

            return entity;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CourseEntity update(CourseEntity entity) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));

            Table courseTable = doc.getTableByName("Course");

            for (int i = 1; i < courseTable.getRowCount(); i++) {

                String currentId = courseTable.getCellByPosition(0, i).getStringValue();

                if (entity.getCourseId().equals(currentId)) {

                    courseTable.getCellByPosition(1, i).setStringValue(entity.getName());
                    courseTable.getCellByPosition(2, i).setStringValue(entity.getCode());
                    courseTable.getCellByPosition(3, i).setStringValue(String.valueOf(entity.getCredits()));
                    courseTable.getCellByPosition(4, i).setStringValue(entity.getDescription());

                    doc.save(FILE_PATH);

                    return entity;
                }
            }

            throw new RuntimeException(
                    "No existe una asignatura con id: " + entity.getCourseId()
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
