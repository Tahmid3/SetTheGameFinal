package Code;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class Game {
    private LinkedList<Card> remainingCards = new LinkedList<>(); //ALle noch nicht benutzen Karten
    private LinkedList<Card> gameDeck = new LinkedList<>(); //Aktuell aufgelegtes Kartenspiel
    private LinkedList<Card> exampleSet = new LinkedList<>(); //Beispielset

    private boolean cardsClickable = true;

    public void createDeck() {
        LinkedList<Card> remainingCards = new LinkedList<>();
        for (int a = 1; a < 4; a++) {
            for (int b = 1; b < 4; b++) {
                for (int c = 1; c < 4; c++) {
                    for (int d = 1; d < 4; d++) {
                        remainingCards.add(new Card(a, b, c, d));
                    }
                }
            }
        }
        this.remainingCards=remainingCards;
    }

    public LinkedList<Card> getExampleSet() {
        return exampleSet;
    }

    public void mixCards() {
        Collections.shuffle(remainingCards);
    }

    public LinkedList<Card> getRemainingCards() {
        return remainingCards;
    }

    public LinkedList<Card> getGameDeck() {
        return gameDeck;
    }

    public void addCardToGameDeck(Card c) {
        gameDeck.add(c);
    }

    public void removeCardFromRemainingCards(Card c) {
        remainingCards.remove(c);
    }

    public void removeCardFromGameDeck(Card c) {
        gameDeck.remove(c);
    }

    public boolean isClickable() { return cardsClickable; }

    public void setClickable(boolean clickable) {
        this.cardsClickable=clickable;
    }

    public int getPossibleSets() {
        HashSet<Integer> set = new HashSet<>();
        LinkedList<Card> exampleSet = new LinkedList<>(); //Nur lokale Liste, in welche nacheinander alle Karten reingespeichert werden die ein Set bilden könnten
        for (int a = 0; a < gameDeck.size(); a++) {
            for (int b = 0; b < gameDeck.size(); b++) {
                for (int c = 0; c < gameDeck.size(); c++) {
                    if (colorIsTheSame(a, b, c)) {
                        if (numberIsTheSame(a, b, c)) {
                            if (symbolIsTheSame(a, b, c)) {
                                if (formIsNotTheSame(a, b, c)) {
                                    exampleSet.add(gameDeck.get(a));
                                    exampleSet.add(gameDeck.get(b));
                                    exampleSet.add(gameDeck.get(c));
                                    set.add(1);
                                }
                            } else if (symbolIsNotTheSame(a, b, c)) {
                                if (formIsTheSame(a, b, c)) {
                                    set.add(2);
                                    exampleSet.add(gameDeck.get(a));
                                    exampleSet.add(gameDeck.get(b));
                                    exampleSet.add(gameDeck.get(c));
                                } else if (formIsNotTheSame(a, b, c)) {
                                    set.add(3);
                                    exampleSet.add(gameDeck.get(a));
                                    exampleSet.add(gameDeck.get(b));
                                    exampleSet.add(gameDeck.get(c));
                                }
                            }
                        } else if (numberIsNotTheSame(a, b, c)) {
                            if (symbolIsTheSame(a, b, c)) {
                                if (formIsTheSame(a, b, c)) {
                                    set.add(4);
                                    exampleSet.add(gameDeck.get(a));
                                    exampleSet.add(gameDeck.get(b));
                                    exampleSet.add(gameDeck.get(c));
                                } else if (formIsNotTheSame(a, b, c)) {
                                    set.add(5);
                                    exampleSet.add(gameDeck.get(a));
                                    exampleSet.add(gameDeck.get(b));
                                    exampleSet.add(gameDeck.get(c));
                                }
                            } else if (symbolIsNotTheSame(a, b, c)) {
                                if (formIsTheSame(a, b, c)) {
                                    set.add(6);
                                    exampleSet.add(gameDeck.get(a));
                                    exampleSet.add(gameDeck.get(b));
                                    exampleSet.add(gameDeck.get(c));
                                } else if (formIsNotTheSame(a, b, c)) {
                                    set.add(7);
                                    exampleSet.add(gameDeck.get(a));
                                    exampleSet.add(gameDeck.get(b));
                                    exampleSet.add(gameDeck.get(c));

                                }
                            }
                        }

                    } else if (colorIsNotTheSame(a, b, c)) {
                        if (numberIsTheSame(a, b, c)) {
                            if (symbolIsTheSame(a, b, c)) {
                                if (formIsTheSame(a, b, c)) {
                                    set.add(8);
                                    exampleSet.add(gameDeck.get(a));
                                    exampleSet.add(gameDeck.get(b));
                                    exampleSet.add(gameDeck.get(c));
                                } else if (formIsNotTheSame(a, b, c)) {
                                    set.add(9);
                                    exampleSet.add(gameDeck.get(a));
                                    exampleSet.add(gameDeck.get(b));
                                    exampleSet.add(gameDeck.get(c));
                                }
                            } else if (symbolIsNotTheSame(a, b, c)) {
                                if (formIsTheSame(a, b, c)) {
                                    set.add(10);
                                    exampleSet.add(gameDeck.get(a));
                                    exampleSet.add(gameDeck.get(b));
                                    exampleSet.add(gameDeck.get(c));
                                } else if (formIsNotTheSame(a, b, c)) {
                                    set.add(11);
                                    exampleSet.add(gameDeck.get(a));
                                    exampleSet.add(gameDeck.get(b));
                                    exampleSet.add(gameDeck.get(c));
                                }
                            }
                        } else if (numberIsNotTheSame(a, b, c)) {
                            if (symbolIsTheSame(a, b, c)) {
                                if (formIsTheSame(a, b, c)) {
                                    set.add(12);
                                    exampleSet.add(gameDeck.get(a));
                                    exampleSet.add(gameDeck.get(b));
                                    exampleSet.add(gameDeck.get(c));
                                } else if (formIsNotTheSame(a, b, c)) {
                                    set.add(13);
                                    exampleSet.add(gameDeck.get(a));
                                    exampleSet.add(gameDeck.get(b));
                                    exampleSet.add(gameDeck.get(c));
                                }
                            } else if (symbolIsNotTheSame(a, b, c)) {
                                if (formIsTheSame(a, b, c)) {
                                    set.add(14);
                                    exampleSet.add(gameDeck.get(a));
                                    exampleSet.add(gameDeck.get(b));
                                    exampleSet.add(gameDeck.get(c));
                                } else if (formIsNotTheSame(a, b, c)) {
                                    set.add(15);
                                    exampleSet.add(gameDeck.get(a));
                                    exampleSet.add(gameDeck.get(b));
                                    exampleSet.add(gameDeck.get(c));
                                }
                            }
                        }
                    }
                }
            }
        }
        this.exampleSet.add(exampleSet.get(0));
        this.exampleSet.add(exampleSet.get(1));
        this.exampleSet.add(exampleSet.get(2));
        return set.size();

    }

    public void sortList(LinkedList<Card> list) {
        Collections.sort(list);
    }

    public boolean checkSet(Card c1, Card c2, Card c3) {
        char[] c1Array = c1.toString().toCharArray();
        char[] c2Array = c2.toString().toCharArray();
        char[] c3Array = c3.toString().toCharArray();

        if((c1Array[0] == c2Array[0] && c1Array[0] == c3Array[0]) || (c1Array[0] != c2Array[0] && c1Array[0] != c3Array[0] && c2Array[0] != c3Array[0])) {
            if((c1Array[1] == c2Array[1] && c1Array[1] == c3Array[1]) || (c1Array[1] != c2Array[1] && c1Array[1] != c3Array[1] && c2Array[1] != c3Array[1])) {
                if((c1Array[2] == c2Array[2] && c1Array[2] == c3Array[2]) || (c1Array[2] != c2Array[2] && c1Array[2] != c3Array[2] && c2Array[2] != c3Array[2])) {
                    if((c1Array[3] == c2Array[3] && c1Array[3] == c3Array[3]) || (c1Array[3] != c2Array[3] && c1Array[3] != c3Array[3] && c2Array[3] != c3Array[3])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean colorIsTheSame(int a, int b, int c) {
        if ((gameDeck.get(a).getColor() == gameDeck.get(b).getColor()) && (gameDeck.get(a).getColor() == gameDeck.get(c).getColor())) {
            return true;
        }
        return false;
    }

    public boolean numberIsTheSame(int a, int b, int c) {
        if ((gameDeck.get(a).getNumber() == gameDeck.get(b).getNumber()) && (gameDeck.get(a).getNumber() == gameDeck.get(c).getNumber())) {
            return true;
        }
        return false;
    }

    public boolean symbolIsTheSame(int a, int b, int c) {
        if ((gameDeck.get(a).getSymbol() == gameDeck.get(b).getSymbol()) && (gameDeck.get(a).getSymbol() == gameDeck.get(c).getSymbol())) {
            return true;
        }
        return false;
    }

    public boolean formIsTheSame(int a, int b, int c) {
        if ((gameDeck.get(a).getForm() == gameDeck.get(b).getForm()) && (gameDeck.get(a).getForm() == gameDeck.get(c).getForm())) {
            return true;
        }
        return false;
    }

    public boolean colorIsNotTheSame(int a, int b, int c) {
        if ((gameDeck.get(a).getColor() != gameDeck.get(b).getColor()) && (gameDeck.get(a).getColor() != gameDeck.get(c).getColor()) && (gameDeck.get(b).getColor() != gameDeck.get(c).getColor())) {
            return true;
        }
        return false;
    }

    public boolean numberIsNotTheSame(int a, int b, int c) {
        if ((gameDeck.get(a).getNumber() != gameDeck.get(b).getNumber()) && (gameDeck.get(a).getNumber() != gameDeck.get(c).getNumber()) && (gameDeck.get(b).getNumber() != gameDeck.get(c).getNumber())) {
            return true;
        }
        return false;
    }

    public boolean symbolIsNotTheSame(int a, int b, int c) {
        if ((gameDeck.get(a).getSymbol() != gameDeck.get(b).getSymbol()) && (gameDeck.get(a).getSymbol() != gameDeck.get(c).getSymbol()) && (gameDeck.get(b).getSymbol() != gameDeck.get(c).getSymbol())) {
            return true;
        }
        return false;
    }

    public boolean formIsNotTheSame(int a, int b, int c) {
        if ((gameDeck.get(a).getForm() != gameDeck.get(b).getForm()) && (gameDeck.get(a).getForm() != gameDeck.get(c).getForm()) && (gameDeck.get(b).getForm() != gameDeck.get(c).getForm())) {
            return true;
        }
        return false;
    }


}
