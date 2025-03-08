package com.budget.my;

import com.budget.my.fileoperations.RecordsFileReader;
import com.budget.my.fileoperations.RecordsFileWriter;
import com.budget.my.records.CommonRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetService {
    @Setter
    @Getter
    public int counter;
    private final RecordsFileWriter recordsFileWriter = new RecordsFileWriter();
    private final RecordsFileReader recordsFileReader = new RecordsFileReader(counter, this);
    @Getter
    private Map<Integer, List<CommonRecord>> commonRecords = new HashMap<>();

    public BudgetService() {
        commonRecords = recordsFileReader.loadRecords("records.json", commonRecords);
        }
    public void saveRecords(String filename) {
        recordsFileWriter.saveRecords(filename, commonRecords);
    }
}