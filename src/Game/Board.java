package Game;

public class Board
{

    private String[][] boardGrids;
    private int rowCount;
    private int colCount;
    private String openPositionChar;

    public Board(int aRowCount, int aColCount, String aOenPositionChar)
    {
        rowCount = aRowCount;
        colCount = aColCount;
        boardGrids = new String[rowCount][colCount];
        openPositionChar = aOenPositionChar;
        clearBoard();
    }

    //Returns number of rows
    public int getRowCount()
    {
        return rowCount;
    }

    //Returns number of columns
    public int getColCount()
    {
        return colCount;
    }

    //Clears the entire board
    public void clearBoard()
    {
        if (boardGrids != null)
        {
            for (int i=0; i < rowCount; i++)
            {
                for (int j=0; j < colCount; j++)
                {
                    boardGrids[i][j] = openPositionChar;
                }
            }
        }
    }

    //Prints out the board
    public void drawBoard()
    {
        if (boardGrids != null)
        {
            System.out.print("  ");
            for (int j=0; j < colCount; j++)
            {
                System.out.printf("%d ", j);
            }
            System.out.println();

            for (int i = 0; i < rowCount; i++)
            {
                System.out.printf("%d ", i);
                for (int j=0; j < colCount; j++)
                {
                    System.out.printf("%s ", boardGrids[i][j]);
                }
                System.out.print("|");
                System.out.println();
            }
            for(int k = 0; k < colCount * 2 + 3; k++)
            {
                System.out.print("-");
            }
        }
        System.out.println("\n");
    }

    public boolean validMove(int possibleCol)
    {
        if (possibleCol<0 || possibleCol >= colCount)
        {
            return false;
        }

        for (int i=0; i< rowCount; i++)
        {
            if (boardGrids[i][possibleCol].equalsIgnoreCase(openPositionChar))
            {
                return true;
            }
        }

        return false;
    }

    //Returns if board is filled or not
    public boolean isBoardFilled()
    {
        boolean isBoardfilled = true;
        for (int i=0; i<rowCount; i++)
        {
            for (int j=0;  j<colCount; j++)
            {
                if (boardGrids[i][j].equalsIgnoreCase(openPositionChar))
                {
                    isBoardfilled = false;
                    break;
                }
            }
        }
        return isBoardfilled;
    }

    //Checks for winner
    public boolean checkForWinner(String player)
    {
        for (int i = 0; i < rowCount; i++)
        {
            int charCount = 0;
            for (int j = 0; j < colCount; j++)
            {
                if (boardGrids[i][j].equalsIgnoreCase(player))
                {
                    charCount ++;
                }
                else
                {
                    charCount = 0;
                }

                if (charCount == 4)
                {
                    return true;
                }
            }
        }

        //check columns
        for (int j = 0; j < colCount; j++)
        {
            int charCount = 0;
            for (int i = 0; i < rowCount; i++)
            {
                if (boardGrids[i][j].equalsIgnoreCase(player))
                {
                    charCount ++;
                }
                else
                {
                    charCount = 0;
                }

                if (charCount == 4)
                {
                    return true;
                }
            }
        }

        //check upper left diagonals starting from (0,0)
        for(int i = 0; i< rowCount; i++)
        {
            int row = i;
            int col = 0;
            int continuousMoveCount = 0;
            while (row >= 0 && col < colCount)
            {
                if (boardGrids[row][col].equalsIgnoreCase(player))
                {
                    continuousMoveCount ++;
                }
                else
                {
                    continuousMoveCount = 0;
                }

                if (continuousMoveCount == 4)
                {
                    return true;
                }

                row--;
                col++;
            }
        }

        //check lower right diagonals starting from (rowCount -1,0)
        for (int j = 0; j< colCount; j++)
        {
            int row = rowCount -1;
            int col = j;
            int continuousMoveCount = 0;
            while (row >= 0 && col < colCount)
            {
                if (boardGrids[row][col].equalsIgnoreCase(player))
                {
                    continuousMoveCount ++;
                }
                else
                {
                    continuousMoveCount = 0;
                }

                if (continuousMoveCount == 4)
                {
                    return true;
                }

                row--;
                col++;
            }
        }

        //check upper right diagonals starting from (0, colCount-1)
        for (int i = 0; i< rowCount; i++)
        {
            int row = i;
            int col = colCount-1;
            int continuousMoveCount = 0;
            while (row < rowCount && col >= 0)
            {
                if (boardGrids[row][col].equalsIgnoreCase(player))
                {
                    continuousMoveCount ++;
                }
                else
                {
                    continuousMoveCount = 0;
                }

                if (continuousMoveCount == 4)
                {
                    return true;
                }

                row++;
                col--;
            }
        }

        //check lower left diagonals starting from (rowCount -1, colCount - 1)
        for (int j =colCount - 1 ; j>=0 ; j--)
        {
            int row = rowCount -1;
            int col = j;
            int continuousMoveCount = 0;
            while (row >= 0 && col >= 0)
            {
                if (boardGrids[row][col].equalsIgnoreCase(player))
                {
                    continuousMoveCount ++;
                }
                else
                {
                    continuousMoveCount = 0;
                }

                if (continuousMoveCount == 4)
                {
                    return true;
                }

                row--;
                col--;
            }
        }

        //check diagonals

        return false;
    }

    //Check if a spot if filled with certain character
    public boolean isGridFilledWith(int row, int col, String aChar)
    {
        if (row <0 || row >= rowCount)
        {
            return false;
        }

        if (col <0 || col >= colCount)
        {
            return false;
        }

        if (boardGrids[row][col].equalsIgnoreCase(aChar))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //Fills the specified spot with a character
    public void fillGrid(int row, int col, String aChar)
    {
        if (row <0 || row >= rowCount)
        {
            return;
        }

        if (col <0 || col >= colCount)
        {
            return;
        }

        boardGrids[row][col] = aChar;
    }
}
