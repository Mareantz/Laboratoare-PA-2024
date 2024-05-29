package com.example;

import java.lang.reflect.Method;
import java.lang.annotation.Annotation;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide the fully qualified name of the class to analyze.");
            return;
        }

        String className = args[0];
        try {
            // Load the class
            Class<?> cls = Class.forName(className);

            // Get and display methods information
            Method[] methods = cls.getDeclaredMethods();
            System.out.println("Methods of class " + className + ":");
            for (Method method : methods) {
                System.out.println(method);
            }

            // Invoke static methods with @Test annotation
            System.out.println("\nInvoking @Test annotated methods:");
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class) && method.getParameterCount() == 0 && java.lang.reflect.Modifier.isStatic(method.getModifiers())) {
                    System.out.println("Invoking method: " + method.getName());
                    method.invoke(null);
                }
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + className);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
