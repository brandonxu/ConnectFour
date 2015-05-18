package Game;
public class EasyComputer extends ComputerPlayer
{

    public EasyComputer()
    {
        super("Easy Computer");
    }

    @Override
    public Position move(Board board, String openPosition, String opponent)
    {
        Position position = new Position();
        int i = 0;

        boolean moved = false;
        if(board != null)
        {
            while(!moved)
            {
                int randomColumn = (int)(Math.random() * (board.getColCount()));

                for(i = board.getRowCount() - 1; i >= 0; i--)
                {
                    if(board.isCellFilledWith(i, randomColumn, openPosition))
                    {
                        board.DropDisc(i, randomColumn, getCharacterOnBoard());
                        moved = true;
                        position.setColumn(randomColumn);
                        position.setRow(i);
                        break;
                    }
                }
            }
        }

        return position;
    }
}
