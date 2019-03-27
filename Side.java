import java.util.List;

/**
 * This class is an interface, so as to achieve extensibility
 * for adding "more" sides in future.
 * Currently we have two sides (Story teller and truth teller)
 * This is part of the Strategy Design Pattern
 *
 * @Author Priyank Joshi
 * @Date 07/10/2017
 **/

public interface Side
{
    public void update();
    public String getSideName();
    public void setChangeTurnListener(CombatTool combatTool);
    public boolean hasCharacter(Character character);
    public void assignTeam(List<Character> characters);
    public int numOfCharacters();
    public Character getCharacter(int index);
    public List<Character> getCharacters();
    public String sideInfo();
    public void addCheckEndGameObserver(CheckEndGameObserver
                                                checkEndGameObserver);
}
