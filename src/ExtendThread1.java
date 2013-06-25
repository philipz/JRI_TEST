import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.JRI.JRIEngine;

// Create a second thread by extending Thread
class NewThread1 extends Thread {
	String name;

	NewThread1(String input) {
		// Create a new, second thread
		super("Demo Thread");
		name = input;
		System.out.println("Child thread" + name + ": " + this);
		start(); // Start the thread
	}

	// This is the entry point for the second thread.
	public void run() {
		try {
			for (int i = 5; i > 0; i--) {
				//System.out.println("Child Thread" + name + " i: " + i);
				// Let the thread sleep for a while.
				String tmp = i + " + " + i;
				try {
					JRIEngine re = new JRIEngine(new String[] { "--vanilla" });
					REXP rexp = re.parseAndEval(tmp);
					int sum = rexp.asInteger();
					System.out.println("Child Thread" + name + " i+i: " + sum);
					re.close();
				} catch (REngineException e) {
					e.printStackTrace();
				} catch (REXPMismatchException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Thread.sleep(500);
			}
		} catch (InterruptedException e) {
			System.out.println("Child interrupted.");
		}
		System.out.println("Exiting child thread.");
	}
}

public class ExtendThread1 {
	public static void main(String args[]) {
		new NewThread1("1"); // create a new thread
		new NewThread1("2");
		try {
			for (int i = 5; i > 0; i--) {
				System.out.println("Main Thread: " + (i + i));
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted.");
		}
		System.out.println("Main thread exiting.");
	}
}