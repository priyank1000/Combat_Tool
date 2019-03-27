import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

/**
 * This is to deal with saving(writing) the game status
 * It will save the Ability status and then the character status respectively
 *
 * @Author Priyank joshi
 * @Date 07/10/2017
 **/

public class Save
{
    /**
     * A constructor that calls writeAbility and writeCharacter methods
     * which will be used in Game
     *
     * @param: list of characters
     * @return: no returns
     **/
    public Save(List<Character> characters)
    {
        writeAbility(createAbility(characters));
        writeCharacter(createCharacters(characters));
    }

	/**
	* A method to write the abilities of all the characters
	* to a String of text
	*
	* @param: list of characters
	* @return: text of type String
	**/
    private String createAbility(List<Character> characters)
    {
        String text = "Type,Name,Target,Base,NumDice,Faces\n";
        for(Character character : characters)
        {
            for(Ability ability : character.getAbilities())
            {
                if(!text.contains(ability.getAbName()))
                {
                    text += ability.getAbType() + "," + ability.getAbName() +
                            "," + ability.getTarget() + "," +
                            ability.getBase() + "," + ability.getNumDice() +
                            "," + ability.getNumFaces() + "\n";
                }
            }
        }
        return text;
    }

	/**
	* A method to write the characters to a String of text
	*
	* @param: list of characters
	* @return: text of type String
	**/
    private String createCharacters(List<Character> characters)
    {
        String text = "Type,Name,HP,CurrentHP,Abilities\n";
        for(Character character : characters)
        {
            text += character.getCharType() + "," + character.getCharName() +
                    "," + character.getMaxHP() + "," + character.getCurrHP() +
                    ",";
            for(int i = 0; i < character.getAbilities().size(); i++)
            {
                text += character.getAbility(i).getAbName() + ",";
                if(i == character.getAbilities().size() - 1)
                {
                    text = cutLastComma(text);
                    text += "\n";
                }
            }
        }

        return text;
    }


	/**
	* This method supports createCharacters() method to cut the last comma of a 
	* string for each line in character file
	*
	* @param: list of characters
	* @return: text of type String
	**/
    private String cutLastComma(String str)
    {
        if(str != null && str.length() > 0 && (str.charAt(str.length() - 1) ==
                ','))
        {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

	/**
	* A method to write/save the ability status of the game to a file
	* Note: the user does not have to write an extention for example
	* .txt, .csv etc
	*
	* @param: text of type String
	* @return: no returns
	**/
    private void writeAbility(String text)
    {
        FileOutputStream fileStrm = null;
        PrintWriter fw = null;

        try
        {
            System.out.println("What is the ability saved file name?");
            String fileName = getFileName();
            fileStrm = new FileOutputStream(fileName);
            fw = new PrintWriter(fileStrm);
            fw.append(text);
            fw.close();
        } catch(IOException e)
        {
            System.out.println(
                    "Error in saving the ability state: " + e.getMessage());
            writeAbility(text);
        }
    }

	/**
	* A method to write/save the ability status of the game to a file
	* Note: the user does not have to write an extention for example
	* .txt, .csv etc
	*
	* @param: text of type String
	* @return: no returns
	**/
    private void writeCharacter(String text)
    {
        FileOutputStream fileStrm = null;
        PrintWriter fw = null;

        try
        {
            System.out.println("What is the character saved file name?");
            String fileName = getFileName();
            fileStrm = new FileOutputStream(fileName);
            fw = new PrintWriter(fileStrm);
            fw.append(text);
            fw.close();
        } catch(IOException e)
        {
            System.out.println(
                    "Error in saving the character state: " + e.getMessage());
            writeCharacter(text);
        }
    }

	/**
	* A method to get the file name
	*
	* @param: no parameters
	* @return: file name
	**/
    private String getFileName()
    {
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();
        return fileName;
    }
}