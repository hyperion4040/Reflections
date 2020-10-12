package com.akozlowski.domain;

import java.util.Date;

public class Employee extends User {
    public int employeeId;

    public String password;

    private Date date;

    private void update() {

    }

    private void setUpdated(final Date date) {
        this.date = date;
    }

    public void setEmployeeId(final int employeeId) {
        this.employeeId = employeeId;
    }
}
