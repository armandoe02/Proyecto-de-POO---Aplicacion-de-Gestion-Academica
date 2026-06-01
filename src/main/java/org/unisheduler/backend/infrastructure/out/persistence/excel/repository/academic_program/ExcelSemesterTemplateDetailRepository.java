package org.unisheduler.backend.infrastructure.out.persistence.excel.repository.academic_program;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;
import org.unisheduler.backend.infrastructure.out.entity.academic_programming.SemesterTemplateDetailEntity;

import java.util.ArrayList;

import java.io.File;
import java.util.ArrayList;

public class ExcelSemesterTemplateDetailRepository {

    private static final String FILE_PATH = "database/unishedulerdatabase.ods";

    public ArrayList<SemesterTemplateDetailEntity> findAllWhereTemplateId(String templateId) {

        ArrayList<SemesterTemplateDetailEntity> results = new ArrayList<>();

        try {
            SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(new File(FILE_PATH));
            Table table = doc.getTableByName("SemesterTemplateDetails");

            for (int i = 0; i < table.getRowCount(); i++) {

                String currentTemplateId = table.getCellByPosition(1, i).getStringValue();

                if (templateId.equals(currentTemplateId)) {

                    SemesterTemplateDetailEntity entity = new SemesterTemplateDetailEntity();

                    entity.setTemplateDetailId(table.getCellByPosition(0, i).getStringValue());
                    entity.setTemplateId(currentTemplateId);
                    entity.setGroupId(table.getCellByPosition(2, i).getStringValue());

                    results.add(entity);
                }
            }

            return results;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}