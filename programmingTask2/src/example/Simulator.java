package example;
//Eliza Karki CSC 501
import java.text.DecimalFormat;
import java.util.*;

/*This simulator class uses a queue and stack to simulate the average wait time that it takes for students in a line of the
 * university's registrar's office. It simulates a typical day's worth of processing to see if one approach provides a lower
 * average wait time and a lower longest wait time. 
 * The simulation runs for 480 minutes. In each minute, there is a 28% chance of 1 student getting into the line,
 * 35% of no one getting into the line, 21% chance of 2 students getting into line, 13% chance of 3 students getting into line
 * and 3% chance of 4 students getting into line.
 * This simulation runs for 2,3,4 and 5 "registrars" who will be processing the students using a queue and repeats the same for stack.
 *  When a registrar is free, he/she takes the next student in line.
 * There is a 30% chance that the student will need 1 minute of the service, a 33% chance that the student will need 2 minutes of
 * service, 23% chance that the student will need 3 minutes of service, 8% chance that the student will need 4 minutes of service,
 * 4% chance that the student will need 5 minutes of service and 2% chance that the student will need 6 minutes of service.
 * 
 * At the end of the 480 minutes, any student remaining in line will be removed and their wait times computed but they will not be
 * serviced. Then, the average wait time will be computed. 
 */
public class Simulator {

	public static void main(String[] args) {
	    int[] registrarArray; // we declare our first registrar array here, we use for queue, so that we don't create a new registrarArray every time we run through the loop.
	    int[] registrarArr; //we declare our second registrar array to store the registrars so that we can use it for stack

	    Random g = new Random(); // we declare a new random intance for using to create random values as we process forward
	    int studentNumber = 0, stackWait = 0, queueWait = 0; //studentNumber is the total number of students who have waited in line, 
	    //														stackWait is for calculating the total wait time using stack
	    														//queueWait is the total wait time when using the queue
	    Queue q = new Queue(); //we create our queue object which has been implemented using circular array here
	    Stack s = new Stack(); //we create our stack object which has been implemented using a linked list here
	    int time, stillProcessing, waitTime, longestQueueWait, longestStackWait; //we declare our variables here for holding the values
	    //time carries the value from 0-480 minutes, stillProcessing is the time taken for a student by the registrar, waitTime is the wait time of a student, 
	    //longestQueueWait is the longest time taken for a student to wait when implemented as Queue
	    //longestStackWait is the longest time taken for a student to wait when implemented as Stack

	    
	    int temp, n, studentTime; //these are temporary variables storing random values.
	    System.out.println("Using the queue/stack:");
	    System.out.println("Registrars\tStudents\tAvg Wait\tLongest\n");
	    
	    /*This is the outer loop to iterate through the number of registrars. It goes from 2, 3, 4 and 5 registrars.  
		    * We create it so that we only need to run it once for each of the queue and stack rather than running each 4 times. */
	    for (int numRegistrars = 2; numRegistrars <= 5; numRegistrars++) {
	        registrarArray = new int[numRegistrars];
	        registrarArr = new int[numRegistrars];
	        
	        studentNumber = 0; queueWait = 0; time = 1; stillProcessing = 1; waitTime = 0; longestQueueWait = 0;
	        //setting studentNumber to be 0 initially when there are no students or when the office hasn't started
	        //queueWait, waitTime and longestQueueWait is set to 0 as there are no students initially
	        //time is set to 1 minute initially as we add this time to our queue or stack,
	        //boolean stillProcessing = true here keeps track of the time within 480 minutes or after
	        //stillProcessing = 1 indicates still within 480 minutes and the registrar is processing and we can add new students,
	        					//2 indicates after 480 minutes and cannot add new students anymore but still processing the students and
	        					//0 indicates the registrar is done processing, we are not adding students
	        while (stillProcessing != 0) { //when the process has been going on and its not done 
	            if (stillProcessing == 1 && time > 480) { //if the processing is going on but the time has passed already and the office is now closed,
	                stillProcessing = 2; //we set its value to 2 and do not add more students
	            }
	            if (stillProcessing == 1) { //if the processing is going on but time is within 480 we keep on adding the students to the queue
	                n = generateNumStudents(g.nextInt(100)); //here we use our method to generate the number of students using the probabilities as shown below for its full description
	                for (int i = 0; i < n; i++) {	//for each new student,
	                    q.enqueue(time); 			//we add the student to the queue
	                }
	                
	                studentNumber += n; //we add the number of new students to our running total of students who entered the line
	            }
	            if (time >= 480 && q.isEmpty())  { //if our time has passed and we create a flag for keeping track of the number of registrars 
	                int flag = registrarArray.length;
	                for (int i = 0; i < registrarArray.length; i++) {
	                    if (registrarArray[i] == 0) flag--; //we decrement flag when the registrar is free
	                }
	                if(flag == 0) stillProcessing = 0; //it means we are done after this so no more servicing the students when the flag is set to 0
	            }
	            for (int i = 0; i < registrarArray.length; i++) {//we are using this loop to calculate the service time for each student by the single registrar
	                if (registrarArray[i] == 0) { //if the registrar is available, we take the next student from the queue
	                    if (!q.isEmpty()) { 		// if there is a student waiting,
	                        temp = q.dequeue();		//remove that student and compute their wait time (current time - time entered queue)
	                        waitTime = (time - temp);
	                        if (waitTime > longestQueueWait) longestQueueWait = waitTime; //if the student's wait time time is greater than the longest wait time, we save it as new longest wait time
	                        queueWait += waitTime;  									//we compute the student's wait time here after we have calculated the longest wait time.
	                        studentTime = generateStudentTime(g.nextInt(100)); 			//we generate the time of servicing each student would require and store it in our students time
	                        registrarArray[i] = studentTime;							//this registrar will work this student for studentTime minutes 
	                    } 
	                } else 
	                    registrarArray[i]--;     //we reach here if the registrar is busy and process this student for one more minute
	            }
	            time++; //we update our time here to keep track within 480 minutes
	        }
	     // we reach here once 480 minutes have elapsed, add all the wait times of the students still in line to our wait time value
	        while(!q.isEmpty())
	        	queueWait += 480 - q.dequeue();
	        double average=(double)queueWait/studentNumber; //we calculate the average wait time here for the queue
	        DecimalFormat df = new DecimalFormat("###.###"); //this is for the decimal point precision
	    	System.out.println("Queue:");
	        System.out.println(numRegistrars + "\t\t" + studentNumber + "\t\t" +df.format(average)+ "\t\t" + longestQueueWait);    
		            
	        //now we repeat our simulation using a stack:
	        studentNumber = 0; time = 1; stillProcessing = 1; stackWait = 0; waitTime = 0; longestStackWait = 0;

	        while (stillProcessing != 0) {
	            if (stillProcessing == 1 && time > 480) {
	                stillProcessing = 2;
	            }
	            if (stillProcessing == 1) {
	                n = generateNumStudents(g.nextInt(100));
	               
	                for (int i = 0; i < n; i++) { //for each new student,
	                	s.push(time); 				//we add the student's time in our stack
	                }
	                studentNumber += n;
	            }
	            if (time >= 480 && s.isEmpty())  {
	                int flag = registrarArr.length;
	                for (int i = 0; i < registrarArr.length; i++) {
	                    if (registrarArr[i] == 0) flag--;
	                }
	                if(flag == 0) stillProcessing = 0;
	            }
	                for (int i = 0; i < registrarArr.length; i++) {
		            	if (registrarArr[i] == 0) { //if the registrar is available, we take the next student from the stack
		            		if (!s.isEmpty()) { //if there is still student available, 
		                        temp = s.pop(); //we remove the student from the stack and calculate the waitTime
		                        waitTime = (time - temp); //wait time is the current time - the student entered the line
		                        if (waitTime > longestStackWait) longestStackWait = waitTime; //if the wait time of a student is greater than the longest wait then we assign the longest to be the wait time 
		                        stackWait += waitTime;  //the stack wait is assigned as the total wait time
		                        studentTime = generateStudentTime(g.nextInt(100)); //we calculate the students time by using our method
		                        registrarArr[i] = studentTime; //the registrar will spend this much time with the current student
		                    }
		            	}
		            	else 
		                    registrarArr[i]--; //we reach here if the registrar is busy, and we process this student for one more minute
		            }
		            	
		            time++; //we update our time as earlier case within 480 minutes
		        }
	        // we reach here once 480 minutes have elapsed, add all the wait times of the students still in line to our wait time value 
	        while(!s.isEmpty())
	        	stackWait += 480 - s.pop();    
	        double average1 = (double)stackWait/studentNumber; //we calculate the average wait time for the stack
	        System.out.println("Stack:");
	        System.out.println(numRegistrars + "\t\t" + studentNumber + "\t\t" +df.format(average1)+ "\t\t" + longestStackWait + "\n");
	        
	      }
	}

