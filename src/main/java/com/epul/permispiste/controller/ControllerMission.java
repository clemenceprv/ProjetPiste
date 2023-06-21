package com.epul.permispiste.controller;
import com.epul.permispiste.domains.ActionEntity;
import com.epul.permispiste.domains.ActionMissionEntity;
import com.epul.permispiste.domains.MissionEntity;
import com.epul.permispiste.service.ActionMissionService;
import com.epul.permispiste.service.ActionService;
import com.epul.permispiste.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;


@RequestMapping("/mission")
@RestController
@CrossOrigin
public class ControllerMission {

    @Autowired
    private MissionService missionService;

    @Autowired
    private ActionService actionService;
    @Autowired
    private ActionMissionService actionMissionService;

    private HttpSession session;

    @RequestMapping("/index")
    public ModelAndView getAll(HttpServletRequest request) {
        String destinationPage;
        try {
            session = request.getSession();
            if (session.getAttribute("id") == null) {
                String message = "Vous n'êtes pas connecté !!";
                request.setAttribute("message", message);
                destinationPage = "vues/connection/login";
            }
            else
            {
                request.setAttribute("missions", missionService.getAll());
                destinationPage = "/vues/mission/afficherMissions";
            }
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }

        // Redirection vers la page jsp appropriee
        return new ModelAndView(destinationPage);
    }

    @RequestMapping("/addForm")
    public ModelAndView addForm(HttpServletRequest request) {
        String destinationPage;
        try {
            session = request.getSession();
            if (session.getAttribute("id") == null) {
                String message = "Vous n'êtes pas connecté !!";
                request.setAttribute("message", message);
                destinationPage = "vues/connection/login";
            }
            else
            {
                request.setAttribute("actions", actionService.getAllAction());
                destinationPage = "/vues/mission/ajouterMission";
            }
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }

        // Redirection vers la page jsp appropriee
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(method = RequestMethod.POST, value ="/add")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response ) {
        String destinationPage;
        try {
            session = request.getSession();
            if (session.getAttribute("id") == null) {
                String message = "Vous n'êtes pas connecté !!";
                request.setAttribute("message", message);
                destinationPage = "vues/connection/login";
            }
            else
            {
                String libelle = request.getParameter("libelle");
                MissionEntity missionEntity = new MissionEntity();

                missionEntity.setInscriptionsById(new ArrayList<>());
                missionEntity.setWording(libelle);


                MissionEntity mission = missionService.addMission(missionEntity);


                /*
                String[] actions = request.getParameterValues("actions");
                if(actions!=null && actions.length>0)
                    for(String idAction : actions){
                        ActionMissionEntity actionMissionEntity = new ActionMissionEntity();
                        //actionMissionEntity.setFkMission(mission.getId());
                        //actionMissionEntity.setActionByFkAction(actionService.getAction(Integer.parseInt(idAction)));
                        //actionMissionEntity.setMissionByFkMission(null);
                        actionMissionService.addActionMission(actionMissionEntity);
                    }
                */



                request.setAttribute("missions", missionService.getAll());
                destinationPage = "/vues/mission/afficherMissions";
            }
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }

        // Redirection vers la page jsp appropriee
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(method = RequestMethod.GET, value ="/editForm/{id}")
    public ModelAndView edit(HttpServletRequest request, @PathVariable(value = "id") int id ) {
        String destinationPage;
        try {
            session = request.getSession();
            if (session.getAttribute("id") == null) {
                String message = "Vous n'êtes pas connecté !!";
                request.setAttribute("message", message);
                destinationPage = "vues/connection/login";
            }
            else
            {
                MissionEntity mission = missionService.getMissionById(id);
                request.setAttribute("mission", mission);
                destinationPage = "/vues/mission/editMission";
            }
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }

        // Redirection vers la page jsp appropriee
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(method = RequestMethod.POST, value ="/edit")
    public ModelAndView edit(HttpServletRequest request, HttpServletResponse response ) {
        String destinationPage;
        try {
            session = request.getSession();
            if (session.getAttribute("id") == null) {
                String message = "Vous n'êtes pas connecté !!";
                request.setAttribute("message", message);
                destinationPage = "vues/connection/login";
            }
            else
            {
                int id = Integer.parseInt(request.getParameter("id"));
                String libelle = request.getParameter("libelle");
                MissionEntity missionEntity = new MissionEntity();
                missionEntity.setId(id);
                missionEntity.setWording(libelle);
                missionService.editMission(missionEntity);
                request.setAttribute("missions", missionService.getAll());
                destinationPage = "/vues/mission/afficherMissions";
            }
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }

        // Redirection vers la page jsp appropriee
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(method = RequestMethod.GET, value ="/delete/{id}")
    public ModelAndView delete(HttpServletRequest request, @PathVariable(value = "id") int id ) {
        String destinationPage;
        try {
            session = request.getSession();
            if (session.getAttribute("id") == null) {
                String message = "Vous n'êtes pas connecté !!";
                request.setAttribute("message", message);
                destinationPage = "vues/connection/login";
            }
            else
            {
                missionService.delete(id);
                request.setAttribute("missions", missionService.getAll());
                destinationPage = "/vues/mission/afficherMissions";
            }
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }

        // Redirection vers la page jsp appropriee
        return new ModelAndView(destinationPage);
    }
}

