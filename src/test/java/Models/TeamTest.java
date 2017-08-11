package Models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeamTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Team.clearAllTeams();
    }

    @Test
    public void NewTeamObjectGetsCorrectlyCreated_true() throws Exception {
        Team team = new Team("a");
        assertEquals(true, team instanceof Team);
    }

    @Test
    public void TeamInstantiatesWithDescription_true() throws Exception {
        Team team = new Team("cryptocurrency");
        assertEquals("cryptocurrency", team.getDescription());
    }

    @Test
    public void AllTeamsAreCorrectlyReturned_true() {
        Team team = new Team("crypto");
        Team otherTeam = new Team ("raspberry pi");
        assertEquals(2, Team.getAll().size());
    }

    @Test
    public void AllTeamsContainsAllTeams_true() {
        Team team = new Team("crypto");
        Team otherTeam = new Team ("raspberry pi");
        assertTrue(Team.getAll().contains(team));
        assertTrue(Team.getAll().contains(otherTeam));
    }

}