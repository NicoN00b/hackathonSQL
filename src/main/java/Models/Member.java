package Models;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private String contact;
    private static List<Member> members = new ArrayList<>();
    private int id;

    public Member (String name) {
        this.name  = name;
        this.contact = contact;
        members.add(this);
        id = members.size();
    }

    public static void clearAllMembers () {
        members.clear();
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

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getId() {
        return id;
    }


}
