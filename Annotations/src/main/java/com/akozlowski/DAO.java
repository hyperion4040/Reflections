package com.akozlowski;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DAO<T> {
    private final Logger logger = Logger.getAnonymousLogger();

    public void save(final T t) {
        final var clazz = t.getClass();

        getAnnotationAttributeFromField(clazz);


    }

    private void getAnnotationAttributeFromField(final Class<?> clazz) {
        final var declaredFields = clazz.getDeclaredFields();

        final var clazzAnnotations = clazz.getDeclaredAnnotationsByType(Entity.class);

        var tableName = clazz.getSimpleName().toLowerCase();
        final var tableNameAttribute = clazzAnnotations[0].value();

        final List<String> fieldList = new ArrayList<>();

        if (clazzAnnotations.length > 0 && tableNameAttribute.length() > 0) {
            tableName = tableNameAttribute;
        }

        for (var field : declaredFields) {
            final var annotationsByType = field.getAnnotationsByType(Field.class);
            final var annotation = annotationsByType[0];
            var fieldName = annotation.columnName();
            final var isKey = annotation.isKey();

            if (fieldName.length() == 0) {
                fieldName = field.getName();
            }
            if (!isKey) {
                fieldList.add(fieldName);
            }
        }

        final var sqlFields = fieldList.stream().collect(Collectors.joining(","));
        final var sqlPlaceholders = fieldList.stream().map(s -> "?").collect(Collectors.joining(","));

        final var sqlStatement = String.format("insert into %s (%s) values (%s)", tableName, sqlFields, sqlPlaceholders);

        logger.log(Level.INFO,sqlStatement);


    }

    private void getFieldsAnnotatedWithFieldAnnotation(final Class<?> clazz) {
        final var list = Arrays.stream(clazz.getDeclaredFields())
                .filter(method -> method.getDeclaredAnnotationsByType(Field.class).length > 0)
                .collect(Collectors.toList());

        logger.log(Level.INFO, list.toString());
    }

    private void proceduralStyleAnnotationCheck(final java.lang.reflect.Field[] fields) {
        for (var field : fields) {
            final var annotations = field.getAnnotationsByType(Field.class);
            if (annotations.length > 0) {
                final String result = field.toString();
                logger.log(Level.INFO, result);
            }
        }
    }
}
