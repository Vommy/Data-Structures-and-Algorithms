import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class PriorityQueueTest{

	private PriorityQueue p1 = new PriorityQueue();
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	
	@BeforeEach
	private void resetPriorityQueue(){
		p1 = new PriorityQueue();
		System.setOut(new PrintStream(outputStreamCaptor));
	}	
	
	
	/*
	*insertTest1()
	*Testing if insert() can insert node values and maintain priority order
	*Directly testing upheap as well
	*/
	@Test
	@DisplayName("Insert Method Test 1: Inserts a value and maintains priority")
	public void insertTest1(){
		Node a1 = new Node(1);
		Node a2 = new Node(2);
		Node a3 = new Node(3);
		p1.insert(a3);
		p1.insert(a2);
		p1.insert(a1); 
		p1.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "Root<Priority: 1>; left<Priority: 3>; right<Priority: 2>;\nRoot<Priority: 3>; left<null>; right<null>;\nRoot<Priority: 2>; left<null>; right<null>;");
	}
	

	/*
	*insertTest2()
	*Testing if insert() can insert null nodes
	*Insert should not be able to insert null nodes
	*/
	@Test
	@DisplayName("Insert Method Test 2: Inserting null")
	public void insertTest2(){
		Node a1 = null;
		p1.insert(a1);
		p1.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "");
	}
	
	
	/*
	*insertTest3()
	*Testing if insert() can insert nodes with negative priorities other than -1
	*A node with a priority of -1 represents an "empty" node 
	*Priorities can theoretically not be less than 0 (highest priority), therefore negative priority nodes should not be added
	*/
	@Test
	@DisplayName("Insert Method Test 3: Inserting Negative Priority Nodes")
	public void insertTest3(){
		Node a1 = new Node(-5);
		p1.insert(a1);
		p1.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "");
	}


	/*
	*insertTest4()
	*Testing if insert() can insert nodes with priorities that already exist in the minheap
	*Insert() should be able to insert duplicates, as nodes can have the same priority. 
	*/
	@Test
	@DisplayName("Insert Method Test 4: Inserting Duplicate Nodes")
	public void insertTest4(){
		Node a1 = new Node(1);
		Node a2 = new Node(2);
		Node a3 = new Node(3);
		Node a1v2 = new Node(1);
		p1.insert(a3);
		p1.insert(a2);
		p1.insert(a1);
		p1.insert(a1v2);
		p1.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "Root<Priority: 1>; left<Priority: 1>; right<Priority: 2>;\nRoot<Priority: 1>; left<Priority: 3>; right<null>;\nRoot<Priority: 2>; left<null>; right<null>;\nRoot<Priority: 3>; left<null>; right<null>;");
	}
	
	
	/*
	*insertTest5()
	*Testing if insert() works after a delete()
	*/
	@Test
	@DisplayName("Insert Method Test 5: Inserting after a delete()")
	public void insertTest5(){
		for(int i = 0; i < 4; i++){
			Node a = new Node(i);
			p1.insert(a);
		}
		p1.delete();
		p1.delete();
		Node a5 = new Node(5);
		p1.insert(a5);
		p1.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "Root<Priority: 2>; left<Priority: 3>; right<Priority: 5>;\nRoot<Priority: 3>; left<null>; right<null>;\nRoot<Priority: 5>; left<null>; right<null>;");
	}
	
	
	/*
	*deleteTest1()
	*Testing if delete() returns the first item in the Priority Queue
	*Also testing if delete() maintains priority order through downheap()
	*/
	@Test
	@DisplayName("Delete Method Test 1: Returns First Item And Maintains Priority Order")
	public void deleteTest1(){
		for(int i = 0; i <= 5; i++){
			Node a = new Node(i);
			p1.insert(a);
		}
		Node b = new Node(0);
		p1.insert(b); 
		Node del = p1.delete();
		Assertions.assertEquals(del.toString(), "Priority: 0");
		p1.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "Root<Priority: 0>; left<Priority: 1>; right<Priority: 2>;\nRoot<Priority: 1>; left<Priority: 3>; right<Priority: 4>;\nRoot<Priority: 2>; left<Priority: 5>; right<null>;\nRoot<Priority: 3>; left<null>; right<null>;\nRoot<Priority: 4>; left<null>; right<null>;\nRoot<Priority: 5>; left<null>; right<null>;");
	}
	
	
	/*
	*deleteTest2()
	*Testing delete() on an empty heap
	*Should return an uninitialized node
	*/
	@Test
	@DisplayName("Delete Method Test 2: Delete() On An Empty Heap")
	public void deleteTest2(){
		Node test = p1.delete();
		p1.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "");
	}
	
	
	/*
	*deleteTest3()
	*Testing if delete() can delete everything from the heap
	*/
	@Test
	@DisplayName("Delete Method Test 3: Delete() deletes everything")
	public void deleteTest3(){
		for(int i = 0; i < 3; i++){
			Node a = new Node(i);
			p1.insert(a);
		}
		p1.delete();
		p1.delete();
		p1.delete();
		p1.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "");
	}
	
	/*
	*dumpTest1()
	*Testing dump() on an empty tree
	*/
	@Test
	@DisplayName("Dump Method Test 1: dump() On An Empty Tree")
	public void dumpTest1(){
		p1.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "");
	}
	
	
	/*
	*dumpTest2()
	*Testing that dump() prints out all the nodes in the order that they are in within the heap
	*/
	@Test
	@DisplayName("Dump Method Test 2: dump() Prints All Nodes In The In Heap Order")
	public void dumpTest2(){
		for(int i = 0; i <= 5; i++){
			Node a = new Node(i);
			p1.insert(a);
		}
		p1.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "Root<Priority: 0>; left<Priority: 1>; right<Priority: 2>;\nRoot<Priority: 1>; left<Priority: 3>; right<Priority: 4>;\nRoot<Priority: 2>; left<Priority: 5>; right<null>;\nRoot<Priority: 3>; left<null>; right<null>;\nRoot<Priority: 4>; left<null>; right<null>;\nRoot<Priority: 5>; left<null>; right<null>;");
	}
	
}
