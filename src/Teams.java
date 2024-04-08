import java.util.ArrayList;
public class Teams {
    private static ArrayList<Team> teams;

    public Teams()
    {
    teams = new ArrayList<>();
    initializeTeams();
    }

    private void initializeTeams() {
        teams.addLast(new Team("Suns"));
        teams.addLast(new Team("Bulls"));
        teams.addLast(new Team("Hawks"));
        teams.addLast(new Team("Nets"));
    }

    public static ArrayList<Team> getTeams() {
        return teams;
    }

    public void use(){
        char choice = getChar();
        while (choice != 'R') {
            switch (choice){
                case '1':
                    printAllTeam();
                    break;
                case '2':
                    printAllPlayer();
                    break;
                case '3':
                    addNewTeam();
                    break;
                case '4':
                    manageTeam();
                    break;
                case '5':
                    deleteTeam();
                    break;
                case '6':
                    printPlayerByLevel();
                    break;
            }
            choice = getChar();
        }
    }

    private void printPlayerByLevel() {
        String level = getInputLevel();
        ArrayList<Player> lstOfPlayer = matchedLevelPlayers(level);
        while (lstOfPlayer.isEmpty()){
            System.out.print("No such level! Please re-enter the level: ");
            level = In.nextLine();
            lstOfPlayer = matchedLevelPlayers(level);
        }
        Utils.DisplayPlayerFromAllTeamsHeader();
        for (Player player : lstOfPlayer){
            System.out.printf(Utils.DisplayPlayerFromAllTeamsFormat, player.getName(), player.getCredit(),
                    player.getLevel(), player.getAge(), player.getNo(), player.getTeam());
        }
        Utils.DisplayPlayerFromAllTeamsEnd();
    }
    private ArrayList<Player> matchedLevelPlayers(String level) {
        ArrayList<Player> playerLst = new ArrayList<>();
        for (Player player : Players.allPlayers){
            if (player.getLevel().equals(level)){
                playerLst.add(player);
            }
        }
        return playerLst;
    }

    private String getInputLevel() {
        System.out.print("Please enter the player's level that you want to view: ");
        return In.nextLine();
    }

    private void deleteTeam() {
        System.out.print("Please enter the team's name that you want to delete: ");
        String teamName = In.nextLine();
        Team matchedteam = matchedTeamName(teamName);
        if (matchedteam == null){
            System.out.println("The team you want to delete does not exist!");
        } else {
            teams.remove(matchedteam);
            System.out.println("The team " + teamName + " has been deleted.");
        }
    }

    private void manageTeam() {
        String name = getInputName();
        boolean haveName = false;
        for (Team team : teams){
            if (team.getName().equals(name)){
                team.use();
                haveName = true;
            }
        }
        if (!haveName){
            System.out.println("Team does not exist!");
        }
    }

    private void addNewTeam() {
        String teamName = getTeamName();
        Team matchedteam = matchedTeamName(teamName);
        while (matchedteam!=null){
            System.out.print("Team " + matchedteam.getName() + " already exist! Please enter a new name: ");
            teamName = In.nextLine();
            matchedteam = matchedTeamName(teamName);
        }
        teams.addLast(new Team(teamName));
        System.out.println("Team " + teamName + " added!");
    }

    private Team matchedTeamName(String teamName) {
        for (Team team : teams){
            if (team.getName().equals(teamName)){
                return team;
            }
        }
        return null;
    }

    private String getTeamName() {
        System.out.print("Please enter the name of the team: ");
        return In.nextLine();
    }

    private void printAllTeam(){
        Utils.teamsHeader();
        for (Team team : teams){
            System.out.printf(Utils.teamsFormat, team.getName(), team.getPlayers().numOfPlayer(), team.getPlayers().averageOfCredit(), team.getPlayers().averageOfAge());
        }
        Utils.teamTableEnd();
    }
    private void printAllPlayer(){
        Utils.DisplayPlayerFromAllTeamsHeader();
        String teamName = "";
        for (Team team : teams) {
            teamName = team.getName();
            for (Player player : Players.allPlayers) {
                if (player.getTeam().equals(teamName)) {
                    System.out.printf(Utils.DisplayPlayerFromAllTeamsFormat, player.getName(), player.getCredit(),
                            player.getLevel(), player.getAge(), player.getNo(), player.getTeam());
                }
            }
            Utils.DisplayPlayerFromAllTeamsEnd();
        }
    }
    private String getInputName(){
        System.out.print("Please enter the team's name that you want to manage: ");
        return In.nextLine();
    }
    private char getChar() {
        help();
        System.out.print("Enter a choice: ");
        return In.nextChar();
    }
    private void help(){
        System.out.println("Welcome to the Teams Page! Please make a selection from the menu:");
        System.out.println("1. Display all teams.");
        System.out.println("2. Display all players.");
        System.out.println("3. Add a new team.");
        System.out.println("4. Manage an existing team.");
        System.out.println("5. Delete an existing team.");
        System.out.println("6. Display all players by an level.");
        System.out.println("R. Return to previous menu.");
    }

}
