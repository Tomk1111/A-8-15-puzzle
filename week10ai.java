import javax.swing.*;
public class week10ai 
{
	
public static void main(String[] args)
{

	int finall[][];
	boolean valid=false;
	finall=new int[3][3];
	finall=getinput();
	valid=inputCheck(finall);
	if(valid)
	{
	int posx=getx(finall);
	int posy=gety(finall);
	solveit(finall,posx,posy);
	}
	else{
		JOptionPane.showMessageDialog(null,"Wrong,Enter numbers 0-8 only once each ");
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
		if(x-1>=0)
		{
			System.out.println(source[x-1][y] + " to the north");
		}
		if(x+1<source.length)
		{
			System.out.println(source[x+1][y] + " to the south");
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
	
}