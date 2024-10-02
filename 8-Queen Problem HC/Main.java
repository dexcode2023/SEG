import java.util.Scanner;

 
public class Main {
	
	


	public static void main(String[] args) {
		
	       
		    int n = 0; 
	        try (Scanner s=new Scanner(System.in)) {
	        	while (true){
	        		System.out.println("Enter the number of Queens :");
	        		n = s.nextInt();
	        		if ( n == 2 || n ==3) {
	        			System.out.println("No Solution possible for "+ n +" Queens. Please enter another number");
	        		}
	        		else
	        			break;
	        	}
	        }
	        long timestamp1 = System.currentTimeMillis();
	        
	        System.out.println("Solution to "+ n +" queens using hill climbing search:");
	        
	        // Create a ThreadGroup to keep track of all threads
	        ThreadGroup threadGroup = new ThreadGroup("HillClimbingGroup");

	        // Create and start threads
	        int numThreads = Runtime.getRuntime().availableProcessors(); // Number of available processors
	        for (int i = 0; i < numThreads; i++) {
	            Thread thread = new Thread(threadGroup, new HillClimbingSearch(n), "Thread-" + (i + 1));
            	thread.start();
	        }
	        long timestamp2 = System.currentTimeMillis();
			
			long timeDiff = timestamp2 - timestamp1;
			System.out.println("Execution Time: "+timeDiff+" ms");
			
	        
	       
	    }
}