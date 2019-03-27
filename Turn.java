import java.util.List;

/**
 * This class performs each character's turn during the game
 *
 * @Author Priyank Joshi
 * @Date 04/10/2017
 **/

public class Turn
{
    private Character currCharacter;
    private List<Side> sides;
    private UserInterface ui;

    /**
     * A constructor that initialises currCharacter and list of sides 
     *
     * @param: current character of type Character, list of sides, ui of 
	 * type UserInterface
     * @return: no returns
     **/
    public Turn(Character currCharacter, List<Side> sides, UserInterface ui)
    {
        this.ui = ui;
        this.currCharacter = currCharacter;
        this.sides = sides;
    }

    /**
     * A method for printing(showing) abilities of the character
     *
     * @param: no parameters
     * @return: list of abilities in string format
     **/
    public String getAbilitiesInfo()
    {

        String listAbilities = "";

		for(int i = 0; i < numOfAbilities(); i++)
		{
			listAbilities += "\t(" + (i + 1) + ")" + "Ability " + ": " +
			currCharacter.showAbility(i) + "\n";
		}
    
        return listAbilities;
    }
	
	 /**
     * A method to check the condition if the current character
     * has an ability or not
	 *
     * @param: no parameters
     * @return: list of abilities in string format
     **/
	public boolean currCharacterHasAbility()
	{
		return numOfAbilities() > 0;
	}

    /**
     * A method for getting number of abilities of the character
     *
     * @param: no parameters
     * @return: number of abilities
     **/
    public int numOfAbilities()
    {

        return currCharacter.numOfAbilities();
    }

    /**
     * A method to check the selection made by the character(user)
     *
     * @param: action of type int
     * @return: result of type boolean
     **/
    public boolean choice(int selection)
    {
        boolean result = false;

        if(selection == 0)
        {
            result = false;
        }
        else if(selection == 1)
        {
            result = true;
        }
        return result;
    }

    /**
     * A method to use an ability from their ability list depending on the
     * ability, target selection occurs.
     *
     * @param: int i
     * @return: no returns
     **/
    public void useAbility(int i)
    {
        Ability ab = currCharacter.getAbility((i - 1));
        int affect = ab.calcAffect();

        //Multiple healing, then heal the whole side respectively
        if(ab.getTarget().equals("M") && ab.getAbType().equals("H"))
        {
            for(Side side : sides)
            {
                if(side.hasCharacter(currCharacter))
                {
                    for(int j = 0; j < side.numOfCharacters(); j++)
                    {
                        ui.showHealAffect(side.getCharacter(j).getCharName(),
                                affect
                        );
                        side.getCharacter(j).heal(affect);
                    }
                }
            }
        }

        //Multiple damage, then damage the opposite side 
        else if(ab.getTarget().equals("M") && ab.getAbType().equals("D"))
        {
            for(Side side : sides)
            {
                if(!side.hasCharacter(currCharacter))
                {
                    for(int j = 0; j < side.numOfCharacters(); j++)
                    {
                        ui.showDamageAffect(
                                side.getCharacter(j).getCharName(), affect);
                        side.getCharacter(j).injury(affect);
                    }
                }
            }
        }

        //Single healing, select a character from ally to heal
        else if(ab.getTarget().equals("S") && ab.getAbType().equals("H"))
        {
            for(Side side : sides)
            {
                if(side.hasCharacter(currCharacter))
                {
                    ui.showSideCharacters(side.sideInfo());
                    int selection = ui.selectCharacter(
                            side.numOfCharacters()) - 1;
                    Character ally = side.getCharacter(selection);
                    ui.showHealAffect(ally.getCharName(), affect);
                    ally.heal(affect);
                }
            }
        }

        //Single damage, then damage the enemy character
        else if(ab.getTarget().equals("S") && ab.getAbType().equals("D"))
        {
            for(Side side : sides)
            {
                if(!side.hasCharacter(currCharacter))
                {
                    ui.showSideCharacters(side.sideInfo());
                    int selection = ui.selectCharacter(
                            side.numOfCharacters()) - 1;
                    Character enemy = side.getCharacter(selection);
                    ui.showDamageAffect(enemy.getCharName(), affect);
                    enemy.injury(affect);
                }
            }
        }
    }


}