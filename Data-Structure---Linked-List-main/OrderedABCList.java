/**
*A subclass of the ABCList. 
*Orders the nodes in the linked list based on character value.
*@author Veren Villegas
*ID: 1574646
*/
public class OrderedABCList extends ABCList{
	//CONSTRUCTORS
	
	OrderedABCList(){
		super();
	}
	
	/**
	*@param newNode A node to initialise the list with
	*/
	OrderedABCList(ABCNode newNode){
		super(newNode);
	}

	/**
	*@param c A character to initialise the list with
	*/
	OrderedABCList(char c){
		super(c);
	}
	
	//METHODS
	 
	/**
	*@param nodeValue The character to insert
	*/
	public void insert(char nodeValue){
		ABCNode newNode = new ABCNode(nodeValue);
		_listHead = sortR(newNode, _listHead);
	}
	
	/**
	*Inserts the newNode in the correct spot through recursion. 
	*Characters are compared using their lowercase value. 
	*@param newNode The new node to insert into the linked list
	*@param currNode The node to compare values with
	*/
	private ABCNode sortR(ABCNode newNode, ABCNode currNode){
		if(currNode != null){
			if(Character.toLowerCase(newNode.getNodeValue()) <= Character.toLowerCase(currNode.getNodeValue())){
				newNode.setNextNode(currNode);
				currNode = newNode;
			}
			else
				currNode.setNextNode(sortR(newNode, currNode.getNextNode()));
		}
		else
			currNode = newNode;
		return currNode;
	}	
	
	/**
	*Overriden add method for the OrderedList Subclass
	*Calls insert so that the list remains in order
	*@param newNodeValue The character we are adding
	*/
	@Override
	public void add(char newNodeValue){
		this.insert(newNodeValue);
	}

}

