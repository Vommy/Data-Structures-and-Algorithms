public class Stack{
	//FIELDS
	private Node top;
	
	//CONSTRUCTORS
	Stack(){
		top = null;
	}
	
	//METHODS
	
	/**
	*push(String x)
	*Pushes a new node on top of the stack
	*@param x The string to initialise the new node with
	*/
	public void push(String x){
		Node newNode = new Node(x);
		newNode.nextNode = top;
		top = newNode;
	}
	
	/**
	*pop()
	*Removes the top node from the stack and returns it
	*/
	public Node pop(){
		Node temp = top;
		if(top.nextNode != null)
			top = top.nextNode;
		else
			top = null;
		return temp;
	}
	
	/**
	*peek()
	*Returns the top node's value as a string
	*/
	public String peek(){
		if(top != null){
			if(top.value != null)
				return top.value;
		}
		return "empty string";
	}	
	
	/**
	*isEmpty()
	*Returns a boolean value if the stack is empty
	*@return true if list is empty
	*/
	public boolean isEmpty(){
		if(top == null)
			return true;
		return false;
	}
	
	/**
	*length()
	*Returns the length of the stack as an int
	*@return Returns the length of the stack
	*/
	public int length(){
		return lengthR(top);
	}
	
	/**
	*lengthR(node Current)
	*Calculates the length of the stack through recursion
	*@param current The current Node to count from
	*/
	private int lengthR(Node current){
		if(current == null)
			return 0;
		else
			return lengthR(current.nextNode) + 1;
	}
	
	/**
	*dump()
	*Prints to the command line the value of each node in the stack
	*Calls the recursive dump function
	*/
	public void dump(){
		dumpR(top);
	}
	
	/**
	*Prints the value of each node in the stack through recursion
	*@param current The current node to print
	*/
	private void dumpR(Node current){
		if(current != null){
			System.out.println(current.value);
			dumpR(current.nextNode);
		}
		else
			System.out.println("\n");
	}
}
