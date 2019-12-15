package com.github.kfang;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import javafx.beans.property.SimpleDoubleProperty;

public class TextToDoubleProperty extends AbstractBeanField<SimpleDoubleProperty, String> {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (value.equals("")) {
            return new SimpleDoubleProperty(0);
        }

        return new SimpleDoubleProperty(Double.parseDouble(value));
    }
}
