<%--
  Created by IntelliJ IDEA.
  User: etulyon1
  Date: 13/04/2023
  Time: 08:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../header.jsp" %>

<html>
<head>
    <title>Ajouter un apprenant</title>
</head>
<body>
<%@include file="../navigation.jsp" %>
<div class="container">
    <div class="row mt-4">
        <div class="col">
            <h1>Ajouter un apprenant :</h1>
        </div>
    </div>

    <div class="card">
        <div class="card-body">
            <form method="post" action="<c:url value="/inserterApprenant.htm" />">

                <div class="col-sm-12 col-lg-7">
                    <div class="form-group">
                        <label class="control-label">Nom de famille *</label>
                        <input type="text" name="surname" value="" id="surname" class="form-control" placeholder="Votre nom" required>
                    </div>

                    <div class="form-group">
                        <label class="control-label">Prénom *</label>
                        <input type="text" name="forname" value="" id="forname" class="form-control" placeholder="Votre prénom" required>
                    </div>
                </div>

                <nav class="d-flex justify-content-end" aria-label="...">
                    <a href="<c:url value="/listeApprenant.htm" />" class="btn btn-secondary mr-2">
                        Annuler
                    </a>
                    <button type="submit" class="btn btn-primary pull-right">
                        Ajouter
                    </button>
                </nav>

            </form>
        </div>
    </div>
</div>
<%@include file="../footer.jsp"%>
</body>
</html>
</html>
