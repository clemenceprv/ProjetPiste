<%--
  Created by IntelliJ IDEA.
  User: etulyon1
  Date: 13/04/2023
  Time: 08:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifier Apprenant</title>
</head>
<%@include file="../header.jsp" %>
<body>
<%@include file="../navigation.jsp" %>
<div class="container">
    <div class="row mt-4">
        <div class="col">
            <h1>Editer l'action : ${apprenant.nomapprenant} ${apprenant.prenomapprenant}</h1>
        </div>
    </div>

    <div class="card">
        <div class="card-body">
            <form method="POST" action="<c:url value="/modifierApprenant.htm?id=${apprenant.numapprenant}" />">
                <div class="col-sm-12 col-lg-7">
                    <div class="form-group">
                        <label class="control-label">Nom de famille : </label>
                        <input type="text" name="surname" value="${apprenant.nomapprenant}" id="surname" class="form-control" placeholder="Votre nom" required>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Prénom : </label>
                        <input type="text" name="forname" value="${apprenant.prenomapprenant}" id="forname" class="form-control" placeholder="Votre prénom" required>
                    </div>
                </div>

                <nav class="d-flex justify-content-end" aria-label="...">
                    <a href="<c:url value="/listeApprenant.htm" />" class="btn btn-secondary mr-2">
                        Annuler
                    </a>
                    <button type="submit" class="btn btn-info pull-right">
                        Modifier
                    </button>
                </nav>
            </form>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>
</body>
</html>
