/**
 * LZdecode class
 * 
 * Reads in LZ encoded standard input from the console and prints out the decoded data
 * @author Veren Villegas 1574646
 * @author Ben Clark 1574645
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
public class LZdecode{

    public static void main(String[] args){
        
        BinarySearchTree BST = new BinarySearchTree();
        
        //Initialise singleton NibbleReader and set its input and output streams to System.in and System.out
        NibbleReader.getInstance();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        NibbleReader.initiateStreams(reader, writer);
    
        //read all of the encoded input and insert it into a BinarySearchTree (BST) datastructure
        String line;
        try{
            while((line = reader.readLine()) != null){
                String[] EncoderPair = line.split(" ");
                //insert the Phrase number and mismatched character into the BST
                int phraseNumber = Integer.parseInt(EncoderPair[0]);
                byte mismatch = (byte) Integer.parseInt(EncoderPair[1]);
                BST.insert(phraseNumber, mismatch);
            }
        }
        catch(IOException e){
            System.out.println("ZDdecode.main: " + e.getMessage());
        }

        //We have finished reading the LZencoded data, close inputStream
        NibbleReader.closeInputStream();

        //Recursively search through the whole Binary Search Tree which will print out all the mismatched characters
        //corresponding to all the phrase numbers. 
        int phraseIndex = 1;
        while(BST.search(phraseIndex)){
           phraseIndex++;
        }

        //close and flush the outputStream
        NibbleReader.closeOutputStream();
    }
}