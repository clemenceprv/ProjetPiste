package com.epul.permispiste.controller;

import com.epul.permispiste.domains.InscriptionEntity;
import com.epul.permispiste.domains.MissionEntity;
import com.epul.permispiste.domains.UtilisateurEntity;
import com.epul.permispiste.domains.UtilisateurJeuEntity;
import com.epul.permispiste.mesExceptions.MonException;
import com.epul.permispiste.service.InscriptionService;
import com.epul.permispiste.service.JeuService;
import com.epul.permispiste.service.MissionService;
import com.epul.permispiste.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RequestMapping("/mission")
@RestController
@CrossOrigin
public class ControllerMission {

    @Autowired
    private MissionService missionService;

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private InscriptionService inscriptionService;


    //  ***************************************
    //  Affichage pour la création d'une nouvelle mission
    //  ***************************************
    @GetMapping(value = "create_mission.htm")
    public ModelAndView create(HttpServletRequest request) throws Exception {
        String destinationPage;


        try {
            //request.setAttribute("jeux", new JeuService().getAll());
            destinationPage = "mission/create";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }

        return new ModelAndView(destinationPage);
    }

    @GetMapping(value= "choixApprenant")
    public ModelAndView selectionnerApprenant(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String destinationPage = "";
        try {
            List<UtilisateurEntity> listeApprenants = utilisateurService.getLearnerUsers();
            System.out.println(listeApprenants);
            request.setAttribute("listeApprenants", listeApprenants);
            destinationPage = "vues/mission/indexMission";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";

        }
        return new ModelAndView(destinationPage);
    }

    @GetMapping(value = "listeMissionApp.htm")
    public ModelAndView getlisteMissionApp(HttpServletRequest request) throws Exception {
        String destinationPage;
        List<MissionEntity> missions = null;
        List<MissionEntity> missionsNonApprentie = null;
        List<InscriptionEntity> missionsApprentie = null;
        List<InscriptionEntity> listeInscriptionsPourUtilisateur = null;
        int idApprenant = Integer.parseInt(request.getParameter("idApprenant"));
        UtilisateurEntity utilisateur = utilisateurService.getUtilisateurById(idApprenant);
        try {
            // On récupère toutes les inscriptions de l'apprenant
            listeInscriptionsPourUtilisateur = inscriptionService.getInscriptionsByIdUsers(idApprenant);
            // On récupère toutes les missions
            missions = missionService.findAll();
            // Si l'utilisateur n'est pas déjà inscrit dans la mission
            if(!missionsApprentie.isEmpty()) {
                for (MissionEntity mission : missions) {

                    if (!missionsApprentie.contains(mission)) {
                        missionsNonApprentie.add(mission);
                    }
                }
            }

            request.setAttribute("missionsApp", missionsApprentie);
            request.setAttribute("missionsNonApp", missionsNonApprentie);
            destinationPage = "vues/mission/listMissionApp";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }
        return new ModelAndView(destinationPage);
    }


}



