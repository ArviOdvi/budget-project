package com.budget.my.fileoperations;

import com.budget.my.enum_data.ExpenseCategory;
import com.budget.my.enum_data.IncomeCategory;
import com.budget.my.enum_data.ExpenseType;
import com.budget.my.enum_data.IncomeType;
import com.budget.my.records_setting.ExpenseRecord;
import com.budget.my.records_setting.IncomeRecord;
import com.budget.my.records_setting.CommonRecord;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FileRecordAdapter extends TypeAdapter<CommonRecord> {
    private final Gson gson;

    public FileRecordAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public CommonRecord read(JsonReader in) throws IOException {
        in.beginObject();
        IncomeRecord incomeRecord = null;
        ExpenseRecord expenseRecord = null;
        String id = null;
        BigDecimal amount = null;
        LocalDateTime date = null;
        String otherInfo = null;
        IncomeCategory incomeCategory = null;
        IncomeType incomeType = null;
        ExpenseCategory expenseCategory = null;
        ExpenseType expenseType = null;

        while (in.hasNext()) {
            JsonToken token = in.peek();
            if (token == JsonToken.NAME) {
                String name = in.nextName();
                switch (name) {
                    case "incomeCategory":
                        try {
                            incomeCategory = IncomeCategory.valueOf(in.nextString());
                        } catch (IllegalArgumentException e) {
                            in.skipValue();
                        }
                        break;
                    case "incomeType": // Ištaisome deserializavimą
                        try {
                            incomeType = IncomeType.valueOf(in.nextString());
                        } catch (IllegalArgumentException e) {
                            in.skipValue();
                        }
                        break;
                    case "expenseCategory":
                        try {
                            expenseCategory = ExpenseCategory.valueOf(in.nextString());
                        } catch (IllegalArgumentException e) {
                            in.skipValue();
                        }
                        break;
                    case "expenseType": // Ištaisome deserializavimą
                        try {
                            expenseType = ExpenseType.valueOf(in.nextString());
                        } catch (IllegalArgumentException e) {
                            in.skipValue();
                        }
                        break;
                    case "id":
                        if (in.peek() != JsonToken.NULL) {
                            id = in.nextString();
                        } else {
                            in.nextNull();
                        }
                        break;
                    case "amount":
                        if (in.peek() != JsonToken.NULL) {
                            amount = new BigDecimal(in.nextString());
                        } else {
                            in.nextNull();
                        }
                        break;
                    case "date":
                        if (in.peek() != JsonToken.NULL) {
                            date = LocalDateTime.parse(in.nextString());
                        } else {
                            in.nextNull();
                        }
                        break;
                    case "otherInfo":
                        if (in.peek() != JsonToken.NULL) {
                            otherInfo = in.nextString();
                        } else {
                            in.nextNull();
                        }
                        break;
                    default:
                        in.skipValue();
                        break;
                }
            }
        }
        in.endObject();

        if (incomeCategory != null || incomeType != null) {
            incomeRecord = new IncomeRecord(id, amount, date, otherInfo, incomeCategory, incomeType);
            return incomeRecord;
        } else if (expenseCategory != null || expenseType != null) {
            expenseRecord = new ExpenseRecord(id, amount, date, otherInfo, expenseCategory, expenseType);
            return expenseRecord;
        } else {
            return null;
        }
    }

    @Override
    public void write(JsonWriter out, CommonRecord record) throws IOException {
        out.beginObject();
        out.name("id").value(record.getId());
        out.name("amount").value(record.getAmount());
        out.name("date").value(record.getDate().toString());
        out.name("otherInfo").value(record.getOtherInfo());

        if (record instanceof IncomeRecord) {
            IncomeRecord incomeRecord = (IncomeRecord) record;
            out.name("incomeCategory").value(incomeRecord.getIncomeCategory().toString());
            out.name("incomeType").value(incomeRecord.getIncomeType().toString());
        } else if (record instanceof ExpenseRecord) {
            ExpenseRecord expenseRecord = (ExpenseRecord) record;
            out.name("expenseCategory").value(expenseRecord.getExpenseCategory().toString());
            out.name("expenseType").value(expenseRecord.getExpenseType().toString());
        }
        out.endObject();
    }
}