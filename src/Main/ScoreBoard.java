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
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class ScoreBoard implements Initializable {

    @FXML
    AnchorPane window;

    @FXML
    Button backToMenu;

    @FXML
    private TableView<Score> table;

    @FXML
    public TableColumn<Score, String> name;

    @FXML
    public TableColumn<Score, Integer> score;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        score.setCellValueFactory(new PropertyValueFactory<>("Score"));
        try {
            add();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add() throws Exception {
        Path path = FileSystems.getDefault().getPath("assets", "ScoreBoard.txt");
        String content = Files.readString(path, StandardCharsets.UTF_8);
        String[] game = content.split("\\;");


        for (String s:game) {
            String[] userData = s.split("\\-");
            table.getItems().add(new Score(userData[0],Integer.parseInt(userData[1])));
            score.setSortType(TableColumn.SortType.DESCENDING);
            table.getSortOrder().add(score);
        }
    }

    public void onClickBackToMenu() throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        window.getChildren().setAll(pane);
    }
}
