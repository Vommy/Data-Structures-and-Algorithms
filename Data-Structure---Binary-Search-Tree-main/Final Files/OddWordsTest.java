import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class OddWordsTest{
	private OddWords ow1 = new OddWords();
	
	@BeforeEach
	private void resetOddWords(){
		ow1 = new OddWords();
	}

	@Test
	@DisplayName("FinalString Method Test 1: Converts Word into AlphaNum")
	/*
	*Testing if finalString() correctly converts a character into an alphanumerical word
	*/
	public void finalStringTest1(){
		String word = "ABC123//123ABC::A1B2C3()";
		Assertions.assertEquals(ow1.finalString(word), "ABC123  123ABC  A1B2C3  ");
		word = "";
		Assertions.assertEquals(ow1.finalString(word), "");
		word = "!@#$%^&*()";
		Assertions.assertEquals(ow1.finalString(word), "          ");
	}

	@Test
	@DisplayName("FinalString Method Test 2: Passing Null")
	/*
	*Testing finalString() when null is passed
	*/
	public void finalStringTest2(){
		String word = null;
		Assertions.assertEquals(ow1.finalString(word), " ");
	}
	
	@Test
	@DisplayName("isAlphaNum Method Test 1: Correctly Determines AlphaNumerical Characters")
	/*
	*Testing if isAlphaNum() correctly determines alphanumerical characters
	*/
	public void isAlphaNumTest1(){
		Assertions.assertTrue(ow1.isAlphaNum('a'));
		Assertions.assertFalse(ow1.isAlphaNum('!'));
		Assertions.assertFalse(ow1.isAlphaNum(' '));
		Assertions.assertTrue(ow1.isAlphaNum('1'));
		Assertions.assertFalse(ow1.isAlphaNum('	'));
	}


	@Test
	@DisplayName("processWord Method Test 1: Inserts a value")
	/*
	*Testing if processWord() can add a word that does not exist in the binarySearchTree
	*/
	public void processWordTest1(){
		Assertions.assertEquals(ow1.processWord("Testingtesting123"), "INSERTED");
		Assertions.assertEquals(ow1.processWord("Testingtesting124"), "INSERTED");
		Assertions.assertEquals(ow1.processWord("Hello World"), "INSERTED");
		Assertions.assertEquals(ow1.processWord("Hello World"), "DELETED");
		Assertions.assertEquals(ow1.processWord("Hello World"), "INSERTED");
	}
	
	
	@Test
	@DisplayName("processWord Method Test 2: Deletes a value")
	/*
	*Testing if processWord() can delete a word that exists in the binarySearchTree
	*/
	public void processWordTest2(){
		Assertions.assertEquals(ow1.processWord("Hello There"), "INSERTED");
		Assertions.assertEquals(ow1.processWord("Hello There"), "DELETED");
	}
}
