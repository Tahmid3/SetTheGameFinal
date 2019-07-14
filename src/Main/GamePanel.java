package Main;

import Code.Card;
import Code.Clock;
import Code.Game;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.IllegalCharsetNameException;
import java.util.*;
import javax.imageio.ImageIO;

public class GamePanel {

    Game g = new Game(); // Neues Spiel wird und g gespeichert und im Verlauf der Klasse mehrmals genutzt

    LinkedHashMap<ImageView, Card> clickedSet = new LinkedHashMap<>(); //Karten mit den dazugehörigen Views, die angeklickt sind
    LinkedHashMap<ImageView, Card> cardDeck = new LinkedHashMap<>(); //Aktuell aufgelegtes Kartendeck als Map
    LinkedList<ImageView> imageViews = new LinkedList<>(); //Alle Views, die bereits angeklickt sind
    int clickedCards = 0; //Integer - zu den angeklicken Karten
    int foundSets = 0; // Integer für die gefundenen Sets
    int score = 0;
    int scoreInt = 0;
    int skipInt = 0;

    boolean sorted = false;
    boolean skipClickable = true;
    boolean onEnterBoolean = true;

    @FXML
    Pane endPane;
    @FXML
    Pane rules;

    @FXML
    Pane leftMenu;

    @FXML
    ChoiceBox box;

    @FXML
    ChoiceBox boxFarbe;

    @FXML
    CheckBox sortedCards;
    @FXML
    CheckBox cardset;

    @FXML
    AnchorPane window; // Deklaration des gesamten Fensters

    @FXML
    Pane options;

    @FXML
    Pane sideMenu; // Deklaration des Seiten Menüs

    @FXML
    TextField field; //Deklaration des Textfeldes für die Namenseingabe

    //Folgend kommt die Deklaration der ImageViews

    @FXML
    ImageView ii1;

    @FXML
    ImageView ii2;

    @FXML
    ImageView ii3;

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
    ImageView remainingCardsView;

    @FXML
    ImageView trayStackView;

    @FXML
    ImageView skipView;

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
    Label scoreLabel;

    @FXML
    Label trayStack;

    @FXML
    Label skipLabel;

    @FXML
    Label gameOverName;

    @FXML
    Label gameOverScore;

    @FXML
    Label gameOverFoundSets;

    @FXML
    Button skip; // SKIP - Button im Spiel

    @FXML
    Button start; // START - Button im Spiel

    @FXML
    Button menuButton; // Back to Menu - Button im Spiel

    @FXML
    Button gameOverBackToMenu;

    ScoreBoard sb = new ScoreBoard();

    public void loadRulesImage() throws Exception {
        ii1.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Symbols/Card_backsite.png")), null));
        ii2.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Symbols/recycle_bin.png")), null));
        ii3.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Symbols/skip_image.png")), null));
    }

