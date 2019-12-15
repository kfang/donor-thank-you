package com.github.kfang;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import javafx.beans.property.SimpleStringProperty;

public class TextToStringProperty extends AbstractBeanField<SimpleStringProperty, String> {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (value == null) {
            return new SimpleStringProperty("");
        }
        return new SimpleStringProperty(value);
    }
}
