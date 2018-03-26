/* Program to concatenate the users first and last name into one string,
 * and return the char count of the name string. 
 * Also provides a range of my hours in class, and compares it with the users.
 * By: Dustin Fehlman
 */

import java.util.Scanner;
import java.text.DecimalFormat;

public class ConcatNameAndCalcClassHours{
	//Final variables used to calculate class hours range.
	public static final int MIN_HOURS = 12;
	public static final int MAX_HOURS = 20;
	
	public static void main(String[ ] args)
	{
		String firstName = "";
		String lastName = "";
		String fullName = "";
		int nameLength;
		Scanner keyboard = new Scanner(System.in);
		
		//Prompts the user, and obtains user input.
		System.out.println("Please provide your first name (Do not forget to capitalize the first letter):");
		firstName = keyboard.next();
		System.out.println("Please provide your last name (Do not forget to capitalize the first letter):");
		lastName = keyboard.next();
		
		//Concats the users first and last name into one var. 
		fullName = firstName + " " +  lastName;
		//Gets the length of the users name.
		nameLength = fullName.length() - 1;
		
		System.out.println("Here is your full name: " +  fullName + ".");
		System.out.println("The length of you name is " + nameLength + " characters.");
		System.out.println("This is your name in all uppercase " + fullName.toUpperCase() + ".");
		System.out.println("This is your name in all lowercase " + fullName.toLowerCase() + ".\n");
		
		//Gets the range of the hours. 
		double hoursRange = MAX_HOURS - MIN_HOURS;
		String userHours;
		//Sets decimal format
		DecimalFormat oneDeciPoint = new DecimalFormat("0.0");
		System.out.println("The range of hours this week I spent on this class is: " + hoursRange);
		System.out.println("How many hours this week did you spend on class this week? (Please provide the hours to 3 decimal places).");
		//Gets data from user.
		userHours = keyboard.next();
		//Prints formated user hours, and casts the string to  double.
		System.out.println("Your hours to 1 decimal point is " + oneDeciPoint.format(Double.parseDouble(userHours)));
	}
}

/*****************************OUTPUT******************************************

Please provide your first name (Do not forget to capitalize the first letter):
Dustin
Please provide your last name (Do not forget to capitalize the first letter):
Fehlman
Here is your full name: Dustin Fehlman.
The length of you name is 13 characters.
This is your name in all uppercase DUSTIN FEHLMAN.
This is your name in all lowercase dustin fehlman.

The range of hours this week I spent on this class is: 8.0
How many hours this week did you spend on class this week? (Please provide the hours to 3 decimal places).
12.345
Your hours to 1 decimal point is 12.3

Please provide your first name (Do not forget to capitalize the first letter):
John
Please provide your last name (Do not forget to capitalize the first letter):
Doe
Here is your full name: John Doe.
The length of you name is 7 characters.
This is your name in all uppercase JOHN DOE.
This is your name in all lowercase john doe.

The range of hours this week I spent on this class is: 8.0
How many hours this week did you spend on class this week? (Please provide the hours to 3 decimal places).
25.234
Your hours to 1 decimal point is 25.2

 *****************************************************************************/