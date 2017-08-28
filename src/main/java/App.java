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

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String title = request.queryParams("title");
            String description = request.queryParams("description");
            Team newTeam = new Team(title, description);
            teamDao.add(newTeam);
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            teamDao.clearAllTeams();
            memberDao.clearAllMembers();
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


        get("/teams/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeam = Integer.parseInt(request.params("id"));
            List<Member> membersByTeam = teamDao.getAllMembersByTeam(idOfTeam);
            model.put("members", membersByTeam);
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            Team thisTeam = teamDao.findById(idOfTeam);
            model.put("thisTeam", thisTeam);
            return new ModelAndView(model, "team-detail.hbs");
        }, new HandlebarsTemplateEngine());


        get("/teams/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeam = Integer.parseInt(request.params("id"));
            Team editTeam = teamDao.findById(idOfTeam);
            model.put("editTeam", editTeam);
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model, "team-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/teams/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeam = Integer.parseInt(request.params("id"));
            String newTitle = request.queryParams("title");
            String newDescription = request.queryParams("description");
            teamDao.update(idOfTeam, newTitle, newDescription);
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/members/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/members/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String contact = request.queryParams("contact");
            int teamId = Integer.parseInt(request.queryParams("teamId"));
            Member member = new Member(name, contact, teamId);
            memberDao.add(member);
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/teams/:teamId/members/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeamMember = Integer.parseInt(request.params("id"));
            Member member = memberDao.findById(idOfTeamMember);
            int idOfTeam = Integer.parseInt(request.params("teamId"));
            Team team = teamDao.findById(idOfTeam);
            List<Team> teamList = teamDao.getAll();
            model.put("team", team);
            model.put("member", member);
            model.put("teams", teamList);
            return new ModelAndView(model, "member-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:teamId/members/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Member editMember = memberDao.findById(Integer.parseInt(request.params("id")));
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            model.put("editMember", editMember);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());


        post("/teams/:teamId/members/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String contact = request.queryParams("contact");
            int teamId = Integer.parseInt(request.queryParams("teamId"));
            int id = Integer.parseInt(request.params("id"));
            memberDao.update(id, name, contact, teamId);
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        get("/teams/:teamId/members/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            memberDao.deleteById(Integer.parseInt(request.params("id")));
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/members/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            memberDao.clearAllMembersByTeam();
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/teams/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params("id"));
            teamDao.deleteById(id);
            List<Team> teamList = teamDao.getAll();
            model.put("teams", teamList);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


    }


}
