package Game;
import Game.Interfaces.IPlayer;

public class Player implements IPlayer {
    Card[] hand = new Card[2];
    Card[] finalHand = new Card[5];
    BasicScoring ranking;
    int money;
    boolean folded;
    int sidePot;

    public Player(Card[] hand, int money){
        this.hand = hand;
        this.money = money;
    }

    Player()
    {}

    public int compareTo(Player that){
        return this.ranking.compareTo(that.ranking);
    }
}