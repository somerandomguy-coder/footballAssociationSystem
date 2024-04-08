public class Association
{
    private Teams teams;
    private Season season;
    public Association()
    {
        this.teams = new Teams();
        this.season = new Season();
    }
    public void use(){
        char choice = getChar();
        while(choice != 'X'){
            switch (choice){
                case '1':
                    teams.use();
                    break;
                case '2':
                    season.use();
                    break;
            }
            choice = getChar();
        }
    }

    private char getChar() {
        help();
        System.out.print("Enter a choice: ");
        return In.nextChar();
    }

    private void help(){
        System.out.println("Welcome to the Association! Please make a selection from the menu:");
        System.out.println("1. Explore the teams.");
        System.out.println("2. Arrange a new season.");
        System.out.println("X. Exit the system.");
    }

    public static void main(String[] args) {
        Association aso = new Association();
        aso.use();
    }
}
