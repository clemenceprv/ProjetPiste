<%--
  Created by IntelliJ IDEA.
  User: christian
  Date: 06/04/2018
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<%@include file="../header.jsp" %>

<body>
<%@include file="../navigation.jsp" %>
<h1>Affichage des apprenants</h1>
<div class="d-flex">
    <p>Ajouter un apprenant</p>
    <a class="btn btn-primary" href="/apprenant/addForm" role="button">Ajouter</a>
</div>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Libellé</th>
        <th scope="col">Score minimum</th>
        <th scope="col">Modifier</th>
        <th scope="col">Supprimer</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${apprenant}" var="apprenant">
        <tr>
            <th>${apprenant.id}</th>
            <td>${apprenant.wording}</td>
            <td>${apprenant.scoreMinimum}</td>
            <td><a class="btn btn-secondary" href="/apprenant/editForm/${apprenant.id}" role="button">Modifier</a></td>
            <td><a class="btn btn-danger" href="/apprenant/delete/${apprenant.id}" role="button">Supprimer</a></td>
        </tr>
    </c:forEach>
    </tbody>

</table>


</body>
</html>


