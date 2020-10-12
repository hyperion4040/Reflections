package com.akozlowski;

import com.akozlowski.domain.Employee;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        final Logger logger = Logger.getAnonymousLogger();
        final Class<Employee> clazz = Employee.class;

        logger.log(Level.INFO, "The name of the class is: {0}",clazz);

        final Class<?> clazzByName = Class.forName("com.akozlowski.domain.Employee");

        logger.log(Level.INFO,"The name of the class found by name is: {0}", clazzByName);
    }
}
