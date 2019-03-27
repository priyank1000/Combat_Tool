import java.util.ArrayList;
import java.util.List;

/**
 * This class represents StoryTeller,
 * that has a list of Non-player characters also plays the role of an observer(listener)
 * for two events when there is a dead character, and when there is a change
 * in the turn. Therefore it implements ChangeTurnObserver and DeadCharacterObserver
 * interfaces. It also acts as an event source for when a side has no character left, 
 * then notify the observer to check the victory condition.
 * Furthermore, these are Strategy Objects that implement the Side interfaces
 * since we have two sides currently. The CombatTool owns these strategy objects
 * This is part of the Observer Design Pattern
 * This is part of the Strategy Design Pattern
 *
 * @Author Priyank Joshi
 * @Date 28/09/2017
 **/

public class StoryTeller
        implements ChangeTurnObserver, DeadCharacterObserver, Side
{
	private UserInterface ui;
    private final String sideName = "Story Teller";
    private List<Character> nonPlayers;
    private int state;
    private CheckEndGameObserver checkEndGameObserver;


    /**
     * A constructor to initialise state of story teller, 
	 * and non-player characters into an array list and then call method 
	 * assignTeam to organise the team respectively.
     *
     * @param: list of characters and ui of type UserInterface
     * @return: no returns
     **/
    public StoryTeller(List<Character> characters, UserInterface ui)
    {
        this.ui = ui;
        nonPlayers = new ArrayList<Character>();
        state = 0; /* using state to determine if a character dies from one team then other team
		receives healing and vice versa */
        assignTeam(characters); // we call this because we dont know which
        // side it is in so this method will solve the problem for it
    }

    /**
     * A getter method to get the side(team) name
     *
     * @param: no parameters
     * @return: string of side name
     **/
    public String getSideName()
    {
        return sideName;
    }

    /**
     * A method for back-door reference i.e a way for listener
     * to be connected with CombatTool(Subject or Event Source)
     * using the combat tool to register the StoryTeller as an observer
     *
     * @param: combat of type CombatTool
     * @return: no returns
     **/
    public void setChangeTurnListener(CombatTool combat)
    {
        combat.registerObserver(this);
    }

    /**
     * A method to check if the non-player list has a character or not
     *
     * @param: character of type Character
     * @return: true or false of type boolean
     **/
    public boolean hasCharacter(Character character)
    {
        return nonPlayers.contains(character);
    }

    /**
     * A method to assign team depending on the character type
     *
     * @param: list of characters
     * @return: no returns
     **/
    public void assignTeam(List<Character> characters)
    {
        for(Character character : characters)
        {
            if(character.getCharType().equals("N"))
            {
                addNonPlayer(character);
            }
        }
        addObservers();
    }

    /**
     * A method for back-door reference i.e a way for observer(listener)
     * to be connected with Character(Subject or Event Source)
     * using the character to register the StoryTeller as an observer
     * so that they can get notified when there is a dead character
     * in their team
     *
     * @param: no parameters
     * @return: no returns
     **/
    public void addObservers()
    {
        for(Character character : nonPlayers)
        {
            character.registerObserver(this);
        }
    }

    /**
     * A setter method for adding a non-player character into the non-players list
     *
     * @param: nonPlayer of type Character
     * @return: no returns
     **/
    public void addNonPlayer(Character nonPlayer)
    {
        nonPlayers.add(nonPlayer);
    }

    /**
     * A setter method for removing a non-player character from the non-players list
     *
     * @param: nonPlayer of type Character
     * @return: no returns
     **/
    public void removeNonPlayer(Character nonPlayer)
    {
        nonPlayers.remove(nonPlayer);
    }

    /**
     * A getter method for getting number of non-player characters in StoryTeller
     *
     * @param: no parameters
     * @return: size of the list
     **/
    public int numOfCharacters()
    {

        return nonPlayers.size();
    }

    /**
     * A getter method for getting a specific non-player character from the list
     *
     * @param: index of type int
     * @return: specific non-player character using an index
     **/
    public Character getCharacter(int index)
    {
        if(index > numOfCharacters())
        {
            throw new ArrayIndexOutOfBoundsException(
                    "index out of the array numOfCharacters");
        }
        else
        {
            return nonPlayers.get(index);
        }
    }

    /**
     * A getter method for getting all the non-player characters in the list
     *
     * @param: no parameters
     * @return: list of nonPlayer characters of type List<Character>
     **/
    public List<Character> getCharacters()
    {
        return nonPlayers;
    }

    /**
     * A method to change the turn by updating the state
     *
     * @param: no parameters
     * @return: no returns
     **/
    public void update()
    {
        state = 1 - state;
        if(state == 1)
        {
            System.out.println("Switch to Story Teller's Turn\n ");
        }
    }

	/**
	* A method to register CombatTool as an observer so that they can be notified whenever 
	* all characters are dead in a side
	*
	* @param: checkEndGameObserver of type CheckEndGameObserver
	* @return: no returns
	**/
    public void addCheckEndGameObserver(CheckEndGameObserver checkEndGameObserver)
    {
        this.checkEndGameObserver = checkEndGameObserver;
    }
	
	/**
	* A method to notify the CombatTool when the event mentioned above occurs
	*
	* @param: checkEndGameObserver of type CheckEndGameObserver
	* @return: no returns
	**/
    public void notifyCheckEndGameObserver()
    {
        checkEndGameObserver.check();
    }

    /**
     * A method to notify whenever a player character reaches 0 health points
     * then all non-player characters are healed for 5% of their maximum health points
     * likewise the team that experience a death character, will remove that character
     * from their team, and remove that character from being an observer.
     *
     * @param: no parameters
     * @return: no returns
     **/
    public void notifyDead(Character charact)
    {
        if(state == 1)
        {
            for(Character character : nonPlayers)
            {
                int tempHP = (int) (character.getCurrHP() +
                        1.05 * character.getMaxHP());
                if(tempHP > character.getMaxHP())
                {
                    character.setCurrHP(character.getMaxHP());
                }
                else
                {
                    character.setCurrHP(tempHP);
                }
            }
        }
        else
        {
            ui.announceDeath(charact.getCharName());
            charact.removeObserver(this);
            removeNonPlayer(charact);

            // if no characters are left in the StoryTeller then notify the 
			// observer(CombatTool)
            if(numOfCharacters() == 0)
            {
                notifyCheckEndGameObserver();
            }
        }
    }

    /**
     * A method for printing Story Teller's information during the game.
     * for example: The number (readability purposes), name and current health points
     *
     * @param: no parameters
     * @return: result of type String
     **/
    public String sideInfo()
    {
        String result = getSideName() + ": \n";

        for(int i = 0; i < numOfCharacters(); i++)
        {
            result += "\t(" + (i + 1) + ")" + " Name " + getCharacter(i)
                    .getCharName() + ": " + getCharacter(i).getCurrHP() + "/" +
                    getCharacter(i).getMaxHP() + "\n";
        }
        return result;
    }
}