<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h2>Meals</h2>

    <br>
    <h3>Фильтр</h3>

    <table>
        <form method="post" action="meals?action=filter">

            <tr>
                <td>От даты</td>
                <td><input type="date" value="${startDate}" name="startDate"></td>
            </tr>
            <tr>
                <td>До даты</td>
                <td><input type="date" value="${endDate}" name="endDate"></td>
            </tr>
            <tr>
                <td>От времени</td>
                <td><input type="time" value="${startTime}" name="startTime"></td>
            </tr>
            <tr>
                <td>До времени</td>
                <td><input type="time" value="${endTime}" name="endTime"></td>
            </tr>
            <tr>
                <td><button><img alt="" src="http://img1.ramapk.com/1/5e/b3538f_0.png" width="25" height="25" style="vertical-align:middle"/></button></td>
        </form>
        <form>
            <form action="/meals">
                <td><button><img alt="" src="https://motorov.net/upload/medialibrary/0df/0dfebbdb0e09924a5cdf56e63caea431.png" width="25" height="25" style="vertical-align:middle"/></button></td>
            </form>
        </form>

        </tr>

    </table>

    <br>
    <br>
    <a href="meals?action=create">Add Meal</a>
    <hr/>


    <table border="1" cellpadding="8" cellspacing="0">

        <thead>
        <tr>

            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>

        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>

</section>

</body>
</html>