package com.epul.permispiste.controller;
import com.epul.permispiste.domains.UtilisateurEntity;
import com.epul.permispiste.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping("/utilisateur")
@RestController
@CrossOrigin
public class ControllerUtilisateur {

    @Autowired
    private UtilisateurService utilisateurService;

    private HttpSession session;

    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            request.setAttribute("utilisateur", utilisateurService.getAll());
            destinationPage = "/vues/utilisateur/afficherUtilisateurs";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
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
                destinationPage = "/vues/utilisateur/ajouterApprenant";
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
                UtilisateurEntity utilisateur = (UtilisateurEntity) session.getAttribute("utilisateur");
                if (utilisateur.getRole().equals("admin")) {
                    UtilisateurEntity utiisateurEntity = new UtilisateurEntity();
                    String nomUti = request.getParameter("nomUtil");
                    String surname = request.getParameter("surname");
                    String forename = request.getParameter("forename");
                    String role = request.getParameter("role");
                    String email = request.getParameter("email");
                    String motPasse = request.getParameter("motPasse");
                    String salt = request.getParameter("salt");
                    utiisateurEntity.setNomUtil(nomUti);
                    utiisateurEntity.setSurname(surname);
                    utiisateurEntity.setForename(forename);
                    utiisateurEntity.setRole(role);
                    utiisateurEntity.setEmail(email);
                    utiisateurEntity.setRole(motPasse);
                    utiisateurEntity.setEmail(salt);
                    utilisateurService.addUtilisateur(utiisateurEntity);
                    request.setAttribute("utilisateurs", utilisateurService.getAll());
                    destinationPage = "/vues/utilisateur/afficherUtilisateurs";
                } else {
                    destinationPage = "/vues/Erreur";
                }
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
                UtilisateurEntity utilisateur = (UtilisateurEntity) session.getAttribute("utilisateur");
                if (utilisateur.getRole().equals("admin")) {
                    UtilisateurEntity utiisateurEntity = utilisateurService.getUtilisateurById(id);
                    request.setAttribute("utilisateurs", utiisateurEntity);
                    destinationPage = "/vues/utilisateur/editApprenants";
                } else {
                    destinationPage = "/vues/Erreur";
                }
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
                UtilisateurEntity utilisateur = (UtilisateurEntity) session.getAttribute("utilisateur");
                if (utilisateur.getRole().equals("admin"))
                {
                    int id = Integer.parseInt(request.getParameter("id"));
                    UtilisateurEntity utiisateurEntity = new UtilisateurEntity();
                    utiisateurEntity.setNumUtil(id);
                    utilisateurService.editUtilisateur(utiisateurEntity);
                    request.setAttribute("utilisateurs", utilisateurService.getAll());
                    destinationPage = "/vues/utilisateur/afficherUtilisateurs";
                } else {
                    destinationPage = "/vues/Erreur";
                }
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
                UtilisateurEntity utilisateur = (UtilisateurEntity) session.getAttribute("utilisateur");
                if (utilisateur.getRole().equals("admin"))
                {
                    utilisateurService.delete(id);
                    request.setAttribute("utilisateurs", utilisateurService.getAll());
                    destinationPage = "/vues/utilisateur/afficherUtilisateurs";
                } else {
                    destinationPage = "/vues/Erreur";
                }
            }
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "/vues/Erreur";
        }

        // Redirection vers la page jsp appropriee
        return new ModelAndView(destinationPage);
    }
}
