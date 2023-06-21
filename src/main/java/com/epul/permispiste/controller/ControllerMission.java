package com.epul.permispiste.controller;

import com.epul.permispiste.domains.MissionEntity;
import com.epul.permispiste.domains.UtilisateurJeuEntity;
import com.epul.permispiste.service.JeuService;
import com.epul.permispiste.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RequestMapping("/mission")
@RestController
@CrossOrigin
public class ControllerMission {

    @Autowired
    private MissionService missionService;
    //  ***************************************
    //  Affichage de la liste des missions existantes dans la BD
    //  ***************************************
    @GetMapping(value = "indexMission.htm")
    public ModelAndView indexMission(HttpServletRequest request) throws Exception {
        String destinationPage;
        List<MissionEntity> missions = null;
        try {
            missions= missionService.findAll();
            request.setAttribute("missions", missions);
            destinationPage = "vues/mission/indexMission";
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


}



