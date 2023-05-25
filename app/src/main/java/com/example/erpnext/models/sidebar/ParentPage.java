package com.example.erpnext.models.sidebar;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;


public enum ParentPage {
    EMPTY, HR, PAYROLL;

    public String toValue() {
        switch (this) {
            case EMPTY:
                return "";
            case HR:
                return "HR";
            case PAYROLL:
                return "Payroll";
        }
        return null;
    }


    public static ParentPage forValue(String value) throws IOException {
        if (value.equals("")) return EMPTY;
        if (value.equals("HR")) return HR;
        if (value.equals("Payroll")) return PAYROLL;
        throw new IOException("Cannot deserialize ParentPage");
    }
}
