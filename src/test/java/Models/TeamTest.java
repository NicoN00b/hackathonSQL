package Models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

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

    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() throws Exception {
        Team myTeam = setupNewTeam();
        assertEquals(LocalDateTime.now().getDayOfWeek(), myTeam.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void getId_TeamsInstantiateWithAnID_1() throws Exception{
        Team.clearAllTeams();
        Team myTeam = new Team("crypto");
        assertEquals(1, myTeam.getId());
    }

    @Test
    public void findReturnsCorrectTeam() throws Exception {
        Team team = setupNewTeam();
        assertEquals(1, Team.findById(team.getId()).getId());
    }

    @Test
    public void findReturnsCorrectTeamWhenMoreThanOneTeamExists() throws Exception {
        Team team = setupNewTeam();
        Team otherTeam = new Team("raspberry pi");
        assertEquals(2, Team.findById(otherTeam.getId()).getId());
    }

    @Test
    public void updateChangesTeamDescription() throws Exception {
        Team team = setupNewTeam();
        String formerDescription = team.getDescription();
        LocalDateTime formerDate = team.getCreatedAt();
        int formerId = team.getId();

        team.update("raspberry pi");

        assertEquals(formerId, team.getId());
        assertEquals(formerDate, team.getCreatedAt());
        assertNotEquals(formerDescription, team.getDescription());
    }

    @Test
    public void deleteDeletesASpecificTeam() throws Exception {
        Team team = setupNewTeam();
        Team otherTeam = new Team("raspberry pi");
        team.deleteTeam();
        assertEquals(1, Team.getAll().size());
        assertEquals(Team.getAll().get(0).getId(), 1);
    }

    @Test
    public void deleteAllTeamsDeletesAllTeams() throws Exception {
        Team team = setupNewTeam();
        Team otherTeam = setupNewTeam();
        Team.clearAllTeams();
        assertEquals(0, Team.getAll().size());
    }

    public Team setupNewTeam(){
        return new Team("crypto");
    }
}