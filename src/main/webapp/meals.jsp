<%--
  Created by IntelliJ IDEA.
  User: Диман
  Date: 19.07.2017
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 18px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>

    <style type="text/css">
        .red {
            color: red;
        }

        .green {
            color: green;
        }
    </style>

</head>

<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<h4><a href="meals?action=create">Создать</a></h4>
<table class="tg">
    <tr>
        <th width="120">Дата/Время</th>
        <th>id</th>
        <th>Калории</th>
        <th>Изменить</th>
        <th>Удалить</th>


    </tr>
    <c:forEach var="meal" items="${mealList}">
        <c:set var="style" value="${meal.exceed ? 'red' : 'green'}"/>

        <tr align="center" class=${style} >
            <td>${fn:replace(meal.dateTime, 'T', ' ')}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=update&id=${meal.id}">Изменить</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Удалить</a></td>
        </tr>

    </c:forEach>
</table>
</body>
</html>