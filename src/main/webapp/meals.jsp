<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<a href="meals/edit?action=create">Add meal</a>
<br><br>
<table border="1">
    <tr>
        <th>
            Date
        </th>
        <th>
            Description
        </th>
        <th>
            Calories
        </th>
        <th></th>
        <th></th>
    </tr>
    <jsp:useBean id="mealsToList" type="java.util.List<ru.javawebinar.topjava.model.MealTo>" scope="request"/>
    <c:forEach var="mealTo" items="${mealsToList}">
        <c:choose>
            <c:when test="${mealTo.excess}">
                <tr style="color: red">
            </c:when>
            <c:otherwise>
                <tr style="color: green">
            </c:otherwise>
        </c:choose>
        <td>
                ${mealTo.dateTime.format(TimeUtil.FORMATTER)}
        </td>
        <td>
                ${mealTo.description}
        </td>
        <td>
                ${mealTo.calories}
        </td>
        <td>
            <a href="meals/edit?action=edit&id=${mealTo.ID}">Update</a>
        </td>
        <td>
            <a href="meals?action=delete&id=${mealTo.ID}">Delete</a>
        </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>