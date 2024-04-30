import java.util.Arrays;
import java.util.Comparator;

public abstract class ResultsOfSportsCompetition {
    protected final String sportType;
    protected final Integer resultA;
    protected final Integer resultB;

    public ResultsOfSportsCompetition(String sportType, Integer resultA, Integer resultB) {
        this.sportType = sportType;
        this.resultA = resultA;
        this.resultB = resultB;
    }

    public Integer getResultA() {
        return resultA;
    }

    public Integer getResultB() {
        return resultB;
    }

    // Abstract method to determine the winner of the game
    public abstract Character getWinnerOfGame();

    @Override
    public String toString() {
        return "Team A " + resultA + " : " + resultB + " Team B";
    }
}

class FootballWinner extends ResultsOfSportsCompetition {
    public FootballWinner(Integer resultA, Integer resultB) {
        super("Football", resultA, resultB);
    }

    @Override
    public Character getWinnerOfGame() {
        if (getResultA() > getResultB()) {
            return 'A';
        } else if (getResultA() < getResultB()) {
            return 'B';
        } else {
            return '-'; // Draw
        }
    }
}

class TennisWinner extends ResultsOfSportsCompetition {
    public TennisWinner(Integer resultA, Integer resultB) {
        super("Tennis", resultA, resultB);
    }

    @Override
    public Character getWinnerOfGame() {
        if (getResultA() > getResultB()) {
            return 'A';
        } else if (getResultA() < getResultB()) {
            return 'B';
        } else {
            return '-'; // Draw
        }
    }
}

class Main {
    public static void main(String[] args) {
        // Football matches
        FootballWinner[] footballMatches = {
                new FootballWinner(3, 1111),
                new FootballWinner(2, 122),
                new FootballWinner(1, 12)
        };

        // Tennis matches
        TennisWinner[] tennisMatches = {
                new TennisWinner(3, 1111),
                new TennisWinner(12, 122),
                new TennisWinner(1, 12)
        };

        // task 1 - lambda comparator: in growth of A-team goals
        Arrays.sort(footballMatches, (fw1, fw2) -> Integer.compare(fw1.getResultA(), fw2.getResultA()));

        System.out.println("Task 1");
        System.out.println("---------------------------------");
        for (FootballWinner match : footballMatches) {
            System.out.println(match);
        }

        // task 2 - Comparator.reversed()
        Arrays.sort(tennisMatches, new Comparator<TennisWinner>() {
            @Override
            public int compare(TennisWinner tw1, TennisWinner tw2) {
                return Integer.compare(tw1.getResultA(), tw2.getResultA());
            }
        }.reversed());

        System.out.println("\n"+"Task 2");
        System.out.println("---------------------------------");
        for (TennisWinner match : tennisMatches) {
            System.out.println(match);
        }


        // task 3 - Comparator.thenComparing(). Will use a new arr to show
        TennisWinner[] tennisMatches_2 = {
                new TennisWinner(1, 8),
                new TennisWinner(2, 5),
                new TennisWinner(2, 1)
        };

        Comparator<TennisWinner> A_Comporator = new Comparator<TennisWinner>() {
            @Override
            public int compare(TennisWinner tw1, TennisWinner tw2) {
                return Integer.compare(tw1.getResultA(), tw2.getResultA());
            }
        };

        Arrays.sort(tennisMatches_2, A_Comporator.thenComparing(TennisWinner::getResultB));

        System.out.println("\n"+"Task 3");
        System.out.println("---------------------------------");
        for (TennisWinner match : tennisMatches_2) {
            System.out.println(match);
        }

        // task 4 - Comparator.nullsLast(). A new arr too
        FootballWinner[] footballMatches_2 = {
                new FootballWinner(3, 1111),
                null,
                new FootballWinner(2, 2),
                null,
                new FootballWinner(1, 12),
                null

        };

        Comparator<FootballWinner> Comp = new Comparator<FootballWinner>() {
            @Override
            public int compare(FootballWinner fw1, FootballWinner fw2) {
                return Integer.compare(fw1.getResultA(), fw2.getResultA());
            }
        };

        Arrays.sort(footballMatches_2, Comparator.nullsLast(Comp));

        System.out.println("\n"+"Task 4");
        System.out.println("---------------------------------");
        for (FootballWinner match : footballMatches_2) {
            System.out.println(match);
        }

    }
}
