import java.io.*;
import javax.swing.*;
import java.util.*;
public class  eightPuzzle
{
	
public static void main(String[] args)
{

	int finall[][];
	boolean valid=false;
	finall=new int[3][3];
	finall=fill("ass");
	valid=inputCheck(finall);
	if(valid)
	{
	int posx=getx(finall);
	int posy=gety(finall);
	solveit(finall,posx,posy);
	}
	else
	{
		JOptionPane.showMessageDialog(null,"Invalid characters entered.");
	}
}


public static void solveit(int[][]source,int x,int y)
	{
		if(y-1>=0)
		{
			System.out.println(source[x][y-1] +" to the west");
		}
		if(y+1<source.length)
		{
			System.out.println(source[x][y+1] + " to the east");
		}
		if(x+1<source.length)
		{
			System.out.println(source[x+1][y] + " to the south");
		}
		if(x-1>=0)
		{
			System.out.println(source[x-1][y] + " to the north");
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
	input=JOptionPane.showInputDialog(null,text);
	sArr=input.split(" ");
	if(sArr.length==9)
	    {
		for(int i=0;i<3;i++)
		    {
			for(int j=0;j<3;j++)
			    {
				answer[i][j]=Integer.parseInt(sArr[counter]);
				counter++;
			    }
		    }
	    }
	else
	    {
		JOptionPane.showMessageDialog(null,"State entered did not contain 9 numbers");
		//full=false;
	    }
	return answer;
    }
}