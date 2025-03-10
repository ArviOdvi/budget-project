package com.budget.my;

import com.budget.my.fileoperations.RecordsFileReader;
import com.budget.my.records.CommonRecord;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class BudgetService {
    public int counter;

    private Map<Integer, List<CommonRecord>> commonRecords = new HashMap<>();

    public BudgetService() {
        RecordsFileReader recordsFileReader = new RecordsFileReader( this);
        commonRecords = recordsFileReader.loadRecords("records.json", commonRecords);
        }
    public Map<Integer, List<CommonRecord>> reindexRecords(){
        Map<Integer, List<CommonRecord>> newRecords = new HashMap<>();
        int newCounter = 1;
        for (List<CommonRecord> records : commonRecords.values()) {
            newRecords.put(newCounter, records);
            newCounter++;
        }
        return newRecords;
    }
}