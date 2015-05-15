package Game;

public class HardComputer extends ComputerPlayer
{
    public HardComputer()
    {
        super("Difficult Computer");
    }
    private Position fill(Board board, int col, String openPositionChar)
    {
        Position position = new Position();

        int rowCount = board.getRowCount();
        for (int i = rowCount -1; i>=0; i--)
        {
            if (board.isGridFilledWith(i, col, openPositionChar))
            {
                position.setColumn(col);
                position.setRow(i);
                board.fillGrid(i, col, this.getCharacterOnBoard());
                break;
            }
        }
        return position;
    }

    @Override
    public Position move(Board board, String openPositionChar, String opponentCharacterOnBoard)
    {

        int colCount = board.getColCount();
        int rowCount = board.getRowCount();

        //check rows
        for (int i = 0; i < rowCount; i++)
        {
            int continuousMoveCount = 0;
            for (int j = 0; j < colCount; j++)
            {
                if (board.isGridFilledWith(i, j, opponentCharacterOnBoard))
                {
                    continuousMoveCount ++;
                }
                else
                {
                    continuousMoveCount = 0;
                }

                if (continuousMoveCount == 3)
                {
                    int possibleRightCol = j + 1;
                    if (possibleRightCol < colCount && board.isGridFilledWith(i, possibleRightCol, openPositionChar))
                    {
                        return fill(board, possibleRightCol, openPositionChar);
                        //board.fillGrid(i, possibleRightCol, getcharacterOnBoard());
                    }

                    int possibleLeftCol = j - 3;
                    if (possibleLeftCol >= 0  && board.isGridFilledWith(i, possibleLeftCol, openPositionChar))
                    {
                        return fill(board, possibleLeftCol, openPositionChar);
                        //board.fillGrid(i, possibleLeftCol, getcharacterOnBoard());
                    }
                }
            }
        }

        //check columns
        for (int j = 0; j < colCount; j++)
        {
            int continuousMoveCount = 0;
            for (int i = 0; i < rowCount; i++)
            {
                if (board.isGridFilledWith(i, j, opponentCharacterOnBoard))
                {
                    continuousMoveCount ++;
                }
                else
                {
                    continuousMoveCount = 0;
                }

                if (continuousMoveCount == 3)
                {
                    int possibleRow = i - 3;
                    if (possibleRow >=0 && board.isGridFilledWith(possibleRow, j, openPositionChar))
                    {
                        return fill(board, j, openPositionChar);
                    }
                }
            }
        }


        //Otherwise the first unused grid
        for (int j=0; j<colCount; j++)
        {
            for (int i= rowCount -1; i>=0; i-- )
            {
                if (board.isGridFilledWith(i, j, openPositionChar))
                {
                    return fill(board, j, openPositionChar);

                }
            }
        }
        return null;
    }
}
