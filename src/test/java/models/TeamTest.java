//package models;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.time.LocalDateTime;
//
//import static org.junit.Assert.*;
//
//public class TeamTest {
//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        Team.clearAllTeams();
//    }
//
//    @Test
//    public void NewTeamObjectGetsCorrectlyCreated_true() throws Exception {
//        Team team = new Team("a", "team");
//        assertEquals(true, team instanceof Team);
//    }
//
//    @Test
//    public void TeamInstantiatesWithTitle_true() throws Exception {
//        Team team = new Team("cryptocurrency", "money");
//        assertEquals("cryptocurrency", team.getTitle());
//    }
//
//    @Test
//    public void AllTeamsAreCorrectlyReturned_true() {
//        Team team = new Team("crypto", "money");
//        Team otherTeam = new Team ("raspberry pi", "little");
//        assertEquals(2, Team.getAll().size());
//    }
//
//    @Test
//    public void AllTeamsContainsAllTeams_true() {
//        Team team = new Team("crypto", "money");
//        Team otherTeam = new Team ("raspberry pi", "little");
//        assertTrue(Team.getAll().contains(team));
//        assertTrue(Team.getAll().contains(otherTeam));
//    }
//
//    @Test
//    public void getCreatedAt_instantiatesWithCurrentTime_today() throws Exception {
//        Team myTeam = setupNewTeam();
//        assertEquals(LocalDateTime.now().getDayOfWeek(), myTeam.getCreatedAt().getDayOfWeek());
//    }
//
//    @Test
//    public void getId_TeamsInstantiateWithAnID_1() throws Exception{
//        Team.clearAllTeams();
//        Team myTeam = new Team("crypto", "money");
//        assertEquals(1, myTeam.getId());
//    }
//
//    @Test
//    public void findReturnsCorrectTeam() throws Exception {
//        Team team = setupNewTeam();
//        assertEquals(1, Team.findById(team.getId()).getId());
//    }
//
//    @Test
//    public void findReturnsCorrectTeamWhenMoreThanOneTeamExists() throws Exception {
//        Team team = setupNewTeam();
//        Team otherTeam = new Team("raspberry pi", "little");
//        assertEquals(2, Team.findById(otherTeam.getId()).getId());
//    }
//
//    @Test
//    public void updateChangesTeamDescription() throws Exception {
//        Team team = setupNewTeam();
//        String formerDescription = team.getDescription();
//        LocalDateTime formerDate = team.getCreatedAt();
//        int formerId = team.getId();
//
//        team.update("raspberry pi", "little");
//
//        assertEquals(formerId, team.getId());
//        assertEquals(formerDate, team.getCreatedAt());
//        assertNotEquals(formerDescription, team.getDescription());
//    }
//
//    @Test
//    public void deleteDeletesASpecificTeam() throws Exception {
//        Team team = setupNewTeam();
//        Team otherTeam = new Team("raspberry pi", "little");
//        team.deleteTeam();
//        assertEquals(1, Team.getAll().size());
//        assertEquals(Team.getAll().get(0).getId(), 1);
//    }
//
//    @Test
//    public void deleteAllTeamsDeletesAllTeams() throws Exception {
//        Team team = setupNewTeam();
//        Team otherTeam = setupNewTeam();
//        Team.clearAllTeams();
//        assertEquals(0, Team.getAll().size());
//    }
//
////    @Test
////    public void addMembertoTeam_true() {
////        Team team = new Team ("a-team", "van");
////        MemberDao member = new MemberDao ("Cody");
////        team.addMember(member);
////        assertTrue(team.getMembers().contains(member));
////    }
//
////    @Test
////    public void addMemberstoTeam_ReturnCorrectMembers() {
////        Team team = new Team ("a-team");
////        MemberDao member = new MemberDao ("Cody");
////        MemberDao otherMember = new MemberDao ("Cody Two");
////        team.addMember(member);
////        team.addMember(otherMember);
////        assertEquals("Cody", team.getMem());
////    }
//
//    public Team setupNewTeam(){
//        return new Team("crypto", "money");
//    }
//}