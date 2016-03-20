package de.titanium.enterprise.Data.Scores;


/**
 * Created by 204g01 on 16.03.2016.
 */
public class Score implements Comparable<Score> {

    private final int score;
    private final String name;

    public Score(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean isGreater(Score o) {
        return (this.getScore() > o.getScore());
    }

    @Override
    public boolean isLess(Score o) {
        return (this.getScore() < o.getScore());
    }

    @Override
    public boolean isEqual(Score o) {
        return (this.getScore() == o.getScore());
    }

    public String getName() {
        return name;
    }
}
