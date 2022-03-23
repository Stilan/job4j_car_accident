<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Accident</title>
</head>
<body>
<div class="row">
    <div class="card" style="width: 100%">
        <div class="card-body">
            <table class="table">
                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>
                            <c:out value="${user}"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>