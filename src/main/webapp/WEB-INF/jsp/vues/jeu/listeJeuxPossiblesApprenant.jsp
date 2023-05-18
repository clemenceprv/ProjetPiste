<%--
  Created by IntelliJ IDEA.
  User: camel
  Date: 17/05/2023
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Action Apprenant</title>
</head>
<%@include file="../header.jsp" %>
<body>
<%@include file="../navigation.jsp" %>
<H1> Affichage des Actions possibles pour un adhérent </H1>
<div class="container">
    <div class="col-md-8 col-sm-8">
        <div class="blanc">
            <br><br>
            <table class="table table-striped">
                <thead>
                <tr>
                    <td scope="col">#</td>
                    <td scope="col">Intitulé</td>
                    <td scope="col">Score minimum</td>
                </tr>
                </thead>
                <c:forEach items="${listeActionsPossibles}" var="item">

                    <tbody>
                        <tr>
                            <th scope="row"></th>
                            <td>${item.wording}</td>
                            <td>${item.scoreMinimum}</td>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<div class="col-md-offset-2 col-md-7" id="resultat">
</div>
</body>
</html>
