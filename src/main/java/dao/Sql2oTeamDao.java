package dao;

import models.Member;
import models.Team;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.description;
import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.title;

public class Sql2oTeamDao implements TeamDao {

    private final Sql2o sql2o;

    public Sql2oTeamDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    public void add(Team team) {
        String sql = "INSERT INTO teams (title, description) VALUES (:title, :description)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .addParameter("title", team.getTitle())
                    .addParameter("description", team.getDescription())
                    .addColumnMapping("TITLE", "title")
                    .addColumnMapping("DESCRIPTION", "DESCRIPTION")
                    .bind(team)
                    .executeUpdate()
                    .getKey();
            team.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public List<Team> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM teams")
                    .executeAndFetch(Team.class);
        }
    }

    public Team findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM teams WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Team.class);
        }
    }

    public void update(int id, String title, String description){
        String sql = "UPDATE teams SET (title, description) = (:title, :description) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("title", title)
                    .addParameter("description", description)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM teams WHERE id=:id"; //raw sql
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public void clearAllTeams() {
        String sql = "DELETE FROM teams"; //raw sql
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public List<Member> getAllMembersByTeam(int teamId) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM members WHERE teamId = :teamId")
                    .addParameter("teamId", teamId)
                    .executeAndFetch(Member.class);
        }
    }
}