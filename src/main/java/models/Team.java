package models;

import java.util.ArrayList;

public class Team {
    private String title;
    private String description;
    private int id;

    public Team (String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }



    public String getDescription() {
        return description;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        if (title != null ? !title.equals(team.title) : team.title != null) return false;
        return description != null ? description.equals(team.description) : team.description == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
