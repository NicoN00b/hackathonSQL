package Models;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private String contact;
    private boolean completed
    private static ArrayList<Member> members = new ArrayList<>();
    private int mId;

    public Member (String name) {
        this.name  = name;
        this.contact = contact;
        members.add(this);
        mId = members.size();
    }

    public static void clearAllMembers () {
        members.clear();
    }

    public static List<Member> getAll(){
        return members;
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
        return mId;
    }

    public static Member findById(int id) {

        return members.get(id-1);
    }

    public void update(String name) {

        this.name = name;
    }

    public void deleteMember() {
        members.remove(mId - 1);
        for (Member thisMember : members) {
            thisMember.mId = members.indexOf(thisMember) + 1;
        }
    }

}
