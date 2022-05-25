<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <title>Edit meal ${meal.id}</title>
</head>
<body>
<h3><a href="../index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>
<form method="post" action="edit">
    <input type="hidden" name="id" value="${meal.id}">
    <table>
        <tr>
            <td width="150px">DateTime:</td>
            <td width="250px">
                <input type="datetime-local" name="datetime" value="${meal.dateTime.toLocalDate().toString()
                .concat('T')
                .concat(meal.dateTime.format(TimeUtil.FORMATTER_TIME))}">
            </td>
        </tr>
        <tr>
            <td>Description:</td>
            <td>
                <input type="text" name="description" value="${meal.description}">
            </td>
        </tr>
        <tr>
            <td>Calories:</td>
            <td>
                <input type="text" name="calories" value="${meal.calories}">
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit">Save</button>
                <input type="button" value=" Cancel " onclick="window.location.href='../meals'">
            </td>
            <td>

            </td>
        </tr>
    </table>
</form>
</body>
</html>