package com.dto;

import java.time.Instant;
import com.entities.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonFormat;

public class OperationDTO {
    private OperationType type;

    private double value;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT")
    private Instant date;

    private String recipientAccountNumber;

    private String description;

    public OperationDTO() {

    }

    public OperationDTO(OperationType type, double value, Instant date, String recipientAccountNumber,
            String description) {
        this.type = type;
        this.value = value;
        this.date = date;
        this.recipientAccountNumber = recipientAccountNumber;
        this.description = description;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getRecipientAccountNumber() {
        return recipientAccountNumber;
    }

    public void setRecipientAccountNumber(String recipientAccountNumber) {
        this.recipientAccountNumber = recipientAccountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
