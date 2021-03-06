package de.titanium.enterprise.Data.Datas;


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

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Score o) {

        if(this.getScore() > o.getScore()) {
            return 1;
        }

        if(this.getScore() < o.getScore()) {
            return -1;
        }

        return 0;
    }
}
