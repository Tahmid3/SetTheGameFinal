package Code;

public class Card implements Comparable<Card> {

    private int color;
    private int number;
    private int symbol;
    private int form;

    public Card(int color, int number, int symbol, int form) {
        this.color = color;
        this.number = number;
        this.symbol = symbol;
        this.form = form;
    }

    @Override
    public String toString() {
        return color + "" + number + "" + symbol + "" + form;
    }


    public int getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public int getSymbol() {
        return symbol;
    }

    public int getForm() {
        return form;
    }

    @Override
    public int compareTo(Card c) {
        int no1 = Integer.parseInt(c.toString());
        int no2 = Integer.parseInt(this.toString());
        if(no1==no2)
            return 0;
        else if(no1<no2)
            return 1;
        else
            return -1;
    }



}

