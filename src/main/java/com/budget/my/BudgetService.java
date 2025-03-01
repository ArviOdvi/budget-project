package com.budget.my;

import com.budget.my.fileoperations.RecordsFileReader;
import com.budget.my.fileoperations.RecordsFileWriter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BudgetService {
    public int counter;
    private RecordsFileWriter recordsFileWriter = new RecordsFileWriter();
    private RecordsFileReader recordsFileReader = new RecordsFileReader(counter, this);
    private Map<Integer, List<CommonRecord>> commonRecords = new HashMap<>();
    public void setCommonRecords(Map<Integer, List<CommonRecord>> commonRecords) {
        this.commonRecords = commonRecords;
    }
    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter= counter;
    }

    public Map<Integer, List<CommonRecord>> getCommonRecords() {
        return commonRecords;
    }
    public BudgetService() {
        commonRecords = recordsFileReader.loadRecords("records.json", commonRecords);
        }
   public void saveRecords(String filename) {
        recordsFileWriter.saveRecords(filename, commonRecords);
    }
}