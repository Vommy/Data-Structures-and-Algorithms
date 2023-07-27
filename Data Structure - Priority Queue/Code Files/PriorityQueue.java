import java.util.*;

public class PriorityQueue{
	//private ArrayList<Node> minheap;
	private Node[] minheap;
	
	public PriorityQueue(){
		minheap = new Node[0];
	}
	
	public PriorityQueue(int size){
		minheap = new Node[size];
	}
	
	
	/*
	*size()
	*Returns the number of nodes in the priority queue
	*/
	public int size(){
		return minheap.length;
	}
	
	
	/*
	*isEmpty()
	*@return true If the priority queue is empty, false otherwise
	*/
	public boolean isEmpty(){
		if(minheap.length == 0)
			return true;
		else if(minheap[0] == null)
			return true;
		return false;
	}
	
	
	/*
	*insert(Node n)
	*Inserts a node into the priority queue by adding it to the minHeap
	*Then calls upheap to ensure that minheap is in order
	*@param n The node to insert
	*/
	public void insert(Node n){
		if(n != null && n.priority != -1){ 
			addNode(n);
			upheap(n);
		}
	}
	
	
	/*
	*addNode(Node n)
	*Adds a node to the end of minheap
	*@param n The node to add
	*/
	private void addNode(Node n){
		Node[] newArray = new Node[minheap.length + 1];
		for(int i = 0; i < minheap.length; i++){
			newArray[i] = minheap[i];
		}
		newArray[minheap.length] = n;
		minheap = newArray;
	}
	
	
	/*
	*upheap()
	*Sorts the heap into priority order.
	*A node with a priority of 0 will have the highest priority
	*@param n The most recently added node
	*/
	private void upheap(Node n){
		if(n != null){
			int index = findIndex(n);
			if(index != - 1){
				int parentIndex = findParentIndex(n);
				Node parent = minheap[parentIndex];
				if(parent == null || n.priority < parent.priority){
					swapNodes(index, parentIndex);
					upheap(n);
				}
			}
		}
	}
	
	
	/*
	*findIndex(Node n)
	*Finds the index of a given node in the minheap
	*@param n The node to find the index of
	*@return The index of the given node
	*/
	private int findIndex(Node n){
		for(int i = 0; i < minheap.length; i++){
			if(minheap[i] == n)
				return i;
		}
		return -1;
	}
	
	
	/*
	*findParentIndex(Node n)
	*Finds the index of the parent node of a given node
	*@param n The child node
	*@return The index of the parent node
	*/
	private int findParentIndex(Node n){
		if(n != null){
			int index = findIndex(n);
			if(index % 2 == 0 && index != 0){
				index = index - 1; 
			}
			return (index/2);
		}
		return -1;
	}
	

	/*
	*delete()
	*Removes the highest priority node and returns it, then sorts minheap using downheap()
	*@return The node with the highest priority
	*/
	public Node delete(){
		Node node2Return = null;
		if(isEmpty() == false){
			node2Return = minheap[0];
			//Swap root with last item in minheap
			int lastIndex = findLastIndex();
			swapNodes(0, lastIndex);
			removeNode();
			if(size() != 0){
				Node root = minheap[0];
				downheap(root);
			}
		}
		return node2Return;
	}
	
	
	//Downheap
	/*
	*downheap()
	*Resorts the minheap in order after a removal
	*Swaps parent and child nodes until they are in order
	*/
	private void downheap(Node n){
		int index = findIndex(n);
		int leftChildIndex = findLeftChild(index);
		int rightChildIndex = findRightChild(index);
		//If this node has children at all
		if(leftChildIndex < minheap.length){
			Node leftChild = minheap[leftChildIndex];
			Node rightChild = null;
			//If this node has two children
			if(rightChildIndex < minheap.length)
				rightChild = minheap[rightChildIndex];
			//If the parent node has a lesser priority than either of its children
			if(n == null || ((leftChild != null && n.priority > leftChild.priority)||(rightChild != null && n.priority > 				rightChild.priority))){
				//If the rightchild has a higher priority than the left child
				if((rightChild != null && leftChild != null) && (rightChild.priority < leftChild.priority && 				rightChild.priority != -1)){
					swapNodes(index, rightChildIndex);
					downheap(n);
				}
				else if(leftChild != null){
					swapNodes(index, leftChildIndex);
					downheap(n);
				}
			}	
		}
	}
	
	
	/*
	*swapNodes(int index, int child)Index
	*Swaps two nodes in the minheap
	*@param index The index of the first node or parent node
	*@param child The index of the second node or child node
	*/
	private void swapNodes(int index, int childIndex){
		Node temp = minheap[index];
		minheap[index] = minheap[childIndex];
		minheap[childIndex] = temp;	
	}
	
	
	/*
	*removeNode()
	*Removes the last node from the minheap
	*/
	private void removeNode(){
		Node[] newArray = new Node[minheap.length - 1];
		for(int i = 0; i < minheap.length - 1; i++){
			newArray[i] = minheap[i];
		}
		minheap = newArray;
	}
	
	
	/*
	*findLeftChild(int index)
	*Finds the index of the left child in minheap
	*@param index The index of the parent node
	*@return The index of the left child
	*/
	private int findLeftChild(int index){
		index++; 
		return ((index * 2) - 1);
	}
	
	
	/*
	*findRightChild(int index)
	*Finds the index of the right child in minheap
	*@param index The index of the parent node
	*@return The index of the right child
	*/
	private int findRightChild(int index){
		index++;
		return index * 2;
	}
	
	/*
	*findLastIndex()
	*Finds the index of the last item in minheap
	*@return The index of the last item in minheap
	*/
	private int findLastIndex(){
		return minheap.length - 1;
	}
	
	//Dump
	/*
	*dump()
	*Prints out the priority of all the nodes in priority queue
	*In priority order based off the minheap
	*Format:
	*Root<p1>; left<p2>; right<p3>;
	*/
	public void dump(){
		dumpR(0);
	}
	
	
	/*
	*dumpR(int index)
	*Recursively prints a node and it's children
	*@param index The index of the parent node
	*/
	private void dumpR(int index){
		if(index < minheap.length && index >= 0 && minheap.length > 0){
			Node parent = minheap[index];
			int leftChildIndex = findLeftChild(index);
			int rightChildIndex = findRightChild(index);
			Node leftChild = null;
			Node rightChild = null;
			if(leftChildIndex < minheap.length)
				leftChild = minheap[leftChildIndex];
			if(rightChildIndex < minheap.length)
				rightChild = minheap[rightChildIndex];
			System.out.print(nodePrint(parent, leftChild, rightChild));
			dumpR(index + 1);
		}
	}
	
	
	/*
	*nodePrint(Node parent, Node leftChild, Node rightChild)
	*Returns a formatted string of the parent, leftchild and rightchild
	*@param parent The parent node
	*@param leftChild The left child node of the parent
	*@param rightChild The right child node of the parent
	*/
	private String nodePrint(Node parent, Node leftChild, Node rightChild){
		if(parent == null)
			return "";
		else if(parent != null && leftChild == null && rightChild == null)
			return "Root<" + parent.toString() + ">; left<null>; right<null>;\n";
		else if(parent != null && leftChild != null && rightChild == null)
			return "Root<" + parent.toString() + ">; left<" + leftChild.toString() + ">; right<null>;\n";
		else
			return "Root<" + parent.toString() + ">; left<" + leftChild.toString() + ">; right<" + 				rightChild.toString() + ">;\n";
	}
	
}
