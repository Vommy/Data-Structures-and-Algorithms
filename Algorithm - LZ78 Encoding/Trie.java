/*
 * @author Veren Villegas 1574646
 * @author Ben Clark 1574645
 * Trie class used in the LZencode class to build up a dictionary
 * Uses phrases as nodes
 */
public class Trie{

    private Phrase _root;
    private int _nextPhraseNumber;

    public Trie(){
        _root = new Phrase();
        _nextPhraseNumber = 1;
    }

    /*
     * Inserts a character into the Trie dictionary using insertR
     * Increments nextPhraseNumber 
     * @param c The character to insert
     */
    public void insert(byte c){
        insertR(c, _root);
        _nextPhraseNumber++;
    }

    /*
     * Recursively traverses the Trie data structure, finding the appropriate place to insert a new phrase
     * @param c The character to insertchar
     * @param curr The current phrase inside of the Trie.
     */
    private void insertR(byte c, Phrase curr){
        //If the current phrase has no child phrases, then immediately create a new child phrase
        if(curr.getChildPhrase() == null){
            curr.setChildPhrase(c, _nextPhraseNumber);

            //EDIT 
            //Change to print out "Phrase number + "[space]" + Decimal representation of character "
            //System.out.println("Phrase Number \t Last Matching Phrase \t Mismatch Character\n" + _nextPhraseNumber + " \t\t " +  curr.getPhraseNumber() + " \t\t\t " + c);
            System.out.println(curr.getPhraseNumber() + " " + c);

        }
        //The current phrase has children, so we need to check if the child and its siblings have the mismatch character
        else{
            //If the current phrase's direct child link has the mismatch character, call insertR on the child node with the next phrase/char using the NibbleReader singleton.
            if(curr.getChildPhrase().getMismatchChar() == c){
                insertR(NibbleReader.getNextHex(), curr.getChildPhrase());
            }
            //Check the current phrase's other children through the direct child's sibling link
            else{
                Phrase childPhrase = curr.getChildPhrase();
                while(childPhrase.getSiblingPhrase() != null && childPhrase.getSiblingPhrase().getMismatchChar() != c){
                    childPhrase = childPhrase.getSiblingPhrase();
                }
                //If none of the current phrase's children have the mismatch character, we can create a new sibling at the end of the sibling list.
                if(childPhrase.getSiblingPhrase() == null){
                    childPhrase.setSiblingPhrase(c, _nextPhraseNumber);

                    //EDIT 
                    //Change to print out "Phrase number + "[space]" + Decimal representation of character "
                    //System.out.println("Phrase Number \t Last Matching Phrase \t Mismatch Character\n" + _nextPhraseNumber + " \t\t " +  curr.getPhraseNumber() + " \t\t\t " + c);
                    System.out.println(curr.getPhraseNumber() + " " + c);
                }
                //We have found a child with the mismatch character, so we need to call insertR on this child phrase with the next phrase from the NibbleReader singleton
                else{
                    insertR(NibbleReader.getNextHex(), childPhrase.getSiblingPhrase());
                }
            }
        }
    }

}