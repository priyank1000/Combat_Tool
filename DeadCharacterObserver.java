/**
 * This class is an interface (observer) that is owned by Character(event Source)
 * and StoryTeller and TruthTeller are the observers(listeners) will implement
 * this class
 * This is part of the Observer Design Pattern
 *
 * @Author Priyank Joshi
 * @Date 28/09/2017
 **/

public interface DeadCharacterObserver
{
    public void notifyDead(Character character);
}