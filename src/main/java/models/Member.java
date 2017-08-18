package models;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private String contact;
    private boolean completed;
    private int id;
    private int teamId;

    public Member (String name, String contact) {
        this.name  = name;
        this.contact = contact;
        this.completed = false;
        this.teamId = teamId;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

//    public void setContact(String contact) {
//        this.contact = contact;
//    }

   public void update(String name) {

        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }


    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (completed != member.completed) return false;
        if (id != member.id) return false;
        if (teamId != member.teamId) return false;
        if (!name.equals(member.name)) return false;
        return contact.equals(member.contact);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + contact.hashCode();
        result = 31 * result + (completed ? 1 : 0);
        result = 31 * result + id;
        result = 31 * result + teamId;
        return result;
    }
}
