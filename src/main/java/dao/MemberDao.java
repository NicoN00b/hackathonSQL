package dao;

import models.Member;

import java.util.List;

public interface MemberDao {


    void add (Member member);

    List<Member> getAll();

    Member findById(int id);

    void update(int id, String name, String contact, int teamId);

    void deleteById(int id);
    void clearAllMembers();

}
