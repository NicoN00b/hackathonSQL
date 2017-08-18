package dao;

import models.Team;
import models.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class Sql2oTeamDaoTest {

    private Sql2oTeamDao teamDao;
    private Sql2oMemberDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        teamDao = new Sql2oTeamDao(sql2o);
        memberDao = new Sql2oMemberDao(sql2o);

        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingTeamSetsId() throws Exception {
        Team team = setupNewTeam();
        int originalTeamId = team.getId();
        teamDao.add(team);
        assertNotEquals(originalTeamId, team.getId());
    }

    @Test
    public void existingTeamsCanBeFoundById() throws Exception {
        Team team = setupNewTeam();
        teamDao.add(team);
        Team foundTeam = teamDao.findById(team.getId());
        assertEquals(team, foundTeam);
    }

    @Test
    public void addedTeamsAreReturnedFromGetAll() throws Exception {
        Team team = setupNewTeam();
        teamDao.add(team);
        assertEquals(1, teamDao.getAll().size());
    }

    @Test
    public void noTeamsReturnsEmptyList() throws Exception {
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void updateChangesTeamContent() throws Exception {
        String initialDescription = "We're all about little robots that do big things";
        Team team = new Team ("Raspberry Pi RoboHacks", initialDescription);
        teamDao.add(team);

        teamDao.update(team.getId(),"Raspberry Pi RoboHacks", "microbots to take over the world HAHAHAH!");
        Team updatedTeam = teamDao.findById(team.getId());
        assertNotEquals(initialDescription, updatedTeam.getDescription());
    }

    @Test
    public void deleteByIdDeletesCorrectTeam() throws Exception {
        Team team = setupNewTeam();
        teamDao.add(team);
        teamDao.deleteById(team.getId());
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Team team = setupNewTeam();
        Team otherTeam = new Team("Cleaning your Code", "Learn how to make it so DRY it pops!");
        teamDao.add(team);
        teamDao.add(otherTeam);
        int daoSize = teamDao.getAll().size();
        teamDao.clearAllTeams();
        assertTrue(daoSize > 0 && daoSize > memberDao.getAll().size());
    }

    @Test
    public void getAllMembersByTeamReturnsMembersCorrectly() throws Exception {
        Team team = setupNewTeam();
        teamDao.add(team);
        int teamId = team.getId();
        Member newMember = new Member("bill", "bill@yahoo.com", teamId);
        Member otherMember = new Member("cody", "cody@gmail.com", teamId);
        Member thirdMember = new Member("annie", "annie@getyourgun.com", teamId);
        memberDao.add(newMember);
        memberDao.add(otherMember);

        assertTrue(teamDao.getAllMembersByTeam(teamId).size() == 2);
        assertTrue(teamDao.getAllMembersByTeam(teamId).contains(newMember));
        assertTrue(teamDao.getAllMembersByTeam(teamId).contains(otherMember));
        assertFalse(teamDao.getAllMembersByTeam(teamId).contains(thirdMember));
    }

    public Team setupNewTeam(){
        return new Team("Raspberry Pi RoboHacks", "We're all about little robots that do big things");
    }
}