/**
 * BinarySearchTree class
 * 
 * A binary search tree containing nodes whose value is an 
 * index, phrase number, and mismatched character.
 * @author Veren Villegas 1574646
 * @author Ben Clark 1574645
 */
public class BinarySearchTree {
    //The root of the tree
    private BSTNode root;

    private boolean firstNibbleValid = false;
    private byte firstNiblle;

    //the number of nodes in the tree (starting from 1)
    private int bstHeight = 0;

    /**
     * BinarySearchTree constructor
     * Initialises an empty BST
     */
    public BinarySearchTree(){
        root = null;
    }

    /**
     * Return a potential sequence of mismatched charcters that are encoded by a phrase number
     * @param phraseNumber the Phrase number we want 
     * @return a potential sequence of mismatched characters
     */
    public boolean search(int phraseNumber){
        if(root == null || phraseNumber <= 0){
            return false;
        }
        return search(phraseNumber, root);
    }

    private boolean search(int phraseNumber, BSTNode curr){

        //base case, means the phraseNumber we are searching for doesn't equal an existing entry
        if(curr == null){
            return false;
        }
        //Compare the current node to the value and move down the tree accordingly
        //if we have found the node THEN print out the mismatch characters from the phrasenumbers then print out this mismatched character
        if(phraseNumber == curr.phraseIndex){

            //if phrasenumber is 0 THEN just print this mistmatch/set to fistNibble
            if(curr.phraseNumber == 0){

                //if we have already stored the last nibble, pair it with the new nibble 
                if(firstNibbleValid == true){
                    //OR the two nibbles to form a byte that can be written to output
                    firstNibbleValid = false;
                    byte output = (byte) (((firstNiblle << 4) | curr.mismatchCharacter));
                    NibbleReader.printByte(output);                   
                }
                else{
                    //set the firstNibble to mismatch
                    firstNibbleValid = true;
                    firstNiblle = curr.mismatchCharacter;
                }
            }
            else{
                //Search for the parent node (int terms of trie structure)
                search(curr.phraseNumber);

                //We have recursed back, time to print this nodes mismatched character
                if(firstNibbleValid == true){
                    //OR the two nibbles to form a byte that can be written to output
                    firstNibbleValid = false;
                    byte output = (byte) ((firstNiblle << 4) | curr.mismatchCharacter);
                    NibbleReader.printByte(output);
                }
                else{
                    firstNibbleValid = true;
                    firstNiblle = curr.mismatchCharacter;
                }
            }
            return true;
        }
        else if(phraseNumber < curr.phraseIndex){
            //move down the left branch
            return search(phraseNumber, curr.left);
        }
        else{
            //move down the right branch
            return search(phraseNumber, curr.right);
        }
    }

    /**
     * Insert a phrase number and mismatched character into the BST
     * @param phraseNumber the phrase number of the encoding
     * @param mismatchCharacter the mismatched character of the encoding
     */
    public void insert(int phraseNumber, byte mismatchCharacter){
        //if the tree is empty insert this value at the top position and increase the height to 1
        if(root == null){
            bstHeight = 1;
            root = new BSTNode(bstHeight, phraseNumber, mismatchCharacter);
            return;
        }
        //root is the top BST node, bstHeight++ is the next bstIndex value
        bstHeight = bstHeight +1;
        BSTNode newNode = new BSTNode(bstHeight, phraseNumber, mismatchCharacter);
        insert(newNode, root);
    }

    /**
     * Recursivly move down the tree until an empty position is found, then insert the node
     * Check the curr node is balanced, if not perform left rotation, THEN recursivly return and
     * rebalance when needed
     * @param newNode the new node we want to insert in to the BST
     * @param curr the current node we are looking at in the BST
     */
    private void insert(BSTNode newNode, BSTNode curr){
        //IF the phraseIndex is greater than the current phraseIndex THEN move down the right branch
        if(newNode.phraseIndex > curr.phraseIndex){
            //IF the right branch is null THEN insert the new BSTNode there
            if(curr.right == null){
                curr.right = newNode; 
                return;
            }
            insert(newNode, curr.right);
        }
        //ELSE move down the left branch
        else{
            //IF the right branch is null THEN insert the new BSTNode there
            if(curr.left == null){
                curr.left = newNode;
                return;
            }
            insert(newNode, curr.left);
        }
        //Node has been inserted, check the tree is balanced
        if(isBalanced(curr.right) != true){
            //the tree is not balanced with this node, perform a left rotation
            curr.right = leftRotate(curr.right);      
        }
    }

    /**
     * Return the height of the BST tree from the passed in node
     * @param curr the BSTNode we want to count the height from
     * @return the height of the BST from the curr BSTNode
     */
    private int height(BSTNode curr){
        if(curr == null)
            return 0;
        return 1 + (Math.max(height(curr.left), height(curr.right)));
    }

    /**
     * A left rotation to fix the right side Zig Zig imbalance (the only possible imbalance with this BST)
     * The return value should be assigned to the parents .right value unless this is root
     * In terms of starting locaiton in the zig zig imbalance B would be the top, then A is B.right, then E is A.left
     * @param B The out of order node
     * @return the top Node of the newly constructed branch, should be assigned to the parents .right value unless root
     */
    private BSTNode leftRotate(BSTNode curr){
        BSTNode right = curr.right;
        BSTNode rightLeft = right.left;
        right.left = curr;
        curr.right = rightLeft;
        return right;
    }

    /**
     * Determine if the BST is unbalanced by traveling from the node passed in down the BST
     * If the height from the node differes more than 1 return false
     * @param curr the node to determine if its balanced
     * @return returns true false if the height difference is more than one, else returns true
     */
    private boolean isBalanced(BSTNode curr){
        if(curr == null){
            return true;
        }

        int leftHeight = height(curr.left);
        int rightHeight = height(curr.right);

        if(Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(curr.left) && isBalanced(curr.right)){
            return true;
        }
        return false;
    }
}