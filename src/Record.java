public class Record
{
    private String WinTeam;
    private String LoseTeam;
    private Integer GameNo;
    private Integer Round;

    public Record(String winTeam, String loseTeam, Integer gameNo, Integer round) {
        WinTeam = winTeam;
        LoseTeam = loseTeam;
        GameNo = gameNo;
        Round = round;
    }

    public String getWinTeam() {
        return WinTeam;
    }

    public String getLoseTeam() {
        return LoseTeam;
    }

    public Integer getGameNo() {
        return GameNo;
    }

    public Integer getRound() {
        return Round;
    }
}
