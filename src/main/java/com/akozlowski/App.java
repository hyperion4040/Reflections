package com.akozlowski;

import com.akozlowski.domain.Employee;
import com.akozlowski.domain.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
        private static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, IllegalAccessException {
//        reflectionForClassName();
//        reflectionForClassFields();
//        reflectionForClassMethods();
        reflectionForGivenClassMethodName("update");
        reflectionForGivenClassFieldName("employeeId");
        reflectionForClassMethodWithParameters("setUpdated", Date.class);
        reflectionForClassMethodWithPrimitiveParameter("setEmployeeId", int.class);
        boolean result = checkIfMethodWithNameExist("setUpdated");
        logger.log(Level.INFO, "Method exists: {0}",result);
        callReflectionMethodWithParameter("setEmployeeId", int.class);
        boolean checkResult = callReflectionPrivateMethodWithParameterAndReturnType("checkIfNumberIsModuloTwo",4, int.class);
        logger.log(Level.INFO, "Method exists and return the result: {0}",checkResult);

    }

    private static boolean callReflectionPrivateMethodWithParameterAndReturnType(final String methodName,final int number, final Class<Integer> integerClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Class<Employee> clazz = Employee.class;
        final Method declaredMethod = clazz.getDeclaredMethod(methodName, integerClass);
        declaredMethod.setAccessible(true);
        final Object invoke = declaredMethod.invoke(null, number);
        return (boolean) invoke;
    }

    private static void callReflectionMethodWithParameter(final String setEmployeeId, final Class<Integer> integerClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final Class<Employee> clazz = Employee.class;
        final Method method = clazz.getDeclaredMethod(setEmployeeId, integerClass);

        method.invoke(new Employee(), 7);

        logger.log(Level.INFO, "The class has method with name: {0}",method);
    }

    private static boolean checkIfMethodWithNameExist(final String methodName) {
        final Class<Employee> clazz = Employee.class;
        return Arrays.stream(clazz.getDeclaredMethods()).anyMatch(method -> method.getName().equals(methodName));
    }

    private static void reflectionForClassMethodWithPrimitiveParameter(final String setEmplyeeId, final Class<Integer> integerClass) throws NoSuchMethodException {
        final Class<Employee> clazz = Employee.class;
        final Method method = clazz.getDeclaredMethod(setEmplyeeId, integerClass);

        logger.log(Level.INFO, "The class has method with name: {0}",method);

    }

    private static void reflectionForClassMethodWithParameters(final String setUpdated, final Class<Date> date) throws NoSuchMethodException {
        final Class<Employee> clazz = Employee.class;
        final Method method = clazz.getDeclaredMethod(setUpdated, date);

        logger.log(Level.INFO, "The class has method with name: {0}",method);
    }

    private static void reflectionForGivenClassFieldName(final String employeeId) throws NoSuchFieldException {
        final Class<Employee> clazz = Employee.class;
        final Field field = clazz.getField(employeeId);

        logger.log(Level.INFO, "The class has field with name: {0}",field);
    }

    private static void reflectionForGivenClassMethodName(final String methodName) throws NoSuchMethodException {
        final Class<Employee> clazz = Employee.class;
        final Method method = clazz.getDeclaredMethod(methodName);
        logger.log(Level.INFO, "The class has method with name: {0}",method);
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
