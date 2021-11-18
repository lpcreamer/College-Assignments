// mineSweeper.java (class GameBoard (part of the MVC model))
// Copyright Dave Binkley 2018

/**
* This is my code! It’s goal is to be Minesweeper
* CS 312 - Assignment 8
* @author Liam Creamer
* @version 1.0 11/28/2018 
*/


import java.util.Random;
public class GameBoard
{
  protected final int BOARD_SIZE = 6;
  protected final int NUMBER_OF_MINES = 6;
  protected final int MAXIMUM = 6;
  
  protected View view;
  protected Block[][] grid = new Block[BOARD_SIZE][BOARD_SIZE];
  
  protected void cheat()               
  {

    
    for(int r=0; r<BOARD_SIZE; r++)        
    {
      String S = "";
      for(int c=0; c<BOARD_SIZE; c++)
        S += grid[r][c].cheat();
      System.out.println(S);
    }
  }


  public GameBoard(View v) 
  { 
    view = v; 
    int count = 0;
    Random random = new Random();
   
    for(int r=0; r<BOARD_SIZE; r++)        // fill in mine counters
    {
      for(int c=0; c<BOARD_SIZE; c++) 
      {
          if(count< NUMBER_OF_MINES)
          {
              MineBlock mineBlock = new MineBlock();
              grid[r][c] = mineBlock;
              count++;
          }
          else
          {
              NumberBlock numberBlock = new NumberBlock();
              grid[r][c] = numberBlock;
          }
      }
    }
    
    Block temp;
    int counter = 0;
    for(int j = 0;j < BOARD_SIZE; j++)
    {
       for (int k = 0; k< BOARD_SIZE; k++)
       {
           if(counter < NUMBER_OF_MINES )
           {
               temp = grid[j][k];
               int tempRow = random.nextInt(MAXIMUM);
               int tempCol = random.nextInt(MAXIMUM);
               grid[j][k] = grid[tempRow][tempCol];
               grid[tempRow][tempCol] = temp;
           }
       }
    }
    
    for(int e = 0; e < BOARD_SIZE; e++)
       for (int f = 0; f < BOARD_SIZE; f++)
           grid[e][f].tellNeighborsAboutMine(this, e, f);
           
     //cheat();  // for testing ... and amazing your friends!
  }
  
  public void guessBlockIsSafe(int row, int col)
  {
      grid[row][col].guessSafe();
  }

  public void markBlockAsMine(int row, int col)
  {
      grid[row][col].markAsMine();
  }
  
  public boolean minesAllFound()
  {
      for(int i = 0; i < BOARD_SIZE; i++)
      {
          for(int k = 0; k < BOARD_SIZE; k++)
          {
              if (!grid[i][k].correctlyGuessed())
                  return false;
          }
      }
      return true;
  }
  
  public void incrementCountForSurroundingBlocks(int row, int col)
  {
         if(this.onBoard(row -1, col -1))
             grid[row -1][col -1].incrementAdjacentMineCount();
         
         if(this.onBoard(row -1, col)) 
             grid[row -1][col].incrementAdjacentMineCount();
         
         if(this.onBoard(row-1, col +1))  
             grid[row -1][col +1].incrementAdjacentMineCount();
         
         if(this.onBoard(row, col -1))  
             grid[row][col -1].incrementAdjacentMineCount();
         
         if(this.onBoard(row, col +1)) 
             grid[row][col +1].incrementAdjacentMineCount();
         
         if(this.onBoard(row +1, col -1)) 
             grid[row +1][col -1].incrementAdjacentMineCount();
         
         if(this.onBoard(row +1, col))  
             grid[row +1][col].incrementAdjacentMineCount();
         
         if(this.onBoard(row +1, col +1))
             grid[row +1][col +1].incrementAdjacentMineCount();
  }
  
  
  
  protected boolean onBoard(int r, int c)
  {
      return(r>=0 && r<BOARD_SIZE && c>=0 && c<BOARD_SIZE);
  }
  
  public String displayAs(int row, int col)
  {
      return grid[row][col].displayAs();
  }
}
