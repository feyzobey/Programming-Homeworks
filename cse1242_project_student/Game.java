
/*
 * Feyzullah Asıllıoğlu  150121021
 * Kadir Bat  150120012
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Game extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            // starter menu
            StackPane starterMenu = new StackPane();
            Button playButton = new Button("",
                    new ImageView(new Image("./projectImages/playButton.png", 300, 200, true, true)));
            starterMenu.getChildren().add(playButton);
            Scene scene = new Scene(starterMenu, 800, 450);

            // generating level1
            Level level1 = new Level(1);
            BorderPane bp1 = new BorderPane();
            Button button1 = new Button("Next Level");
            button1.setDisable(true);
            Label score1 = new Label();
            bp1.setCenter(level1.getLevel());
            bp1.setLeft(score1);
            bp1.setRight(button1);
            BorderPane.setAlignment(score1, Pos.CENTER_LEFT);
            BorderPane.setAlignment(button1, Pos.CENTER_RIGHT);
            BorderPane.setMargin(level1.getLevel(), new Insets(12));
            BorderPane.setMargin(score1, new Insets(12));
            BorderPane.setMargin(button1, new Insets(12));

            // generating level2
            Level level2 = new Level(2);
            BorderPane bp2 = new BorderPane();
            Button button2 = new Button("Next Level");
            button2.setDisable(true);
            Label score2 = new Label();
            bp2.setCenter(level2.getLevel());
            bp2.setLeft(score2);
            bp2.setRight(button2);
            BorderPane.setAlignment(score2, Pos.CENTER_LEFT);
            BorderPane.setAlignment(button2, Pos.CENTER_RIGHT);
            BorderPane.setMargin(level2.getLevel(), new Insets(12));
            BorderPane.setMargin(score2, new Insets(12));
            BorderPane.setMargin(button2, new Insets(12));

            // generating level3
            Level level3 = new Level(3);
            BorderPane bp3 = new BorderPane();
            Button button3 = new Button("Next Level");
            button3.setDisable(true);
            Label score3 = new Label("Score : " + level3.getCounter());
            bp3.setCenter(level3.getLevel());
            bp3.setLeft(score3);
            bp3.setRight(button3);
            BorderPane.setAlignment(score3, Pos.CENTER_LEFT);
            BorderPane.setAlignment(button3, Pos.CENTER_RIGHT);
            BorderPane.setMargin(level3.getLevel(), new Insets(12));
            BorderPane.setMargin(score3, new Insets(12));
            BorderPane.setMargin(button3, new Insets(12));

            // generating level4
            Level level4 = new Level(4);
            BorderPane bp4 = new BorderPane();
            Button button4 = new Button("Next Level");
            button4.setDisable(true);
            Label score4 = new Label();
            bp4.setCenter(level4.getLevel());
            bp4.setLeft(score4);
            bp4.setRight(button4);
            BorderPane.setAlignment(score4, Pos.CENTER_LEFT);
            BorderPane.setAlignment(button4, Pos.CENTER_RIGHT);
            BorderPane.setMargin(level4.getLevel(), new Insets(12));
            BorderPane.setMargin(score4, new Insets(12));
            BorderPane.setMargin(button4, new Insets(12));

            // generating level5
            Level level5 = new Level(5);
            BorderPane bp5 = new BorderPane();
            Button button5 = new Button("Go To Last Level");
            button5.setDisable(true);
            Label score5 = new Label();
            bp5.setCenter(level5.getLevel());
            bp5.setLeft(score5);
            bp5.setRight(button5);
            BorderPane.setAlignment(score5, Pos.CENTER_LEFT);
            BorderPane.setAlignment(button5, Pos.CENTER_RIGHT);
            BorderPane.setMargin(level5.getLevel(), new Insets(12));
            BorderPane.setMargin(score5, new Insets(12));
            BorderPane.setMargin(button5, new Insets(12));

            // generating level6
            Level level6 = new Level(6);
            BorderPane bp6 = new BorderPane();
            Label score6 = new Label();
            bp6.setCenter(level6.getLevel());
            bp6.setLeft(score6);
            BorderPane.setAlignment(score6, Pos.CENTER_LEFT);
            BorderPane.setMargin(level6.getLevel(), new Insets(12));
            BorderPane.setMargin(score6, new Insets(12));

            stage.setTitle("Tile Game");
            stage.setScene(scene);

            // play button
            playButton.setOnAction(e -> {
                stage.setScene(new Scene(bp1, 800, 450));
            });

            // level1 events
            bp1.setOnMouseMoved(e -> {
                score1.setText("Number Of Moves: " + level1.getCounter());
                if (level1.getAnimation()) {
                    button1.setDisable(false);
                }
            });

            button1.setOnAction(e -> {
                stage.setScene(new Scene(bp2, 800, 450));
            });

            // level2 events
            bp2.setOnMouseMoved(e -> {
                score2.setText("Number Of Moves: " + level2.getCounter());
                if (level2.getAnimation()) {
                    button2.setDisable(false);
                }
            });

            button2.setOnAction(e -> {
                stage.setScene(new Scene(bp3, 800, 450));
            });

            // level3 events
            bp3.setOnMouseMoved(e -> {
                score3.setText("Number Of Moves: " + level3.getCounter());
                if (level3.getAnimation()) {
                    button3.setDisable(false);
                }
            });

            button3.setOnAction(e -> {
                stage.setScene(new Scene(bp4, 800, 450));
            });

            // level4 events
            bp4.setOnMouseMoved(e -> {
                score4.setText("Number Of Moves: " + level4.getCounter());
                if (level4.getAnimation()) {
                    button4.setDisable(false);
                }
            });

            button4.setOnAction(e -> {
                stage.setScene(new Scene(bp5, 800, 450));
            });

            // level5 events
            bp5.setOnMouseMoved(e -> {
                score5.setText("Number Of Moves: " + level5.getCounter());
                if (level5.getAnimation()) {
                    button5.setDisable(false);
                }
            });

            button5.setOnAction(e -> {
                stage.setScene(new Scene(bp6, 800, 450));
            });

            // level6 events
            bp6.setOnMouseMoved(e -> {
                score6.setText("Number Of Moves: " + level6.getCounter());
            });
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
