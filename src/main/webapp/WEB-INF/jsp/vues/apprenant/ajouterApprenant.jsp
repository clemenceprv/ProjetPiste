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
<h1>Ajouter une action</h1>
<form method="post" action="/apprenant/add">
    <div class="form-group">
        <label for="nom">Nom</label>
        <input type="text" class="form-control" id="nom" name="nom">
    </div>
    <div class="form-group">
        <label for="prenom">Prenom</label>
        <input type="text" class="form-control" id="prenom" name="prenom">
    </div>
    <button type="submit" class="btn btn-primary">Enregistrer</button>
</form>
</body>
</html>