	/* The generateNumStudents method determines how many students enter the line
	 * at a particular minute. It takes as an argument an integer from 1-100, inclusive,
	 * indicating the random value generated for the current minute. It returns an integer
	 * indicating the number of students who arrive based on a provided set of probabilities.
	 * 4 students - 3%
	 * 3 students - 13%
	 * 2 students - 21%
	 * 1 student - 28%
	 * 0 student - 35%
	 * 
	 */
	public static int generateNumStudents(int probability) {
	    if (probability < 3) {
	        return 4;
	    } else if (probability < 16) {
	        return 3;
	    } else if (probability < 37) {
	        return 2;
	    } else if (probability < 65) {
	        return 1;
	    } else {
	        return 0;
	    }
	}
	/* The generateStudentTime method determines the amount of time of a student requires with 
	 * a particular a registrar. It takes as an argument an integer from 1-100, inclusive,
	 * indicating the random value generated for the current student. It returns an integer
	 * indicating the amount of time that it takes for a particular student based on a provided set of probabilities.
	 * 6 minutes - 2%
	 * 5 minutes - 4%
	 * 4 minutes - 8%
	 * 3 minutes - 23%
	 * 2 minutes - 33%
	 * 1 minute - 30%
	 * 
	 */

	public static int generateStudentTime(int probability) {
	    if (probability < 2) {
	        return 6;
	    } else if (probability < 6) {
	        return 5;
	    } else if (probability < 14) {
	        return 4;
	    } else if (probability < 37) {
	        return 3;
	    } else if (probability < 70) {
	        return 2;
	    } else {
	        return 1;
	    }
	}
}

	
