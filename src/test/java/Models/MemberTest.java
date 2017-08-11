package Models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {
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
    public void AllMembersInstantiateWithContent_true() throws Exception {
        Member member = setupNewMember();
        Member otherMember = setupNewMember();
        assertEquals("bill", member.getName());
        assertEquals("bill", otherMember.getName());
    }

    @Test
    public void MembersContainAllMembers_true() {
        Member member = setupNewMember();
        Member otherMember = setupNewMember();
        assertTrue(Member.getAll().contains(member));
        assertTrue(Member.getAll().contains(otherMember));
    }


    public Member setupNewMember(){
        return new Member("bill");
    }

}