package com.epul.permispiste.controller;

import com.epul.permispiste.domains.*;
import com.epul.permispiste.mesExceptions.MonException;
import com.epul.permispiste.service.InscriptionService;
import com.epul.permispiste.service.MissionService;
import com.epul.permispiste.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
        String destinationPage;
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
        List<MissionEntity> missionsNonApprentie =  new ArrayList<>();
        List<MissionEntity> missionsApprentie =  new ArrayList<>();
        List<InscriptionEntity> listeInscriptionsPourUtilisateur = null;

        //Récupération information utilisateur
        int idApprenant = Integer.parseInt(request.getParameter("idApprenant"));


        try {
            // On récupère toutes les inscriptions de l'apprenant
            listeInscriptionsPourUtilisateur = inscriptionService.getInscriptionsByIdUsers(idApprenant);
            missions = missionService.findAll();
            //On les ajoutee dans
            for (InscriptionEntity inscription : listeInscriptionsPourUtilisateur) {
                for (MissionEntity mission : missions) {
                    if (mission.getId()==inscription.getFkMission()) {
                        missionsApprentie.add(mission);
                    }
                }
            }

            // On récupère toutes les missionsNon apprise
            for (MissionEntity mission : missions) {
                if (!missionsApprentie.contains(mission)) {
                    missionsNonApprentie.add(mission);
                }
            }


            System.out.println("Taille liste non apprise"+ missionsNonApprentie.size());
            System.out.println("Taille liste Apprise"+ missionsApprentie.size());
            request.setAttribute("missionsApp", missionsApprentie);
            request.setAttribute("missionsNonApp", missionsNonApprentie);
            destinationPage = "vues/mission/listMissionApp";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "ajouterApprenant.htm")
    public void ajouterApp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            System.out.println("Début controlleur");
            //Récupération Information utilisateur
            int idApprenant = Integer.parseInt(request.getParameter("idApprenant"));
            System.out.println("idApprenant" + idApprenant);

            //Récupération Information mission
            int idMission = Integer.parseInt(request.getParameter("idMission"));
            System.out.println("idMission" + idMission);

            InscriptionEntity inscriptionEntity = inscriptionService.addNewInscription(idApprenant,idMission);
            System.out.println("Appelle controlleur avant redirection");
            String redirectUrl = "/mission/ajouterMissionPourInscription.htm?inscriptionId="+inscriptionEntity.getId()+
                    "&idApprenant="+idApprenant+"&idMission="+idMission;
            response.sendRedirect(redirectUrl);

        }  catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            String destinationPage = "vues/Erreur";
            RequestDispatcher dispatcher = request.getRequestDispatcher(destinationPage);
            dispatcher.forward(request, response);
        }
    }

    @RequestMapping(value = "ajouterMissionPourInscription.htm")
    public void ajouterNewApp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            System.out.println("Début controlleur");
            //Récupération Information utilisateur
            int idApprenant = Integer.parseInt(request.getParameter("idApprenant"));
            System.out.println("idApprenant" + idApprenant);

            //Récupération Information mission
            int idMission = Integer.parseInt(request.getParameter("idMission"));
            System.out.println("idMission" + idMission);

            // Récupération id Inscription
            int idInscription = Integer.parseInt(request.getParameter("inscriptionId"));
            System.out.println("Inscription id"+idInscription);

            inscriptionService.addNewInscriptionAction(idApprenant,idMission, idInscription);
            System.out.println("Appelle controlleur avant redirection");
            String redirectUrl = "/";
            response.sendRedirect(redirectUrl);

        }  catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            String destinationPage = "vues/Erreur";
            RequestDispatcher dispatcher = request.getRequestDispatcher(destinationPage);
            dispatcher.forward(request, response);
        }
    }


}



