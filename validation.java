import java.util.*;
public class validation
{
    public static void main(String[] args)
	{
	   int[][] values = {{3,4,5},{6,7,0},{1,2,8}};
       int[][] values0 = {{12,4,5},{6,7,0},{1,2,8}};
	   
	   System.out.println(inputCheck(values));
	   System.out.println(inputCheck(values0));
	   
	}
	
	// check are individual numbers in the range 1 to 9
	// check there are no repeating numbers 
	public static boolean inputCheck(int[][] values)
	{ 
		boolean[] check = {false,false,false,false,false,false,false,false,false,false};
		for (int i = 0; i < values.length; i++)
		{
		    for (int j = 0; j < values[0].length; j++)
			{
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
        return true;
	}
}