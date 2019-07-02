package Code;

public class Card {

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



}

