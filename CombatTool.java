import java.util.ArrayList;
import java.util.List;

/**
 * This class performs the whole round of the game.
 * Furthermore, it represents an event source and its observers are
 * TruthTeller and StoryTeller which implement ChangeTurnObserver interface.
 * triggers an event when a there is a change in turn from Truth teller to Story teller
 * and vice versa.
 * Morever, the CombatTool is also a listener(observer) for the event if only one side left
 * has a character or more (when all other sides have no characters)
 * This is part of the Observer Design Pattern
 * This is part of the Strategy Design Pattern
 *
 * @Author Priyank Joshi
 * @Date 10/10/2017
 **/

public class CombatTool implements CheckEndGameObserver
{
    private int round;
    private List<ChangeTurnObserver> turnObservers;
    private List<Side> sides;
    private UserInterface ui;
    /**
     * A constructor initialises round, list of turnObservers and a way
	 * for the combat tool to be connected with Side (event source)
     *
     * @param: list of sides
     * @return: no returns
     **/
    public CombatTool(List<Side> sides, UserInterface ui)
    {
        this.ui = ui;
        round = 1;
        turnObservers = new ArrayList<ChangeTurnObserver>();
        this.sides = sides;
        for(Side side : sides)
        {
            side.setChangeTurnListener(this); //to set the sides(StoryTeller and TruthTeller as observers)
            side.addCheckEndGameObserver(this); // to register the CombaTool as the listener for the side
        }
        /* This line is to update the turn for one of the sides */
        sides.get(0).update();
    }

    /**
     * A getter method for getting current character name
     *
     * @param: current character of type Character
     * @return: current character name in String
     **/
    private String getCurrCharacterStatus(Character currCharacter)
    {
        String result = "Current Character\n";
        result += "\tName: " + currCharacter.getCharName() + "\n";
        result += "\tCurrent HP: " + currCharacter.getCurrHP() + "/" +
                currCharacter.getMaxHP() + "\n";
        return result;
    }

    /**
     * A getter method for getting characters to be used for saving
     *
     * @param: no parameters
     * @return: list of characters
     **/
    public List<Character> getCharacters()
    {
        List<Character> characterList = new ArrayList<>();
        for(Side side : sides)
        {
            characterList.addAll(side.getCharacters());
        }
        return characterList;
    }

    /**
     * A method to register TruthTeller and StoryTeller as observers by adding into the list
     * so that they can recieve updates whenever Turn changes in the CombatTool.
     *
     * @param: turnObserver
     * @return: no returns
     **/
    public void registerObserver(ChangeTurnObserver turnObserver)
    {
        turnObservers.add(turnObserver);
    }

    /**
     * A method to notify all turnObservers to tell all the observers about the state
     *
     * @param: no paramters
     * @return: no returns
     **/
    public void notifyChangeTurnObservers()
    {
        for(ChangeTurnObserver turnObserver : turnObservers)
        {
            turnObserver.update();
        }
    }

    /**
     * A method that changes turn and triggers notifyChangeTurnObservers method
     *
     * @param: no paramters
     * @return: no returns
     **/
    private void changeTurn()
    {
        notifyChangeTurnObservers();
    }

	/**
	* A getter method to get round
	*
	* @param: no parameters
	* @return: round (int)
	**/
    public int getRound()
    {
        return round;
    }

	/**
	* A getter method increment the round
	*
	* @param: no parameters
	* @return: no returns
	**/
    public void nextRound()
    {
        round++;
    }

	/**
	* A method that performs the round of a game
	*
	* @param: no parameters
	* @return: no returns
	**/
    public void round()
    {
        Turn thisTurn;

        for(Side side : sides)
        {
            for(int i = 0; i < side.numOfCharacters(); i++)
            {
                for(Side side2 : sides)
                {
                    ui.showSideCharacters(side2.sideInfo());
                }
                ui.showSideTurn(side.getSideName());
                ui.showCurrentCharacterStatus(
                        getCurrCharacterStatus(side.getCharacter(i)));
                thisTurn = new Turn(side.getCharacter(i), sides, ui);
				if(thisTurn.currCharacterHasAbility())
				{
					int actionSelection = ui.promptAction();
					if(thisTurn.choice(actionSelection))
					{
						ui.showAbilities(thisTurn.getAbilitiesInfo());
						int skillSelection = ui.promptSkill(
								thisTurn.numOfAbilities());
						thisTurn.useAbility(skillSelection);
					}
				}
				else
				{
					ui.characterHasNoAbilityMessage();
				}
            }
            changeTurn();
        }
        nextRound();
    }

	/**
	* A method that checks if the all the sides are dead
	* except for a side that has a character or more left,
	* then to end the game and tell the user interface to print victory
	* @param: no parameters
	* @return: no returns
	**/
    public void check()
    {
        int numSides = sides.size();
        int i = 0;
        Side aliveSide = null;
        for(Side side: sides)
        {
            if(side.numOfCharacters() == 0)
            {
                i++;
            }
        }
        if(i == (numSides - 1))
        {
            for(Side side: sides)
            {
                if(side.numOfCharacters() == 0)
                {
                    ui.printAllDead(side.getSideName());
                }
                else
                {
                    aliveSide = side;
                }
            }
            ui.printVictory(aliveSide.getSideName());
            Game.exit();
        }
    }
}

