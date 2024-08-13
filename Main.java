public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

class Game {
    private List<Player> players;
    private Level[] levels;
    private int currentLevel;
    private Scanner scanner;

    public Game() {
        players = new ArrayList<>();
        scanner = new Scanner(System.in);
        setupPlayers();
        levels = new Level[]{
            new Level("Introduction", new Challenge[]{
                new VariableChallenge()
            }),
            new Level("Control Structures", new Challenge[]{
                new ControlStructureChallenge()
            })
        };
        currentLevel = 0;
    }

    private void setupPlayers() {
        System.out.print("Enter the number of players: ");
        int numPlayers = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = scanner.nextLine();
            players.add(new Player(name));
        }
    }

    public void start() {
        System.out.println("Welcome to the Digital Defense Academy!");
        while (currentLevel < levels.length) {
            playLevel(levels[currentLevel]);
            currentLevel++;
        }
        System.out.println("Congratulations! All players have completed the game!");
    }

    private void playLevel(Level level) {
        System.out.println("Starting level: " + level.getName());
        System.out.println(TutorialLoader.loadTutorial(level.getName().toLowerCase() + ".txt"));
        for (Player player : players) {
            System.out.println(player.getName() + ", it's your turn.");
            for (Challenge challenge : level.getChallenges()) {
                boolean success = challenge.run(scanner);
                if (!success) {
                    System.out.println("Try again, " + player.getName() + "!");
                    return;
                }
            }
            System.out.println(player.getName() + " completed the level!");
        }
    }
}

class Player {
    private String name;
    private int experience;

    public Player(String name) {
        this.name = name;
        this.experience = 0;
    }

    public void addExperience(int amount) {
        experience += amount;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }
}

class Level {
    private String name;
    private Challenge[] challenges;

    public Level(String name, Challenge[] challenges) {
        this.name = name;
        this.challenges = challenges;
    }

    public String getName() {
        return name;
    }

    public Challenge[] getChallenges() {
        return challenges;
    }
}

abstract class Challenge {
    public abstract boolean run(Scanner scanner);
}

class VariableChallenge extends Challenge {
    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Challenge: Declare a variable of type int and assign it a value of 10.");
        System.out.print("Enter your code: ");
        String code = scanner.nextLine();
        if (code.contains("int") && code.contains("=") && code.contains("10")) {
            System.out.println("Correct!");
            return true;
        } else {
            System.out.println("Incorrect. Hint: Use 'int' to declare an integer variable.");
            return false;
        }
    }
}

class ControlStructureChallenge extends Challenge {
    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Challenge: Write an if-else statement that checks if a variable 'x' is greater than 5.");
        System.out.print("Enter your code: ");
        String code = scanner.nextLine();
        if (code.contains("if") && code.contains("x > 5") && code.contains("else")) {
            System.out.println("Correct!");
            return true;
        } else {
            System.out.println("Incorrect. Hint: Use 'if' to start the condition and 'else' for the alternative.");
            return false;
        }
    }
}

class TutorialLoader {
    public static String loadTutorial(String filename) {
        // Mock implementation since we can't read files in this environment.
        return "Tutorial content for " + filename;
    }
}

    

