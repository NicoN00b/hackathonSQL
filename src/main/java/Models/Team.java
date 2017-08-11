package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Team {
    private String name;
    private String description;
    private static ArrayList<Team> instances = new ArrayList<>();
    //private String member;
    private LocalDateTime createdAt;
    private int id;

    public Team (String description) {
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        instances.add(this);
        this.id = instances.size();
        //this.member =
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
