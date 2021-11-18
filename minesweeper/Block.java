// mineSweeper.java (class Block (part of the MVC model))
// Copyright Dave Binkley 2018

/**
* This is my code! It’s goal is to be Minesweeper
* CS 312 - Assignment 8
* @author Liam Creamer
* @version 1.0 11/28/2018 
*/

abstract class Block
{
    protected boolean markedAsMine;
    
    public abstract String cheat();
    
    public abstract void incrementAdjacentMineCount();
    public abstract String displayAs();
    public abstract boolean correctlyGuessed();
    public abstract void tellNeighborsAboutMine(GameBoard gb, int row, int col);
    public abstract void guessSafe();

    public void initialize()
    {
        markedAsMine = false;
    }
    
    public void markAsMine()
    {
        markedAsMine = true;
    }
    
}


class MineBlock extends Block   // [ no instance variables ]
{
    public MineBlock()
    {
        this.initialize();
    }
    
    public String cheat() 
    { 
        return "M"; 
    } 
    
    public void incrementAdjacentMineCount()
    {
        
    }
    
    public void guessSafe()
    {
        System.out.println("Boom");
        System.exit(0);
    }
    
    public void tellNeighborsAboutMine(GameBoard gb, int row, int col )
    {
        gb.incrementCountForSurroundingBlocks(row, col);
    }
    
    public boolean correctlyGuessed()
    {
        return markedAsMine;
    }
    
    public String displayAs()
    {
        if(markedAsMine)
        {
            return "M";
        }
        return ".";
    }

}


class NumberBlock extends Block
{
    protected int adjacentMineCount;
    protected boolean exposed;
    
    public NumberBlock()
    {
        this.initialize();
    }
    
    public String cheat() { return ""+"0123456789".charAt(adjacentMineCount); }
    
    @Override
    public void initialize()
    {
        adjacentMineCount = 0;
        exposed = false;
    }
    
    public void guessSafe()
    {
        exposed = true;
        markedAsMine = false;
    }
    
    public void incrementAdjacentMineCount()
    {
        adjacentMineCount++;
    }
    
    public boolean correctlyGuessed() 
    {
        return !markedAsMine;
    }
    
    public String displayAs()
    {
        if(markedAsMine)
            return "M";
        
        if(exposed)
        {
            return "" + adjacentMineCount;
        }
        else
        {
            return ".";
        }
    }

    @Override
    public void tellNeighborsAboutMine(GameBoard gb, int row, int col)
    {
        
    }
}


