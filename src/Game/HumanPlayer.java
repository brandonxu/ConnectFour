package Game;

public class HumanPlayer extends BasePlayer
{
    private String characterOnBoard = "O";

    public HumanPlayer(String name)
    {
        super(name);
    }

    //Drops a chip in to a column
    public int move(Board board, int col, String openPositionChar)
    {
        int i = 0;
        if (board != null)
        {
            int rowCount = board.getRowCount();
            for (i = rowCount - 1; i >= 0; i--)
            {
                if (board.isGridFilledWith(i, col, openPositionChar))
                {
                    board.fillGrid(i, col, getCharacterOnBoard());
                    break;
                }
            }
        }

        return i;
    }

    //Returns the character of the player
    public String getCharacterOnBoard()
    {
        return characterOnBoard;
    }
}
