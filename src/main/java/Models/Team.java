package Models;

import java.util.ArrayList;

public class Team {
    private String name;
    private String description;
    private static ArrayList<Team> instances = new ArrayList<>();
    //private String member;
    private int id;

    public Team () {
        this.name = name;
        this.description = description;
        instances.add(this);
        this.id = instances.size();
        //this.member =
    }
}
