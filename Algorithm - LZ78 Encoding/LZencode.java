/**
 * LZencode class
 * 
 * Reads from standard input and encodes standard bytes as a Phrase number and mismatched character pair
 * @author Veren Villegas 1574646
 * @author Ben Clark 1574645
 */
import java.io.*;
public class LZencode{

    public static void main(String args[]){
        Trie dictionary = new Trie();

        //Initialise singleton NibbleReader and set its input and output streams to System.in and System.out
        NibbleReader.getInstance();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        NibbleReader.initiateStreams(reader, writer);

        //While NibbleReader doesn't return the EOF byte (255) insert the byte into the Trie data structure
        byte character2Encode = NibbleReader.getNextHex();
        while(character2Encode != (byte) 255){
            dictionary.insert(character2Encode);
            character2Encode = NibbleReader.getNextHex();
        }

        NibbleReader.closeInputStream();
        NibbleReader.closeOutputStream();
    }
}