import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Factory design pattern
 * The purpose of this class is to read ability, character file and then
 * validate the ability and character.
 *
 * @Author Priyank Joshi
 * @Date 4/10/2017
 **/

public abstract class Read
{
    private Ability ability;
    private List<Ability> abilityList;

    protected abstract void validateCharacter(String text)
            throws InvalidAttributeException, NoCharacterInOneSideException,
            HeaderFileException, NoSuchAbilityExistsException;

    protected abstract List<Side> getSides();

    /**
     * A method to read character file and validate the character
     *
     * @param: no parameters
     * @return: no returns
     **/
    private void readCharacter()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the character file name:");
        String filename = sc.next();
        System.out.println("\n");

        String text = "";

        try
        {
            String aLine;

            BufferedReader br = new BufferedReader(new FileReader(filename));

            while((aLine = br.readLine()) != null)
            {
                text += aLine + "\n";
            }
            br.close();

            validateCharacter(text);

        } catch(IOException e)
        {
            System.out.println(
                    "ERROR in reading the character file! " + e.getMessage());
            System.exit(1);
        } catch(InvalidAttributeException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch(NoCharacterInOneSideException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch(HeaderFileException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (NoSuchAbilityExistsException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("\n");
    }

    /**
     * A getter method to get the ability
     *
     * @param: no parameters
     * @return: Ability
     **/
    private Ability getAbility()
    {
        return ability;
    }

    /**
     * A getter method to get a list of abilities
     *
     * @param: no parameters
     * @return: list of abilities
     **/
    protected List<Ability> getAbilityList()
    {
        return abilityList;
    }

    /**
     * A method to read ability file
     *
     * @param: no parameters
     * @return: no returns
     **/
    protected void readAbility()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the ability file name:");
        String filename = sc.next();
        System.out.println("\n");

        String text = "";

        abilityList = new ArrayList<Ability>();

        try
        {
            String aLine;

            BufferedReader br = new BufferedReader(new FileReader(filename));

            while((aLine = br.readLine()) != null)
            {
                text += aLine + "\n";
            }
            br.close();

            validateAbility(text);

        } catch(IOException e)
        {
            System.out.println(
                    "ERROR in reading the ability file! " + e.getMessage());
            System.exit(1);
        } catch(InvalidAttributeException e)
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        readCharacter();
    }

    /**
     * A method to validate ability file
     * checks if the attributes of ability file are correct, throws exception if there
     * any errors when validating.
     *
     * @param: text of type String
     * @return: no returns
     **/
    private void validateAbility(String text) throws InvalidAttributeException
    {
        String[] newText = text.split("\\r?\\n");

        if(newText[0].equals("Type,Name,Target,Base,NumDice,Faces"))
        {
            for(int j = 1; j < newText.length; j++)
            {
                String[] attribute = newText[j].split(",");
                if(!(attribute[0].equals("D") || attribute[0].equals("H")))
                {
                    throw new InvalidAttributeException(
                            attribute[1] + " has wrong ability type!");
                }

                if(!(attribute[2].equals("S") || attribute[2].equals("M")))
                {
                    throw new InvalidAttributeException(
                            attribute[1] + " has wrong Target Selection!");
                }
                try
                {
                    int base = Integer.parseInt(attribute[3]);
                    if(base <= 0)
                    {
                        throw new InvalidAttributeException(attribute[1] +
                                "'s Base must be positive integer!");
                    }

                    int numDice = Integer.parseInt(attribute[4]);
                    if(numDice <= 0 && numDice > 10)
                    {
                        throw new InvalidAttributeException(attribute[1] +
                                "'s number of dice must be in the range 1-10!");
                    }

                    int[] set = { 2, 3, 4, 6, 8, 10, 12 };
                    int numFaces = Integer.parseInt(attribute[5]);

                    boolean found = false;

                    for(int k = 0; k < set.length; k++)
                    {
                        if(numFaces == set[k])
                        {
                            found = true;
                        }
                    }

                    if(!found)
                    {
                        throw new InvalidAttributeException(attribute[1] +
                                "'s number of faces must be in the set!");
                    }

                    ability = new Ability(attribute[0], attribute[1],
                            attribute[2], base, numDice, numFaces
                    );
                    abilityList.add(ability);
                } catch(NumberFormatException e)
                {
                    System.out.println(
                            "base, numDice and numFace must be an integer");
                }
            }
        }
    }
}
