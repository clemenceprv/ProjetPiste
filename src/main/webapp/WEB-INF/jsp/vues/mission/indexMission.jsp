<%--
  Created by IntelliJ IDEA.
  User: melin
  Date: 21/06/2023
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des missions existantes</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .registration-button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 8px 16px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 14px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
        }
    </style>
</head>
<%@include file="../header.jsp" %>
<body>
<%@include file="../navigation.jsp" %>
<div class="container text-center">
    <H1>Liste des missions existantes</H1>
<div class="col-auto row justify-content-center">
    <div class="blanc ">
        <br><br>
        <table>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Inscription</th>
            </tr>
            <c:forEach var="mission" items="${missions}">
                <tr>
                    <td>${mission.id}</td>
                    <td>${mission.wording}</td>
                    <td>
                        <a href="  " class="registration-button">Affecter un nouvel élève</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</div>

</body>
</html>