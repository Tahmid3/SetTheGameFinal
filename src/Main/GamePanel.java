package Main;

import Code.Card;
import Code.Clock;
import Code.Game;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;

public class GamePanel {

    Game g = new Game();

    HashMap<ImageView, Card> clickedSet = new HashMap<>(); //Karten, die derzeit angeklickt sind
    HashMap<ImageView, Card> cardDeck = new HashMap<>(); //Aktuell aufgelegtes Kartendeck
    LinkedList<ImageView> imageViews = new LinkedList<>(); //Lediglich die angeklickten Karten
    int i = 0;
    int foundSets = 0;

    @FXML
    AnchorPane window;

    @FXML
    Pane sideMenu;

    @FXML
    TextField field;

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
    Label l;

    @FXML
    Label foundSetsLabel;

    @FXML
    Label possibleSetsLabel;

    @FXML
    Label cardsRemainingLabel;

    @FXML
    Label timeLabel;

    @FXML
    Label console;

    @FXML
    Label nameLabel;

    @FXML
    Button check;

    @FXML
    Button start;

    @FXML
    Button menuButton;




    public void onClick(Event event) throws IOException {
        System.out.println(g.getGameDeck().toString());
        ImageView view = (ImageView) event.getSource();
        if (clickedSet.keySet().contains(view)) {
            view.setEffect(cardShadow());
            clickedSet.remove(view);
            imageViews.remove(view);
            i--;
        } else {
            clickedSet.put(view, cardDeck.get(view));
            imageViews.add(view);
            i++;
            view.setEffect(setMarkedCard());
            if (i == 3) {
                ImageView image1 = imageViews.get(0);
                ImageView image2 = imageViews.get(1);
                ImageView image3 = imageViews.get(2);


                if (g.checkSet(clickedSet.get(image1), clickedSet.get(image2), clickedSet.get(image3))) {
                    g.removeCardFromGameDeck(clickedSet.get(image1));
                    g.removeCardFromGameDeck(clickedSet.get(image2));
                    g.removeCardFromGameDeck(clickedSet.get(image3));
                    Card c1 = g.getRemainingCards().get(0);
                    Card c2 = g.getRemainingCards().get(1);
                    Card c3 = g.getRemainingCards().get(2);
                    image1.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Cards/" + c1.toString() + ".png")), null));
                    image2.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Cards/" + c2.toString() + ".png")), null));
                    image3.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Cards/" + c3.toString() + ".png")), null));
                    cardDeck.put(image1, c1);
                    cardDeck.put(image2, c2);
                    cardDeck.put(image3, c3);
                    g.addCardToGameDeck(c1);
                    g.addCardToGameDeck(c2);
                    g.addCardToGameDeck(c3);
                    g.removeCardFromRemainingCards(c1);
                    g.removeCardFromRemainingCards(c2);
                    g.removeCardFromRemainingCards(c3);
                    foundSets++;
                    foundSetsLabel.setText(foundSets + "");
                    possibleSetsLabel.setText(g.getPossibleSets() + "");
                    cardsRemainingLabel.setText(g.getRemainingCards().size() + "");
                }
                setCardShadow(image1, image2, image3);
                clickedSet.clear();
                imageViews.clear();
                i = 0;
                System.out.println(g.getGameDeck().toString());
            }
        }


    }

    public void onClickBackToMenu() throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        window.getChildren().setAll(pane);
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
            for (ImageView imageView : cardDeck.keySet()) {
                Card c = g.getRemainingCards().get(counter);
                imageView.setEffect(cardShadow());
                g.addCardToGameDeck(c);
                cardDeck.put(imageView, c);
                imageView.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Cards/" + c.toString() + ".png")), null));
                g.removeCardFromRemainingCards(c);
                counter++;

            }
            setLabels();
            sideMenu.setVisible(true);
            check.setVisible(true);
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

    public void setCardShadow(ImageView i1, ImageView i2, ImageView i3) {
        i1 = imageViews.get(0);
        i2 = imageViews.get(1);
        i3 = imageViews.get(2);
        i1.setEffect(cardShadow());
        i2.setEffect(cardShadow());
        i3.setEffect(cardShadow());
    }

    public HashMap<ImageView, Card> createCardDeckMap() {
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
