/**
 * This class represents ability that is owned by character class
 *
 * @Author Priyank Joshi
 * @Date 28/09/2017
 **/

public class Ability
{
    private String abType;
    private String abName;
    private String target;
    private int base;
    private int numDice;
    private int numFaces;

    /**
     * A constructor
     *
     * @param: abType, abName, target, base, numDice and numFaces for Ability object
     * @return: no returns
     **/
    public Ability(String abType, String abName, String target, int base,
                   int numDice, int numFaces)
    {
        this.abType = abType;
        this.abName = abName;
        this.target = target;
        this.base = base;
        this.numDice = numDice;
        this.numFaces = numFaces;
    }

    /**
     * A getter method for getting ability's type
     * (D for Damage or H for Healing)
     *
     * @param: no parameters
     * @return: abType
     **/
    public String getAbType()
    {
        return abType;
    }

    /**
     * A setter method for setting new ability's type
     *
     * @param: newAbType
     * @return: no return
     **/
    public void setAbType(String newAbType)
    {
        abType = newAbType;
    }

    /**
     * A getter method for getting ability's name
     *
     * @param: no parameters
     * @return: abName
     **/
    public String getAbName()
    {
        return abName;
    }

    /**
     * A setter method for setting new ability's name
     *
     * @param: newAbName
     * @return: no returns
     **/
    public void setAbName(String newAbName)
    {
        abName = newAbName;
    }

    /**
     * A getter method for getting ability's target
     *
     * @param: no parameters
     * @return: target
     **/
    public String getTarget()
    {
        return target;
    }

    /**
     * A setter method for setting new ability's target
     *
     * @param: newTarget
     * @return: no returns
     **/
    public void setTarget(String newTarget)
    {
        target = newTarget;
    }

    /**
     * A getter method for getting ability's base
     *
     * @param: no parameters
     * @return: base
     **/
    public int getBase()
    {
        return base;
    }

    /**
     * A setter method for setting new ability's base
     *
     * @param: newBase
     * @return: no returns
     **/
    public void setBase(int newBase)
    {
        base = newBase;
    }

    /**
     * A getter method for getting ability's number of dices
     *
     * @param: no parameters
     * @return: number of dice(s)
     **/
    public int getNumDice()
    {
        return numDice;
    }

    /**
     * A setter method for setting ability's number of dices
     *
     * @param: newNumDice
     * @return: no returns
     **/
    public void setNumDice(int newNumDice)
    {
        numDice = newNumDice;
    }

    /**
     * A getter method for getting ability's number of faces
     *
     * @param: no parameters
     * @return: number of face(s)
     **/
    public int getNumFaces()
    {
        return numFaces;
    }

    /**
     * A setter method for setting ability's number of faces
     *
     * @param: newNumFaces
     * @return: no returns
     **/
    public void setNumFaces(int newNumFaces)
    {
        numFaces = newNumFaces;
    }

    /**
     * A method for printing(showing) character's ability
     *
     * @param: no parameters
     * @return: String of ability
     **/
    public String showAbility()
    {
        String ability = "";
        ability = "Name: " + getAbName() + ", ";
        ability += "Type: ";

        if(getAbType().equals("D"))
        {
            ability += "Damage, ";
        }
        else if(getAbType().equals("H"))
        {
            ability += "Heal, ";
        }

        ability += "Target: ";

        if(getTarget().equals("S"))
        {
            ability += "Single Target, ";
        }
        else if(getTarget().equals("M"))
        {
            ability += "Multiple Target, ";
        }

        ability += "Base: " + getBase() + ", NumDice: " + getNumDice() +
                ", NumFaces: " + getNumFaces();

        return ability;
    }

    /**
     * A method for calculating the affect of an ability
     *
     * @param: no parameters
     * @return: affect
     **/
    public int calcAffect()
    {
        int affect = 0;
        affect += base;

        for(int i = 0; i < numDice; i++)
        {
            affect += (int) (Math.random() * numFaces + 1);
        }
        return affect;
    }
}
