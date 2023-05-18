<%--
  Created by IntelliJ IDEA.
  User: etulyon1
  Date: 13/04/2023
  Time: 08:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%@include file="../header.jsp" %>
<body>
<%@include file="../navigation.jsp" %>
<div class="container">
    <div class="row mt-4">
        <div class="col">
            <h1>Ajouter une mission :</h1>
        </div>
    </div>

    <div class="card">
        <div class="card-body">
            <form method="post" action="<c:url value="/save_mission.htm" />">
                <div class="col-sm-12 col-lg-7">
                    <div class="form-group">
                        <label class="control-label">Libellé mission *</label>
                        <input type="text" name="libmission" id="libmission" class="form-control" placeholder="Exemple : Mission apollo" required>
                    </div>
                    <div class="form-group">
                        <label class="control-label">Libellé jeu *</label>
                        <select name="numJeu" id="list_jeux" class="form-control" required>
                            <option value="">-- Sélectionner le jeu --</option>
                            <c:forEach items="${jeux}" var="item2">
                                <option value="${item2.numjeu}">${item2.libellejeu}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <nav class="d-flex justify-content-end" aria-label="...">
                    <a href="<c:url value="/index_mission.htm" />" class="btn btn-secondary mr-2">Annuler</a>
                    <button type="submit" class="btn btn-primary pull-right">
                        Ajouter
                    </button>
                </nav>
            </form>
        </div>
    </div>
</div>

<%@include file="../footer.jsp" %>
</body>

</html>
