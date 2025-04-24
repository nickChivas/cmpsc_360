package code.visualcountingproblems;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author nicho
 */
public class MultisetController {

    @FXML
    Button returnButton;

    @FXML
    Label caption;

    @FXML
    Pane content;

    @FXML
    void handleReturnButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent animationScene = loader.load();
        PrimaryController primaryController = loader.getController();
        Scene newScene = new Scene(animationScene, 1280, 720);
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(newScene);
        currentStage.setResizable(false);
        currentStage.show();
    }

    @FXML
    public void startAnimation() throws InterruptedException {
        Timeline animation = new Timeline();
        Duration startTime = Duration.seconds(3);
        KeyFrame hideReturnButton = new KeyFrame(Duration.seconds(0), new KeyValue(returnButton.visibleProperty(), false));
        animation.getKeyFrames().add(hideReturnButton);
        startTime = incrementStartTime(startTime, 2);

        KeyFrame generalize = new KeyFrame(startTime, new KeyValue(caption.textProperty(), "Let's try to generalize this problem."));
        animation.getKeyFrames().add(generalize);
        startTime = incrementStartTime(startTime, 2);

        KeyFrame clearPane = new KeyFrame(startTime, event -> content.getChildren().clear());
        animation.getKeyFrames().add(clearPane);
        startTime = incrementStartTime(startTime, 2);

        KeyFrame askQuestion = new KeyFrame(startTime, new KeyValue(caption.textProperty(), "How many ways can n be written as the sum of m integers?"));
        animation.getKeyFrames().add(askQuestion);
        startTime = incrementStartTime(startTime, 2);

        KeyFrame populatePane = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text leftText = new Text("n = 5");
            leftText.setFont(new Font("Cambria", 36));
            leftText.setLayoutX(590);
            leftText.setLayoutY(275);

            Text rightText = new Text("a + b + c + d = n");
            rightText.setFont(new Font("Cambria", 36));
            rightText.setLayoutX(520);
            rightText.setLayoutY(225);

            content.getChildren().addAll(leftText, rightText);
        });
        animation.getKeyFrames().add(populatePane);
        startTime = incrementStartTime(startTime, 2);

        KeyFrame countFrame1 = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text leftText = new Text("Count: 1");
            leftText.setFont(new Font("Cambria", 36));
            leftText.setLayoutX(590);
            leftText.setLayoutY(275);

            Text rightText = new Text("5 + 0 + 0 + 0 = 5");
            rightText.setFont(new Font("Cambria", 36));
            rightText.setLayoutX(520);
            rightText.setLayoutY(225);

            content.getChildren().addAll(leftText, rightText);
        });
        animation.getKeyFrames().add(countFrame1);
        startTime = incrementStartTime(startTime, 2);

        KeyFrame realize = new KeyFrame(startTime, new KeyValue(caption.textProperty(), "Each variety is different, so this is another possible combination."));
        animation.getKeyFrames().add(realize);
        startTime = incrementStartTime(startTime, 3);

        KeyFrame countFrame2 = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text leftText = new Text("Count: 2");
            leftText.setFont(new Font("Cambria", 36));
            leftText.setLayoutX(590);
            leftText.setLayoutY(275);

            Text rightText = new Text("0 + 5 + 0 + 0 = 5");
            rightText.setFont(new Font("Cambria", 36));
            rightText.setLayoutX(520);
            rightText.setLayoutY(225);

            content.getChildren().addAll(leftText, rightText);
        });
        animation.getKeyFrames().add(countFrame2);
        startTime = incrementStartTime(startTime, 5);

        KeyFrame remark = new KeyFrame(startTime, new KeyValue(caption.textProperty(), "You could count each combination, but we already have a formula for that!"));
        animation.getKeyFrames().add(remark);
        startTime = incrementStartTime(startTime, 1);

        KeyFrame countFrame3 = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text leftText = new Text("Count: 3");
            leftText.setFont(new Font("Cambria", 36));
            leftText.setLayoutX(590);
            leftText.setLayoutY(275);

            Text rightText = new Text("4 + 1 + 0 + 0 = 5");
            rightText.setFont(new Font("Cambria", 36));
            rightText.setLayoutX(520);
            rightText.setLayoutY(225);

            content.getChildren().addAll(leftText, rightText);
        });
        animation.getKeyFrames().add(countFrame3);
        startTime = incrementStartTime(startTime, 1);

        KeyFrame countFrame4 = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text leftText = new Text("Count: 4");
            leftText.setFont(new Font("Cambria", 36));
            leftText.setLayoutX(590);
            leftText.setLayoutY(275);

            Text rightText = new Text("3 + 1 + 1 + 0 = 5");
            rightText.setFont(new Font("Cambria", 36));
            rightText.setLayoutX(520);
            rightText.setLayoutY(225);

            content.getChildren().addAll(leftText, rightText);
        });
        animation.getKeyFrames().add(countFrame4);
        startTime = incrementStartTime(startTime, 1);

        KeyFrame countFrame5 = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text leftText = new Text("Count: 5");
            leftText.setFont(new Font("Cambria", 36));
            leftText.setLayoutX(590);
            leftText.setLayoutY(275);

            Text rightText = new Text("2 + 1 + 1 + 1 = 5");
            rightText.setFont(new Font("Cambria", 36));
            rightText.setLayoutX(520);
            rightText.setLayoutY(225);

            content.getChildren().addAll(leftText, rightText);
        });
        animation.getKeyFrames().add(countFrame5);
        startTime = incrementStartTime(startTime, 1);

        KeyFrame countFrame6 = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text leftText = new Text("Count: 6");
            leftText.setFont(new Font("Cambria", 36));
            leftText.setLayoutX(590);
            leftText.setLayoutY(275);

            Text rightText = new Text("1 + 2 + 1 + 1 = 5");
            rightText.setFont(new Font("Cambria", 36));
            rightText.setLayoutX(520);
            rightText.setLayoutY(225);

            content.getChildren().addAll(leftText, rightText);
        });
        animation.getKeyFrames().add(countFrame6);
        startTime = incrementStartTime(startTime, 1);

        KeyFrame countFrame7 = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text leftText = new Text("Count: 7");
            leftText.setFont(new Font("Cambria", 36));
            leftText.setLayoutX(590);
            leftText.setLayoutY(275);

            Text rightText = new Text("1 + 1 + 2 + 1 = 5");
            rightText.setFont(new Font("Cambria", 36));
            rightText.setLayoutX(520);
            rightText.setLayoutY(225);

            content.getChildren().addAll(leftText, rightText);
        });
        animation.getKeyFrames().add(countFrame7);
        startTime = incrementStartTime(startTime, 1);

        KeyFrame countFrame8 = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text leftText = new Text("Count: 8");
            leftText.setFont(new Font("Cambria", 36));
            leftText.setLayoutX(590);
            leftText.setLayoutY(275);

            Text rightText = new Text("1 + 1 + 1 + 2 = 5");
            rightText.setFont(new Font("Cambria", 36));
            rightText.setLayoutX(520);
            rightText.setLayoutY(225);

            content.getChildren().addAll(leftText, rightText);
        });
        animation.getKeyFrames().add(countFrame8);
        startTime = incrementStartTime(startTime, 1);

        KeyFrame countFrame9 = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text leftText = new Text("Count: 9");
            leftText.setFont(new Font("Cambria", 36));
            leftText.setLayoutX(590);
            leftText.setLayoutY(275);

            Text rightText = new Text("2 + 2 + 1 + 0 = 5");
            rightText.setFont(new Font("Cambria", 36));
            rightText.setLayoutX(520);
            rightText.setLayoutY(225);

            content.getChildren().addAll(leftText, rightText);
        });
        animation.getKeyFrames().add(countFrame9);
        startTime = incrementStartTime(startTime, 1);

        KeyFrame countFrame10 = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text leftText = new Text("Count: 10");
            leftText.setFont(new Font("Cambria", 36));
            leftText.setLayoutX(580);
            leftText.setLayoutY(275);

            Text rightText = new Text("2 + 2 + 0 + 1 = 5");
            rightText.setFont(new Font("Cambria", 36));
            rightText.setLayoutX(520);
            rightText.setLayoutY(225);

            content.getChildren().addAll(leftText, rightText);
        });
        animation.getKeyFrames().add(countFrame10);
        startTime = incrementStartTime(startTime, 1);

        KeyFrame newQuestion = new KeyFrame(startTime, new KeyValue(caption.textProperty(), "How many ways can we add to n with m variables?"));
        animation.getKeyFrames().add(newQuestion);
        startTime = incrementStartTime(startTime, 3);

        KeyFrame formula = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text chooseText = new Text("( n + m - 1 )\n( m - 1 )");
            chooseText.setFont(new Font("Cambria", 36));
            chooseText.setTextAlignment(TextAlignment.CENTER);
            chooseText.setLayoutX(560);
            chooseText.setLayoutY(220);
            content.getChildren().addAll(chooseText);
        });
        animation.getKeyFrames().add(formula);
        startTime = incrementStartTime(startTime, 4);

        KeyFrame plugInText = new KeyFrame(startTime, new KeyValue(caption.textProperty(), "How many ways can we add to 5 with 4 variables?"));
        animation.getKeyFrames().add(plugInText);
        startTime = incrementStartTime(startTime, 2);

        KeyFrame plugIn = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text chooseText = new Text("( 5 + 4 - 1 )\n( 4 - 1 )");
            chooseText.setFont(new Font("Cambria", 36));
            chooseText.setTextAlignment(TextAlignment.CENTER);
            chooseText.setLayoutX(560);
            chooseText.setLayoutY(220);
            content.getChildren().addAll(chooseText);
        });
        animation.getKeyFrames().add(plugIn);
        startTime = incrementStartTime(startTime, 4);

        KeyFrame algebra = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text chooseText = new Text("( 8 )\n( 3 )");
            chooseText.setFont(new Font("Cambria", 36));
            chooseText.setTextAlignment(TextAlignment.CENTER);
            chooseText.setLayoutX(590);
            chooseText.setLayoutY(220);
            content.getChildren().addAll(chooseText);
        });
        animation.getKeyFrames().add(algebra);
        startTime = incrementStartTime(startTime, 4);

        KeyFrame almost = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text chooseText = new Text("8! / (3! x 5!) =");
            chooseText.setFont(new Font("Cambria", 36));
            chooseText.setTextAlignment(TextAlignment.CENTER);
            chooseText.setLayoutX(570);
            chooseText.setLayoutY(220);
            content.getChildren().addAll(chooseText);
        });
        animation.getKeyFrames().add(almost);
        startTime = incrementStartTime(startTime, 4);

        KeyFrame finished = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text chooseText = new Text("8! / (3! x 5!) = 56 ways");
            chooseText.setFont(new Font("Cambria", 36));
            chooseText.setTextAlignment(TextAlignment.CENTER);
            chooseText.setLayoutX(520);
            chooseText.setLayoutY(220);
            content.getChildren().addAll(chooseText);
        });
        animation.getKeyFrames().add(finished);
        startTime = incrementStartTime(startTime, 2);

        KeyFrame whyMultiset = new KeyFrame(startTime, new KeyValue(caption.textProperty(), "This is called counting multisets because rather than a normal set,"));
        animation.getKeyFrames().add(whyMultiset);
        startTime = incrementStartTime(startTime, 2);

        KeyFrame normal = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text chooseText = new Text("S = {Red, Blue, Green, Yellow, Orange}");
            chooseText.setFont(new Font("Cambria", 36));
            chooseText.setTextAlignment(TextAlignment.CENTER);
            chooseText.setLayoutX(340);
            chooseText.setLayoutY(220);
            content.getChildren().addAll(chooseText);
        });
        animation.getKeyFrames().add(normal);
        startTime = incrementStartTime(startTime, 2);

        KeyFrame thatsWhy = new KeyFrame(startTime, new KeyValue(caption.textProperty(), "we have a set with repeated items."));
        animation.getKeyFrames().add(thatsWhy);
        startTime = incrementStartTime(startTime, 2);

        KeyFrame multi = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text chooseText = new Text("S = {Blue, Blue, Blue, Blue, Blue}");
            chooseText.setFont(new Font("Cambria", 36));
            chooseText.setTextAlignment(TextAlignment.CENTER);
            chooseText.setLayoutX(430);
            chooseText.setLayoutY(220);
            content.getChildren().addAll(chooseText);
        });
        animation.getKeyFrames().add(multi);
        startTime = incrementStartTime(startTime, 2);

        KeyFrame moreQuestions = new KeyFrame(startTime, new KeyValue(caption.textProperty(), "Try mapping what you saw here to these problems."));
        animation.getKeyFrames().add(moreQuestions);
        startTime = incrementStartTime(startTime, 2);

        KeyFrame problems = new KeyFrame(startTime, event -> {
            content.getChildren().clear();
            Text chooseText = new Text("1. How many ways can 4 children share 15 chocolate chip cookies?\n2. If, one day, 1000 people bought movie tickets to a movie with 5 showings,\nhow many ways were those people split into each showing given a minimum\nof 50 was needed to play the movie?");
            chooseText.setFont(new Font("Cambria", 24));
            chooseText.setTextAlignment(TextAlignment.LEFT);
            chooseText.setLayoutX(200);
            chooseText.setLayoutY(220);
            content.getChildren().addAll(chooseText);
        });
        animation.getKeyFrames().add(problems);
        startTime = incrementStartTime(startTime, 2);

        KeyFrame revealReturnButton = new KeyFrame(startTime, new KeyValue(returnButton.visibleProperty(), true));
        animation.getKeyFrames().add(revealReturnButton);
        animation.play();
    }

    private Duration incrementStartTime(Duration time, int seconds) {
        Duration returnTime = time.add(Duration.seconds(seconds));
        return returnTime;
    }
}
