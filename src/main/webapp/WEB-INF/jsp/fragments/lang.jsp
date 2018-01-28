<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Сергей
  Date: 28.01.2018
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
</head>
<body>
<div class="dropdown navbar-form navbar-right">
    <form:form class="navbar-form navbar-right">
        <select onchange='changeLocale($(this).val())'>
            <option value="ru"
                    <c:if test="${pageContext.response.locale.toString().equals('ru')}">selected</c:if>>ru
            </option>
            <option value="en"
                    <c:if test="${pageContext.response.locale.toString().equals('en')}">selected</c:if>>en
            </option>
        </select>
    </form:form>

    <script type="text/javascript">
        function changeLocale(locale) {
            window.location.search = 'lang=' + locale;
        }
    </script>
</div>
</body>
</html>
