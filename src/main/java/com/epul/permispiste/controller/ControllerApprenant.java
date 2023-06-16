package com.epul.permispiste.controller;


import com.epul.permispiste.domains.ApprenantEntity;
import com.epul.permispiste.service.ApprenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping("/apprenant")
@RestController
@CrossOrigin
public class ControllerApprenant {

    @Autowired
    private ApprenantService apprenantService;

    private HttpSession session;

    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            request.setAttribute("apprenant", apprenantService.getAll());
            destinationPage = "/vues/apprenant/afficherApprenants";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }
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
                destinationPage = "/vues/apprenant/ajouterApprenant";
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
                ApprenantEntity apprenantEntity = new ApprenantEntity();
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                apprenantEntity.setNomApprenant(nom);
                apprenantEntity.setPrenomApprenant(prenom);
                apprenantService.addApprenant(apprenantEntity);
                request.setAttribute("apprenants", apprenantService.getAll());
                destinationPage = "/vues/apprenant/afficherApprenants";
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
                destinationPage = "vues/apprenant/login";
            }
            else
            {
                ApprenantEntity apprenantEntity = apprenantService.getApprenantById(id);
                request.setAttribute("apprenant", apprenantEntity);
                destinationPage = "/vues/apprenant/editApprenants";
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
                ApprenantEntity apprenantEntity = new ApprenantEntity();
                apprenantEntity.setId(id);
                apprenantService.editApprenant(apprenantEntity);
                request.setAttribute("actions", apprenantService.getAll());
                destinationPage = "/vues/apprenant/afficherApprenants";
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
                apprenantService.delete(id);
                request.setAttribute("apprenants", apprenantService.getAll());
                destinationPage = "/vues/apprenant/afficherApprenants";
            }
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }

        // Redirection vers la page jsp appropriee
        return new ModelAndView(destinationPage);
    }
}