    public void onClick(Event event) throws IOException { // Die Methode wird immer dann aufgerufen, wenn eine Karte angeklickt wird
        if (g.isClickable()) { //Wenn die Karte anklickbar ist (Sie ist nur dann nicht anklickbar, während der 2,5 Sekunden in denen ein Beispielset angezeigt wird)
            ImageView view = (ImageView) event.getSource(); //Das angeklickte "Bild" wird sich über das event geholt
            if (clickedSet.keySet().contains(view)) { //Wenn die Map, in der nur die aktuell angeklickten Karten - mit ihren dazugehörigen Images - drin sind, die aktuell angeklickte Karte bereits enthält...
                view.setEffect(cardShadow()); //Wird die Karte entwählt...also der Schatten wird zurückgesetzt
                clickedSet.remove(view); //Die Karte wird aus set mit den aktuell angeklickten Karten entfernt
                imageViews.remove(view);
                clickedCards--; //Die angeklickte Anzahl der Karten wird um 1 nach unten gesetzt
            } else { //sonst (also wenn die Karte noch nicht in dem Set ist)
                clickedSet.put(view, cardDeck.get(view)); //
                imageViews.add(view); //
                clickedCards++; //Zählvar. um 1 nach oben
                view.setEffect(setMarkedCard()); //Effekt der Makierten Karte wird gesetzt
                if (clickedCards == 3) { // Wenn 3 Karten ausgewählt sind...

                    ImageView image1 = imageViews.get(0); // Die ausgewählten Images werden unter den Variabelen image1,image2 und image3 gespeichert
                    ImageView image2 = imageViews.get(1);
                    ImageView image3 = imageViews.get(2);


                    Task<Void> sleeper = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            try {
                                g.setClickable(false);

                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            return null;
                        }
                    };
                    sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                        @Override
                        public void handle(WorkerStateEvent workerStateEvent) {
                            if (g.checkSet(clickedSet.get(image1), clickedSet.get(image2), clickedSet.get(image3))) { // Wenn die ausgewählten Karten ein Set sind...


                                foundSets++; // Gefundene Sets um 1 nach oben
                                foundSetsLabel.setText("" + foundSets);
                                if ((300 - scoreInt) > 0) {
                                    score = score + (((300 - scoreInt) * (300 - scoreInt)) / (900 * Integer.parseInt(possibleSetsLabel.getText())));
                                    System.out.println(Integer.parseInt(possibleSetsLabel.getText()));
                                    g.getExampleSet().clear();
                                    if (sorted) {
                                        score = (score * 3) / 4;
                                    }
                                }
                                scoreLabel.setText(score + "");
                                scoreInt = 0;
                                g.removeCardFromGameDeck(clickedSet.get(image1)); // Karten werden aus dem Spieldeck entfernt
                                g.removeCardFromGameDeck(clickedSet.get(image2));
                                g.removeCardFromGameDeck(clickedSet.get(image3));
                                setThreeNewCards(image1, image2, image3);

                                if (g.getPossibleSets() == 0) {
                                    try {
                                        refreshGamePanel();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                    g.getExampleSet().clear();
                                }
                            } else {
                                setCardShadow(image1, image2, image3); // Kartenschatten wird auf allen 3 Images wieder zurückgesetzt
                                clickedSet.clear(); // clickedSet wird gecleared
                                imageViews.clear(); // Image Views werden geleared
                                clickedCards = 0; // Ausgewählten Karten werden auf 0 zurückgesetzt

                            }
                            g.setClickable(true);
                        }
                    });
                    new Thread(sleeper).start();
                }
            }
        }
    }

    public void onCLickSkip(Event event) throws IOException { //Fehler mit der helpList
        if (skipClickable) {
            skipClickable = false;
            scoreInt = 0;
            LinkedList<ImageView> helpList = new LinkedList<>();
            g.getPossibleSets();
            for (ImageView view : cardDeck.keySet()) {
                view.setEffect(cardShadow());
                for (int i = 0; i < 3; i++) {
                    if (cardDeck.get(view).toString().equals(g.getExampleSet().get(i).toString())) {
                        view.setEffect(exampleCardEffect());
                        helpList.add(view);
                    }
                }
            }
            g.setClickable(false);


            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent workerStateEvent) {
                    skipClickable = true;
                    for (ImageView view : cardDeck.keySet()) {
                        view.setEffect(cardShadow());
                    }
                    if (helpList.size() > 2) {
                        ImageView v1 = helpList.get(0);
                        ImageView v2 = helpList.get(1);
                        ImageView v3 = helpList.get(2);

                        g.removeCardFromGameDeck(g.getExampleSet().get(0));
                        g.removeCardFromGameDeck(g.getExampleSet().get(1));
                        g.removeCardFromGameDeck(g.getExampleSet().get(2));
                        g.getExampleSet().clear();
                        setThreeNewCards(v1, v2, v3);
                    }

                    g.setClickable(true);

                    if (g.getPossibleSets() == 0) {
                        try {
                            g.getExampleSet().clear();
                            refreshGamePanel();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }


                }
            });
            new Thread(sleeper).start();
        }

    }

    public void onClickBackToMenu() throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        window.getChildren().setAll(pane);
    }

    public void refreshGamePanel() throws IOException {
        skipInt++;
        skipLabel.setText(skipInt * 12 + "");
        scoreInt = 0;
        if (g.getRemainingCards().size() > 11) {
            g.getGameDeck().clear();
            addTwelveNewCards();
            cardsRemainingLabel.setText(g.getRemainingCards().size() + "");
            trayStack.setText((69 - g.getRemainingCards().size()) + "");
            possibleSetsLabel.setText("" + g.getPossibleSets());
            g.getExampleSet().clear();
            if (g.getPossibleSets() == 0) {
                try {
                    g.getExampleSet().clear();
                    refreshGamePanel();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            setEndScreen();
        }

    }

    public void createFirstDeck() throws IOException {
        rules.setVisible(false);
        String s = field.getText();
        if (s.length() > 16) {
            console.setText("This name is too long!");
        } else if (s.length() < 3) {
            console.setText("This name is too short!");
        } else {
            if (sortedCards.isSelected()) {
                sorted = true;
            }
            options.setVisible(false);
            console.setVisible(false);
            Game g = new Game();
            this.g = g;

            g.createDeck();
            g.mixCards();
            createCardDeckMap();
            if (g.getRemainingCards().size() > 11) {
                addTwelveNewCards();
                if (g.getPossibleSets() == 0) {
                    g.getExampleSet().clear();
                    refreshGamePanel();
                }
                setLabels();
                sideMenu.setVisible(true);
                skip.setVisible(true);
                field.setVisible(false);
                start.setVisible(false);
            } else {
                setEndScreen();
            }


        }

    }

    public void addTwelveNewCards() throws IOException {
        int counter = 0;
        for (int i = 0; i < 12; i++) {
            Card c = g.getRemainingCards().pop();
            g.addCardToGameDeck(c);
        }
        if (sorted) {
            g.sortList(g.getGameDeck());
        }
        for (ImageView imageView : cardDeck.keySet()) {
            Card c = g.getGameDeck().get(counter);
            imageView.setEffect(cardShadow());
            cardDeck.put(imageView, c);
            if (!cardset.isSelected()) {
                imageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Cards/" + c.toString() + ".png")), null));
            }else {
                imageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/CardsBlack/" + c.toString() + ".png")), null));
            }
            counter++;
        }
    }

    public void setThreeNewCards(ImageView image1, ImageView image2, ImageView image3) {
        if (g.getRemainingCards().size() > 2) {
            Card c1 = g.getRemainingCards().pop(); // Es werden duch c1,c2,c3 3 neue Karten vom "Stapel" abgehoben
            Card c2 = g.getRemainingCards().pop();
            Card c3 = g.getRemainingCards().pop();
            cardDeck.remove(image1); // Die 3 Katen, welche ein Set ergeben werden vom Spielfeld entfernt (bzw. aus der Map cardDeck
            cardDeck.remove(image2);
            cardDeck.remove(image3);
            cardDeck.put(image1, c1); // Die neuen Karten werden dem Image Views zugewiesen und damit in die Map aufgenommen
            cardDeck.put(image2, c2);
            cardDeck.put(image3, c3);
            g.addCardToGameDeck(c1); // Die Karten werden auch in der externen Liste in Spieldeck aufgenommen
            g.addCardToGameDeck(c2);
            g.addCardToGameDeck(c3);
            try {

                if (!cardset.isSelected()) {
                    image1.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Cards/" + c1.toString() + ".png")), null)); // Diese Karten werde über den Befehl angezeigt
                    image2.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Cards/" + c2.toString() + ".png")), null));
                    image3.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Cards/" + c3.toString() + ".png")), null));
                } else {
                    image1.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/CardsBlack/" + c1.toString() + ".png")), null)); // Diese Karten werde über den Befehl angezeigt
                    image2.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/CardsBlack/" + c2.toString() + ".png")), null));
                    image3.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/CardsBlack/" + c3.toString() + ".png")), null));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (sorted) {
                cardDeck.clear();
                g.sortList(g.getGameDeck());
                int counter = 0;
                for (ImageView imageView : createCardDeckMap().keySet()) {
                    Card c = g.getGameDeck().get(counter);
                    imageView.setEffect(cardShadow());
                    cardDeck.put(imageView, c);
                    try {
                        if (!cardset.isSelected()) {
                            imageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Cards/" + c.toString() + ".png")), null));
                        }else {
                            imageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/CardsBlack/" + c.toString() + ".png")), null));

                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    counter++;
                }
            }
            possibleSetsLabel.setText(g.getPossibleSets() + ""); // Possibe Sets wird gepudatet
            g.getExampleSet().clear();
            cardsRemainingLabel.setText(g.getRemainingCards().size() + ""); // Remainig Cards wird gepudatet
            trayStack.setText((69 - g.getRemainingCards().size()) + "");
            setCardShadow(image1, image2, image3); // Kartenschatten wird auf allen 3 Images wieder zurückgesetzt
            clickedSet.clear(); // clickedSet wird gecleared
            imageViews.clear(); // Image Views werden geleared
            g.setClickable(true);
            clickedCards = 0;
            if (g.getPossibleSets() == 0) {
                try {
                    g.getExampleSet().clear();
                    refreshGamePanel();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        } else {
            setEndScreen();
        }

    }

    public void setEndScreen() {
        try {
            writeData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        endPane.setVisible(true);
        skip.setVisible(false);
        leftMenu.setVisible(false);
        for (ImageView view : cardDeck.keySet()) {
            view.setVisible(false);
        }
        cardDeck.clear();
        gameOverFoundSets.setText(foundSets + "");
        gameOverName.setText(nameLabel.getText());
        gameOverScore.setText(score + "");
    }

    public void writeData() throws Exception {

        File file = new File("assets/ScoreBoard.txt");
        FileWriter fr = new FileWriter(file, true);
        fr.write(nameLabel.getText() + "-" + score + ";");
        fr.close();

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
        i1.setEffect(cardShadow());
        i2.setEffect(cardShadow());
        i3.setEffect(cardShadow());
    }

    Boolean sceneBoolean = true;

    public void onMouseEnter() throws Exception {
        loadRulesImage();
        if (onEnterBoolean) {
            box.getItems().add("Normal");
            box.setValue("Normal");
            box.getItems().add("Time");

            onEnterBoolean = false;
        }
        if (sceneBoolean) {
            boxFarbe.getItems().add("Default");
            boxFarbe.setValue("Default");
            boxFarbe.getItems().add("Rot");
            boxFarbe.getItems().add("Grün");
            boxFarbe.getItems().add("Blau");
            sceneBoolean = false;
        }
        String rot = "-fx-background-color: #86001e";
        String rot2 = "-fx-background-color: #86001e; -fx-border-color: #F4F4F6; -fx-border-radius: 100; -fx-border-width: 4";

        if (boxFarbe.getValue().equals("Rot")) {
            setBackgroundColor(rot, rot2);
        } else if (boxFarbe.getValue().equals("Grün")) {
            //#115d06
            setBackgroundColor("-fx-background-color: #115d06", "-fx-background-color: #115d06; -fx-border-color: #F4F4F6; -fx-border-radius: 100; -fx-border-width: 4");

        } else if (boxFarbe.getValue().equals("Blau")) {
            //#04074b
            setBackgroundColor("-fx-background-color: #04074b", "-fx-background-color: #04074b; -fx-border-color: #F4F4F6; -fx-border-radius: 100; -fx-border-width: 4");

        } else if (boxFarbe.getValue().equals("Default")) {
            //#35374C default
            setBackgroundColor("-fx-background-color: #35374C", "-fx-background-color: #35374C; -fx-border-color: #F4F4F6; -fx-border-radius: 100; -fx-border-width: 4");

        }

    }

    private void setBackgroundColor(String color1, String color2) {
        leftMenu.setStyle(color1);
        sideMenu.setStyle(color1);
        options.setStyle(color1);
        endPane.setStyle(color1);
        menuButton.setStyle(color1);
        gameOverBackToMenu.setStyle(color2);
        start.setStyle(color2);
        skip.setStyle(color2);
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

    public void setLabels() throws IOException {
        remainingCardsView.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Symbols/Card_backsite.png")), null));
        trayStackView.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Symbols/recycle_bin.png")), null));
        skipView.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Symbols/skip_image.png")), null));
        Timer timer = new Timer();
        Clock c = new Clock();
        for (ImageView iv : cardDeck.keySet()) {
            iv.setVisible(true);


        }
        if (box.getValue() == "Time") {
            c.setMinute2(5);
            timeLabel.setText("00:05:00");
        } else {
            timeLabel.setText("00:00:00");
        }
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(() -> {
                    scoreInt++;
                    if (box.getValue() == "Time") {
                        String timer = c.timer();
                        if (timer.equals("00:00:00")) {
                            setEndScreen();
                        }
                        timeLabel.setText(timer);
                    } else {
                        String normal = c.start();
                        if (normal.equals("02:00:00")) {
                            try {
                                AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                                window.getChildren().setAll(pane);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }


                            //setEndScreen();
                        }
                        timeLabel.setText(normal);
                    }

                });
            }
        }, 1000, 1000);
        nameLabel.setText(field.getText());
        scoreLabel.setText(score + "");
        skipLabel.setText(skipInt * 12 + "");
        foundSetsLabel.setText("0");
        cardsRemainingLabel.setText(g.getRemainingCards().size() + "");
        trayStack.setText((69 - g.getRemainingCards().size()) + "");
        possibleSetsLabel.setText("" + g.getPossibleSets());
        g.getExampleSet().clear();
    }

}
