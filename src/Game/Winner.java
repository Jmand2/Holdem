package Game;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static Game.Table.river;
import static java.util.Collections.sort;

//class that contains the logic that decides the winner of the game
enum  BasicScoring {HighCard, OnePair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind, StraightFlush}

public class Winner {
    ArrayList<Card> riverPlus = river;


    public BasicScoring FindRanking(Player player){
        riverPlus.addAll(List.of(player.hand));
        sort(river);
        if(CheckStraightFlush())
            return BasicScoring.StraightFlush;
        if (CheckFourOfAKind())
            return BasicScoring.FourOfAKind;
        if (CheckFullHouse())
            return BasicScoring.FullHouse;
        if (CheckFlush())
            return BasicScoring.Flush;
        if (CheckStraight())
            return BasicScoring.Straight;
        if (CheckThreeOfAKind())
            return BasicScoring.ThreeOfAKind;
        if (CheckTwoPair())
            return BasicScoring.TwoPair;
        if (CheckOnePair())
            return BasicScoring.OnePair;
        return BasicScoring.HighCard;
    }

    private boolean CheckStraightFlush(){
        return CheckStraight() && CheckFlush();
    }

    private boolean CheckFourOfAKind(){
        return CheckDuplicates() == 4;
    }

    private boolean CheckFullHouse(){
        if(CheckDuplicates() != 3) return false;
        return Duplicates();
    }

    private boolean Duplicates() {
        ArrayList<Card> noDupes = new ArrayList<>();
        noDupes.add(riverPlus.get(2));
        noDupes.add(riverPlus.get(3));
        noDupes.add(riverPlus.get(4));
        noDupes.add(riverPlus.get(5));
        noDupes.add(riverPlus.get(6));
        return CheckDuplicates(noDupes) == 2;
    }

    private boolean CheckFlush(){
        int flushCounter = 0;
        for (Suits suit : Suits.values()){
        for (Card card : riverPlus) {
            if(card.suit == suit)
                flushCounter++;
            if (flushCounter == 5)
                return true;
        }
        flushCounter = 0;
        }
        return false;
    }

    //check ace then two
    private boolean CheckStraight(){
        int straightCounter = 0;
        if(riverPlus.get(6).rank == Ranks.Ace && riverPlus.get(0).rank == Ranks.Two)
            straightCounter = 1;
        for (int j = 0; j < riverPlus.size(); j++){
        for (int i = 0; i < Ranks.values().length; i++) {
            if(riverPlus.get(j).rank == Arrays.stream(Ranks.values()).toArray()[i]){
                straightCounter++;
                j++;
            }
            else straightCounter = 0;
            if(straightCounter == 5) return true;
        }
        }
        return false;
    }

    private boolean CheckThreeOfAKind(){
        return CheckDuplicates() == 3;
    }

    private boolean CheckTwoPair(){
        if(CheckDuplicates() != 2) return false;
        return Duplicates();

    }

    private boolean CheckOnePair(){
        return CheckDuplicates() == 2;
    }


    private int CheckDuplicates(){
        int highCounter = 1, sameCounter = 1, commonIndex = 0;
        for (int i = 1; i < riverPlus.size(); i++) {
            if(riverPlus.get(i) == riverPlus.get(i -1)){
                sameCounter++;
                if(sameCounter > highCounter){
                    highCounter = sameCounter;
                    commonIndex = i;
                }
            }
            else sameCounter = 1;
        }
        for (int i = highCounter; i > 0; i--)
            riverPlus.add(0, riverPlus.remove(i + commonIndex));
        return highCounter;
    }

    private int CheckDuplicates(ArrayList<Card> c){
        int highCounter = 1, sameCounter = 1;
        Ranks commonCard;
        for (int i = 1; i < c.size(); i++) {
            if(c.get(i) == c.get(i -1)){
                sameCounter++;
                if(sameCounter > highCounter){
                    highCounter = sameCounter;
                    commonCard = c.get(i).rank;
                }
            }
            else sameCounter = 1;
        }
        return highCounter;
    }


    public Player DecideWinner(ArrayList<Player> players){
        for (Player p : players)
            p.ranking = FindRanking(p);
        return Collections.max(players, Player::compareTo);
        //if tie?
    }
}