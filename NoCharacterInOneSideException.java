/**
 * This class is used to create a new exception type relating to whether there is
 * no character in one of the side. In other words, having a game but with onlt one
 * team, the opposition team is not there.
 *
 * @Author Priyank Joshi
 * @Date 04/10/2017
 **/

public class NoCharacterInOneSideException extends Exception
{
    public NoCharacterInOneSideException(String msg)
    {

        super(msg);
    }

    public NoCharacterInOneSideException(String msg, Throwable cause)
    {

        super(msg, cause);
    }
}