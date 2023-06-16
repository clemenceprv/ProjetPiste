package com.epul.permispiste.controller;
import com.epul.permispiste.domains.MissionEntity;
import com.epul.permispiste.service.ActionService;
import com.epul.permispiste.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;


@RequestMapping("/mission")
@RestController
@CrossOrigin
public class ControllerMission {

    @Autowired
    private MissionService missionService;

    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            request.setAttribute("missions", missionService.getAll());
            destinationPage = "/vues/mission/afficherMissions";
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
            destinationPage = "/vues/mission/ajouterMissions";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }

        return new ModelAndView(destinationPage);
    }

    @GetMapping(value = "edit_mission.htm")
    public ModelAndView edit(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            request.setAttribute("mission", new MissionService().getMissionById(Integer.parseInt(request.getParameter("id"))));
            destinationPage = "/vues/mission/modifierMissions";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }

        return new ModelAndView(destinationPage);
    }

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

