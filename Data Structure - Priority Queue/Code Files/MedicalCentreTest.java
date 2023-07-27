import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class MedicalCentreTest{
	private MedicalCentre m1 = new MedicalCentre();
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@BeforeEach
	private void resetM1(){
		m1 = new MedicalCentre();
		System.setOut(new PrintStream(outputStreamCaptor));
	}
	
	/*
	*processPatientTest1()
	*Testing if processPatient() can properly process a properly formatted patient record
	*/
	@Test
	@DisplayName("processPatient Test 1: Processes a Properly Formatted Patient Record")
	public void processPatientTest1(){
		String[] details = {"TestSubject1", "54", "low"};
		String separator = ", 	";
		m1.processPatient(details);
		Patient test = (Patient)m1.patientQueue.delete();
		System.out.println(test.toStringLong());
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "Priority: 3" + separator + "Name: TestSubject1" + separator + "Age: 54" + separator + "Medical Severity Level: low" + separator + "Patient Number: 1");
	}
	
	
	/*
	*processPatientTest2()
	*Testing if processPatient() can process a record with whitespaces surrounding the entries
	*/
	@Test
	@DisplayName("processPatient Test 2: Processes A Patient Record With WhiteSpaces")
	public void processPatientTest2(){
		String[] details = {"  TestSubject1     ", "    54", "low       "};
		String separator = ", 	";
		m1.processPatient(details);
		Patient test = (Patient)m1.patientQueue.delete();
		System.out.println(test.toStringLong());
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "Priority: 3" + separator + "Name: TestSubject1" + separator + "Age: 54" + separator + "Medical Severity Level: low" + separator + "Patient Number: 1");
	}
	
	
	/*
	*processPatientTest3()
	*Testing if processPatient() can process a record with a mixed case severity
	*/
	@Test
	@DisplayName("processPatient Test 3: Processes a Patient Record With A Mixed Case Severity")
	public void processPatientTest3(){
		String[] details = {"TestSubject1", "54", "meDiUm"};
		String separator = ", 	";
		m1.processPatient(details);
		Patient test = (Patient)m1.patientQueue.delete();
		System.out.println(test.toStringLong());
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "Priority: 2" + separator + "Name: TestSubject1" + separator + "Age: 54" + separator + "Medical Severity Level: medium" + separator + "Patient Number: 1");
	}	
	
	
	/*
	*processPatientTest4()
	*Testing what happens when processPatient() is passed a String[] full of null
	*Should not add an entry into the priorityQueue
	*/
	@Test
	@DisplayName("processPatient Test 4: Processing Null")
	public void processPatientTest4(){
		String[] details = {null, null, null};
		m1.processPatient(details);
		m1.patientQueue.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "");
	}
	
	
	/*
	*processPatientTest5()
	*Testing what happens when processPatient() is passed a String[] with the data points in the wrong place
	*Should not add the entry into the priorityQueue
	*/
	@Test
	@DisplayName("processPatient Test 5: Processing a Patient Record With Incorrect Data Points")
	public void processPatientTest5(){
		String[] details = {"54", "Medium", "Agather"};
		m1.processPatient(details);
		m1.patientQueue.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "");
	}
	
	/*
	*processPatientTest6()
	*Testing what happens when processPatient() is passed a String[] with purely numerical values
	*Should not add the entry into the priorityQueue as severity cannot be a numerical value
	*/
	@Test
	@DisplayName("processPatient Test 6: Processing a Patient Record With Purely Numerical Data Points")
	public void processPatientTest6(){
		String[] details = {"54", "78993", "800"};
		m1.processPatient(details);
		m1.patientQueue.dump();
		Assertions.assertEquals(outputStreamCaptor.toString().trim(), "");
	}
	
	
	/*
	*calculatePriorityTest1()
	*Testing that calculatePriority() calculates the correct priority
	*Based on a patient's severity and age
	*/
	@Test
	@DisplayName("calculatePriority Test 1: Calculates Correct Priority")
	public void calculatePriorityTest1(){
		int result = m1.calculatePriority("low", 4);
		Assertions.assertEquals(result, 3);
		result = m1.calculatePriority("medium", 64);
		Assertions.assertEquals(result, 2);
		result = m1.calculatePriority("medium", 65);
		Assertions.assertEquals(result, 1);
		result = m1.calculatePriority("high", 65);
		Assertions.assertEquals(result, 1);
	}
	
	/*
	*calculatePriorityTest2()
	*Testing that calculatePriority() returns -1 if an invalid severity is passed
	*/
	@Test
	@DisplayName("calculatePriority Test 2: Returns -1 With Invalid Severity")
	public void calculatePriorityTest2(){
		int result = m1.calculatePriority("5666", 80);
		Assertions.assertEquals(result, -1);
		result = m1.calculatePriority("not severe", 80);
		Assertions.assertEquals(result, -1);
		result = m1.calculatePriority("COMPX7882", 80);
		Assertions.assertEquals(result, -1);
		result = m1.calculatePriority(null, 80);
		Assertions.assertEquals(result, -1);
	}
	
	/*
	*calculatePriorityTest3()
	*Testing the calculatePriority returns -1 if an invalid age is passed (negative)
	*/
	@Test
	@DisplayName("calculatePriority Test 3: Returns -1 With Invalid Age")
	public void calculatePriorityTest3(){
		int result = m1.calculatePriority("low", -1);
		Assertions.assertEquals(result, -1);
	}
	
}
