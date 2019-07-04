package Main;

import Code.Card;
import Code.Clock;
import Code.Game;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class GamePanel {

    Game g = new Game(); // Neues Spiel wird und g gespeichert und im Verlauf der Klasse mehrmals genutzt

    LinkedHashMap<ImageView, Card> clickedSet = new LinkedHashMap<>(); //Karten mit den dazugehörigen Views, die angeklickt sind
    LinkedHashMap<ImageView, Card> cardDeck = new LinkedHashMap<>(); //Aktuell aufgelegtes Kartendeck als Map
    LinkedList<ImageView> imageViews = new LinkedList<>(); //Alle Views, die bereits angeklickt sind
    int i = 0; //Integer - zu den angeklicken Karten
    int foundSets = 0; // Integer für die gefundenen Sets

    @FXML
    AnchorPane window; // Deklaration des gesamten Fensters

    @FXML
    Pane sideMenu; // Deklaration des Seiten Menüs

    @FXML
    TextField field; //Deklaration des Textfeldes für die Namenseingabe

    //Folgend kommt die Deklaration der ImageViews

    @FXML
    ImageView i1;

    @FXML
    ImageView i2;

    @FXML
    ImageView i3;

    @FXML
    ImageView i4;

    @FXML
    ImageView i5;

    @FXML
    ImageView i6;

    @FXML
    ImageView i7;

    @FXML
    ImageView i8;

    @FXML
    ImageView i9;

    @FXML
    ImageView i10;

    @FXML
    ImageView i11;

    @FXML
    ImageView i12;

    @FXML
    Label l; // Deklaration des Überschrift

    @FXML
    Label foundSetsLabel; //Deklaration des Labels für gefundene Sets

    @FXML
    Label possibleSetsLabel; // Deklaration des Labels für die möglichen Sets

    @FXML
    Label cardsRemainingLabel; // Deklaration des Labels für die remaining cards

    @FXML
    Label timeLabel; // Deklaration des Labels für die Zeit

    @FXML
    Label console; // Deklaration des Labels für die Konsole

    @FXML
    Label nameLabel; // Deklaration des Labels für den eingegebenen Namen

    @FXML
    Button skip; // SKIP - Button im Spiel

    @FXML
    Button start; // START - Button im Spiel

    @FXML
    Button menuButton; // Back to Menu - Button im Spiel#




    public void onClick(Event event) throws IOException { // Die Methode wird immer dann aufgerufen, wenn eine Karte angeklickt wird
        if(g.isClickable()) { //Wenn die Karte anclickbar ist (Sie ist nur dann nicht anklickbar, während der 2,5 Sekunden in denen ein Beispielset angezeigt wird)
            ImageView view = (ImageView) event.getSource(); //Das angeklickte "Bild" wird sich über das event geholt
            if (clickedSet.keySet().contains(view)) { //Wenn die Map, in der nur die aktuell angeklickten Karten - mit ihren dazugehörigen Images - drin sind, die aktuell angeklickte Karte bereits enthält...
                view.setEffect(cardShadow()); //Wird die Karte entwählt...also der Schatten wird zurückgesetzt
                clickedSet.remove(view); //Die Karte wird aus set mit den aktuell angeklickten Karten entfernt
                imageViews.remove(view);
                i--; //Die angeklickte Anzahl der Karten wird um 1 nach unten gesetzt
            } else { //sonst (also wenn die Karte noch nicht in dem Set ist)
                clickedSet.put(view, cardDeck.get(view)); //
                imageViews.add(view); //
                i++; //Zählvar. um 1 nach oben
                view.setEffect(setMarkedCard()); //Effekt der Makierten Karte wird gesetzt
                if (i == 3) { // Wenn 3 Karten ausgewählt sind...

                    ImageView image1 = imageViews.get(0); // Die ausgewählten Images werden unter den Variabelen image1,image2 und image3 gespeichert
                    ImageView image2 = imageViews.get(1);
                    ImageView image3 = imageViews.get(2);


                    Task<Void> sleeper = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            try {
                                g.setClickable(false);
                                Thread.sleep(1000);
                            } catch(InterruptedException ex) {

                            }
                            return null;
                        }
                    };
                    sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                        @Override
                        public void handle(WorkerStateEvent workerStateEvent) {
                            if (g.checkSet(clickedSet.get(image1), clickedSet.get(image2), clickedSet.get(image3))) { // Wenn die ausgewählten Karten ein Set sind...
                                g.removeCardFromGameDeck(clickedSet.get(image1)); // Karten werden aus dem Spieldeck entfernt
                                g.removeCardFromGameDeck(clickedSet.get(image2));
                                g.removeCardFromGameDeck(clickedSet.get(image3));
                                foundSets++; // Gefundene Sets um 1 nach oben
                                if (g.getRemainingCards().size() < 12) { // Noch in Arbeit
                                    System.out.println("Spiel beendet");
                                }
                                Card c1 = g.getRemainingCards().get(0); // Es werden duch c1,c2,c3 3 neue Karten vom "Stapel" abgehoben
                                Card c2 = g.getRemainingCards().get(1);
                                Card c3 = g.getRemainingCards().get(2);
                                try{
                                    image1.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Cards/" + c1.toString() + ".png")), null)); // Diese Karten werde über den Befehl angezeigt
                                    image2.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Cards/" + c2.toString() + ".png")), null));
                                    image3.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Cards/" + c3.toString() + ".png")), null));
                                } catch (IOException ex) {

                                }

                                cardDeck.remove(image1); // Die 3 Katen, welche ein Set ergeben werden vom Spielfeld entfernt (bzw. aus der Map cardDeck
                                cardDeck.remove(image2);
                                cardDeck.remove(image3);
                                cardDeck.put(image1, c1); // Die neuen Karten werden dem Image Views zugewiesen und damit in die Map aufgenommen
                                cardDeck.put(image2, c2);
                                cardDeck.put(image3, c3);
                                g.addCardToGameDeck(c1); // Die Karten werden auch in der externen Liste in Spieldeck aufgenommen
                                g.addCardToGameDeck(c2);
                                g.addCardToGameDeck(c3);
                                g.removeCardFromRemainingCards(c1); // Die neu hinzugefügten Karten werden vom "Stapel" gelöscht
                                g.removeCardFromRemainingCards(c2);
                                g.removeCardFromRemainingCards(c3);
                                foundSetsLabel.setText(foundSets + ""); //Found Sets wird gepudatet
                                possibleSetsLabel.setText(g.getPossibleSets() + ""); // Possibe Sets wird gepudatet
                                g.getExampleSet().clear();
                                cardsRemainingLabel.setText(g.getRemainingCards().size() + ""); // Remainig Cards wird gepudatet
                                setCardShadow(image1, image2, image3); // Kartenschatten wird auf allen 3 Images wieder zurückgesetzt
                                clickedSet.clear(); // clickedSet wird gecleared
                                imageViews.clear(); // Image Views werden geleared
                                i = 0; // Ausgewählten Karten werden auf 0 zurückgesetzt
                                g.setClickable(true);
                            } else {
                                setCardShadow(image1, image2, image3); // Kartenschatten wird auf allen 3 Images wieder zurückgesetzt
                                clickedSet.clear(); // clickedSet wird gecleared
                                imageViews.clear(); // Image Views werden geleared
                                i = 0; // Ausgewählten Karten werden auf 0 zurückgesetzt
                                g.setClickable(true);
                            }
                        }
                    });
                    new Thread(sleeper).start();
                }
            }
        }
    }

    public void onCLickSkip(Event event) throws IOException{
        g.getPossibleSets();
        for(ImageView view : cardDeck.keySet()) {
                for(int i=0;i<3;i++) {
                    if(cardDeck.get(view).toString().equals(g.getExampleSet().get(i).toString())) {
                        view.setEffect(exampleCardEffect());
                    }
                }
            }
        g.setClickable(false);

        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(2500);
                } catch(InterruptedException ex) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                for(ImageView view : cardDeck.keySet()) {
                    view.setEffect(cardShadow());
                }
                g.getGameDeck().clear();
                g.setClickable(true);
                try{
                    refreshGamePanel();
                } catch (Exception ex) {

                }
                g.getExampleSet().clear();
                foundSetsLabel.setText(foundSets + ""); //Found Sets wird gepudatet
                possibleSetsLabel.setText(g.getPossibleSets() + ""); // Possibe Sets wird gepudatet
                g.getExampleSet().clear();
                cardsRemainingLabel.setText(g.getRemainingCards().size() + ""); // Remainig Cards wird gepudatet
            }
        });
        new Thread(sleeper).start();

    }

    public void onClickBackToMenu() throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        window.getChildren().setAll(pane);
    }

    public void refreshGamePanel() throws IOException {
        int counter = 0;
        for (ImageView imageView : cardDeck.keySet()) {
            Card c = g.getRemainingCards().get(counter);
            imageView.setEffect(cardShadow());
            g.addCardToGameDeck(c);
            cardDeck.put(imageView, c);
            imageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Cards/" + c.toString() + ".png")), null));
            g.removeCardFromRemainingCards(c);
            counter++;
        }
    }

    public void createFirstDeck() throws IOException {
        String s = field.getText();
        if (s.length() > 16) {
            console.setText("This name is too long!");
        } else if (s.length() < 3) {
            console.setText("This name is too short!");
        } else {
            console.setVisible(false);
            int counter = 0;
            Game g = new Game();
            this.g = g;

            g.createDeck();
            g.mixCards();
            createCardDeckMap();
            for (int i=0; i<12;i++) {
                Card c = g.getRemainingCards().get(i);
                g.addCardToGameDeck(c);
                g.removeCardFromRemainingCards(c);
            }
            g.sortList(g.getGameDeck());
            for (ImageView imageView : cardDeck.keySet()) {
                Card c = g.getGameDeck().get(counter);
                imageView.setEffect(cardShadow());
                cardDeck.put(imageView, c);
                imageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Cards/" + c.toString() + ".png")), null));
                counter++;
            }
            if(g.getPossibleSets()==0) {

            }
            setLabels();
            sideMenu.setVisible(true);
            skip.setVisible(true);
            field.setVisible(false);
            start.setVisible(false);
            System.out.println(g.getGameDeck().toString());

        }

    }

    public Effect cardShadow() {
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(8);
        shadow.setOffsetY(12);

        shadow.setColor(Color.GREY);
        return shadow;
    }

    public Effect setMarkedCard() {
        DropShadow borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.BLUE);
        borderGlow.setWidth(30);
        borderGlow.setHeight(30);
        return borderGlow;
    }

    public Effect exampleCardEffect() {
        DropShadow borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(Color.DARKRED);
        borderGlow.setWidth(30);
        borderGlow.setHeight(30);
        return borderGlow;
    }

    public void setCardShadow(ImageView i1, ImageView i2, ImageView i3) {
        i1 = imageViews.get(0);
        i2 = imageViews.get(1);
        i3 = imageViews.get(2);
        i1.setEffect(cardShadow());
        i2.setEffect(cardShadow());
        i3.setEffect(cardShadow());
    }

    private LinkedHashMap<ImageView, Card> createCardDeckMap() {
        cardDeck.put(i1, null);
        cardDeck.put(i2, null);
        cardDeck.put(i3, null);
        cardDeck.put(i4, null);
        cardDeck.put(i5, null);
        cardDeck.put(i6, null);
        cardDeck.put(i7, null);
        cardDeck.put(i8, null);
        cardDeck.put(i9, null);
        cardDeck.put(i10, null);
        cardDeck.put(i11, null);
        cardDeck.put(i12, null);
        return cardDeck;
    }

    public void setLabels() {
        Timer timer = new Timer();
        Clock c = new Clock();
        timeLabel.setText("00:00:00");
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(() -> {
                    timeLabel.setText(c.start());
                });
            }
        }, 1000, 1000);
        nameLabel.setText(field.getText());
        foundSetsLabel.setText("0");
        cardsRemainingLabel.setText(g.getRemainingCards().size() + "");
        possibleSetsLabel.setText("" + g.getPossibleSets());
        g.getExampleSet().clear();
    }

    public void dragOverEffect(Event event) {
        ImageView b = (ImageView) event.getSource();

        b.setEffect(null);
    }

    public void dragOverEffectOver(Event event) {
        ImageView b = (ImageView) event.getSource();
        b.setEffect(cardShadow());
    }
}
