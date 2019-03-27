/**
 * This class is an interface (observer) that is owned by TruthTeller
 * and StoryTeller (event Sources) while CombatTool is the observer(listener) will implement
 * this class
 * This is part of the Observer Design Pattern
 *
 * @Author Priyank Joshi
 * @Date 10/10/2017
 **/

public interface CheckEndGameObserver
{
    public void check();
}
