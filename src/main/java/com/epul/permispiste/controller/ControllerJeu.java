package com.epul.permispiste.controller;

import com.epul.permispiste.domains.*;
import com.epul.permispiste.dto.ActionEntityWDernierScore;
import com.epul.permispiste.dto.ActionWEntityWDernierScoreComparator;
import com.epul.permispiste.dto.IndicatorsActions;
import com.epul.permispiste.mesExceptions.MonException;
import com.epul.permispiste.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

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

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private JeuService jeuService;

    @Autowired
    private ActionJeuService actionJeuService;

    @Autowired
    private IndicateurService indicateurService;



    @Autowired
    private UtilisateurService ServiceUtilisateur;
    @Autowired
    private UtilisateurJeuService unUtilisateurJeuService;

    @Autowired
    private ActionService unActionService;

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
    @GetMapping(value= "choixApprenant")
    public ModelAndView selectionnerApprenant(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String destinationPage = "";
        try {
            List<UtilisateurEntity> listeApprenants = ServiceUtilisateur.getLearnerUsers();
            System.out.println(listeApprenants);
            request.setAttribute("listeApprenants", listeApprenants);
            request.setAttribute("controllerType", request.getParameter("controllerType"));
            destinationPage = "vues/jeu/choixApprenants";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }




    public List<ActionEntity> verifierDependances(ActionEntity action, InscriptionActionEntity inscription)
    {
        ArrayList<ActionEntity> actionsAAjouter = new ArrayList<>();
        while (action.getFkAction() != null)
        {
            ActionEntity actionMere = actionWRepoService.getActionById(action.getFkAction());

            InscriptionActionEntity inscriptionActionMere = inscriptionActionService.getInscriptionActionByIdAction(actionMere.getId(), inscription.getFkInscription());

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
        return actionsAAjouter;
    }
    @RequestMapping(value = "listeJeuxPossiblesApprenant.htm")
    public ModelAndView getJeuxPossiblesPourUnApp(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String destinationPage = "";
        int idApprenant = Integer.parseInt(request.getParameter("idApprenant"));

        // Récupération des informations sur l'utilisateur
        UtilisateurEntity utilisateur = utilisateurService.getUtilisateurById(idApprenant);
        request.setAttribute("utilisateur", utilisateur);

        List<InscriptionActionEntity> listeInscriptionActions = null;
        List<InscriptionEntity> listeInscriptionsPourUtilisateur = null;
        List<ActionEntity> listeActionsPossibles = new ArrayList<>();
        List<ActionEntityWDernierScore> listeActionsWDernierScore = new ArrayList<>();

        try {
            // On récupère toutes les inscriptions de l'apprenant
            listeInscriptionsPourUtilisateur = inscriptionService.getInscriptionsByIdUsers(idApprenant);
            for (InscriptionEntity inscription : listeInscriptionsPourUtilisateur) {
                // On récupère toutes les actions de l'inscription
                listeInscriptionActions = inscriptionActionService.getInscriptionActionsById(inscription.getId());

                // On récupère toutes les actions possibles
                for (InscriptionActionEntity inscriptionAction : listeInscriptionActions) {
                    ActionEntity action = actionWRepoService.getActionById(inscriptionAction.getFkAction());
                    if (action.getFkAction() == null) {
                        // Dans le cas où l'action n'a pas de dépendance
                        if (!listeActionsPossibles.contains(action)) {
                            ActionEntityWDernierScore actionWDernierScore = new ActionEntityWDernierScore(
                                    action.getId(),
                                    action.getWording(),
                                    "",
                                    action.getScoreMinimum(),
                                    inscriptionAction.getFkInscription(),
                                    inscriptionAction.getScore(),
                                    true
                            );
                            listeActionsWDernierScore.add(actionWDernierScore);
                        }
                    }
                    else
                    {
                        // Dans le cas où l'action a des dépendances
                        List<ActionEntity> actionsAAjouter = verifierDependances(action,inscriptionAction);

                        if(actionsAAjouter.size() == 0)
                        {
                            ActionEntityWDernierScore actionWDernierScore = new ActionEntityWDernierScore(
                                    action.getId(),
                                    action.getWording(),
                                    actionWRepoService.getActionById(action.getFkAction()).getWording(),
                                    action.getScoreMinimum(),
                                    inscriptionAction.getFkInscription(),
                                    inscriptionAction.getScore(),
                                    false
                            );
                            listeActionsWDernierScore.add(actionWDernierScore);
                        }
                        else
                        {
                            for (ActionEntity actionAAjouter : actionsAAjouter)
                            {
                                ActionEntityWDernierScore actionWDernierScore = new ActionEntityWDernierScore(
                                        actionAAjouter.getId(),
                                        actionAAjouter.getWording(),
                                        "",
                                        actionAAjouter.getScoreMinimum(),
                                        inscriptionAction.getFkInscription(),
                                        inscriptionAction.getScore(),
                                        true
                                );
                                listeActionsWDernierScore.add(actionWDernierScore);

                            }
                        }

                    }
                }

            }
            Collections.sort(listeActionsWDernierScore, new ActionWEntityWDernierScoreComparator());
            request.setAttribute("listeActions", listeActionsWDernierScore);;
            request.setAttribute("idApprenant", idApprenant);
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

    @RequestMapping(value = "/creerJeu")
    public void creerJeu(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String nomJeu = request.getParameter("nomPartie");
            String[] options = request.getParameterValues("actionsCheckbox");
            ArrayList<Integer> listeIdActions = new ArrayList<>();

            for (String option : options) {
                listeIdActions.add(Integer.parseInt(option));
            }

            JeuEntity jeu = jeuService.addActionsEtJeu(nomJeu, listeIdActions, Integer.parseInt(request.getParameter("idApprenant")));

            String redirectUrl = "/jeu/jouer.htm?idApprenant=" + request.getParameter("idApprenant") + "&idJeu=" + jeu.getId();
            response.sendRedirect(redirectUrl);

        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            String destinationPage = "/vues/Erreur";
            RequestDispatcher dispatcher = request.getRequestDispatcher(destinationPage);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            String destinationPage = "vues/Erreur";
            RequestDispatcher dispatcher = request.getRequestDispatcher(destinationPage);
            dispatcher.forward(request, response);
        }
    }


    @RequestMapping(value = "jouer.htm")
    public ModelAndView jouer(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String destinationPage = "";
        try {
            String idJeuParam = request.getParameter("idJeu");
            int idJeu = Integer.parseInt(idJeuParam);
            System.out.println("Affichage idJeu : "+idJeu);
            List<ActionEntity> listeActions = actionJeuService.getActionsByJeu(idJeu);
            List<IndicatorsActions> listeIndicateursActions = new ArrayList<>();

            for (ActionEntity action : listeActions)
            {
                //Récupérer les indicateurs de l'action
                // TODO : Créer DTO ActionIndicateurEntity
                List<IndicatorEntity> listeIndicateurs = indicateurService.findAllByFkAction(action.getId());
                IndicatorsActions indicatorsActions = new IndicatorsActions(action, listeIndicateurs);
                listeIndicateursActions.add(indicatorsActions);
            }
            request.setAttribute("listeIndicateursActions", listeIndicateursActions);
            request.setAttribute("idJeu", idJeu);
            request.setAttribute("idApprenant", request.getParameter("idApprenant"));
            destinationPage = "vues/jeu/jouer";

        }
        catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    public ActionEntity verifyIfActionIsInList(LinkedHashMap listeActions, int idAction)
    {
        ActionEntity action = null;
        Iterator it = listeActions.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            ActionEntity actionEntity = (ActionEntity) pair.getKey();
            if(actionEntity.getId() == idAction)
            {
                action = actionEntity;
            }
        }
        return action;
    }

    @RequestMapping(value = "/validerJeu")
    public ModelAndView validerJeu(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String destinationPage = "";
        try
        {
            // Récupération des indicateurs sélectionnés
            String[] options = request.getParameterValues("checkboxesChecked");
            List<String> idIndicateursSelected = new ArrayList<String>(Arrays.asList(options));
            System.out.println("idIndicateursSelected : "+idIndicateursSelected);

            int idJeu = Integer.parseInt(request.getParameter("idJeu"));
            int idApprenant = Integer.parseInt(request.getParameter("idApprenant"));

            List<InscriptionEntity> listeInscriptions = inscriptionService.getInscriptionsByIdUsers(idApprenant);

            List<ActionEntity> listeActions = actionJeuService.getActionsByJeu(idJeu);

            LinkedHashMap<ActionEntity, Integer> actionsAAfficherScore = new LinkedHashMap();

            System.out.println("idJeu : "+idJeu);
            System.out.println("idApprenant : "+idApprenant);
            System.out.println("options : "+options);

            // Pour chaque inscription, on calcule le score et on le met à jour
            for (InscriptionEntity inscription : listeInscriptions)
            {
                for (ActionEntity action : listeActions)
                {
                    int score;
                    ActionEntity actionEntity = verifyIfActionIsInList(actionsAAfficherScore, action.getId());
                    if (actionEntity == null)
                    {
                        List<IndicatorEntity> listeIndicateurs = indicateurService.findAllByFkAction(action.getId());
                        score = 0;
                        for (IndicatorEntity indicateur : listeIndicateurs)
                        {
                            if (idIndicateursSelected.contains(String.valueOf(indicateur.getId())))
                            {
                                score += indicateur.getValueIfCheck();
                                System.out.println("J'ai trouvé l'indicateur : "+indicateur.getId()+" dans la liste des indicateurs cochés");
                            }
                            else
                            {
                                score += indicateur.getValueIfUnCheck();
                                System.out.println("L'id de l'indicateur : "+indicateur.getId()+" n'est pas dans la liste des indicateurs cochés");
                            }
                        }
                        actionsAAfficherScore.put(action, score);
                    }
                    else
                    {
                        score = actionsAAfficherScore.get(actionEntity);
                    }
                    System.out.println("action : "+action.getWording());
                    System.out.println("score : "+score);
                    inscriptionActionService.updateScore(inscription.getId(), action.getId(), score);
                }
            }
            System.out.println("actionsAAfficherScore : "+actionsAAfficherScore);
            // Faire afficher le score
            request.setAttribute("actionsAAfficherScore", actionsAAfficherScore);
            System.out.println("Taille de la liste des actions : "+actionsAAfficherScore.size());
            request.setAttribute("idJeu", idJeu);
            request.setAttribute("idApprenant", idApprenant);
            destinationPage = "vues/jeu/afficherResultats";
        }
        catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);



    }


    @RequestMapping(value = "listeJeuxRealise.htm")
    public ModelAndView getJeuxRealise(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        System.out.println("arrivée liste jeu");
        String destinationPage = "";
        int idUser = Integer.parseInt(request.getParameter("idApprenant"));
        System.out.println("Trouver jeu : " + jeuService.getAllJeu());

        List<UtilisateurJeuEntity> listeJeuxPourUtilisateur = null;
        UtilisateurEntity Utilisateurs = null;
        List<JeuEntity> listeJeux;

        try {
            System.out.println("idUser: " + idUser);
            listeJeuxPourUtilisateur = unUtilisateurJeuService.getInscriptionByUsers(idUser);
            System.out.println("liste jeux : " + listeJeuxPourUtilisateur.get(0));

            // Récupérer les informations des jeux associés
            listeJeux = new ArrayList<>();
            for (UtilisateurJeuEntity jeu : listeJeuxPourUtilisateur) {
                //On récupère le jeu grâce à son id
                JeuEntity jeuEntity = jeuService.getJeubyID(jeu.getFkJeu());
                //On récupère les actions lié au jeu
                listeJeux.add(jeuEntity);
                Utilisateurs = ServiceUtilisateur.getUserByFkKey(idUser);
            }



            request.setAttribute("Utilisateur", Utilisateurs);
            request.setAttribute("controllerType", "getJeuxRealise");
            System.out.println("numéro"+ idUser);
            request.setAttribute("listeJeux", listeJeux);
            request.setAttribute("listeJeuxRealise", listeJeuxPourUtilisateur);
            destinationPage = "vues/jeu/listeJeuxRealise";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }
}






