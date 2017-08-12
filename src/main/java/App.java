import Models.Member;
import Models.Team;
//import Models.Member;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {

        staticFileLocation("/public");

        get("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/new", (request, response) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String title = request.queryParams("title");
            String description = request.queryParams("description");
            Team newTeam = new Team(title, description);
//            newTeam.addMember(String name);
            model.put("teams", newTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Team> instances = Team.getAll();
            model.put("teams", instances);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Team> instances = Team.getAll();
            model.put("teams", instances);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Team.clearAllTeams();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = Team.findById(idOfTeamToEdit);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String title = req.queryParams("title");
            String description = req.queryParams("description");
            int idOfTeamToEdit = Integer.parseInt(req.params("id"));
            Team editTeam = Team.findById(idOfTeamToEdit);
            editTeam.update(title, description);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:id/add-member", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idTeam = Integer.parseInt(req.params("id"));
            Team editTeam = Team.findById(idTeam);
            model.put("Member", editTeam);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/:id/add-member", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idTeam = Integer.parseInt(req.params("id"));
            Team editTeam = Team.findById(idTeam);
            String member = req.queryParams("member");
            editTeam.addMember(member);
            model.put("newMember", editTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Team foundTeam = Team.findById(idOfTeamToFind); //use it to find team
            model.put("teams", foundTeam); //add it to model for template to display
            return new ModelAndView(model, "team-detail.hbs"); //individual team page.
        }, new HandlebarsTemplateEngine());

    }
}
