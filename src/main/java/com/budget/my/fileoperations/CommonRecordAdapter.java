package com.budget.my.fileoperations;

import com.budget.my.ExpenseRecord;
import com.budget.my.IncomeRecord;
import com.budget.my.CommonRecord;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CommonRecordAdapter extends TypeAdapter<CommonRecord> {
    private final Gson gson;

    public CommonRecordAdapter(Gson gson) {
        this.gson = gson;
    }

    @Override
    public CommonRecord read(JsonReader in) throws IOException {
        in.beginObject();
        IncomeRecord incomeRecord = null;
        ExpenseRecord expenseRecord = null;
        Integer id = null;
        BigDecimal amount = null;
        LocalDateTime date = null;
        String otherInfo = null;
        String incomeCategory = null;
        String incomeType = null;
        String expenseCategory = null;
        String expenseType = null;
        String entry = null;

        while (in.hasNext()) {
            JsonToken token = in.peek();
            if (token == JsonToken.NAME) {
                String name = in.nextName();
                switch (name) {
                    case "incomeCategory":
                        incomeCategory = in.nextString();
                        break;
                    case "incomeType":
                        incomeType = in.nextString();
                        break;
                    case "expenseCategory":
                        expenseCategory = in.nextString();
                        break;
                    case "expenseType":
                        expenseType = in.nextString();
                        break;
                    case "id":
                        if (in.peek() != JsonToken.NULL) {
                            id = in.nextInt();
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
                    case "entry":
                        if (in.peek() != JsonToken.NULL) {
                            entry = in.nextString();
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
        gson.toJson(record, record.getClass(), out);
    }
}
