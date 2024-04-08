import java.util.ArrayList;

public class Game
{
    private ArrayList<Team> teams;
    private ArrayList<Team> results;
    public Integer term;
    public static boolean updated = false;
    public Game(Integer term)
    {
        this.teams = new ArrayList<>();
        this.results = new ArrayList<>();
        this.term = term;
    }
    public void addTeam(Team team) {
        teams.add(team);
        System.out.println("Team " + team.getName() + " has been added at the Game " + term + " position " + teams.size());
    }
    public ArrayList<Team> getTeams() {
        return teams;
    }
    public Record play(int round) {
        Record record;
        results = new ArrayList<>();
        if (teams.getFirst().getPlayers().averageOfCredit() > teams.getLast().getPlayers().averageOfCredit() ){
            results.add(teams.getFirst());
            results.add(teams.getLast());
            record = new Record(teams.getFirst().getName(), teams.getLast().getName(), term, round);
        } else {
            results.add(teams.getLast());
            results.add(teams.getFirst());
            record = new Record(teams.getLast().getName(), teams.getFirst().getName(), term, round);
        }
        updateStat();
        return record;
    }

    private void updateStat() {
        Team teamWin = results.getFirst();
        Team teamLose = results.getLast();
        float different = teamWin.getPlayers().averageOfCredit()
                - teamLose.getPlayers().averageOfCredit();
        for (int i = 0; i < Players.allPlayers.size(); i++){
            Player player = Players.allPlayers.get(i);
            if (inTeam(player, teamWin)) {
                Double newCredit = player.getCredit() + different / 5;
                Players.allPlayers.set(i, new Player(player.getName(), newCredit, player.getAge(), player.getTeam(), player.getNo()));
            } else if (inTeam(player, teamLose)) {
                Double newCredit = player.getCredit() - different / 5;
                Players.allPlayers.set(i, new Player(player.getName(), newCredit, player.getAge(), player.getTeam(), player.getNo()));
            }
        }
        for (Team team: Teams.getTeams()){
            team.setTeamPlayer();
        }
        updated = true;
    }

    private boolean inTeam(Player matchedplayer, Team teamWin) {
        return matchedplayer.getTeam().equals(teamWin.getName());
    }
}




