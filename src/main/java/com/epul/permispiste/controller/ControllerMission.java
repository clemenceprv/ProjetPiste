package com.epul.permispiste.controller;

import com.epul.permispiste.domains.MissionEntity;
import com.epul.permispiste.service.MissionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;



@RequestMapping("/mission")
@RestController
@CrossOrigin
public class ControllerMission {

    //  ***************************************
    //  Affichage de la liste des missions existantes dans la BD
    //  ***************************************
    @GetMapping(value = "index_mission.htm")
    public ModelAndView index(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            request.setAttribute("missions", new MissionService().getAll());
            destinationPage = "mission/index";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }
        return new ModelAndView(destinationPage);
    }

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

    //  ***************************************
    //  Enregistre une nouvelle mission dans la BD
    //  ***************************************
    @PostMapping(value = "store_mission.htm")
    public ModelAndView store(HttpServletRequest request) throws Exception {
        String destinationPage = "";
        try {
            MissionEntity mission = new MissionEntity();
            //mission.setJeuByNumjeu(new JeuService().getJeuById(request.getParameter("id")));
            //mission.setLibmission(request.getParameter("wording"));
            new MissionService().insert(mission);
            destinationPage = "redirect: index_mission.htm";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }

        return new ModelAndView(destinationPage);
    }

    //  ***************************************
    //  Modification d'une mission dans la BD
    //  ***************************************
    @GetMapping(value = "edit_mission.htm")
    public ModelAndView edit(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            request.setAttribute("mission", new MissionService().getMissionById(Integer.parseInt(request.getParameter("id"))));
            //request.setAttribute("jeux", new JeuService().getAll());
            destinationPage = "mission/edit";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }

        return new ModelAndView(destinationPage);
    }

    //  ***************************************
    //  Mise à jour d'une mission dans la BD
    //  ***************************************
    @PostMapping(value = "update_mission.htm")
    public ModelAndView update(HttpServletRequest request) throws Exception {
        String destinationPage = "";
        try {
            MissionService serviceMission = new MissionService();
            MissionEntity mission = serviceMission.getMissionById(Integer.parseInt(request.getParameter("id")));
            //mission.setJeuByNumjeu(new JeuService().getJeuById(request.getParameter("id")));
            //mission.setLibmission(request.getParameter("wording"));
            serviceMission.update(mission);
            destinationPage = "redirect: index_mission.htm";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }
        return new ModelAndView(destinationPage);
    }

    //  ***************************************
    //  Suppression d'une mission dans la BD
    //  ***************************************
    @GetMapping(value = "delete_mission.htm")
    public RedirectView delete(HttpServletRequest request) throws Exception {
        MissionService serviceMission = new MissionService();
        MissionEntity mission = serviceMission.getMissionById(Integer.parseInt(request.getParameter("id")));
        serviceMission.delete(mission);

        RedirectView rv = new RedirectView();
        rv.setContextRelative(true);
        rv.setUrl("/index_mission.htm");
        return rv;
    }
}

