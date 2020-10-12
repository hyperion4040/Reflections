package com.akozlowski;

import com.akozlowski.domain.Employee;
import com.akozlowski.domain.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
        private static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
//        reflectionForClassName();
//        reflectionForClassFields();
//        reflectionForClassMethods();
        reflectionForGivenClassMethodName("update");
    }

    private static void reflectionForGivenClassMethodName(final String methodName) throws NoSuchMethodException {
        final Class<Employee> clazz = Employee.class;
        final Method method = clazz.getMethod(methodName);
        logger.log(Level.INFO, "The class has method with name: {0}",methodName);
    }

    private static void reflectionForClassName() throws ClassNotFoundException {
        final Class<Employee> clazz = Employee.class;

        logger.log(Level.INFO, "The name of the class is: {0}",clazz);

        final Class<?> clazzByName = Class.forName("com.akozlowski.domain.Employee");

        logger.log(Level.INFO,"The name of the class found by name is: {0}", clazzByName);

        final User user = new Employee();

        final Class<? extends User> clazzByObject = user.getClass();

        logger.log(Level.INFO,"The name of the class found by using object: {0}",clazzByObject);
    }

    private static void reflectionForClassFields() {
        final Class<Employee> clazz = Employee.class;

        final Field[] clazzFields = clazz.getFields();
        final List<Field> fieldList = Arrays.asList(clazzFields);
        logger.log(Level.INFO, "Class {0} has fields: {1}", new Object[]{clazz, fieldList});

        final List<Field> clazzDeclaredFieldList = Arrays.asList(clazz.getDeclaredFields());

        logger.log(Level.INFO, "Class {0} has fields: {1}", new Object[]{clazz, clazzDeclaredFieldList} );
    }

    private static void reflectionForClassMethods() {
        final Class<Employee> clazz = Employee.class;

        final List<Method> methodList = Arrays.asList(clazz.getMethods());
        final List<Method> declaredMethodList = Arrays.asList(clazz.getDeclaredMethods());
        methodList.forEach(method -> logger.log(Level.INFO, "{0}", method ));
        logger.log(Level.INFO, "DECLARED METHODS VIEW");
        declaredMethodList.forEach(method -> logger.log(Level.INFO, "{0}", method ));

    }
}
