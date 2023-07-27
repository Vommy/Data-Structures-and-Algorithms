public class BinarySearchTree{
	//FIELDS
	
	BSTNode root; 
	
	//CONSTRUCTOR
	/*
	*BinarySearchTree()
	*Initializes a new BinarySearchTree object
	*/
	BinarySearchTree(){
		root = null;
	}
	
	//METHODS
	
	
	/*
	*search(String value)
	*Finds out if a value exists in the binary search tree
	*Calls the recursive search function
	*@return true If value is in binary search tree
	*@param value The value to find
	*/
	public boolean search(String value){
		if(value != null)
			return searchR(value, root);
		else
			return false;
	}
	
	
	/*
	*insert(String value)
	*Inserts a new value into the binary search tree 
	*Assumes no duplicates
	*Calls the recursive insertR function
	*@param value The value to insert
	*/
	public void insert(String value){
		if(value != null)
			root = insertR(value, root);
	}
	
	
	/*
	*insertR(String value, BSTNode curr)
	*Recursively goes through the binary search tree to insert a new value
	*Does not insert value if value exists in the tree
	*@return Returns a the current BSTNode
	*@param value The value to insert
	*@param curr The current node in the tree
	*/
	private BSTNode insertR(String value, BSTNode curr){
		if(curr == null){
			BSTNode newNode = new BSTNode(value);
			return newNode;
		}
		else{
			int result = getResult(curr, value);
			if(result < 0)
				curr.right = insertR(value, curr.right);
			else if(result > 0)
				curr.left = insertR(value, curr.left);
		}
		return curr;		
	}
	
	
	/*
	*searchR(String value, BSTNode curr)
	*Recursively searches the BST if a value exists
	*@return true If the value exists, return true
	*@param value The value we are searching for
	*@param curr The current node in the BST
	*/
	private boolean searchR(String value, BSTNode curr){
		if(curr!=null){
			System.out.print(curr.value + " ");
			int result = getResult(curr, value);
			if(result == 0){
				System.out.print(curr.value + " ");
				return true;
			}
			else if(result > 0){
				if(curr.left != null)
					System.out.print(curr.left.value + " ");
				return searchR(value, curr.left);
			}
			else{
				if(curr.right != null)
					System.out.print(curr.right.value + " ");
				return searchR(value, curr.right);
			}
		}
		else{
			System.out.print(value + " ");
			return false;
		}
	}
	
	
	
	/*
	*getResult(BSTNode curr, String value)
	*Calculates whether a value is greater than, equal to, or less than a node in the BST
	*@return 0 If the value is equal to the current node. 
	*@return <0 A value less than 0 if the value is greater than the current node
	*@return >0 A value greater than 0 if the value is less than the current node
	*@param curr The current BSTNode we are comparing the value to
	*@param value The value we are using to compare
	*/
	private int getResult(BSTNode curr, String value){
		return curr.value.compareTo(value);
	}
	
	
	/*
	*dump()
	*Prints the value of each node in the BST by following an in order traversal through dumpR(root)
	*/
	public void dump(){
		dumpR(root);
		System.out.println();
	}
	
	
	/*
	*dumpR(BSTNode curr)
	*Recursively traverses the BST in order, printing each node.
	*@param curr The current node to process
	*/
	private void dumpR(BSTNode curr){
		if(curr != null){
			if(curr.left != null){
				dumpR(curr.left);
			}
			System.out.print(curr.value + " ");
			if(curr.right != null)
				dumpR(curr.right);
		}
		return;
	}
	
	
	/*
	*remove(String value)
	*Removes the specified string from the BST
	*@param value The value to remove
	*/
	public void remove(String value){
		if(value != null)
			root = removeR(value, root);
	}
	
	
	/*
	*removeR(String value, BSTNode curr)
	*Recursively searches the tree for the specified value and removes it
	*@return null If the value is found, return null
	*@param value The value to remove
	*@param curr The current BSTNode to process
	*/
	private BSTNode removeR(String value, BSTNode curr){
		if(curr != null){
			int result = getResult(curr, value);
			if(result == 0){
				curr = setNewNode(curr);
			}
			else if(result < 0)
				curr.right = removeR(value, curr.right);
			else
				curr.left = removeR(value, curr.left);
		}
		return curr;	
	}
	
	
	/*
	*setNewNode(BSTNode curr)
	*Replaces a node in the BST with the appropriate node
	*@return The appropriate node
	*@param curr The current node to replace
	*/
	private BSTNode setNewNode(BSTNode curr){
		//If the node is a leaf node, set it to null
		if(curr.left == null && curr.right == null)
			return null;
		//If the node has right and left children, replace curr with the lowest node from the right side.
		else if(curr.left != null && curr.right != null){
			return replaceNode(curr);
		}
		//If the node only has a left child, return the left child
		else if(curr.left != null)
			return curr.left;
		//If the node only has a right child, return the right child
		else
			return curr.right;
	}
	
	/*
	*Replaces curr with the lowest node from the right side of the BST
	*@param curr The right node of the node we are replacing
	*@return node2Replace The lowest node from the right side
	*@return curr The original node if the right node of the node we are replacing is the lowest node from the right side.
	*/
	private BSTNode replaceNode(BSTNode curr){
		BSTNode tempLeft = curr.left;
		curr = curr.right;
		BSTNode node2Replace = findLowestRight(curr.left);
		if(node2Replace != null){
			curr.left = null;
			node2Replace.left = tempLeft;
			node2Replace.right = curr;
			return node2Replace;
		}
		else{
				curr.left = tempLeft;
				return curr;
		}
	}
	
	/*
	*findLowestRight(BSTNode node)
	*If a node that we want to remove has two children, 
	*findLowestRight will find the lowest node from the right side to replace it with
	*@return The lowest node from the right side of the node we are removing
	*@param The node we are recursing through
	*/
	private BSTNode findLowestRight(BSTNode node){
		if(node != null){
			if(node.left != null){
				if(node.left.left == null)
					return node.left;
				else
					return findLowestRight(node.left);
			}
			else
				return node;
		}
		return node;
	}
	
	
	/*
	*height()
	*Calculates the height of the BST
	*A BST with a singular node will return a height of 1
	*@return The height of the node
	*/
	public int height(){
		return heightR(root);
	}
	
	/*
	*heightR(BSTNode curr)
	*Calculates the height of the BST through recursion
	*@return The height of the BST
	*@param curr The current node
	*/
	private int heightR(BSTNode curr){
		if(curr == null)
			return 0;
		else{
			int rightTree = heightR(curr.right) + 1;
			int leftTree = heightR(curr.left) + 1;
			if(leftTree > rightTree)
				return leftTree;
			else
				return rightTree;
		}
	}
}
