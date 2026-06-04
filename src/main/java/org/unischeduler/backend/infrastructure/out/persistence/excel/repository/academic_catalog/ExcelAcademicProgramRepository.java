package org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.AcademicProgramEntity;

import java.io.File;
import java.util.Optional;

public class ExcelAcademicProgramRepository {
    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public Optional<AcademicProgramEntity> findById(String id) {
        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table academicProgramTable = doc.getTableByName("AcademicProgram");

            for (int i = 0; i < academicProgramTable.getRowCount(); i++) {

                String academicProgramId = academicProgramTable.getCellByPosition(0, i).getStringValue();

                if (academicProgramId.equals(id)) {

                    AcademicProgramEntity academicProgram = new AcademicProgramEntity();
                    academicProgram.setAcademicProgramId(academicProgramId);
                    academicProgram.setName(academicProgramTable.getCellByPosition(1, i).getStringValue());
                    academicProgram.setTotalSemesters(Integer.parseInt(academicProgramTable.getCellByPosition(2, i).getStringValue()));

                    return Optional.of(academicProgram);
                }
            }

            return Optional.empty();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
