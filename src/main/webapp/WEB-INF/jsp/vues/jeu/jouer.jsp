<%--
  Created by IntelliJ IDEA.
  User: camel
  Date: 21/05/2023
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
            <h2>Jeu lancé</h2>
            <form action="/jeu/validerJeu" method="post">
                <input type="hidden" name="idApprenant" value="${idApprenant}" />
                <input type="hidden" name="idJeu" value="${idJeu}" />
                <c:forEach items="${listeIndicateursActions}" var="actionIndicateurDTO">
                    <h2>${actionIndicateurDTO.action.wording}</h2>

                    <c:forEach items="${actionIndicateurDTO.indicators}" var="indicateur">
                        <div class="checkbox-wrapper">
                            <label for="choix${indicateur.id}">${indicateur.wording}</label>
                            <input type="checkbox" id="choix${indicateur.id}" name="checkboxesChecked"
                                   value="${indicateur.id}">
                        </div>
                    </c:forEach>
                </c:forEach>

                <h2>A cocher si rien ne devrait être coché</h2>

                <div class="checkbox-wrapper">
                    <label for="choix-1">Aucun élement sélectionné</label>
                    <input type="checkbox" id="choix-1" name="checkboxesChecked"
                           value="-1">
                </div>



                <button type="submit">Valider le questionnaire</button>
            </form>
        </div>
    </div>

</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        var checkboxes = document.querySelectorAll("input[name='checkboxesChecked']");
        var aucunElementCheckbox = document.getElementById("choix-1");

        // Fonction pour vérifier l'état des autres checkboxes
        function checkOtherCheckboxes() {
            var isAnyChecked = false;

            for (var i = 0; i < checkboxes.length; i++) {
                if (checkboxes[i].checked) {
                    isAnyChecked = true;
                    break;
                }
            }

            // Cocher/décocher la checkbox "Aucun élément sélectionné" en fonction de l'état des autres checkboxes
            aucunElementCheckbox.checked = !isAnyChecked;
        }

        // Ajouter un écouteur d'événement sur chaque autre checkbox
        for (var i = 0; i < checkboxes.length; i++) {
            checkboxes[i].addEventListener("change", checkOtherCheckboxes);
        }

        // Vérifier l'état initial des autres checkboxes au chargement de la page
        checkOtherCheckboxes();
    });
</script>

</body>
</html>
