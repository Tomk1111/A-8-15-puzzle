import java.io.*;
import javax.swing.*;
import java.util.*;
import java.util.Collections;


/* 
 * Thomas Kiely - 17185203
 * Sean Morrissey - 17222761
 * Paul Murphy - 17198046
 * Mark McNabola - 17226163 
*/


public class is17185203
{
	private static boolean full=false;
	
	private static ArrayList<Node> openNodes = new ArrayList<Node>();
	private static ArrayList<Node> closedNodes = new ArrayList<Node>();
	private static ArrayList<Node> bestPath = new ArrayList<Node>();

	private static int gValue = 0;
	private static int size=0;
	private final static String message1="Please enter the Start state as a sequence of numbers between [0, 8] e.g 5 2 0 6 1 3 4 8 7";
	private final static String message2="Please enter the Start state as a sequence of numbers between [0, 15] e.g 5 2 0 6 1 3 4 8 7 10 9 13 12 11 15 14";
	
public static void main(String[] args) throws Exception
{
	setSize();
	int origin[][];
	int goal[][];
	String message;
	if(size==3)
		message=message1;
	else 
		message=message2;
	boolean valid=false;
	origin=new int[3][3];
	origin=fill(message);
	goal=new int[3][3];
	goal=fill(message.replaceAll("Start","End"));
	int posx=getx(origin);
	int posy=gety(origin);
	int cost = getCost(origin, goal);
	Node sourceNode = new Node(origin, gValue,cost);
	openNodes.add(sourceNode);
	solveit(sourceNode, posx, posy, goal);
}



public static void solveit(Node source,int x,int y,int [][]goal)
{
	try
	{	
		boolean check = false;
		if (compareBoards(source.board, goal))	
		{
			while (!check)
			{
				bestPath.add(source);
				

				if (source.parent == null)
					check = true;		
				else
					source = source.parent;
			}
			Collections.reverse(bestPath);
			for (Node tempN : bestPath)
			{
				printBoard(tempN.board);
				
			}
		}
		
		else
		{
			gValue = openNodes.get(0).gVal+1;
			int[][]temp=new int[source.board.length][source.board.length];
			temp=copy(source.board);
			
			int tempx=0;
			if(y+1<source.board.length)
				{
					temp=copy(source.board);
					tempx =temp[x][y+1];
					temp[x][y+1]=0;
					temp[x][y]=tempx;
	
					if (!boardSearched(temp))
					{
						Node tempNode = new Node(temp, gValue, getCost(temp, goal), source);
						openNodes.add(tempNode);

					}
				}
				if(y-1>=0)
				{
					temp=copy(source.board);
					tempx =temp[x][y-1];
					temp[x][y-1]=0;
					temp[x][y]=tempx;
	
					if (!boardSearched(temp))
					{
						
						Node tempNode = new Node(temp, gValue, getCost(temp, goal), source);
						openNodes.add(tempNode);
					}
				}
				
				if(x-1>=0)
				{
					temp=copy(source.board);
					tempx =temp[x-1][y];
					temp[x-1][y]=0;
					temp[x][y]=tempx;

					if (!boardSearched(temp))
					{
						Node tempNode = new Node(temp, gValue, getCost(temp, goal), source);
						openNodes.add(tempNode);
					}
				}
				
				if(x+1<source.board.length)
				{
					temp=copy(source.board);
					tempx =temp[x+1][y];
					temp[x+1][y]=0;
					temp[x][y]=tempx;
	
					if (!boardSearched(temp))
					{
						Node tempNode = new Node(temp, gValue, getCost(temp, goal), source);
						openNodes.add(tempNode);
					}
				}
				
				if (openNodes.size() > 0)
				{
				closedNodes.add(openNodes.get(0));
				openNodes.remove(0);
				}
						
				sortOpenList();	
				
				Node newSource = openNodes.get(0);
				int posX = getx(newSource.board);
				int posY = gety(newSource.board);
				solveit(newSource, posX, posY, goal);		
		}
	}
	catch (Exception e)
	{System.out.println(e.getMessage());}
}
	
