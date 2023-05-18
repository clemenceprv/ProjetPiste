package com.epul.permispiste.controller;

import com.epul.permispiste.domains.ActionEntity;
import com.epul.permispiste.service.ActionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;


@RequestMapping("/action")
@RestController
@CrossOrigin
public class ControllerAction {
    @GetMapping(value = "index_action.htm")
    public ModelAndView index(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            request.setAttribute("actions", new ActionService().getAll());
            destinationPage = "action/index";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }
        return new ModelAndView(destinationPage);
    }

    @GetMapping(value = "create_action.htm")
    public ModelAndView create(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            request.setAttribute("actions", new ActionService().getAll());
            destinationPage = "action/create";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }

        return new ModelAndView(destinationPage);
    }

    @PostMapping(value = "store_action.htm")
    public ModelAndView store(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            ActionService serviceAction = new ActionService();

            ActionEntity require_action = null;
            if (!request.getParameter("actNumaction").equals(""))
                require_action = serviceAction.getActionById(request.getParameter("actNumaction"));

            ActionEntity action = new ActionEntity();
            action.setActionWording(request.getParameter("libaction"));
            if (!request.getParameter("scoremin").equals(""))
                action.setScoreMinimum(Integer.parseInt(request.getParameter("scoremin")));

            new ActionService().insert(action);
            destinationPage = "redirect: index_action.htm";

        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }

        return new ModelAndView(destinationPage);
    }

    @GetMapping(value = "edit_action.htm")
    public ModelAndView edit(HttpServletRequest request) throws Exception {
        String destinationPage;
        try {
            ActionService serviceAction = new ActionService();
            request.setAttribute("action", serviceAction.getActionById(request.getParameter("id")));
            request.setAttribute("actions", serviceAction.getAll());
            destinationPage = "action/edit";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }

        return new ModelAndView(destinationPage);
    }

    @PostMapping(value = "update_action.htm")
    public ModelAndView update(HttpServletRequest request) throws Exception {
        String destinationPage = "";
        try {
            ActionService serviceAction = new ActionService();
            ActionEntity action = serviceAction.getActionById(request.getParameter("id"));

            ActionEntity require_action = null;
            if (!request.getParameter("actNumaction").equals(""))
                require_action = serviceAction.getActionById(request.getParameter("actNumaction"));
            action.setActionWording(request.getParameter("libaction"));

            if (!request.getParameter("scoremin").equals(""))
                action.setScoreMinimum(Integer.parseInt(request.getParameter("scoremin")));

            serviceAction.update(action);

            destinationPage = "redirect: index_action.htm";
        } catch (Exception e) {
            request.setAttribute("errors", e.getMessage());
            destinationPage = "layouts/error";
        }

        return new ModelAndView(destinationPage);
    }

    @GetMapping(value = "delete_action.htm")
    public RedirectView delete(HttpServletRequest request) throws Exception {
        ActionService serviceAction = new ActionService();
        ActionEntity action = serviceAction.getActionById(request.getParameter("id"));
        serviceAction.delete(action);

        RedirectView rv = new RedirectView();
        rv.setContextRelative(true);
        rv.setUrl("/index_action.htm");
        return rv;
    }
}

