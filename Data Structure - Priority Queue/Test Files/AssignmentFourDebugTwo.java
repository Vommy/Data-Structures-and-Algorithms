public class AssignmentFourDebugTwo {

	/**
	* The entry point for AssignmentFourDebugTwo
	* @param args String array of arguments
	*/
	public static void main(String[] args){

		MedicalCentre medicalCentre = new MedicalCentre();

		// Testing the small patient file
		System.out.println("\nTesting the small patient file:");
		medicalCentre.processPatients("patients_small.txt");

		// Testing the medium patient file
		System.out.println("\nTesting the medium patient file:");
		medicalCentre.processPatients("patients_medium.txt");

		// EXPECTED OUTPUT
		// Testing the small patient file:
		// Priority: 1, Name: Amanda Lopez, Age: 91, Medical severity level: medium, Patient number: 2
		// Priority: 1, Name: Jesse Curry, Age: 7, Medical severity level: high, Patient number: 3
		// Priority: 2, Name: John Schmidt, Age: 25, Medical severity level: medium, Patient number: 4
		// Priority: 3, Name: Emily Hughes, Age: 84, Medical severity level: low, Patient number: 5
		// Priority: 3, Name: Michael Howard, Age: 9, Medical severity level: low, Patient number: 1
		//
		// Testing the medium patient file:
		// Priority: 1, Name: Amanda Lopez, Age: 91, Medical severity level: medium, Patient number: 2
		// Priority: 1, Name: Chad Rodgers, Age: 25, Medical severity level: high, Patient number: 8
		// Priority: 1, Name: Jesse Curry, Age: 7, Medical severity level: high, Patient number: 3
		// Priority: 2, Name: John Schmidt, Age: 25, Medical severity level: medium, Patient number: 4
		// Priority: 2, Name: Stephanie Gross, Age: 39, Medical severity level: medium, Patient number: 9
		// Priority: 2, Name: Kim Huff, Age: 19, Medical severity level: medium, Patient number: 10
		// Priority: 2, Name: Nicholas Riddle, Age: 56, Medical severity level: medium, Patient number: 6
		// Priority: 3, Name: Michael Howard, Age: 9, Medical severity level: low, Patient number: 1
		// Priority: 3, Name: Amy Lester, Age: 52, Medical severity level: low, Patient number: 7
		// Priority: 3, Name: Emily Hughes, Age: 84, Medical severity level: low, Patient number: 5

	}
	
}
