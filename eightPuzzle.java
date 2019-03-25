import java.io.*;
import javax.swing.*;
import java.util.*;
public class  eightPuzzle
{
	public static boolean full=false;
public static void main(String[] args)
{
	
	int finall[][];
	int goal[][];
	boolean valid=false;
	finall=new int[3][3];
	finall=fill("Please enter the Start state as a sequence of numbers between [0, 8] e.g 5 2 0 6 1 3 4 8 7"");
	goal=new int[3][3];
	goal=fill("Please enter the End state as a sequence of numbers between [0, 8] e.g 5 2 0 6 1 3 4 8 7"");
	int posx=getx(finall);
	int posy=gety(finall);
	solveit(finall,posx,posy,goal);
}


public static void solveit(int[][]source,int x,int y,int [][]goal)
	{
	int[][]temp=new int[source.length][source.length];
	temp=copy(source);
	
	int tempx=0;
	
	if(y+1<source.length)
		{
			System.out.println("(B): "+source[x][y+1] + " to the west");
			temp=copy(source);
			tempx =temp[x][y+1];
			temp[x][y+1]=0;
			temp[x][y]=tempx;
			System.out.println("H Value="+getCost(temp,goal));
		}
		
		if(y-1>=0)
		{
			System.out.println("(A): "+source[x][y-1] +" to the east");
			temp=copy(source);
			tempx =temp[x][y-1];
			temp[x][y-1]=0;
			temp[x][y]=tempx;
			System.out.println("H Value="+getCost(temp,goal));
			
			
		}
		
		if(x-1>=0)
		{
			System.out.println("(C): "+source[x-1][y] + " to the south");
			temp=copy(source);
			tempx =temp[x-1][y];
			temp[x-1][y]=0;
			temp[x][y]=tempx;
			System.out.println("H Value="+getCost(temp,goal));
		}
		
		if(x+1<source.length)
		{
			System.out.println("(D): "+source[x+1][y] + " to the north");
			temp=copy(source);
			tempx =temp[x+1][y];
			temp[x+1][y]=0;
			temp[x][y]=tempx;
			System.out.println("H Value="+getCost(temp,goal));
		}
		
		
	}
	
	public static int[][] getinput()
	{
		int answer[][];
		answer=new int[3][3];
		for(int i=0;i<3;i++)
		{
			for(int z=0;z<3;z++)
			{
				String code = JOptionPane.showInputDialog(null,"Enter number at position ["+i+"]"+",["+z+"]:");
				int num=Integer.parseInt(code);
				answer[i][z]=num;
			}
		}
		return answer;
	}
	
	public static int getx(int[][]a)
	{
		int answ=0;
		for(int i=0;i<3;i++)
		{
			for(int z=0;z<3;z++)
			{	
				if(a[i][z]==0)
				{
				answ=i;
				
				}
			}
		}
		return answ;
	}
	
	public static int gety(int[][]a)
	{
		int ans=0;
		for(int i=0;i<3;i++)
		{
			for(int z=0;z<3;z++)
			{	
				if(a[i][z]==0)
				{
				ans=z;
				
				}
				
			}
		}
		return ans;
	}
	
	public static boolean inputCheck(int[][] values)
	{ 
		boolean[] check = {false,false,false,false,false,false,false,false,false,false};
		for (int i = 0; i < values.length; i++)
		{
		    for (int j = 0; j < values[0].length; j++)
			{
			         // check to see if values are in range.
                                 if (values[i][j] > 8 || values[i][j] < 0) return false;
				 
				 // we are accessing each value once here. Have to check to make sure that that is the only time that number appeared.
				 else if (check[values[i][j]] == false)
				 {
					 check[values[i][j]] = true;
				 }
                                 else if(check[values[i][j]] == true)
				 {
					 return false; // this means that the value has occurred more than once
				 }					 
			}
		}
        return true; // the values have passed the range check and the duplication check so set must be valid.
	}
	
public static int [][] fill(String text)
    {
	int [][] answer=new int[3][3];
	String [] sArr=new String[9];
	String input="";
	int counter=0;
	boolean alldigit=true;
	while(!full)
		{
		input=JOptionPane.showInputDialog(null,text);
		sArr=input.split(" ");
		if(sArr.length==9)
			{
			for(int h=0;h<sArr.length;h++)
			{
				if(!sArr[h].matches("\\d+"))
					alldigit=false;
			}
			if(alldigit)
			{
			for(int i=0;i<3;i++)
				{
				for(int j=0;j<3;j++)
					{
					answer[i][j]=Integer.parseInt(sArr[counter]);
					counter++;
					}
				}
					if(inputCheck(answer))
					{
						full=true;
					}
					else
					{
						JOptionPane.showMessageDialog(null,"State entered did not contain numbers 0-8");
					}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"State entered did not contain all numbers");
			}
			}
		else
			{
			JOptionPane.showMessageDialog(null,"State entered did not contain exactly 9 states");
			}
			alldigit=true;
			counter=0;
		}
		full=false;
		return answer;
		
	
    }
	
	
	public static int getCost(int[][] values,int[][] goal)
	{
		int val=0;
		for(int i=0;i<values.length;i++)
		{
			for(int j=0;j<values.length;j++)
			{
				if(values[i][j]!=0)
				{
					if(values[i][j]!=goal[i][j])
						val++;
					
				}
			}
		}
		
		return val;
	}
	
	public static int [][] copy(int [][] source)
	{
		int [][]temp=new int[source.length][source.length];
		
		for (int i=0;i<source.length;i++)
		{
			for(int j=0;j<source.length;j++)
			{
			temp[i][j]=source[i][j];
			}
		
		}
		return temp;
	}
	
}
