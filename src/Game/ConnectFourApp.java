package Game;
import java.util.*;
import java.io.*;

public class ConnectFourApp {
    static int colMove;
    public static void main(String [] args) throws Exception{
        Scanner input = new Scanner(System.in);
        String name;
        String goAgain;
        HumanPlayer humanPlayer;
        ComputerPlayer computerPlayer;
        ConnectFour game;
        File file = new File("Result.rtf");
        if(file.exists())
        {
            FileReader fr = new FileReader("Result.rtf");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null)
            {
                System.out.print(line);
                System.out.println();
            }
            System.out.println("");
            fr.close();
        }

        System.out.print("Enter your name: ");
        name = input.next();
        int difficulty;
        do
        {
            System.out.print("Select a Difficulty: (1) Easy (2)Hard\n");
            difficulty = input.nextInt();
        }while(!(difficulty == 1 || difficulty == 2));

        if(difficulty == 1)
        {
            computerPlayer = new EasyComputer();
        }
        else
        {
            computerPlayer = new HardComputer();
        }

        humanPlayer = new HumanPlayer(name);
        game = new ConnectFour(humanPlayer, computerPlayer);
        do{
            game.clearBoard();
            game.drawBoard();
            while(true){
                if(game.getTurn().equalsIgnoreCase(humanPlayer.getCharacterOnBoard())){
                    do{
                        try
                        {
                            System.out.print("Enter column move: ");
                            colMove =  input.nextInt();
                        }
                        catch(InputMismatchException e)
                        {
                            input.nextLine();
                            System.out.print("You must enter an integer. Please try again.\n");
                            colMove = -1;
                        }
                    }while(!game.validMove(colMove));
                    game.moveHumanPlayer(colMove);
                    game.switchTurns();
                    System.out.println();
                    game.drawBoard();
                    System.out.println();
                    if(game.gameOver())
                        break;
                }
                else{
                    System.out.println("Computer is moving");
                    game.moveComputer();
                    game.switchTurns();
                    System.out.println();
                    game.drawBoard();
                    if(game.gameOver())
                        break;
                }
            }
            if(game.humanHasWon()){
                System.out.println("\nCongrats " + humanPlayer.getName() +
                        " you win!");
                game.getHumanPlayer().increaseWins();
            }
            else if(game.computerHasWon()){
                System.out.println("\nComputer Wins.");
                game.getComputerPlayer().increaseWins();
            }
            else{
                System.out.println("\nTie.");
            }
            System.out.print("Want to play again?(Y/N)");
            goAgain = input.next();
        }while(goAgain.equalsIgnoreCase("Y"));

        Date dt = new Date();
        String result = String.format("\n%25s won %d     %25s won %d     %25s\n", computerPlayer.getName(), computerPlayer.getWins(), humanPlayer.getName(), humanPlayer.getWins(), dt);
        FileWriter fw = new FileWriter("Result.rtf", true);
        fw.write(result);
        fw.close();
        System.out.println(game.getHumanPlayer().getName() + " you won " +  game.getHumanPlayer().getWins() + " games.");
    }

}