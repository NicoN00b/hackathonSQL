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
        Member Member = setupNewMember();
        assertEquals("bill", Member.getName());
    }

    public Member setupNewMember(){
        return new Member("bill");
    }

}