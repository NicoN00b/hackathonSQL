package dao;

import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oMemberDao implements MemberDao {

    private final Sql2o sql2o;

    public Sql2oMemberDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    public void add(Member member) {

        String sql = "INSERT INTO members (name, contact, teamId) VALUES (:name, :contact, :teamId)"; //raw sql
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql)
                    .addParameter("name", member.getName())
                    .addParameter("contact", member.getContact())
                    .addParameter("teamId", member.getTeamId())
                    .addColumnMapping("NAME", "name")
                    .addColumnMapping("CONTACT", "contact")
                    .addColumnMapping("TEAMID", "teamId")
                    .executeUpdate()
                    .getKey();
            member.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex); //oops we have an error!
        }
    }


    public List<Member> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM members") //raw sql
                    .executeAndFetch(Member.class); //fetch a list
        }
    }

    public Member findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM members WHERE id = :id")
                    .addParameter("id", id) //key/value pair, key must match above
                    .executeAndFetchFirst(Member.class); //fetch an individual item
        }
    }

    public void update(int id, String newName, String newContact, int newTeamId){
        String sql = "UPDATE members SET (name, contact, teamId) = (:name, :contact, :teamId) WHERE id=:id"; //raw sql
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name", newName)
                    .addParameter("contact", newContact)
                    .addParameter("teamId", newTeamId)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE from members WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    public void clearAllMembers() {
        String sql = "DELETE from members";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
