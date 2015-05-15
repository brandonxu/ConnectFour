package Game;
public  abstract class ComputerPlayer extends BasePlayer
{
    private String characterOnBoard;

    public ComputerPlayer(String name)
    {
        super(name);
        characterOnBoard = "X";
    }

    public abstract Position move(Board board, String openSpots, String opponent);

    public String getCharacterOnBoard()
    {
        return characterOnBoard;
    }
}
