<%--
  Created by IntelliJ IDEA.
  User: camel
  Date: 19/06/2023
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
</head>
<%@include file="../header.jsp" %>
<body>
    <%@include file="../navigation.jsp" %>
    <div class="container">
        <div class="col-md-8 col-sm-8">
            <div class="blanc">
                <c:forEach var="entryAction" items="${actionsAAfficherScore}">
                    <h2>Score pour l'action ${entryAction.key.wording} : ${entryAction.value}</h2>

                    <c:if test="${entryAction.value < entryAction.key.scoreMinimum}">
                        <p>Le score obtenu est insuffisant pour que l'action soit validée</p>
                        <p>Il vous manque ${entryAction.key.scoreMinimum - entryAction.value} points</p>
                    </c:if>

                    <c:if test="${entryAction.value >= entryAction.key.scoreMinimum}">
                        <p>Action validée, Bravo !!</p>
                    </c:if>
                </c:forEach>

            </div>
            <form action="/">
                <button type="submit">Revenir à la page d'accueil</button>
            </form>
        </div>
    </div>

</body>
</html>
