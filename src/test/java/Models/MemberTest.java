package Models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class MemberTest {

    private Sql2oTaskDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Member.clearAllMembers();
    }

    @Test
    public void NewMemberObjectGetsCorrectlyCreated_true() throws Exception {
        Member member = setupNewMember();
        assertEquals(true, member instanceof Member);
    }

    @Test
    public void MemberInstantiatesWithContent_true() throws Exception {
        Member member = setupNewMember();
        assertEquals("bill", member.getName());
    }

    @Test
    public void AllMemberInstantiateWithContent_true() throws Exception {
        Member member = setupNewMember();
        Member otherMember = setupNewMember();
        assertEquals("bill", member.getName());
        assertEquals("bill", otherMember.getName());
    }

    @Test
    public void MemberContainAllMember_true() {
        Member member = setupNewMember();
        Member otherMember = setupNewMember();
        assertTrue(Member.getAll().contains(member));
        assertTrue(Member.getAll().contains(otherMember));
    }

    @Test
    public void getId_MemberInstantiateWithAnID_1() throws Exception {
        Member member = setupNewMember();
        assertEquals(1, member.getId());
    }


    @Test
    public void findReturnsCorrectMember() throws Exception {
        Member member = setupNewMember();
        assertEquals(1, Member.findById(member.getId()).getId());
    }


    public Member setupNewMember(){
        return new Member("bill");
    }

    @Test
    public void findReturnsCorrectMemberWhenMoreThanOneMemberExists() throws Exception {
        Member member = setupNewMember();
        Member otherMember = new Member("tom");
        assertEquals(2, Member.findById(otherMember.getId()).getId());
    }

    @Test
    public void updateChangesMemberName() throws Exception {
        Member member = setupNewMember();
        String formerName = member.getName();
        int formerId = member.getId();

        member.update("cody");

        assertEquals(formerId, member.getId());
        assertNotEquals(formerName, member.getName());
    }

    @Test
    public void DeletesASpecificMember() throws Exception {
        Member member = setupNewMember();
        Member otherMember = new Member("cody");
        member.deleteMember();
        assertEquals(1, Member.getAll().size());
        assertEquals(Member.getAll().get(0).getId(), 1);
    }

    @Test
    public void deleteAllMembersDeletesAllMembers() throws Exception {
        Member member = setupNewMember();
        Member otherMember = setupNewMember();
        Member.clearAllMembers();
        assertEquals(0, Member.getAll().size());
    }
}