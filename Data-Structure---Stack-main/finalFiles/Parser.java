import java.util.Random;

public class Parser{
	//FIELDS
	private Stack stack;
	
	//CONSTRUCTORS
	Parser(){
		stack = new Stack();
	}
	
	/**
	*check()
	*Calls getString, then checks the equation
	*/
	public void check(){
		String randEqu = getString();
		System.out.println(randEqu + " : " + check(randEqu) + "\n");
	}
	
	/**
	*check(String equation)
	*Overridden check method that deals with string arguments
	*Returns the boolean value from result(equation)
	*@param equation The equation string that we are checking
	*/
	public boolean check(String equation){
		return result(equation);
	}
	
	/**
	*result(String equation)
	*Checks if an equation is properly formatted
	*@return true if the equation is properly formatted
	*@param equation The equation to check
	*/	
	private boolean result(String equation){
		if(equation != null){
			if(equation == "")
				return true;
			else{
				char[] charArray = equation.toCharArray();
				loadStack(charArray);
				int [] bracketCount = countBrackets();
				return isValidEquation(bracketCount);
			}
		}
		else
			return false;
	}
	
	/**
	*loadStack(char[] charArray)
	*Loads a each element of a character array into the stack.
	*@param charArray The character array to load into the stack
	*/
	private void loadStack(char[] charArray){
		for(int i = charArray.length - 1; i >= 0; i--){
			stack.push(String.valueOf(charArray[i]));
		}
	}
	
	/**
	*validEquation(int[] bracketCount)
	*Checks to see if the number of open brackets is equal to the number of closed brackets
	*@return true if the number of closed and open brackets are equal
	*@param bracketCount An integer array storing the count of open brackets and closed brackets
	*/
	private boolean isValidEquation(int[] bracketCount){
		if(bracketCount[0] == bracketCount[1])
			return true;
		else
			return false;
	}
	
	/**
	*node2CharArray(Node node2Conv)
	*Converts a node into a character array
	*@param node2Conv The node to convert
	*/
	private char[] node2CharArray(Node node2Conv){
		String str2Chck = node2Conv.value;
		char[] charArray = str2Chck.toCharArray();
		return charArray;
	}	
	
	/**
	*countBrackets(char[] charArray)
	*Counts the number of brackets in a character array
	*Returns an int[] array of size 2
	*First element is the number of open brackets
	*Second element is the number of closed brackets
	*@param charArray The character array to count
	*/
	private int[] countBrackets(){
		int oBracketCount = 0;
		int cBracketCount = 0;
		while(stack.isEmpty() == false){
			Node charNode = stack.pop();
			char[] charArray = node2CharArray(charNode);
			if(charArray[0] == '(')
				oBracketCount++;
			else if(charArray[0] == ')')
				cBracketCount++;
			if(cBracketCount > oBracketCount){
					oBracketCount = -1;
					break;
				}
		}
		return createBracketCount(oBracketCount, cBracketCount);
	}
	
	/**
	*createBracketCount(int oBracketCount, int cBracketCount)
	*Creates an integer array holding the number of closed and open brackets
	*@param oBracketCount The number of open brackets
	*@param cBracketCount The number of closed brackets
	*/
	private int []createBracketCount(int oBracketCount, int cBracketCount){
		int[] bracketCount = {oBracketCount, cBracketCount};
		return bracketCount;
	}
	
	
	/**
	*getString()
	*Creates an random equation with a maximum size of 10
	*@return Returns the value of createString(charArray)
	*/
	private String getString(){
		Random rand = new Random();
		int arraySize = rand.nextInt(10) + 1;
		char[] charArray = new char[arraySize];
		return createString(charArray);
	}
	
	/**
	*createString(char[] charArray)
	*Creates a random equation string and returns it
	*@param charArray The character array to turn into a string
	*/
	private String createString(char[] charArray){
		char[] brcArray = {'(', ')'};
		Random rand1 = new Random();
		for(int i = 0; i < charArray.length; i++){
			charArray[i] = brcArray[rand1.nextInt(2)];
		}
		String equString = String.valueOf(charArray);
		return equString;
	}
}
