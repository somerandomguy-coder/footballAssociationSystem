public class Team {
    private final String name;
    private Players players;

    public Team(String name)
    {
        this.name = name;
        setTeamPlayer(true);
    }
    private void setTeamPlayer() {
        players = new Players(true);
        for (Player player : Players.allPlayers){
            if (player.getTeam().equals(name)){
                players.addPlayer(player);
            }
        }
    }
    private void setTeamPlayer(boolean firstTime) {
        players = new Players();
        for (Player player : Players.allPlayers){
            if (player.getTeam().equals(name)){
                players.addPlayer(player);
            }
        }
    }
    public Players getPlayers() {
        return players;
    }
    public String getName() {
        return name;
    }
    public void use() {
        char choice = getChar();
        while (choice != 'R') {
            switch (choice) {
                case '1':
                    printTeamPlayer();
                    break;
                case '2':
                    String playerName = addNewPlayer();
                    System.out.println("Player "+ playerName +" added!");
                    break;
                case '3':
                    updatePlayer();
                    break;
                case '4':
                    String delete = deletePlayer();
                    break;
            }
            choice = getChar();
        }
    }
    private void printTeamPlayer(){
        Utils.playerHeader();
        for (Player player : players.getPlayers()){
            System.out.printf(Utils.PlayerFormat, player.getName(), player.getCredit(), player.getLevel(),
                    player.getNo(), player.getAge());
        }
        Utils.playerTableEnd();
    }
    private String addNewPlayer(){
        String playerName = getInputName();
        double credit = getInputCredit();
        int age = getInputAge();
        int No = getInputNo();
        Player duplicatedPlayer = DuplicateWith(No);
        while (duplicatedPlayer != null){
            System.out.print("This No has been occupied by: "+ duplicatedPlayer.getName() +". Please re-enter the No: ");
            No = In.nextInt();
            duplicatedPlayer = DuplicateWith(No);
        }
        Player player = new Player(playerName, credit, age, name, No);
        Players.allPlayers.add(player);
        setTeamPlayer();
        return playerName;
    }
    private Player DuplicateWith(int No){
        for (Player player : players.getPlayers()) {
            if (player.getNo() == No){ return player;}
        }
        return null;
    }
    private void updatePlayer() {
        String delete = deletePlayer();
        if (delete.equals("success")) {
            addNewPlayer();
            System.out.println("Player information updated.");
        }
    }
    private String deletePlayer(){
        String playerName = getInputName();
        Player matchedPlayer = matchName(playerName);
        if (matchedPlayer != null) {
            Players.removeFromAllPlayers(matchedPlayer);
            setTeamPlayer();
            System.out.println("Player deleted.");
            return "success";
        } else {
            System.out.println("Player does not exist.");
            return "failed";
        }
    }
    private Player matchName(String playerName) {
        for (Player player : players.getPlayers()){
            if (player.getName().equals(playerName)){
                return player;
            }
        }
        return null;
    }
    private String getInputName(){
        System.out.print("Please enter the player's name: ");
        return In.nextLine();
    }
    private double getInputCredit(){
        System.out.print("Please enter the player's credit: ");
        return In.nextDouble();
    }
    private int getInputAge(){
        System.out.print("Please enter the player's age: ");
        return In.nextInt();
    }
    private int getInputNo(){
        System.out.print("Please enter the player's No: ");
        return In.nextInt();
    }
    private char getChar() {
        help();
        System.out.print("Enter a choice: ");
        return In.nextChar();
    }
    private void help(){
        System.out.println("Welcome to the "+ name +" 's Page! Please make a selection from the menu:");
        System.out.println("1. Display team's players.");
        System.out.println("2. Add a new player.");
        System.out.println("3. Update an existing player.");
        System.out.println("4. Delete an existing player.");
        System.out.println("R. Return to previous menu.");
    }
}
