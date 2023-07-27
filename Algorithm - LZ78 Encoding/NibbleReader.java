/*
 * Singleton Class To Read in Nibbles
 * 
 * A singleton class who can read and write bytes via standard input and output passed into it.
 * Its main purpose is to be a global point of access to the opened input and output streams.
 * @author Veren Villegas 1574646
 * @author Ben Clark 1574645
 */
import java.io.*;
public class NibbleReader{

    private static NibbleReader instance = null;
    
    //Have the next hex value be a unique character which is ONE BACK SLASH
    //private static char nextHex = '\\';

    private static boolean nextByteValid = false;
    private static byte nextByte = 0;

    //the input stream that stores the characters, should be the System.in stream of the main class
    private static BufferedReader inputStream;
    private static BufferedWriter outputStream;

    /**
     * Private Constructor to remove the ability to create an extra instance of this class
     */
    private NibbleReader(){
    }

    /**
     * Creates an initialisation of a NibbleReader if the class doesn't yet exist 
     * else the existing instance is returned
     * @return an instance of the NibbleReader
     */
    public static NibbleReader getInstance(){
        if(instance==null){
            instance = new NibbleReader();
        }
        return instance;
    }

    /**
     * Prints the byte passed in to the current outputStream instance
     * @param output the byte to print 
     */
    public static void printByte(byte output){
        if(outputStream == null){
            System.out.println("outputStream is null");
        }
        try{
            outputStream.write((int) output);
            outputStream.flush();
        }
        catch(IOException e){
            System.out.println("NibbleReader.printByte(): " + e.getMessage());
        }
    }

    /**
     * Closes the inputSteam instance if its initialised
     */
    public static void closeInputStream(){
        if(inputStream != null){
            try{
                inputStream.close();
            }
            catch(IOException e){
                System.out.println("NibbleReader.closeStreams(): " + e.getMessage());
            }
        }

    }

    /**
     * Closes the outputStream instance if its initialsed
     */
    public static void closeOutputStream(){
        if(outputStream != null){
            try{
                outputStream.flush();
                outputStream.close();
            }
            catch(IOException e){
                System.out.println("NibbleReader.closeStreams(): " + e.getMessage());
            }
        }
    }

    /**
     * sets the input output streams to read and write bytes to
     * @param input the inputstream to read bytes from, e.g. System.in from main class
     * @param output the outputstream to write bytes to, e.g. System.out from main class
     */
    public static void initiateStreams(BufferedReader input, BufferedWriter output){
        inputStream = input;
        outputStream = output;
    }

    /**
     * Reads in the next byte from the input stream then breaks it into two seperate nibbles.
     * The most significant nibble will be returned first, the on the next call the second nibble will be returned.
     * @return Must return the nibble as a byte with the bits in the lower position (ie no bits set above index 3)
     */
    public static byte getNextHex(){

        if(inputStream == null){
            System.out.print("Error: NibbleReader has no input stream");
            return (byte) 255;
        }

        //IF the next nibble (as a byte) value is empty THEN read another character and return its first nibble
        if(nextByteValid == false){

            try{
                int intCharInput;
                //IF we have not read all the available bytes THEN read a byte and return a hex value of the first nibble
                if ((intCharInput = inputStream.read()) != -1){
                    byte byteValue = (byte) intCharInput;

                    //Store the right nibble, so it can be returned on the second time getNextHex() is calles
                    byte rightAndInt = (byte) 15;
                    byte rightNibble = (byte) (byteValue & rightAndInt);
                    nextByte = rightNibble;

                    //Shift the left nibble of teh byte to the lower nibble then return it
                    byte leftAndInt = (byte) 240;
                    byte leftNibble = (byte) (byteValue & leftAndInt);
                    leftNibble = (byte) (leftNibble >> 4);

                    nextByteValid = true;
                    return leftNibble; 

                } 
                else{
                    //We have reached the EOF, return 255 which is our EOF byte
                    return (byte) 255;
                }
            }
            catch(Exception e){
                //return EOF byte which is 255, as no normal bytes should have any bits set higer than position 3
                return (byte) 255;
            }
        }
        //ELSE return the second nibble of the last byte read which is stored as "nextHex"
        else{
            //set the next hex value to be empty, this way we read a new byte next time
            nextByteValid = false;
            return nextByte;
        }
    }

}