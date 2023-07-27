/**
 *  BSTNode class
 * 
 * Stores an index, phrase number, and mismatched character to illustrate an LZ78 entry
 * @author Veren Villegas 1574646
 * @author Ben Clark 1574645
 */
public class BSTNode {
    //index/Positon in the tree
    public int phraseIndex;

    //will be another nodes index
    public int phraseNumber;

    //public String mismatchCharacter;
    public byte mismatchCharacter;


    public BSTNode left;
    public BSTNode right;

    /**
     * CONSTRUCTOR initialise the node to represent an LZ78 entry with an index
     */
    public BSTNode(int phraseIndex, int phraseNumber, byte mismatchCharacter){
        this.phraseIndex = phraseIndex;
        this.phraseNumber = phraseNumber;
        this.mismatchCharacter = mismatchCharacter;

        left = null;
        right = null;
    }
}
