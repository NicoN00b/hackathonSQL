package dao;

import models.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.*;

public class Sql2oMemberDaoTest {

    private Sql2oMemberDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingMemberSetsId() throws Exception {
        Member member = setupNewMember();
        int originalMemberId = member.getId();
        memberDao.add(member);
        assertNotEquals(originalMemberId, member.getId()); //how does this work?
    }

    @Test
    public void existingMembersCanBeFoundById() throws Exception {
        Member member = setupNewMember();
        memberDao.add(member);
        Member foundMember = memberDao.findById(member.getId());
        assertEquals(member, foundMember);
    }

    @Test
    public void addedMembersAreReturnedFromGetAll() throws Exception {
        Member member = setupNewMember();
        memberDao.add(member);
        assertEquals(1, memberDao.getAll().size());
    }

    @Test
    public void noMembersReturnsEmptyList() throws Exception {
        assertEquals(0, memberDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Member member = setupNewMember();
        Member otherMember = new Member("cody", "cd@gmail.com", 1);
        memberDao.add(member);
        memberDao.add(otherMember);
        int daoSize = memberDao.getAll().size();
        memberDao.clearAllMembers();
        assertTrue(daoSize > 0 && daoSize > memberDao.getAll().size());
    }

    @Test
    public void teamIdIsReturnedCorrectly() throws Exception {
        Member member = setupNewMember();
        int originalTeamId = member.getTeamId();
        memberDao.add(member);
        assertEquals(originalTeamId, memberDao.findById(member.getId()).getTeamId());
    }

    public Member setupNewMember(){
        return new Member("bill", "bill@yahoo.com", 1);
    }

}