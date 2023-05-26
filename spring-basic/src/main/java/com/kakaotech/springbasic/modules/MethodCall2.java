package com.kakaotech.springbasic.modules;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class MethodCall2 {
    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.kakaotech.springbasic.controller.getDay");
        Object object = clazz.newInstance();

        Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);

        Model model = new BindingAwareModelMap();
        System.out.println("[ before ] model = " + model);

//        String viewName = object.main(2023, 5, 26, model); // 아래 코드와 동일
        String viewName = (String) main.invoke(object, new Object[] { 2023, 5, 26, model });
        System.out.println("viewName = " + viewName);

        System.out.println("[ after ] model = " + model);

        render(model, viewName);
    }

    static void render(Model model, String viewName) throws IOException {
        String result = "";

        Scanner scanner = new Scanner(new File("src/main/webapp/WEB-INF/views/" + viewName + ".jsp"), "utf-8");
        while (scanner.hasNextLine()) {
            result += scanner.nextLine() + System.lineSeparator();
        }

        Map map = model.asMap();
        Iterator iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            result = result.replace("${" + key + "}", map.get(key).toString());
        }

        System.out.println(result);
    }
}


//출력 결과
//[ before ] model = {}
//viewName = day
//[ after ] model = {year=2023, month=5, date=26, day=금}
//<%--
//    Created by IntelliJ IDEA.
//    User: seokjun
//    Date: 2023/05/25
//    Time: 9:14 PM
//    To change this template use File | Settings | File Templates.
//--%>
//<%@ page contentType="text/html;charset=utf-8" %>
//<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
//<%@ page session="false" %>
//<html>
//<head>
//    <title>Calc Day</title>
//</head>
//<body>
//    <h1>year=<%=request.getParameter("year") %></h1>
//    <P> getDay =  2023년 5월 26일은 "금요일" 입니다.</P>
//    <P> getDay2 = ${myDate.year}년 ${myDate.month}월 ${myDate.date}일은 "금요일" 입니다.</P>
//</body>
//</html>
