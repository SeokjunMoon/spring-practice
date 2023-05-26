package com.kakaotech.springbasic.modules;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

public class MethodInfo {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.kakaotech.springbasic.controller.getDay");
        Object object = clazz.newInstance();

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String name = method.getName();
            Parameter[] parameters = method.getParameters();
            Class return_type = method.getReturnType();

            StringJoiner params = new StringJoiner(", ", "(", ")");
            for (Parameter parameter : parameters) {
                String parameter_name = parameter.getName();
                Class parameter_type = parameter.getType();

                params.add(parameter_type.getName() + " " + parameter_name);
            }

            System.out.printf("%s %s %s %n", return_type.getName(), name, params);
        }
    }
}
