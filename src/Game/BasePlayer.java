package Game;

public class BasePlayer
{
    private String name;
    private int wins;
    private int losses;

    //Constructor
    public BasePlayer()
    {
        name = "Default Player";
        wins = 0;
        losses = 0;
    }

    //Constructor sets name
    public BasePlayer(String n)
    {
        name =  n;
        wins = 0;
        losses = 0;
    }

    //Increases number of wins by one
    public void increaseWins()
    {
        wins++;
    }

    //Increases number of losses by one
    public void increaseLosses()
    {
        losses--;
    }

    //Resets wins and losses to zero
    public void resetStats()
    {
        wins =0;
        losses=0;
    }

    /**
     *
     * @return Returns player name
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @return number of wins
     */
    public int getWins()
    {
        return wins;
    }

    /**
     *
     * @return number of losses
     */
    public int getLosses()
    {
        return losses;
    }

}