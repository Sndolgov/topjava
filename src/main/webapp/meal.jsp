<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 05.11.2017
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Meal</title>


</head>
<body>

<h3><a href="index.html">Home</a></h3>


<br/>


<h2>Meal</h2>
<br/>
<form method="post" action="meals?action=submit">

    <table>

        <tr align="left" hidden>
            <th width="150">id</th>
            <td><input type="text" name="id" value="${meal.id}"/></td>
        </tr>

        Дата/Время:
        <input name="dateTime" type="datetime-local" value="${meal.dateTime}" /> <br/>

        <tr align="left">
            <th>Дата и время</th>
            <td><input type="datetime-local" name="dateTime" value="${meal.dateTime}" /></td>
        </tr>

        <tr align="left">
            <th>Описание</th>
            <td><input type="text" name="description" value="${meal.description}"/>
            </td>
        </tr>

        <tr align="left">
            <th>Количество калорий</th>
            <td><input type="text" name="calories" value="${meal.calories}" /></td>
        </tr>


        <tr>

        <td>
            <button type="submit">Save</button>
        </td>

        <tr/>


    </table>



</form>

</body>
</html>
