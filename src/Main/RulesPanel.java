package Main;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.imageio.ImageIO;
import java.io.File;


public class RulesPanel {
    @FXML
    AnchorPane window;

    @FXML
    Button backToMenu;

    @FXML
    ImageView i1;

    @FXML
    ImageView i2;

    @FXML
    ImageView i3;

    public void onClickBackToMenu() throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        window.getChildren().setAll(pane);
    }

    public void onMouseEnter() throws Exception {
        i1.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Symbols/Card_backsite.png")), null));
        i2.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Symbols/recycle_bin.png")), null));
        i3.setImage(SwingFXUtils.toFXImage(ImageIO.read(new File("assets/Symbols/skip_image.png")), null));
    }
}
