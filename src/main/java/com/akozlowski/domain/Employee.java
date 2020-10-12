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

    private static boolean checkIfNumberIsModuloTwo(final int number) {
        return number % 2 == 0;
    }

    public void setEmployeeId(final int employeeId) {
        this.employeeId = employeeId;
    }
}
