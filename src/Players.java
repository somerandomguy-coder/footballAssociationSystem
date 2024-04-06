import java.util.ArrayList;

public class Players {
    private ArrayList<Player> Players;
    public static ArrayList<Player> allPlayers;

    public Players() {
        this.Players = new ArrayList<>();
        allPlayers = initialAllPlayers();
    }

    public Players(boolean notFirstTime) {
        this.Players = new ArrayList<>();
    }

    public float averageOfCredit() {
        float sum = 0;
        for (Player player : Players) {
            sum += player.getCredit();
        }
        if (sum == 0){
            return 0;
        } else {
            return sum / Players.size();
        }
    }

    public float averageOfAge() {
        float sum = 0;
        for (Player player : Players) {
            sum += player.getAge();
        }
        if (sum == 0){
            return 0;
        } else {
            return sum / Players.size();
        }
    }

    public int numOfPlayer() {
        return Players.size();
    }

    public ArrayList<Player> getPlayers() {
        return Players;
    }

    private ArrayList<Player> initialAllPlayers() {
        ArrayList<Player> allPlayers = new ArrayList<>();
        allPlayers.add(new Player("Devin Booker", 2500.0, 21, "Suns", 1));
        allPlayers.add(new Player("Chris Paul", 1500.0, 37, "Suns", 3));
        allPlayers.add(new Player("Deandre Ayton", 2000.0, 24, "Suns", 22));
        allPlayers.add(new Player("Kevin Durant", 3000.0, 34, "Suns", 35));
        allPlayers.add(new Player("Terrence Ross", 1000.0, 32, "Suns", 8));
        allPlayers.add(new Player("Andre Drummond", 1500.0, 29, "Bulls", 3));
        allPlayers.add(new Player("Zach LaVine", 3000.0, 28, "Bulls", 8));
        allPlayers.add(new Player("Dalen Terry", 900.0, 20, "Bulls", 25));
        allPlayers.add(new Player("Terry Taylor", 1000.0, 23, "Bulls", 32));
        allPlayers.add(new Player("Carlik Jones", 800.0, 25, "Bulls", 22));
        allPlayers.add(new Player("Trae Young", 2200.0, 24, "Hawks", 11));
        allPlayers.add(new Player("John Collins", 2000.0, 25, "Hawks", 20));
        allPlayers.add(new Player("Aaron Holiday", 800.0, 26, "Hawks", 3));
        allPlayers.add(new Player("Jalen Johnson", 1000.0, 21, "Hawks", 1));
        allPlayers.add(new Player("Trent Forrest", 1200.0, 24, "Hawks", 2));
        allPlayers.add(new Player("Mikal Bridges", 2400.0, 26, "Nets", 1));
        allPlayers.add(new Player("Ben Simmons", 2000.0, 26, "Nets", 10));
        allPlayers.add(new Player("Patty Mills", 900.0, 34, "Nets", 8));
        allPlayers.add(new Player("Joe Harris", 1200.0, 31, "Nets", 12));
        allPlayers.add(new Player("Seth Curry", 1900.0, 32, "Nets", 30));
        return allPlayers;
    }

    public void addPlayer(Player player) {
        Players.add(player);
    }

    public static void removeFromAllPlayers(Player removedplayer) {
        for (int i = 0; i < allPlayers.size(); i++) {
            if (allPlayers.get(i).getNo().equals(removedplayer.getNo()) && allPlayers.get(i).getTeam().equals(removedplayer.getTeam())) {
                System.out.println("yo ima delete");
                allPlayers.remove(i);
                break;
            }
        }
    }
}

