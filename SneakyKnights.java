import java.awt.Point;
import java.util.*; 

public class SneakyKnights 
{
	public static double difficultyRating()
	{ 
		return 2.0; 
	}
	
	public static double hoursSpent()
	{
		return 14.0; 
	}
	
	//This method collects the y coordinate of a single string
	public static int numberCollector(String str)
	{
		int total = 0;
		int current;
		int counter = 1;
		
		//Starts at the final index of the coordinate string
		for (int i = str.length() - 1; i >= 0 ; i--)
		{			
			if (Character.isLetter(str.charAt(i)))
			{
				//do not finish loop
				break;
			}
			
			//gets the numeric value of that index
			current = (int)str.charAt(i) - '0';
			
			//properly multiplies value by 10 for its position
			current = current * counter;
			total += current;
			
			counter = counter * 10;
		}
		
		return total;
	}
	
	//converts letters portion of base 26 to correct x value
	public static int convertWord(String str)
	{
		int total = 0;
		int current;
		long expo = 0;
		
		for (int i = str.length() - 1; i >= 0 ; i--)
		{
			//if the index is a number skip loop
			if (Character.isDigit(str.charAt(i)))
			{
				continue;
			}
			
			//get letters value of 1 - 26
			current = str.charAt(i) - 'a' + 1;
			
			//first loop is 26^0 
			if (expo == 0)
			{
				total += current;
				expo = 26;
				continue;
			}
			
			total += current * expo;
			expo = expo * 26;	//base 26
		}
		return total;
	}
	
	
	public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)
	{
		int x, y; 
		int j; 
		Point point;
		Point attack1 = new Point(); 
		Point attack2 = new Point(); 
		 
		HashSet<Point> set = new HashSet<>();

		for (int i = 0; i < coordinateStrings.size(); i++)
		{
			j = 0; 
			x = convertWord(coordinateStrings.get(i)); 
			y = numberCollector(coordinateStrings.get(i));
			
			while(j < 4)
			{
				if(j == 0)
				{
					attack1.setLocation(x + 2, y + 1);
					attack2.setLocation(x + 1, y + 2);
					
					if(set.contains(attack1))
					{
						return false; 
					}
					if(set.contains(attack2))
					{
						return false;
					}
				}
				else if(j == 1)
				{
					attack1.setLocation(x - 2, y - 1);
					attack2.setLocation(x - 1, y - 2);
					
					if(set.contains(attack1))
					{
						return false; 
					}
					if(set.contains(attack2))
					{
						return false;
					}
				}
				else if(j == 2)
				{
					attack1.setLocation(x + 2, y - 1);
					attack2.setLocation(x + 1, y - 2);
					
					if(set.contains(attack1))
					{
						return false; 
					}
					if(set.contains(attack2))
					{
						return false;
					}
				}
				else
				{
					attack1.setLocation(x - 2, y + 1);
					attack2.setLocation(x - 1, y + 2);
					
					if(set.contains(attack1))
					{
						return false; 
					}
					if(set.contains(attack2))
					{
						return false;
					}
				}
				j++; 
			}
			point = new Point(x, y); 
			set.add(point); 			
		}
		return true; 
	}
}
