import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the main class to run the Game
 *
 * @Author Priyank Joshi
 * @Date 09/10/2017
 **/

public class Game
{
    public static void main(String[] args)
    {
        Read read = null;
        UserInterface ui = new UserInterface();

        ui.welcome();

        String readOption = ui.getReadOption();

        // if user enters "Q" then it will quit/exit the program
        // else read factory will decide what to do depending on
        // user's choice
        if(readOption.equals("Q"))
        {
            exit();
        }
        else
        {
            read = ReadFactory.readGame(readOption, ui);
        }

        CombatTool combat = new CombatTool(read.getSides(), ui);

        String result = "";

        // start of each round
        do
        {
            result = ui.getEachRoundOption();

            // continue the round
            if(result.equals("G"))
            {
                ui.printStartRound(combat.getRound());
                combat.round();
                ui.printEndRound();
            }
			
            //create new game or load an existing saved game
            else if(result.equals("C") || result.equals("L"))
            {
                read = ReadFactory.readGame(result, ui);
                combat = new CombatTool(read.getSides(), ui);
            }

            //save the game
            else if(result.equals("S"))
            {
                Save save = new Save(combat.getCharacters());
            }

            //exit the game
            else if(result.equals("Q"))
            {
                exit();
            }
        } while(true);
    }

    /**
     * A method to exit the game
     *
     * @param: no parameters
     * @return: no return
     **/
    public static void exit()
    {
        System.exit(0);
    }
}