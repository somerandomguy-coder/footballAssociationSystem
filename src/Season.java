import java.util.ArrayList;
import java.util.Objects;

public class Season {
    private ArrayList<Game> schedule;
    private ArrayList<Team> currentTeamList;
    private Integer round;
    private ArrayList<Record>  records;
    private ArrayList<Team> allTimeTeamList;
    public Season()
    {
        this.schedule = new ArrayList<>();
        this.currentTeamList = (ArrayList<Team>) Teams.getTeams().clone();
        this.allTimeTeamList = new ArrayList<>();
        this.round = 1;
        this.records = new ArrayList<>();
    }
    public void use(){
        char choice = getChar();
        while(choice != 'R'){
            switch (choice){
                case '1':
                    addTeamToRound();
                    break;
                case '2':
                    printCurrentRound();
                    break;
                case '3':
                    play();
                    break;
                case '4':
                    printResult();
                    break;
            }
            choice = getChar();
        }
    }

    private void printCurrentRound() {
        Utils.GameHeader();
        for (Game game : schedule){
            System.out.printf(Utils.GamesFormat, game.getTeams().getFirst().getName(), "vs" ,game
                    .getTeams().getLast().getName());
        }
        Utils.GameEnd();
    }

    private void printResult() {
        Utils.RecordHeader();
        for (Record record : records){
            System.out.printf(Utils.RecordFormat, record.getRound(), record.getGameNo(), record.getWinTeam(), record.getLoseTeam());
        }
        Utils.RecordEnd();
    }

    private void addTeamToRound() {
        int term = 1;
        Game game = new Game(term);
        while(!currentTeamList.isEmpty()){
            String teamName = getTeamName();
            int matchedTeam = matchedTeam(teamName);
            while (matchedTeam == -1){
                System.out.println("No such team! Please try again");
                teamName = getTeamName();
                matchedTeam = matchedTeam(teamName);
            }
            game.addTeam(currentTeamList.get(matchedTeam));
            currentTeamList.remove(matchedTeam);
            allTimeTeamList = Teams.getTeams();
            if (game.getTeams().size()==2){
                schedule.add(game);
                game = new Game(++term);
            }
        }
    }

    private int matchedTeam(String teamName){
        for (int i = 0; i<currentTeamList.size(); i++){
            if (currentTeamList.get(i).getName().equals(teamName)){
                return i;
            }
        }
        return -1;
    }

    private int matchedWithAllTeam(String teamName) {
            for (int i = 0; i<allTimeTeamList.size(); i++){
                if (allTimeTeamList.get(i).getName().equals(teamName)){
                    return i;
                }
            }
            return -1;
    }

    private String getTeamName() {
        printExistingTeam();
        System.out.println("Please enter the team's name that you want to schedule:");
        return In.nextLine();
    }

    private void printExistingTeam() {
        System.out.println("The existing teams are as follows:");
        for (int i = 0; i<currentTeamList.size(); i++){
            if (i == currentTeamList.size()-1){
                System.out.println(currentTeamList.get(i).getName());
                break;
            }
            System.out.print(currentTeamList.get(i).getName() + " ");
        }
    }

    private void play() {
        if (schedule.isEmpty()){
            System.out.println("No game in the current round, please add teams to the round first!");
        } else {
            for (Game game: schedule){
                Record record = game.play(round);
                records.add(record);
            }
            System.out.println("All games finished! You can use 4 to check the results.");
        }
        round++;
        addWinToList();
        boolean win = checkFinalWin();
        if (win){
            System.out.println("This season ends!\n" + currentTeamList.getFirst().getName() + "is the Champion!!");
        }
        schedule = new ArrayList<>();
    }

    private boolean checkFinalWin() {
        return currentTeamList.size() == 1;
    }

    private void addWinToList() {
        for (Record record : records){

            if (Objects.equals(record.getRound(), round-1)) {
                String winTeam = record.getWinTeam();
                int matchedTeam = matchedWithAllTeam(winTeam);
                Team team = allTimeTeamList.get(matchedTeam);
                currentTeamList.add(team);
            }
        }
    }



    private char getChar() {
        help();
        System.out.print("Enter a choice: ");
        return In.nextChar();
    }
    private void help(){
        System.out.println("Welcome to the season page! Please make a selection from the menu:");
        System.out.println("1. Add a team to the round.");
        System.out.println("2. Display the current round.");
        System.out.println("3. Play games.");
        System.out.println("4. Display the game result records.");
        System.out.println("R. Return to previous menu.");
    }

}
