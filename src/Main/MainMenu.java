package Main;

import Code.Game;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.event.Event;
import javafx.scene.effect.Effect;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.IOException;


public class MainMenu {

    @FXML
    AnchorPane window;

    @FXML
    Button end;

    @FXML
    Button rules;

    @FXML
    Button button;

    @FXML
    ImageView rulesView;

    Effect effect;

    public void onClickStart() throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("GamePanel.fxml"));
        window.getChildren().setAll(pane);
    }

    public void onClickOptions() throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ScoreBoard.fxml"));
        window.getChildren().setAll(pane);
    }


    public void onClickEnd() {
        Stage stage = (Stage) end.getScene().getWindow();
        stage.close();
    }

    public void dragOverEffect(Event event) {
        Button b = (Button) event.getSource();
        effect = b.getEffect();
        //b.setStyle("-fx-border-color: #46ff6e; ");
        b.setEffect(null);
    }

    public void dragOverEffectOver(Event event) {
        Button b = (Button) event.getSource();
        b.setEffect(effect);
    }

    public void preSettings() throws IOException {
        System.out.println("Test");
        rulesView.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Symbols/rules_png.png")), null));

    }

    public void test() {
        try{
            preSettings();
        } catch (IOException ex) {

        }

    }
}
