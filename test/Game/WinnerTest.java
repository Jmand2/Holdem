package Game;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class WinnerTest {

        Card fiveD = new Card(Suits.Diamonds, Ranks.Five);
        Card sixD = new Card(Suits.Diamonds, Ranks.Six);
        Card sevenD = new Card(Suits.Diamonds, Ranks.Seven);
        Card eightD = new Card(Suits.Diamonds, Ranks.Eight);

        Player p1 = new Player(new Card[]{fiveD, sixD}, 100);
        Player p2 = new Player(new Card[]{sevenD, eightD}, 100);

        Card fiveS = new Card(Suits.Spades, Ranks.Five);
        Card sixS = new Card(Suits.Spades, Ranks.Six);
        Card aceC = new Card(Suits.Clubs, Ranks.Ace);
        Card jackS = new Card(Suits.Spades, Ranks.Jack);
        Card queenS = new Card(Suits.Spades, Ranks.Queen);
        Card[] total = new Card[]{fiveS, sixS, aceC, jackS, queenS};


    @Test
    public void checkBasic() {
        Winner w = new Winner();
        ArrayList<Player> placeholder = new ArrayList<>();
        placeholder.add(p1);
        placeholder.add(p2);
        w.DecideWinner(placeholder);
    }
}
