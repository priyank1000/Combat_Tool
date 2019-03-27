/**
 * This class is used to create a new exception type which will throw
 * whenever a character has a unique ability which is not in the 
 * ability file.
 * For example Character: Joe has abilities --> Ice Blast, Arrow Attack
 * and Ice Blast is mentioned in the ability file while Arrow attack 
 * is not mentioned in the ability file.
 *
 * @Author Priyank Joshi
 * @Date 07/10/2017
 **/

public class NoSuchAbilityExistsException extends Exception
{
    public NoSuchAbilityExistsException(String msg)
    {

        super(msg);
    }

    public NoSuchAbilityExistsException(String msg, Throwable cause)
    {

        super(msg, cause);
    }
}