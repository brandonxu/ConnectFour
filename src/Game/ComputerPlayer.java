package Game;
public  abstract class ComputerPlayer extends BasePlayer
{
    private String characterOnBoard;

    public ComputerPlayer(String name)
    {
        super(name);
        characterOnBoard = "X";
    }

    /**
     *
     * @param board
     * @param openSpots
     * @param opponent
     * @return
     */
    public abstract Position move(Board board, String openSpots, String opponent);

    /**
     * 
     * @return
     */
    public String getCharacterOnBoard()
    {
        return characterOnBoard;
    }
}
