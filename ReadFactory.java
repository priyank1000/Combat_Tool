/**
 * This is part of the Factory design pattern
 * This class is the Decision Maker to decide which subclass to use
 * that is if the user has typed in "C" then create new game.
 * Else if user has typed "L" then Load existing game.
 *
 * @Author Priyank Joshi
 * @Date 4/10/2017
 **/

public class ReadFactory
{
    private static Read testRead = null;

	/**
     * A method to set testRead to read of type Read
     *
     * @param: read of type Read
     * @return: no returns
     **/
    public static void setTestRead(Read read)
    {
        testRead = read;
    }

    /**
     * A method to read game (either create a new game or load an existing game)
     * its a method that creates and returns and object
     *
     * @param: String option, UserInterface ui
     * @return: Read Object
     **/
    public static Read readGame(String option, UserInterface ui)
    {
        Read read = null;
        if(testRead == null)
        {
            if(option.equals("C"))
            {
                read = new CreateNewGame(ui);
            }
            if(option.equals("L"))
            {
                read = new LoadExistingGame(ui);
            }
        }
        else
        {
            read = testRead;
        }
        return read;
    }
}