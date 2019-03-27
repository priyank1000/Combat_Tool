/**
 * This class is used to create a new exception type relating to invalid
 * header in the files.
 * Thrown when reading the character and ability files have first header line
 * incorrect
 * For example missing currentHP in file loaded.
 *
 * @Author Priyank Joshi
 * @Date 07/10/2017
 **/

public class HeaderFileException extends Exception
{
    public HeaderFileException(String msg)
    {

        super(msg);
    }

    public HeaderFileException(String msg, Throwable cause)
    {

        super(msg, cause);
    }
}