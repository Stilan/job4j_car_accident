<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <title>Accident</title>
</head>

<body>
<form action="<c:url value='/save?id=${user.id}'/>" method='POST'>
    <table>
        <tr>
            <td>Имя:</td>
            <td><input type='text' name='name'></td>
        </tr>
        <tr>
            <td>Текст:</td>
            <td><input type='text' name='text'></td>
        </tr>
        <tr>
            <td>Адрес:</td>
            <td><input type='text' name='address'></td>
        </tr>
        <tr>
            <td>Тип:</td>
            <td>
                <select name="type.id">
                    <c:forEach var="type" items="${types}" >
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
        </tr>
        <tr>
            <td>Статьи:</td>
            <td>
                <select name="rIds" multiple>
                    <c:forEach var="rule" items="${rules}" >
                        <option value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>
