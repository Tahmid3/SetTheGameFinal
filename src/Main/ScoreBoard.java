package Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ScoreBoard implements Initializable {

    @FXML
    AnchorPane window;

    @FXML
    Button backToMenu;

    @FXML
    private TableView<Score> table;

    @FXML
    public TableColumn<Score, Integer> rank;

    @FXML
    public TableColumn<Score, String> name;

    @FXML
    public TableColumn<Score, Integer> score;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rank.setCellValueFactory(new PropertyValueFactory<>("Rank"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        score.setCellValueFactory(new PropertyValueFactory<>("Score"));
        add();


    }

    public void add() {
        for(int i=0;i<10;i++) {
            table.getItems().add(new Score(i,"a",100+i));
        }
    }

    public void onClickBackToMenu() throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        window.getChildren().setAll(pane);
    }
}
