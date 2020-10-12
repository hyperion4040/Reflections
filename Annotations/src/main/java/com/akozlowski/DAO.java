package com.akozlowski;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DAO<T> {
    private final Logger logger = Logger.getAnonymousLogger();

    public void save(final T t) {
        final var clazz = t.getClass();




    }

    private void getAnnotationAttributeFromField(final Class<?> clazz) {
        final var declaredFields = clazz.getDeclaredFields();

        for (var field : declaredFields) {
            final var annotationsByType = field.getAnnotationsByType(Field.class);
            final var annotation = annotationsByType[0];
            var fieldName = annotation.value();
            final var key = annotation.isKey();

            if (fieldName.length() == 0) {
                fieldName = field.getName();
            }
        }
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
