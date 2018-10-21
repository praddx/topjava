
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/meals.css"/>
    <title>Meals</title>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meals</h2>
    <table style="width:100%">
        <tr>
            <th>Date/Time</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        <c:forEach var="meal" items="${meals}">
            <tr class="${meal.exceed ? 'redtext' : 'greentext'}">
                <th>${dateTimeFormatter.format(meal.dateTime)}</th>
                <th>${meal.description}</th>
                <th>${meal.calories}</th>
            </tr>
        </c:forEach>
    </table>

</body>
</html>