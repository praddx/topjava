
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/meals.css"/>
    <title>Meals</title>
</head>
<body>
    <h2><a href="index.html">Home</a></h2>
    <form action="meals" method="get">
        First name: <input type="text" name="dateTime"><br>
        Last name: <input type="text" name="description"><br>
        Calories: <input type="text" name="calories"><br>
        <input type="submit" value="New Meal">
    </form>
    <h3>Meals</h3>
    <table style="width:100%">
        <tr>
            <th>Date/Time</th>
            <th>Description</th>
            <th>Calories</th>
            <th>...</th>
        </tr>
        <c:forEach var="meal" items="${meals}">
            <tr class="${meal.exceed ? 'redtext' : 'greentext'}">
                <td>${dateTimeFormatter.format(meal.dateTime)}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>
                    <table style="width:100%">
                        <tr>
                            <td>
                                <a href="delete/${meal.uid}">...</a>
                            </td>
                            <td>
                                <a href="update/${meal.uid}">...</a>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>