public class BSTNode{
	//PUBLIC FIELDS
	
	String value;
	BSTNode left;
	BSTNode right;
	
	//CONSTRUCTOR
	
	/*
	*BSTNode()
	*Creates a new BSTNode object and initializes fields.
	*/
	BSTNode(){
		value = null;
		left = null;
		right = null;
	}
	
	
	/*
	*BSTNode(String Value)
	*Creates a new BSTNode object with an initialized value
	*@param Value The value to initialize the node with
	*/
	BSTNode(String Value){
		value = Value;
		left = null;
		right = null;
	}
}
