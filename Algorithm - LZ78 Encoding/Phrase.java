/*
 * @author Veren Villegas 1574646
 * @author Ben Clark 1574645
 * Phrase class that represents the output of LZ78
 * Used in the trie structure of the LZ78 encoder,
 * as well as the dictionary of the LZ78 decoder. 
 * Utilizes a linked list data structure.
 */
public class Phrase{
    //Private fields for Phrase
    private byte _mismatchCharacter;
    private int _phraseNumber;
    private Phrase _previousPhrase;
    private Phrase _siblingPhrase;
    private Phrase _childPhrase;


    /*
     * Default constructor
     * Creates a root Phrase
     */
    public Phrase(){
        _mismatchCharacter = '\0';
        _phraseNumber = 0;
        _siblingPhrase = null;
        _childPhrase = null;
        _previousPhrase = null;
    }

    /*
     * Phrase constructor for non-root phrases
     */
    public Phrase(byte mismatchCharacter, int phraseNumber, Phrase previousPhrase){
        _mismatchCharacter = mismatchCharacter;
        _phraseNumber = phraseNumber;
        _previousPhrase = previousPhrase;
        _siblingPhrase = null;
        _childPhrase = null;
    }

    public byte getMismatchChar(){return _mismatchCharacter;}

    public int getPhraseNumber(){return _phraseNumber;}

    public Phrase getPreviousPhrase(){return _previousPhrase;}

    public Phrase getSiblingPhrase(){return _siblingPhrase;}

    public Phrase getChildPhrase(){return _childPhrase;}

    public void setSiblingPhrase(byte mismatch, int phraseNumber){
        _siblingPhrase = new Phrase(mismatch, phraseNumber, this._previousPhrase);
    }

    /*
     * Set's this phrase's sibling
     * Polymorphed method
     * @param nextPhrase The sibling phrase to set.
     */
    public void setSiblingPhrase(Phrase nextPhrase){
        _siblingPhrase = nextPhrase;
    }


    public void setChildPhrase(byte mismatch, int phraseNumber){
        _childPhrase = new Phrase(mismatch, phraseNumber, this._previousPhrase);
    }
}