package com.crudapi.employeeaccountapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class EmployeeAccount {

    @Id
    private int id;

    private String bankAccountNumber;
    private String bankName;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
