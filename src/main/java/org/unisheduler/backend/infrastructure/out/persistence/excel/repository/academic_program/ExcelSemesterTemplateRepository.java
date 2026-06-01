package org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_program;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unisheduler.backend.infrastructure.out.entity.academic_catalog.AcademicProgramEntity;
import org.unisheduler.backend.infrastructure.out.entity.academic_programming.SemesterTemplateEntity;

import java.io.File;

public class ExcelSemesterTemplateRepository {

    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public SemesterTemplateEntity findByProgramAndSemester(AcademicProgramEntity program, int semester) {

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table table = doc.getTableByName("SemesterTemplate");

            for (int i = 1; i < table.getRowCount(); i++) {

                String programId = table.getCellByPosition(1, i).getStringValue();
                int semesterValue = Integer.parseInt(table.getCellByPosition(2, i).getStringValue());

                if (programId.equals(program.getAcademicProgramId()) && semesterValue == semester) {

                    SemesterTemplateEntity entity = new SemesterTemplateEntity();
                    entity.setTemplateId(table.getCellByPosition(0, i).getStringValue());
                    entity.setProgramId(programId);
                    entity.setSemester(semesterValue);

                    return entity;
                }
            }

            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}