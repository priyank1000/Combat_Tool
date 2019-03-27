import java.util.ArrayList;
import java.util.List;

/**
 * This class represents Character, owns a list of abilities
 * also plays the role of the event source that triggers an event
 * when there is a dead character then update the health points of respective team
 *
 * @Author Priyank Joshi
 * @Date 28/09/2017
 **/

public class Character
{
    private String charType;
    private String charName;
    private int maxHP;
    private int currHP;
    private List<Ability> abilities; //Character owns a list of abilities
    //Character owns a list of DeadCharacterObservers
    private List<DeadCharacterObserver> deadObservers;

    /**
     * A constructor
     *
     * @param: charType, charName, maxHP, abilities and currHP for Character object
     * @return: no returns
     **/
    public Character(String charType, String charName, int maxHP,
                     List<Ability> abilities, int currHP)
    {

        this.charType = charType;
        this.charName = charName;
        this.maxHP = maxHP;
        this.currHP = currHP;
        this.abilities = abilities;
        deadObservers = new ArrayList<DeadCharacterObserver>();
    }

    /**
     * A getter method for getting character type
     * (P for Player or N for Non-player character)
     *
     * @param: no parameters
     * @return: charType
     **/
    public String getCharType()
    {

        return charType;
    }

    /**
     * A setter method for setting new character type
     *
     * @param: newCharType
     * @return: no return
     **/
    public void setCharType(String newCharType)
    {

        charType = newCharType;
    }

    /**
     * A getter method for getting character name
     *
     * @param: no parameters
     * @return: charName
     **/
    public String getCharName()
    {

        return charName;
    }

    /**
     * A setter method for setting character name
     *
     * @param: newCharName
     * @return: no returns
     **/
    public void setCharName(String newCharName)
    {

        charName = newCharName;
    }

    /**
     * A getter method for getting character's maximum health points
     *
     * @param: no parameters
     * @return: maxHP
     **/
    public int getMaxHP()
    {

        return maxHP;
    }

    /**
     * A setter method for setting character's maximum health points
     *
     * @param: newMaxHP
     * @return: no returns
     **/
    public void setMaxHP(int newMaxHP)
    {

        maxHP = newMaxHP;
    }

    /**
     * A getter method for getting character's current health points
     *
     * @param: no parameters
     * @return: currHP
     **/
    public int getCurrHP()
    {

        return currHP;
    }

    /**
     * A setter method for setting character's current health points
     *
     * @param: newCurrHP
     * @return: no returns
     **/
    public void setCurrHP(int newCurrHP)
    {

        currHP = newCurrHP;
    }

    /**
     * A getter method for getting a list of abilities
     *
     * @param: no parameters
     * @return: list of abilities of type List<Ability>
     **/
    public List<Ability> getAbilities()
    {

        return abilities;
    }

    /**
     * A setter method for adding a character's ability
     *
     * @param: ability
     * @return: no returns
     **/
    public void addAbility(Ability ability)
    {

        abilities.add(ability);
    }

    /**
     * A getter method for getting number of abilities that a character has
     *
     * @param: no parameters
     * @return: size of the list
     **/
    public int numOfAbilities()
    {

        return abilities.size();
    }

    /**
     * A getter method for getting a specific ability from the list
     *
     * @param: index of type int
     * @return: specific ability using an index
     **/
    public Ability getAbility(int index)
    {

        if(index > numOfAbilities())
        {
            throw new ArrayIndexOutOfBoundsException(
                    "index out of array numOfAbilities");
        }
        else
        {
            return abilities.get(index);
        }
    }

    /**
     * A method for showing the abilities the character has
     *
     * @param: index
     * @return: string of abilities
     **/
    public String showAbility(int index)
    {

        if(index > numOfAbilities())
        {
            throw new ArrayIndexOutOfBoundsException(
                    "index out of array numOfAbilities");
        }
        else
        {
            return abilities.get(index).showAbility();
        }
    }

    /**
     * A method to register dead character observer by adding into the list
     *
     * @param: deadObserver
     * @return: no returns
     **/
    public void registerObserver(DeadCharacterObserver deadObserver)
    {

        deadObservers.add(deadObserver);
    }

    /**
     * A method to remove dead character observer by removing from the list
     *
     * @param: deadObserver
     * @return: no returns
     **/
    public void removeObserver(DeadCharacterObserver DeadObserver)
    {

        int i = deadObservers.indexOf(DeadObserver);
        if(i >= 0)
        {
            deadObservers.remove(i);
        }
    }

    /**
     * A method to notify all dead character observers
     *
     * @param: character
     * @return: no returns
     **/
    public void notifyDeadCharacterObservers()
    {

        for(int i = 0; i < deadObservers.size(); i++)
        {
            deadObservers.get(i).notifyDead(this);
        }

    }

    /**
     * A method to heal a character (ally)
     *
     * @param: healthPoint
     * @return: no returns
     **/
    public void heal(int healthPoint)
    {

        if(currHP + healthPoint > maxHP)
        {
            currHP = maxHP;
        }
        else
        {
            currHP += healthPoint;
        }
    }

    /**
     * A method to injure(damage) an opponent
     *
     * @param: healthPoint
     * @return: no returns
     **/
    public void injury(int healthPoint)
    {

        if(currHP - healthPoint <= 0)
        {
            currHP = 0;
            notifyDeadCharacterObservers();
        }
        else
        {
            currHP -= healthPoint;
        }
    }
}