    public static void printBoard(int[][] board)
	{
		for (int i=0;i < board.length;i++)
		{
			for (int j=0;j < board.length;j++)
			{	
				if (size == 4)
				{
					System.out.printf("%2d",board[i][j]);
				    System.out.print(" ");
				}
				else
				{
					System.out.print(board[i][j] + " ");
				}
			}
			System.out.println();
		}
			System.out.println("-----------");
	}
	
	
	
	public static void sortOpenList() 
	{		
		Node tempNode;
		
		for (int i=0;i < openNodes.size()-1;i++)
		{
			for (int j=0;j < openNodes.size()-i-1;j++)
			{
				if (openNodes.get(j).hVal + openNodes.get(j).gVal > openNodes.get(j+1).hVal + openNodes.get(j+1).gVal)
				{
					tempNode = openNodes.get(j+1);
					openNodes.set(j+1, openNodes.get(j));
					openNodes.set(j, tempNode);
				}
			}
		}
	}
	
	
	public static boolean compareBoards(int[][] board1, int[][]board2)
	{
		for (int i=0;i < board1.length;i++)
		{
			for (int j=0;j < board1.length;j++)
			{
				if (board1[i][j] != board2[i][j])
					return false;
			}
		}
		return true;
	}
	
	
	public static boolean boardSearched(int[][] board)
	{
		if (openNodes.size() > 0)
		{
			for (int i=0;i < openNodes.size();i++)
			{
				if (compareBoards(board, openNodes.get(i).board))
					return true;
			}
		}
		if (closedNodes.size() > 0)
		{
			for (int i=0;i < closedNodes.size();i++)
			{
				if (compareBoards(board, closedNodes.get(i).board))
					return true;
			}
		}
		return false;
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
		for(int i=0;i<size;i++)
		{
			for(int z=0;z<size;z++)
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
		for(int i=0;i<size;i++)
		{
			for(int z=0;z<size;z++)
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
		boolean[] check=new boolean[size*size];
		for(int g=0;g<((size*size));g++)
		{
			check[g]=false;
		}
		for (int i = 0; i < values.length; i++)
		{
		    for (int j = 0; j < values[0].length; j++)
			{
			         // check to see if values are in range.
                                 if (values[i][j] > ((size*size)-1) || values[i][j] < 0) return false;
				 
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
	int [][] answer=new int[size][size];
	String [] sArr=new String[size];
	String input="";
	int counter=0;
	boolean alldigit=true;
	while(!full)
		{
		input=JOptionPane.showInputDialog(null,text);
		sArr=input.split(" ");
		if(sArr.length==(size*size))
			{
			for(int h=0;h<sArr.length;h++)
			{
				if(!sArr[h].matches("\\d+"))
					alldigit=false;
			}
			if(alldigit)
			{
			for(int i=0;i<size;i++)
				{
				for(int j=0;j<size;j++)
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
						JOptionPane.showMessageDialog(null,"State entered did not contain numbers 0-"+((size*size)-1));
					}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"State entered did not contain all numbers");
			}
			}
		else
			{
			JOptionPane.showMessageDialog(null,"State entered did not contain exactly"+(size*size)+ " states");
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
				temp[i][j]=source[i][j];
		}
		return temp;
	}
	
	public static void setSize()
	{
		String options[]={"8","15"};
        int result = JOptionPane.showOptionDialog(null, "Please select the size of the puzzle.", "A*", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if(result==0)
			size=3;
		else
			size=4;
	}
	
static class Node{  
	 public  int[][] board;
	public  int gVal = 0;
	 public int hVal = 0;
	public int cost;
	public Node parent;
	
  	Node(int[][] board, int gVal, int hVal, Node parent)
	{
		this.board = board;
		this.gVal = gVal;
		this.hVal = hVal;
		this.parent = parent;
		this.cost = gVal + hVal;
	}
	
	Node (int[][]board, int gVal, int hVal)
	{
		this.board= board;
		this.gVal = gVal;
			this.hVal = hVal;
		this.cost = gVal + hVal;
	}
  }  
}  
	
		
