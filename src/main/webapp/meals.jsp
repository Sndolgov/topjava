<%--
  Created by IntelliJ IDEA.
  User: Диман
  Date: 19.07.2017
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="style.css" rel="stylesheet">
    <title>Meals</title>
</head>

<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<h4><a href="meals?action=create">Создать</a></h4>
<table border="3" bordercolor="grey">
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
        <th>Обновление</th>
        <th>Удаление</th>
    </tr>
    <c:forEach var="meal" items="${mealList}">

        <tr >

            <td>${meal.description}</td>

        </tr>
    </c:forEach>
</table>
</body>
</html>