/**
*A Character Linked List Class
*Holds a head node, which is the start and access point to the linked list
*Acts as a super class for the ordered linked list
*@author Veren Villegas
*ID: 1574646
*/
public class ABCList{
	protected ABCNode _listHead;
	
	//CONSTRUCTORS
	
	ABCList(){
		_listHead = null;
	}
	
	/**
	*@param nodeToAdd The node to initialise the linked list with
	*/
	ABCList(ABCNode nodeToAdd){
		_listHead = nodeToAdd;
	}
	
	/**
	*@param c The character to initialise the linked list with
	*/
	ABCList(char c){
		ABCNode newNode = new ABCNode(c);
		_listHead = newNode;
	}
	
	//METHODS
	
	/**
	*Sets the ListHead for this object through the recursive addR function
	*@param newNodeValue The character we are adding to the list
	*/
	public void add(char newNodeValue){
		_listHead = addR(_listHead, newNodeValue);
	}
	
	/**
	*Adds a new node to the list through recursion
	*@param node2Return The node to return from the method
	*@param newNodeValue The value of the new node we are adding.
	*/
	private ABCNode addR(ABCNode node2Return,char newNodeValue){
		if(node2Return == null)
			node2Return = new ABCNode(newNodeValue);
		else
			node2Return.setNextNode(addR(node2Return.getNextNode(), newNodeValue));
		return node2Return;	
	}
	
	/**
	*Returns the result of the recursive hasR function
	*@param charToCheck The character we are looking for
	*/
	public boolean has(char charToCheck){
		return hasR(charToCheck, _listHead);
	}
	
	/**
	*Checks if a character exists in the linked list through recursion
	*@param charToCheck The character we are looking for
	*@param node2Comp The current node we are comparing values with
	*/
	private boolean hasR(char charToCheck, ABCNode node2Comp){
		if(node2Comp != null)
			if(node2Comp.getNodeValue() == charToCheck)
				return true;
			else
				return hasR(charToCheck, node2Comp.getNextNode());
		return false;
	}
	
	/**
	*Returns the length of the list
	*/
	public int length(){
		ABCNode currNode = _listHead;
		int count = 0;
		while(currNode != null){
			count++;
			currNode = currNode.getNextNode();
		}
		return count;
		//Iterative solution chosen for speed, 
		//Due to the simple nature of the operation
		
		//RECURSIVE Solution:
		//return lengthR(_listHead); 	
	}
	/**
	*Calculates the length of the linked list through recursion
	*@param currNode The current node
	
	private int lengthR(ABCNode currNode){
		if(currNode == null)
			return 0;
		else
			return lengthR(currNode.getNextNode()) +1;
	}
	*/ 
	
	/**
	*Calls the recursive dump function
	*/
	public void dump(){
		dumpR(_listHead);
	}
	
	/**
	*Prints the value of each node in the linked list with a newline character
	*If the list is empty, dumpR will print "Empty List"
	*@param currNode The current node to print
	*/
	private void dumpR(ABCNode currNode){
		if(_listHead == null)
			System.out.println("Empty List");
		else{
			if(currNode != null){
				System.out.println(currNode.getNodeValue() + "\n");
				dumpR(currNode.getNextNode());
			}
		}
	}
	
	/**
	*Returns a boolean value indicating if the list is empty
	*/
	public boolean isEmpty(){
		if(_listHead == null)
			return true;
		else
			return false;
	}
	
	/**
	*Sets the listhead through the recursive removeR function
	*@param c The character to remove from the linked list
	*/
	public void remove(char c){
		_listHead = removeR(c, _listHead);
	}
	
	/**
	*Removes the first instance of a character from the list. 
	*@param char2Rem The character to remove
	*@param currNode The current node we are comparing
	*/
	private ABCNode removeR(char char2Rem, ABCNode currNode){
		if(currNode != null){
			if(currNode.getNodeValue() == char2Rem){
				currNode = currNode.getNextNode();
				//currNode = removeR(char2Rem, currNode); Removes all instances of the character
			}
			else
				currNode.setNextNode(removeR(char2Rem, currNode.getNextNode()));
		}
		return currNode;
	}
}
