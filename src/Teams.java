import java.util.ArrayList;
public class Teams {
    private ArrayList<Team> teams;

    public Teams()
    {
    this.teams = new ArrayList<>();
    initializeTeams();
    }

    private void initializeTeams() {
        teams.addLast(new Team("Suns"));
        teams.addLast(new Team("Bulls"));
        teams.addLast(new Team("Hawks"));
        teams.addLast(new Team("Nets"));
    }

    public void use(){
        char choice = getChar();
        while (choice != 'R') {
            switch (choice){
                case '1':
                    printAllTeam();
                    break;
                case '2':
                    Players.printAllPlayer();
                    break;
                case '3':
                    System.out.println("add..");
                    break;
                case '4':
                    String name = getInputName();
                    boolean haveName = false;
                    for (Team team : teams){
                        if (team.getName().equals(name)){
                            team.use();
                            haveName = true;
                        }
                    }
                    if (!haveName){
                        System.out.println("No team");
                    }
                    break;
                case '5':
                    System.out.println("deleted team...");
                    break;
                case '6':
                    System.out.println("level1 player1...");
                    break;
            }
            choice = getChar();
        }
    }

    private void printAllTeam(){
        Utils.teamsHeader();
        for (Team team : teams){
            System.out.printf(Utils.teamsFormat, team.getName(), team.getPlayers().numOfPlayer(), team.getPlayers().averageOfCredit(), team.getPlayers().averageOfAge());
        }
        Utils.teamTableEnd();
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
