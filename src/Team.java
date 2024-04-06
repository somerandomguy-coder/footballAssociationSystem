public class Team {
    private final String name;
    private Players players;
    public Team(String name)
    {
        this.name = name;
        this.players = new Players();
        for (Player player : Players.allPlayers){
            if (player.getTeam().equals(name)){
                this.players.addPlayer(player);
            }
        }
    }

    public Players getPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }
    private void printTeamPlayer(){
        Utils.playerHeader();
        for (Player player : players.getPlayers()){
            System.out.printf(Utils.PlayerFormat, player.getName(), player.getCredit(), player.getLevel(),
                    player.getNo(), player.getAge());
        }
        Utils.playerTableEnd();
    }
    public void use() {
        char choice = getChar();
        while (choice != 'R') {
            switch (choice) {
                case '1':
                    printTeamPlayer();
                    break;
                case '2':
                    addNewPlayer();
                    break;
                case '3':
                    updatePlayer();
                    break;
                case '4':
                    deletePlayer();
                    break;
            }
            choice = getChar();
        }
    }

    private void updatePlayer() {
        deletePlayer();
        addNewPlayer();
    }

    private void deletePlayer(){
        String playerName = getInputName();
        boolean removed = false;
        for (Player player : players.getPlayers()){
            if (player.getName().equals(playerName)){
                Players.allPlayers.remove(player);
                players.removePlayer(player);
                player = null;
                removed = true;
                break;
            }
        }
        if (!removed){
            System.out.println("Player does not exist.");
        }
    }
    private void addNewPlayer(){
        String playerName = getInputName();
        double credit = getInputCredit();
        int age = getInputAge();
        int No = getInputNo();
        boolean duplicated = false;
        String duplicatedName = "";
        for (Player player : players.getPlayers()) {
            if (player.getNo() == No){
                duplicatedName = player.getName();
                duplicated = true;
                break;
            }
        }
        while (duplicated){
            System.out.print("This No has been occupied by: "+ duplicatedName +". Please re-enter the No: ");
            No = In.nextInt();
            duplicated = false;
            for (Player player : players.getPlayers()) {
                if (player.getNo() == No){
                    duplicatedName = player.getName();
                    duplicated = true;
                    break;
                }
            }
        }
        Player player = new Player(playerName, credit, age, name, No);
        players.addPlayer(player);
        Players.allPlayers.add(player);
        System.out.println("Player "+ playerName +" added!");
    }

    private boolean duplicateNo(int No){
        for (Player player : players.getPlayers()){
            if (player.getNo() == No){
                return true;
            }
        }
        return false;
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
        System.out.println("Welcome to the Suns 's Page! Please make a selection from the menu:");
        System.out.println("1. Display team's players.");
        System.out.println("2. Add a new player.");
        System.out.println("3. Update an existing player.");
        System.out.println("4. Delete an existing player.");
        System.out.println("R. Return to previous menu.");
    }
}
