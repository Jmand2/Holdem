package Game;

import java.util.ArrayList;

public class Table {
    public static ArrayList<Card> river = new ArrayList<>();
    public static ArrayList<Card> activePlayers = new ArrayList<>();
    public int pot;

    public Table(ArrayList<Card> river){
        Table.river = river;
    }

    public Table(){}

    public void setRiver(ArrayList<Card> cards){
        river.addAll(cards);
    }
}