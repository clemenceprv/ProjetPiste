<%--
  Created by IntelliJ IDEA.
  User: camel
  Date: 19/06/2023
  Time: 19:14
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

<div class="container">
  <div class="col-md-8 col-sm-8">
    <div class="blanc">
      <h1>Liste des appenants</h1>
      <c:forEach items="${listeApprenants}" var="apprenant">
        <a href="/jeu/listeJeuxPossiblesApprenant.htm?idApprenant=${apprenant.numUtil}">${apprenant.surname} ${apprenant.forename}</a>
      </c:forEach>
    </div>
  </div>
</div>

</body>
</html>
