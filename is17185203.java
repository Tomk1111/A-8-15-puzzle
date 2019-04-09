import java.io.*;
import javax.swing.*;
import java.util.*;
import java.util.Collections;
public class is17185203
{
	private static boolean full=false;
	private static ArrayList<int[][]> open = new ArrayList<int[][]>();
	private static ArrayList<Integer> openCosts = new ArrayList<Integer>(); //HVALUE
	private static ArrayList<Integer> openLevel = new ArrayList<Integer>(); //GVALUE
	private static ArrayList<int[][]> closed = new ArrayList<int[][]>();
	
	private static ArrayList<Node> openNodes = new ArrayList<Node>();
	private static ArrayList<Node> closedNodes = new ArrayList<Node>();
	
	
	
	
	
	
	
	private static ArrayList<Node> bestMoves = new ArrayList<Node>();
	private static int gValue = 0;
	private static int size=0;
	private final static String message1="Please enter the Start state as a sequence of numbers between [0, 8] e.g 5 2 0 6 1 3 4 8 7";
	private final static String message2="Please enter the Start state as a sequence of numbers between [0, 15] e.g 5 2 0 6 1 3 4 8 7 10 9 13 12 11 15 14";
	
public static void main(String[] args) throws Exception
{
	setSize();
	int finall[][];
	int goal[][];
	String message;
	if(size==3)
		message=message1;
	else 
		message=message2;
	boolean valid=false;
	finall=new int[3][3];
	finall=fill(message);
	goal=new int[3][3];
	goal=fill(message.replaceAll("Start","End"));
	int posx=getx(finall);
	int posy=gety(finall);

	int cost = getCost(finall, goal);
	Node sourceNode = new Node(finall, gValue,cost);
	openNodes.add(sourceNode);
//	printBoard(finall);
	//System.out.println();
	solveit(sourceNode, posx, posy, goal);
}



public static void solveit(Node source,int x,int y,int [][]goal)
{
	try
	{	
		ArrayList<Node> bestPath = new ArrayList<Node>();
		boolean check = false;
		if (compareBoards(source.board, goal))	
		{
			System.out.println("WIN");
			while (!check)
			{
				bestPath.add(source);
				

				if (source.parent == null)
				{
					check = true;
					
				}
				else
					source = source.parent;
			}
			Collections.reverse(bestPath);
			for (Node tempN : bestPath)
			{
				printBoard(tempN.board);
				System.out.println("---------");
				
			}
		}
			
			//System.out.println("You win");
			//printBoard(source);
		
		else
		{
			gValue = openNodes.get(0).gVal+1;
			int[][]temp=new int[source.board.length][source.board.length];
			temp=copy(source.board);
			
			int tempx=0;
			if(y+1<source.board.length)
				{
				//	System.out.println("(B): "+source[x][y+1] + " to the west");
					temp=copy(source.board);
					tempx =temp[x][y+1];
					temp[x][y+1]=0;
					temp[x][y]=tempx;
	
					if (!boardSearched(temp))
					{
					//	printBoard(temp);
					//	System.out.println();
					//	System.out.println("Cost =" +(getCost(temp,goal) + gValue));
						Node tempNode = new Node(temp, gValue, getCost(temp, goal), source);
						openNodes.add(tempNode);
				//		open.add(temp);
					//	openCosts.add(getCost(temp, goal));
						//openLevel.add(gValue);
					}
				}
				if(y-1>=0)
				{
				//	System.out.println("(A): "+source[x][y-1] +" to the east");
					temp=copy(source.board);
					tempx =temp[x][y-1];
					temp[x][y-1]=0;
					temp[x][y]=tempx;
	
					if (!boardSearched(temp))
					{
						
						Node tempNode = new Node(temp, gValue, getCost(temp, goal), source);
						openNodes.add(tempNode);
					//	printBoard(temp);
					//	System.out.println();
					//	System.out.println("Cost =" +(getCost(temp,goal) + gValue));
						//open.add(temp);
						//openCosts.add(getCost(temp, goal));
						//openLevel.add(gValue);
					}
				}
				
				if(x-1>=0)
				{
				//	System.out.println("(C): "+source[x-1][y] + " to the south");
					temp=copy(source.board);
					tempx =temp[x-1][y];
					temp[x-1][y]=0;
					temp[x][y]=tempx;

					if (!boardSearched(temp))
					{
						Node tempNode = new Node(temp, gValue, getCost(temp, goal), source);
						openNodes.add(tempNode);
					//	printBoard(temp);
					//	System.out.println();
					//	System.out.println("Cost =" +(getCost(temp,goal) + gValue));	
					//	open.add(temp);
					//	openCosts.add(getCost(temp, goal));
					//	openLevel.add(gValue);
					}
				}
				
				if(x+1<source.board.length)
				{
				//	System.out.println("(D): "+source[x+1][y] + " to the north");
					temp=copy(source.board);
					tempx =temp[x+1][y];
					temp[x+1][y]=0;
					temp[x][y]=tempx;
	
					if (!boardSearched(temp))
					{
						Node tempNode = new Node(temp, gValue, getCost(temp, goal), source);
						openNodes.add(tempNode);
					//	printBoard(temp);
					//	System.out.println();
					//	System.out.println("Cost =" +(getCost(temp,goal) + gValue));
						//open.add(temp);
						//openCosts.add(getCost(temp, goal));
						//openLevel.add(gValue);
					}
				}
				
				if (openNodes.size() > 0)
				{
				closedNodes.add(openNodes.get(0));
				openNodes.remove(0);
				}
				
				
				sortOpenList();
			
				//	System.out.println("Best Choice is:\n");
				//printBoard(open.get(0));
				//	System.out.println();

				//Node move = new Node(open.get(0), gValue, getCost(open.get(0), goal), parentNode);
				//bestMoves.add(parentNode);
				//int pos = 0;
			/*	if (bestMoves.size() > 0)
				{
						System.out.println("ln178 SIZE : " + bestMoves.size());
							int smallest = bestMoves.get(bestMoves.size()-1).cost;
							for (int i=bestMoves.size()-1;i>=0;i--)
							{
								System.out.println("ln183 SIZE : " + bestMoves.size());
								if (bestMoves.get(i).cost < smallest)
								{
									System.out.println("ln186 SIZE : " + bestMoves.size());
									smallest = bestMoves.get(i).cost;
									pos = i;
									parentNode = bestMoves.get(pos).parent;
								}
							}
							
							if (bestMoves.get(pos).gVal <=bestMoves.get(bestMoves.size()-1).gVal)
							{
								System.out.println("ln195 SIZE : " + bestMoves.size());
								bestMoves.clear();
								System.out.println("ln197 SIZE : " + bestMoves.size());
								while(parentNode.parent != null)
								{
									System.out.println("ln199 SIZE : " + bestMoves.size());
									bestMoves.add(parentNode);
									if (parentNode.parent != null)
									{
										System.out.println("ln203 SIZE : " + bestMoves.size());
										parentNode = parentNode.parent;
									}
								};
							}
				}*/
				//int[][] newSource = open.get(0);
				
			
				
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
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	
	public static void sortOpenList() //BUBBLE SORT FOR NOW
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
		if (open.size() > 0)
		{
			for (int i=0;i < open.size();i++)
			{
				if (compareBoards(board, open.get(i)))
					return true;
			}
		}
		if (closed.size() > 0)
		{
			for (int i=0;i < closed.size();i++)
			{
				if (compareBoards(board, closed.get(i)))
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
			{
			temp[i][j]=source[i][j];
			}
		
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
	
	