import dao.*;
import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import models.Team;
import models.Member;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {

        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/hackathon.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);


        get("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String title = request.queryParams("title");
            String description = request.queryParams("description");
            Team newTeam = new Team(title, description);
            teamDao.add(newTeam);
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);
            List<Member> members = memberDao.getAll();
            model.put("members", members);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String title = request.queryParams("title");
            String description = request.queryParams("description");
            Team newTeam = new Team(title, description);
            teamDao.add(newTeam);
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:team_id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToDelete = Integer.parseInt(request.params("team_id"));
            teamDao.deleteById(idOfTeamToDelete);
            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("editTeam", true);
            List<Team> allTeams = teamDao.getAll();
            model.put("teams", allTeams);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teams/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToEdit = Integer.parseInt(req.queryParams("editTeamId"));
            String title = req.queryParams("newTitle");
            String description = req.queryParams("newDescription");
            teamDao.update(teamDao.findById(idOfTeamToEdit).getId(), title, description);
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:team_id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamToFind = Integer.parseInt(request.params("team_id"));
            List<Team> teams = teamDao.getAll();
            model.put("teams", teams);
            Team foundTeam = teamDao.findById(idOfTeamToFind);
            model.put("team", foundTeam);
            List<Member> allMembersByTeam = teamDao.getAllMembersByTeam(idOfTeamToFind);
            model.put("members", allMembersByTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
