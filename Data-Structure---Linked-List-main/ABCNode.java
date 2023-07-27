/**
*Node class which is a blueprint for nodes in a linked list
*Holds a character value, and points to the next node in the linked list
*@author Veren Villegas
*ID 1574646
*Date Modified: 1/4/2022
*/
public class ABCNode{
	//Private Fields
	private char _nodeValue;
	private ABCNode _nextNode = null;
	
	//CONSTRUCTORS
	
	/**
	*@param nodeValue The character for the node to hold
	*/
	ABCNode(char nodeValue){
		_nodeValue = nodeValue;
	}
	
	ABCNode(){
		_nodeValue = 0;
	}
	
	//FUNCTIONS
	
	/**
	*Returns this object's next node
	*/
	public ABCNode getNextNode(){
		return _nextNode;
	}
	
	/**
	*Sets this object's next node
	*@param nextNode The next node to set
	*/
	public void setNextNode(ABCNode nextNode){
		_nextNode = nextNode;
	}
	
	/**
	*Returns this object's character value
	*/
	public char getNodeValue(){
		return _nodeValue;
	}
		
	/**
	*Sets this object's character value
	*@param nodeValue The character to set
	*/
	public void setNodeValue(char nodeValue){
		_nodeValue = nodeValue;
	}
}
