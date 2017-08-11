package Models;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private String title;
    private String description;
    private static ArrayList<Team> instances = new ArrayList<>();
    private LocalDateTime createdAt;
    private int id;
    private static ArrayList<Member> teamMembers;

    public Team (String title, String description) {
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        instances.add(this);
        this.id = instances.size();
        teamMembers = new ArrayList<>();
    }

    public static ArrayList<Team> getAll() {
        return instances;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public static void clearAllTeams() {
        instances.clear();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ArrayList<Team> getInstances() {
        return instances;
    }

    public static void setInstances(ArrayList<Team> instances) {
        Team.instances = instances;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Team findById(int id) {

        return instances.get(id-1);
    }

    public void update(String description) {

        this.description = description;
    }

    public void deleteTeam() {
        instances.remove(id - 1);
        for (Team thisTeam : instances) {
            thisTeam.id = instances.indexOf(thisTeam) + 1;
        }
    }

    public static ArrayList<Member> getMembers() {
        return teamMembers;
    }

    public void addMember(Member member) {
        teamMembers.add(member);
    }
}
