/**
 * This class is used to create a new exception type relating to invalid attribute type in the files.
 * Thrown when reading the character and ability files have incorrect type of attribute
 * for example numDices having an integer more than 10 will throw an exception etc
 *
 * @Author Priyank Joshi
 * @Date 02/10/2017
 **/

public class InvalidAttributeException extends Exception
{
    public InvalidAttributeException(String msg)
    {

        super(msg);
    }

    public InvalidAttributeException(String msg, Throwable cause)
    {

        super(msg, cause);
    }
}