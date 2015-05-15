package Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Graphical game
public class GraphicalBoard extends Application
{
    HumanPlayer humanPlayer = new HumanPlayer("");
    ComputerPlayer computerPlayer;
    ConnectFour game;
    Circle[][] circles = new Circle[6][7];
    Stage  difficulty = new Stage();
    Stage name = new Stage();
    Button[] human = new Button[7];
    BorderPane borderPane = new BorderPane();
    Win winner;
    Text playerScore;
    Text computerScore;

    @Override
    public void start(Stage primaryStage)
    {
        GridPane board = new GridPane();
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        board.setPadding(new Insets(10, 10, 10, 10));
        board.setVgap(5.5);
        board.setHgap(5.5);
        board.setAlignment(Pos.CENTER);
        Button reset = new Button("Reset");

        for(int i = 0; i < 7; i++)
        {
            human[i] = new Button("" + i);
            hBox.getChildren().add(human[i]);
            human[i].setOnAction(new ButtonHandler());

        }

        for(int i = 0; i < 6; i++)
        {
            for(int j = 0; j < 7; j++)
            {
                circles[i][j] = new Circle(30);
                circles[i][j].setStroke(Color.BLACK);
                circles[i][j].setFill(Color.WHITE);
            }
        }

        for(int i = 0; i <= 5; i++)
        {
            for(int j = 0; j <= 6; j++)
            {
                board.add(circles[i][j], j, i);
            }
        }

        hBox.spacingProperty().bind(circles[0][0].radiusProperty().add(0.2));
        borderPane.setCenter(board);
        borderPane.setTop(hBox);
        VBox scoreBoard = new VBox();
        scoreBoard.getChildren().add(0, new Text("Score"));
        playerScore = new Text();
        computerScore = new Text();
        scoreBoard.getChildren().add(1, playerScore);
        scoreBoard.getChildren().add(2, computerScore);
        borderPane.setRight(scoreBoard);
        scoreBoard.setAlignment(Pos.TOP_CENTER);
        scoreBoard.setPadding(new Insets(10, 20, 10, 10));


        Scene scene = new Scene(borderPane, 600, 600);
        hBox.getChildren().add(reset);
        reset.setOnAction(new ResetHandler());

        HBox hBox1 = new HBox();
        Button easy = new Button("Easy");
        Button hard = new Button("Hard");

        GridPane nameGrid = new GridPane();
        nameGrid.setPadding(new Insets(10, 10, 10, 10));
        nameGrid.setVgap(5);
        nameGrid.setHgap(5);
        final TextField nameField = new TextField();
        nameField.setPromptText("Enter your first name.");
        nameField.setPrefColumnCount(10);
        nameField.getText();
        GridPane.setConstraints(nameField, 0, 0);
        nameGrid.getChildren().add(nameField);
        Button submit = new Button("Enter");
        GridPane.setConstraints(submit, 1, 0);
        nameGrid.getChildren().add(submit);
        name.setTitle("Name");
        Scene scene1  = new Scene(nameGrid, 250, 100);
        name.setScene(scene1);
        difficulty.showAndWait();

        easy.setOnAction(new easyHandler());
        hard.setOnAction(new hardHandler());
        hBox1.getChildren().addAll(easy, hard);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setPadding(new Insets(5, 5, 5, 5));
        hBox1.setSpacing(10);
        Scene scene2  = new Scene(hBox1, 250, 100);
        difficulty.setTitle("Difficulty");
        difficulty.setScene(scene2);
        difficulty.showAndWait();

        primaryStage.setTitle("Connect Four");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class submitHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            name.hide();
        }
    }

    class ResetHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            game.clearBoard();
            winner.clear();
            for(int i = 0; i < 6; i++)
            {
                for(int j = 0; j < 7; j++)
                {
                    circles[i][j].setStroke(Color.BLACK);
                    circles[i][j].setFill(Color.WHITE);
                }
            }

        }
    }
    class easyHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            computerPlayer = new EasyComputer();
            game = new ConnectFour(humanPlayer, computerPlayer);
            difficulty.hide();
        }
    }

    class hardHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            computerPlayer = new HardComputer();
            game = new ConnectFour(humanPlayer, computerPlayer);
            difficulty.hide();
        }
    }

    class ButtonHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            if(game.humanHasWon())
            {
                game.getHumanPlayer().increaseWins();
                System.out.println(game.getComputerPlayer().getWins());
                playerScore.setText("P: " + game.getHumanPlayer().getWins());
                winner = new Win("Human Won");
                borderPane.setBottom(winner.getStackPane());
            }
            else if(game.computerHasWon())
            {
                game.getComputerPlayer().increaseWins();
                computerScore.setText("C: " + game.getComputerPlayer().getWins());
                winner = new Win("Computer Won");
                borderPane.setBottom(winner.getStackPane());
            }

            Object button = e.getSource();
            int column = Integer.parseInt(((Button)button).getText());
            int row = 0;
            if(!(game.computerHasWon() || game.humanHasWon()))
            {
                if(game.validMove(column))
                {
                    row = game.moveHumanPlayer(column);
                    circles[row][column].setFill(Color.RED);
                    game.switchTurns();
                    Position position = game.moveComputer();
                    circles[position.getRow()][position.getColumn()].setFill(Color.BLACK);
                    game.switchTurns();
                }
            }
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

    class Win
    {
        StackPane stackPane;
        Label labelWin;
        public Win(String winner)
        {
            stackPane = new StackPane();
            labelWin = new Label(winner);
            stackPane.setAlignment(Pos.CENTER);
            stackPane.getChildren().add(labelWin);
        }

        public StackPane getStackPane()
        {
            return stackPane;
        }

        public void clear()
        {
            stackPane.getChildren().clear();
        }
}


