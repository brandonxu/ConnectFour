package Game;

public class ConnectFour
{
    private static final int NUM_COLS = 7;
    private static final int NUM_ROWS = 6;
    private static String openPositionChar = " ";

    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    private String turn;
    private Board board;

    public ConnectFour(HumanPlayer p1, ComputerPlayer p2)
    {
        humanPlayer = p1;
        computerPlayer = p2;
        turn  = humanPlayer.getCharacterOnBoard();
        board = new Board(NUM_ROWS, NUM_COLS, openPositionChar);
        clearBoard();
    }

    public int getNUM_COLS()
    {
        return NUM_COLS;
    }

    public int getNUM_ROWS()
    {
        return NUM_ROWS;
    }

    public HumanPlayer getHumanPlayer()
    {
        return humanPlayer;
    }

    public ComputerPlayer getComputerPlayer()
    {
        return computerPlayer;
    }

    public void clearBoard()
    {
        board.clearBoard();
    }

    public String getTurn()
    {
        return turn;
    }

    public int moveHumanPlayer(int nextCol)
    {
        return humanPlayer.move(board, nextCol, openPositionChar);
    }

    public Position moveComputer()
    {
        return computerPlayer.move(board, openPositionChar, humanPlayer.getCharacterOnBoard());
    }

    public void drawBoard()
    {
        board.drawBoard();
        System.out.println();
        System.out.println();
    }

    public void switchTurns()
    {
        if (turn.equalsIgnoreCase(humanPlayer.getCharacterOnBoard()))
        {
            turn = computerPlayer.getCharacterOnBoard();
        }
        else
        {
            turn = humanPlayer.getCharacterOnBoard();
        }
    }

    public boolean validMove(int possibleCol)
    {
        return board.validMove(possibleCol);
    }

    public boolean humanHasWon()
    {
        return checkForWinner(humanPlayer.getCharacterOnBoard());
    }

    public boolean computerHasWon()
    {
        return checkForWinner(computerPlayer.getCharacterOnBoard());
    }

    public boolean tieGame()
    {
        boolean isTie = false;
        boolean isBoardfilled = board.isBoardFilled();

        if (isBoardfilled)
        {
            if (!humanHasWon() && !computerHasWon())
            {
                isTie = true;
            }
        }
        return isTie;
    }

    public boolean gameOver()
    {
        return (humanHasWon() || computerHasWon() || tieGame());
    }

    private boolean checkForWinner(String player)
    {
        return board.checkForWinner(player);
    }
}