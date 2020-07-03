package Model;

import Model.Users.Buyer;

import java.util.ArrayList;

public class Score {
    public static ArrayList<Score> allScore = new ArrayList<>();
    private Buyer personWhoGiveScore;
    private int score;
    private Product product;

    public Score(Buyer buyer, int score, Product product) {
        this.score = score;
        this.product = product;
        this.personWhoGiveScore = buyer;
        product.addScore(this);
    }

    public Product getProduct() {
        return this.product;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Buyer getPersonWhoGiveScore() {
        return this.personWhoGiveScore;
    }

    public int getScore() {
        return this.score;
    }

    public static boolean isPersonScoredBefore(Buyer buyer, Product product) {
        for (Score eachScore : allScore)
            if (eachScore.getPersonWhoGiveScore().equals(buyer) && eachScore.getProduct().equals(product)) return true;
        return false;
    }

}
