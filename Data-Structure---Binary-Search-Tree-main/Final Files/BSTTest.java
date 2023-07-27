import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class BSTTest{
	private BinarySearchTree bst;
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	private String output = "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z [ \\ ] ^ _ ` a b c d e f g h i j k l m n o p q r s t u v w x y z";
	
	@BeforeEach
	private void resetTree(){
		bst = new BinarySearchTree();
		System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	private void resetOut(){
		System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	
	private void fillTree(){
		int i = 66;
		while(i <= 122){
			bst.insert(String.valueOf((char)i));
			bst.insert(String.valueOf((char)(i -1)));
			i = i + 2;
		}
	}
	

	@Test
	@DisplayName("Search Method Test 1: Searching for Null")
	/*
	*Testing the intial conditional statements of search()
	*Testing that search returns false when null is passed as an argument (Else statement)
	*Testing that search can execute a search if null is not passed in (IF Statement)
	*/
	public void searchTest(){
		Assertions.assertEquals(bst.search(null), false);
		bst.insert("A");
		Assertions.assertEquals(bst.search("A"), true);
		Assertions.assertEquals(bst.search(null), false);
		Assertions.assertEquals(bst.search(""), false);	
	}
	
	
	@Test
	@DisplayName("Search Method Test 2: Searching for a Value in an Empty Tree")
	/*
	*Testing search() on an empty tree
	*/
	public void searchTest2(){
		int i = 0; 
		while(i <= 123){
			Assertions.assertEquals(bst.search(String.valueOf(i)), false);
			i++;
		}
		Assertions.assertEquals(bst.search(""), false);
	}
	
	
	@Test
	@DisplayName("Search Method Test 3: Searching for the Root Value")
	/*
	*Testing if search() can search for the root value of a bst
	*/
	public void searchTest3(){
		bst.insert("B");
		Assertions.assertEquals(bst.search("B"), true);
		Assertions.assertEquals(bst.search("A"), false);
		Assertions.assertEquals(bst.search("C"), false);
		bst.insert("A");
		bst.insert("C");
		bst.remove("B");
		Assertions.assertEquals(bst.search("B"), false);
	}
	
	
	@Test
	@DisplayName("Search Method Test 4: Searching Left and Right")
	/*
	*Test if search() can search for a value on either side of a tree
	*/
	public void searchTest4(){
		fillTree();
		Assertions.assertEquals(bst.search("B"), true);
		Assertions.assertEquals(bst.search("A"), true);
		Assertions.assertEquals(bst.search("y"), true);
		Assertions.assertEquals(bst.search("z"), true);
		resetTree();
		bst.insert("b");
		bst.insert("a");
		Assertions.assertEquals(bst.search("a"), true);
		bst.remove("a");
		bst.insert("d");
		bst.insert("c"); 
		Assertions.assertEquals(bst.search("c"), true);
		Assertions.assertEquals(bst.height(), 3);
	}
	
	
	@Test
	@DisplayName("Insert Method Test 1: Inserting Null")
	/*
	*Testing if insert() can insert the standalone value of Null into the BST
	*/
	public void insertTest1(){
		bst.insert(null);
		Assertions.assertEquals(bst.height(), 0);
		bst.insert("A");
		bst.insert(null);
		Assertions.assertEquals(bst.height(), 1);
		Assertions.assertEquals(bst.search("A"), true);
	}
	
	
	@Test
	@DisplayName("Insert Method Test 2: Inserting a Value in an Empty Tree")
	/*
	*Testing if insert() can insert a value into an initially empty tree
	*/
	public void insertTest2(){
		bst.insert("B");
		Assertions.assertEquals(bst.height(), 1);
		Assertions.assertEquals(bst.search("B"), true);
	}
	
	
	@Test
	@DisplayName("Insert Method Test 3: Inserting in Order")
	/*
	*Testing if insert() correctly inserts the value in the right place
	*/
	public void insertTest3(){
		fillTree();
		bst.dump();
		Assertions.assertEquals(bst.height(), 30);
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), output);
	}
	
	
	@Test
	@DisplayName("Insert Method Test 4: Inserting Duplicates")
	/*
	*Testing if insert() can insert duplicates
	*/
	public void insertTest4(){
		bst.insert("d");
		bst.insert("d");
		Assertions.assertEquals(bst.height(), 1);
		bst.insert("d" + null);
		bst.insert("D".toLowerCase());
		bst.insert(" d ".trim());
		Assertions.assertEquals(bst.height(), 2);
		resetTree();
		fillTree();
		fillTree();
		bst.dump();
		Assertions.assertEquals(bst.height(), 30);
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), output);
	}
	
	
	@Test
	@DisplayName("Remove Method Test 1: Removing Null")
	/*
	*Testing what happens when remove() is passed null
	*/
	public void removeTest1(){
		bst.remove(null);
		Assertions.assertEquals(bst.height(), 0);
		bst.insert("A");
		bst.insert("null");
		bst.remove(null);
		bst.remove("null");
		Assertions.assertEquals(bst.height(), 1);
		Assertions.assertEquals(bst.search("A"), true);
	}
	
	@Test
	@DisplayName("Remove Method Test 2: Removing from an Empty Tree")
	/*
	*Testing what happens when we try to remove from an empty tree
	*/
	public void removeTest2(){
		bst.remove("A");
		Assertions.assertEquals(bst.height(), 0);
		Assertions.assertEquals(bst.search("A"), false);
		bst.insert("A");
		bst.insert("B");
		bst.remove("A");
		bst.remove("A");
		Assertions.assertEquals(bst.height(), 1);
		Assertions.assertEquals(bst.search("A"), false);
	}
	
	@Test
	@DisplayName("Remove Method Test 3: Removing the root value")
	/*
	*Testing if remove() can remove the root value of a tree.
	*/
	public void removeTest3(){
		fillTree();
		bst.remove("B");
		bst.remove("D");
		Assertions.assertEquals(bst.search("B"), false);
		Assertions.assertEquals(bst.height(), 29);
		Assertions.assertEquals(bst.search("b"), true);
		bst.dump();	
	}
	
	
	@Test 
	@DisplayName("Remove Method Test 4: Removing Left and Right Nodes")
	/*
	*Testing if remove() can remove values on the left and right side of a tree
	*/
	public void removeTest4(){
		fillTree();
		bst.remove("B");
		bst.remove("D");
		Assertions.assertEquals(bst.search("B"), false);
		Assertions.assertEquals(bst.height(), 29);
	}
	
	@Test
	@DisplayName("Remove Method Test 5: Removing From a Right-Sided Tree")
	/*
	*Testing remove() on a right-sided tree (No values on the left side)
	*/
	public void removeTest5(){
		int i = 0;
		while(i < 10){
			bst.insert(String.valueOf(i));
			i++;
		}
		bst.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "0 1 2 3 4 5 6 7 8 9");
		bst.remove("5");
		bst.remove("0");
		bst.remove("9");
		Assertions.assertEquals(bst.search("0"), false);
		Assertions.assertEquals(bst.search("5"), false);
		Assertions.assertEquals(bst.search("9"), false);
		Assertions.assertEquals(bst.height(), 7);
	}
	
	@Test
	@DisplayName("Remove Method Test 6: Removing from a Left-Sided Tree")
	/*
	*Testing if remove() on a left-sided tree (No values on the right side)
	*/
	public void removeTest6(){
		int i = 9;
		while(i >=0){
			bst.insert(String.valueOf(i));
			i--;
		}
		bst.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "0 1 2 3 4 5 6 7 8 9");
		bst.remove("5");
		Assertions.assertEquals(bst.search("5"), false);
		Assertions.assertEquals(bst.height(), 9);	
	}
	
	//DUMP TEST on EMPTY TREE
	@Test
	@DisplayName("Dump Method Test 1: Dumping on an Empty Tree")
	/*
	*Testing dump() on an empty tree
	*/
	public void dumpTest1(){
		bst.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "");
	}
	
	@Test
	@DisplayName("Dump Method Test 2: Dumps in Order")
	/*
	*Testing dump() performs in order traversal and prints items in order according to the compare() method
	*/ 
	public void dumpTest2(){
		fillTree();
		bst.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), output);
	}
	
	@Test
	@DisplayName("Dump Method Test 3: Dumps After Insert()")
	/*
	*Testing if dump() functions after performing an insertion
	*/
	public void dumpTest3(){
		int i = 66;
		while(i <= 122){
			bst.insert(String.valueOf((char)i));
			bst.dump();
			Assertions.assertTrue(outputStreamCaptor.toString().trim().contains(String.valueOf((char)(i))));
			bst.insert(String.valueOf((char)(i -1)));
			bst.dump();
			Assertions.assertTrue(outputStreamCaptor.toString().trim().contains(String.valueOf((char)(i - 1))));
			i = i + 2;
		}
	}
	
	@Test
	@DisplayName("Dump Method Test 4: Dumps After Remove()")
	/*
	*Testing if dump() functions after performing a removal
	*/
	public void dumpTest4(){
		int i = 66;
		while(i <= 122){
			bst.remove(String.valueOf((char)i));
			bst.dump();
			Assertions.assertFalse(outputStreamCaptor.toString().trim().contains(String.valueOf((char)(i))));
			bst.remove(String.valueOf((char)(i -1)));
			bst.dump();
			Assertions.assertFalse(outputStreamCaptor.toString().trim().contains(String.valueOf((char)(i - 1))));
			i = i + 2;
		}
	}

	@Test
	@DisplayName("Height Method Test 1: Height on an Empty Tree")
	/*
	*Testing height() on an empty tree
	*/
	public void heightTest1(){
		Assertions.assertEquals(bst.height(), 0);
		bst.insert("A");
		bst.remove("A");
		Assertions.assertEquals(bst.height(), 0);
		
	}
	
	@Test
	@DisplayName("Height Method Test 2: Returns Correct Height")
	/*
	*Testing height() returns the correct height of the binary search tree
	*/
	public void heightTest2(){
		bst.insert("B");
		bst.insert("C");
		bst.insert("A"); 
		Assertions.assertEquals(bst.height(), 2);
		resetTree();
		fillTree();
		Assertions.assertEquals(bst.height(), 30);
	}
	
	@Test 
	@DisplayName("Height Method Test 3: Height() after Insert()")
	/*
	*Testing height() after insert()
	*/
	public void heightTest3(){
		int i = 66;
		int k = 0;
		while(i <= 122){
			bst.insert(String.valueOf((char)i));
			Assertions.assertEquals(bst.height(), k + 1); 
			bst.insert(String.valueOf((char)(i -1)));
			Assertions.assertEquals(bst.height(), k + 2);
			i = i + 2;
			k++;
		}
		bst.insert("ASKDJAKLSDJALKSDJ");
		Assertions.assertEquals(bst.height(), k + 1);
	}
	
	@Test
	@DisplayName("Height Method Test 4: Height after Remove()")
	/*
	*Testing height() after remove()
	*/
	public void heightTest4(){
		fillTree();
		bst.remove("J");
		bst.remove("I");
		bst.remove("L");
		Assertions.assertEquals(bst.search("J"), false);
		Assertions.assertEquals(bst.height(), 29);
		bst.remove("B");
		Assertions.assertEquals(bst.search("B"), false);
		Assertions.assertEquals(bst.height(), 29);
	}
		
}
