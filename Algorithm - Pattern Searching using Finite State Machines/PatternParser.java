import java.util.ArrayList;
import java.util.Arrays;

/**
 * Defines a singleton class used for parsing a regular expression. 
 * @author Veren Villegas 1574646 
 * @author Ben Clark 1574645
 */
public class PatternParser{
    private static PatternParser _instance;
    public String[] _regexp;
    public int _counter;
    private ArrayList<String> _nonTerminals = new ArrayList<String>(Arrays.asList(".", "*", "+", "\\", "|", "?", "(", ")", "[", "]"));
    private int numBrackets = 0;

    private PatternParser(String regexp){
        _regexp = regexp.split("");
        _counter = -1;
    }

    public static PatternParser getInstance(String regexp){
        if(!(regexp == null)){
            if (_instance == null){
                _instance = new PatternParser(regexp);
            }
        }
        return _instance;
    }

    /**
     * Increments the counter which keeps track of the current character being parsed in the regular expression by one.
     */
    public void incrementCounter(){
        _counter++;
        if(_counter >= _regexp.length){
            throw new RuntimeException(" Invalid regular expression. Counter exceeded the number of elements in the regular expression." + _counter);
        }
    }

    /**
     * Finds the current character in the regular expression i.e. The terminal or non-terminal we are trying to parse.
     * @return The terminal or non-terminal we are trying to parse. Null if _counter is negative.
     */
    public String getNextSymbol(){
        if(_counter >= -1 && _counter < _regexp.length){
            return _regexp[_counter + 1];
        }
        else
            throw new RuntimeException(" Invalid regular expression. Number of characters does not match the regular expression.");
    }

    /**
     * Says whether the current regular expression has still more characters to parse.
     * @return True if there are more characters to parse, false otherwise.
     */
    public Boolean hasMoreData(){
        if(_counter < _regexp.length - 1){
            return true;
        }
        return false;
    }

    /**
     * Checks if the character is a literal by comparing it against the list of non-terminal characters.
     * @param character The character in question. 
     * @return True if the character is not a non-terminal, False otherwise.
     */
    public Boolean isLit(String character){
        if(_nonTerminals.contains(character)){
            return false;
        }
        return true;
    }

    public void incrementNumBrackets(){
        numBrackets++;
    }

    public void decrementNumBrackets(){
        numBrackets--;
    }

    public int getNumBrackets(){
        return numBrackets;
    }
}