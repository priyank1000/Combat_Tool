import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is the Command Line Interface (CLI)
 * It interacts with the user (user input and output) 
 
 * @Author Priyank Joshi
 * @Date 10/10/2017
 **/

public class UserInterface
{
	/**
	* A method to print out welcoming message of the game
	*
	* @param: no parameters
	* @return: no returns
	**/
    public void welcome()
    {
        System.out.println("********************************");
        System.out.println("Welcome to the Combat Tool Game");
        System.out.println("********************************");

        System.out.println("Press (C) to create a new game\n");
        System.out.println("Press (L) to load a saved game\n");
        System.out.println("Press (Q) to quit game\n");
    }

	/**
	* A method to prompt the user again if they entered a wrong key word 
	* as to whether to create a new game, or load a saved game or quit game
	*
	* @param: no parameters
	* @return: readOption of type String
	**/
    public String getReadOption()
    {
        String readOption = "";
        Scanner sc = new Scanner(System.in);
        readOption = sc.nextLine();
        // if the user enters incorrect keyword, then keep prompting
        while(!readOption.equals("C") && !readOption.equals("L") &&
                !readOption.equals("Q"))
        {
            System.out.println("Press (C) to create a new game\n");
            System.out.println("Press (L) to load a saved game\n");
            System.out.println("Press (Q) to quit game\n");
            readOption = sc.nextLine();
        }

        return readOption;
    }

	/**
	* A method to print out the options at each round for the user
	* --> continue, new game, load, save or exit.
	*
	* @param: no parameters
	* @return: no returns
	**/
    public String getEachRoundOption()
    {
        System.out.println("Press (G) to continue the game\n");
        System.out.println("Press (C) to create a new game\n");
        System.out.println("Press (L) to load an exisiting game\n");
        System.out.println("Press (S) to save the game\n");
        System.out.println("Press (Q) to exit the game\n");

        String choice = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your choice?");
        choice = sc.nextLine();
        return choice;
    }

	/**
	* A method to print out the start of the round
	*
	* @param: no parameters
	* @return: no returns
	**/
    public void printStartRound(int round)
    {
        System.out.println("Start of round " + round);
    }

	/**
	* A method to print out the start of the round
	*
	* @param: no parameters
	* @return: no returns
	**/
    public void printEndRound()
    {
        System.out.println("End of round! \n");
    }


    /**
     * A method to ask the user what action do they want to do
     * at the start of the round
     * 1) No action and move on to the next character
     * 2) Use an ability
     *
     * @param: no parameters
     * @return: choice of type int
     **/
    public int promptAction()
    {
        Scanner sc = new Scanner(System.in);

        int choice = -1;
        do
        {
            try
            {
                System.out.println("(0) No Action");
                System.out.println("(1) Use Ability");
                System.out.println("What is your selection?");
                choice = sc.nextInt();
            } catch(InputMismatchException e)
            {
                sc.nextLine();
            }
        } while(choice != 0 && choice != 1);

        return choice;
    }

    /**
     * A method to ask the user which ability to use from their
     * abil list
     *
     * @param: number of abilities (int)
     * @return: choice of type int
     **/
    public int promptSkill(int numOfAbilities)
    {
        Scanner sc = new Scanner(System.in);

        int choice = 0;
        do
        {
            try
            {
                System.out.println("Which ability do you want to select?");
                System.out.println(
                        "Selection must be from 1 to " + numOfAbilities);
                choice = sc.nextInt();
            } catch(InputMismatchException e)
            {
                sc.nextLine();
            }
        } while(choice <= 0 || choice > numOfAbilities);

        return choice;
    }

    /**
     * A method to select a character to perform an action (heal an ally)
     * or damage an enemy in the opposition team
     *
     * @param: number of characters (int)
     * @return: choice of type int
     **/
    public int selectCharacter(int numOfCharacters)
    {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do
        {
            try
            {
                System.out.println("Which character do you want to select?");
                System.out.println(
                        "Selection must be from 1 to " + numOfCharacters);
                System.out.println("Selection must be number");
                choice = sc.nextInt();
            } catch(InputMismatchException e)
            {
                sc.nextLine();
            }
        } while(choice < 1 || choice > numOfCharacters);

        return choice;
    }

    /**
     * A method to print out all the StoryTeller and TruthTeller characters
     * appropriately.
     *
     * @param: characters of type String
     * @return: no returns
     **/
    public void showSideCharacters(String characters)
    {
        System.out.print(characters);
    }

    /**
     * A method to print out the turn during the game
     *
     * @param: side(team) name of type String
     * @return: no returns
     **/
    public void showSideTurn(String sideName)
    {
        System.out.println("This is " + sideName + "\'s Character Turn");
    }


	/**
	* A method to print out the current character status -> name, and current health
	*
	* @param: status of type String
	* @return: no returns
	**/
    public void showCurrentCharacterStatus(String status)
    {
        System.out.println(status);
    }

	/**
	* A method to print out the ability information so that user can select
	* which ability to use after the user suggests to use ability.
	*
	* @param: abilities info of type String
	* @return: no returns
	**/
    public void showAbilities(String abilitiesInfo)
    {
        System.out.println(abilitiesInfo);
    }



	/**
	* A method to print out the healing affect.
	*
	* @param: character name of type String and affect of type int
	* @return: no returns
	**/
    public void showHealAffect(String charName, int affect)
    {
        System.out.println(charName + " is healed by " + affect + "\n");
    }


	/**
	* A method to print out the damage affect.
	*
	* @param: character name of type String and affect of type int
	* @return: no returns
	**/
    public void showDamageAffect(String charName, int affect)
    {
        System.out.println(charName + " is damaged by " + affect + "\n");
    }



	/**
	* A method to print out a dead character.
	*
	* @param: character name of type String
	* @return: no returns
	**/
    public void announceDeath(String name)
    {
        System.out.println("Character: " + name + " is dead!");
    }

	/**
	* A method to print out all the dead characters.
	*
	* @param: sideName of type String
	* @return: no returns
	**/
    public void printAllDead(String sideName)
    {
        System.out.println("All characters in " + sideName + " are dead");
    }

	/**
	* A method to print out the team that won the game.
	*
	* @param: sideName of type String
	* @return: no returns
	**/
    public void printVictory(String sideName)
    {
        System.out.println(sideName + " is victorious!");
    }
	
	public void characterHasNoAbilityMessage()
	{
		System.out.println("This character has no ability\n");
	}
}


