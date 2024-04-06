
public class Player {
    private final String name;
    private final Double credit;
    private final String level;
    private final Integer age;
    private final String team;
    private final Integer No;

    public Player(String name, Double credit, Integer age, String team, Integer no) {
        this.name = name;
        this.credit = credit;
        this.age = age;
        this.team = team;
        this.No = no;

        if (credit < 1000){
            this.level = "Edge";
        } else if (credit >= 1000 && credit < 1500){
            this.level = "Common";
        } else if (credit >= 1500 && credit < 2000) {
            this.level = "Core";
        } else {
            this.level = "All Star";
        }
    }


    public String getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public Double getCredit() {
        return credit;
    }

    public String getLevel() {
        return level;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getNo() {
        return No;
    }
}
