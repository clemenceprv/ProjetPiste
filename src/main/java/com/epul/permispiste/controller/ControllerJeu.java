package com.epul.permispiste.controller;

import com.epul.permispiste.domains.ActionEntity;
import com.epul.permispiste.domains.InscriptionActionEntity;
import com.epul.permispiste.domains.InscriptionEntity;
import com.epul.permispiste.domains.JeuEntity;
import com.epul.permispiste.dto.ActionEntityWDernierScore;
import com.epul.permispiste.mesExceptions.MonException;
import com.epul.permispiste.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/jeu")
@RestController
@CrossOrigin
public class ControllerJeu {

    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private InscriptionActionService inscriptionActionService;

    @Autowired
    private ActionWRepoService actionWRepoService;

    //  ***************************************
    //  On affiche la liste des apprenants recherchés
    //  ***************************************
    /*@GetMapping(value = "searchlisteApprenant.htm")
    public ModelAndView search(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            request.setAttribute("apprenants", new ApprenantService().searchListeApprenants(request.getParameter("search")));
            destinationPage = "apprenant/index";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }
        return new ModelAndView(destinationPage);
    }
*/
    public List<ActionEntity> verifierDependances(ActionEntity action, List<ActionEntity> listeActionsPossibles)
    {
        ArrayList<ActionEntity> actionsAAjouter = new ArrayList<>();
        while (action.getFkAction() != null)
        {
            ActionEntity actionMere = actionWRepoService.getActionById(action.getFkAction());
            InscriptionActionEntity inscriptionActionMere = inscriptionActionService.getInscriptionActionByIdAction(actionMere.getId(), inscription.getId());

            if (listeActionsPossibles.contains(actionMere))
            {
                actionsAAjouter.add(action);
                break;
            }
            else
            {
                System.out.println("actionMere.getScoreMinimum() : " + actionMere.getScoreMinimum());
                if (actionMere.getScoreMinimum() > inscriptionActionMere.getScore())
                {
                    actionsAAjouter = new ArrayList<>();
                    break;
                }
                else
                {
                    actionsAAjouter.add(action);
                    action = actionMere;
                }
            }
        }
        return actionsAAjouter;
    }
    @RequestMapping(value = "listeJeuxPossiblesApprenant.htm")
    public ModelAndView getJeuxPossiblesPourUnApp(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String destinationPage = "";
        int idApprenant = Integer.parseInt(request.getParameter("idApprenant"));

        List<InscriptionActionEntity> listeInscriptionActions = null;
        List<InscriptionEntity> listeInscriptionsPourUtilisateur = null;
        List<ActionEntity> listeActionsPossibles = new ArrayList<>();
        List<String> listeDerniersScore = new ArrayList<>();
        List<JeuEntity> listeJeuxPossibles = null;

        try {
            System.out.println("idApprenant : " + idApprenant);
            listeInscriptionsPourUtilisateur = inscriptionService.getInscriptionsByIdUsers(idApprenant);
            for (InscriptionEntity inscription : listeInscriptionsPourUtilisateur) {
                listeInscriptionActions = inscriptionActionService.getInscriptionActionsById(inscription.getId());
                for (InscriptionActionEntity inscriptionAction : listeInscriptionActions) {
                    ActionEntity action = actionWRepoService.getActionById(inscriptionAction.getFkAction());
                    if (action.getFkAction() == null) {
                        if (!listeActionsPossibles.contains(action)) {
                            listeActionsPossibles.add(action);

                        }
                    }
                    else
                    {
                        List<ActionEntity> actionsAAjouter = new ArrayList<>();
                        boolean ajouter = true;

                        if (ajouter)
                        {
                            for (ActionEntity actionAAjouter : actionsAAjouter)
                            {
                                if (!listeActionsPossibles.contains(actionAAjouter))
                                    listeActionsPossibles.add(actionAAjouter);
                            }
                        }

                    }
                }
            }
            request.setAttribute("listeActionsPossibles", listeActionsPossibles);
            destinationPage = "vues/jeu/listeJeuxPossiblesApprenant";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }



}


