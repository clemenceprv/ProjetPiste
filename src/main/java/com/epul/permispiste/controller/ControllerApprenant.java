package com.epul.permispiste.controller;


import com.epul.permispiste.domains.ApprenantEntity;
import com.epul.permispiste.mesExceptions.MonException;
import com.epul.permispiste.service.ApprenantService;
import com.epul.permispiste.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/apprenant")
@RestController
@CrossOrigin
public class ControllerApprenant {

    @Autowired
    private ApprenantService apprenantService;

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

    @RequestMapping(value = "/listeApprenant")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            ApprenantService serviceApprenant = new ApprenantService();
            request.setAttribute("apprenants", serviceApprenant.consulterListeApprenants());
            destinationPage = "/vues/apprenant/afficherApprenants";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }
        return new ModelAndView(destinationPage);
    }

    @GetMapping(value = "searchlisteApprenant.htm")
    public ModelAndView search(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            request.setAttribute("apprenants", new ApprenantService().searchListeApprenants(request.getParameter("search")));
            destinationPage = "/vues/apprenant/index";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "ajouterApprenant.htm")
    public ModelAndView ajouterApprenant(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            destinationPage = "apprenant/ajouterApprenant";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "layouts/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "inserterApprenant.htm")
    public ModelAndView inserterApprenant(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            ApprenantService serviceApprenant = new ApprenantService();
            ApprenantEntity apprenant = new ApprenantEntity();
            apprenant.setNomApprenant(request.getParameter("surname"));
            apprenant.setPrenomApprenant(request.getParameter("forname"));

            serviceApprenant.insertApprenant(apprenant);
            destinationPage = "redirect: listeApprenant.htm";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "layouts/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "supprimerApprenant.htm")
    public ModelAndView supprimerApprenant(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            ApprenantService serviceApprenant = new ApprenantService();
            ApprenantEntity apprenant = serviceApprenant.adherentById(Integer.parseInt(request.getParameter("id")));
            serviceApprenant.deleteApprenant(apprenant);
            destinationPage = "redirect: listeApprenant.htm";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "layouts/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "pageModifierApprenant.htm")
    public ModelAndView pageModifierApprenant(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            ApprenantService serviceApprenant = new ApprenantService();
            request.setAttribute("apprenant", serviceApprenant.adherentById(Integer.parseInt(request.getParameter("id"))));
            destinationPage = "apprenant/modifierApprenant";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "layouts/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "modifierApprenant.htm")
    public ModelAndView modifierApprenant(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            ApprenantService serviceApprenant = new ApprenantService();
            ApprenantEntity apprenant = serviceApprenant.adherentById(Integer.parseInt(request.getParameter("id")));
            apprenant.setNomApprenant(request.getParameter("surname"));
            apprenant.setPrenomApprenant(request.getParameter("forname"));
            serviceApprenant.update(apprenant);

            destinationPage = "redirect: listeApprenant.htm";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "layouts/Erreur";
        }
        return new ModelAndView(destinationPage);
    }
}
