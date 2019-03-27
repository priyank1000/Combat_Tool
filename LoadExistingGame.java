import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This is part of the Factory design pattern
 * The purpose of this class is to load an existing saved game
 * by validating the character file
 * @Author Priyank Joshi
 * @Date 4/10/2017
 **/

public class LoadExistingGame extends Read
{
    private List<Side> sides;
    private UserInterface ui;

    /**
     * A constructor that initialises user interface to enable dependency injection
	 * array list of sides and calls readAbility method
     *
     * @param: ui of type UserInterface
     * @return: no returns
     **/
    public LoadExistingGame(UserInterface ui)
    {
        this.ui = ui;
        sides = new ArrayList<>();
        readAbility();
    }

    /**
     * A getter method to get a list of sides
     *
     * @param: no parameters
     * @return: list of sides
     **/
    protected List<Side> getSides()
    {
        return sides;
    }

    /**
     * Overriden method to validate character.
     * checks if the attributes of character file are correct.
     * throws 3 different exceptions:
     * 1) InvalidAttributeException
     * 2) NoCharacterInOneSideException
     * 3) HeaderFileException
     * if there are any errors when validating.
     *
     * @param: text of type String
     * @return: no returns
     **/
    protected void validateCharacter(String text)
            throws InvalidAttributeException, NoCharacterInOneSideException,
            HeaderFileException, NoSuchAbilityExistsException
    {
        List<Character> characterList = new ArrayList<>();
        Character character;

        String[] newText = text.split("\\r?\\n");
        if(newText[0].equals("Type,Name,HP,CurrentHP,Abilities"))
        {
            for(int j = 1; j < newText.length; j++)
            {
                String[] attribute = newText[j].split(",\\s?");

                if(!(attribute[0].equals("N") || attribute[0].equals("P")))
                {
                    throw new InvalidAttributeException(
                            attribute[1] + "has Wrong character type!");
                }

                int HP = Integer.parseInt(attribute[2]);
                if(HP < 20)
                {
                    throw new InvalidAttributeException(attribute[1] +
                            "'s Max HP must be an integer greater than 20 " +
                            "inclusive");
                }

                int currHP = Integer.parseInt(attribute[3]);
                if(currHP > HP)
                {
                    throw new InvalidAttributeException(attribute[1] +
                            "'s Current HP must be less than or equals to " +
                            "maximum HP");
                }

                List<Ability> charAbList = new ArrayList<Ability>();
                List<Ability> abilityList = getAbilityList();

                for(int k = 4; k < attribute.length; k++)
                {
                    boolean found = false;

                    for(int l = 0; l < abilityList.size(); l++)
                    {
                        if(attribute[k].equals(abilityList.get(l).getAbName()))
                        {
                            found = true;
                            charAbList.add(abilityList.get(l));
                        }

                    }
                    if(!found)
                    {
                        throw new NoSuchAbilityExistsException(attribute[k] + " ability " +
                                "is not in the ability file");
                    }
                }

                character = new Character(
                        attribute[0], attribute[1], HP, charAbList, currHP);
                characterList.add(character);
            }
        }
        else
        {
            throw new HeaderFileException("Character file in create new game " +
                    "must have header that starts with: \n" +
                    "Type,Name,HP,CurrentHP,Abilities");
        }

		/* This is to check if there is two unique different character type */
        boolean diffSides = false;
        List<String> diffCharTypes = new ArrayList<String>();
        for(Character charact : characterList)
        {
            if(!diffSides)
            {
                diffCharTypes.add(charact.getCharType());
                for(String s : diffCharTypes)
                {
                    if(!s.equals(charact.getCharType()))
                    {
                        diffSides = true;
                    }
                }
            }
        }

        if(!diffSides)
        {
            throw new NoCharacterInOneSideException(
                    "There is only one side in this game!");
        }

		
		/*
		Now we have unique character types, then depending on whether it is N or P, 
		we put them on their respective sides (Story Teller and Truth Teller). 
		*/
        Set<String> uniqueCharTypes = new HashSet<>();
        for(Character charact : characterList)
        {
            uniqueCharTypes.add(charact.getCharType());
        }

        Side side = null;
        for(String s : uniqueCharTypes)
        {
            if(s.equals("N"))
            {
                side = new StoryTeller(characterList, ui);
                sides.add(side);
            }
            else if(s.equals("P"))
            {
                side = new TruthTeller(characterList, ui);
                sides.add(side);
            }
        }
    }
}
