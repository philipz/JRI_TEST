import org.rosuda.REngine.JRI.*;
import org.rosuda.REngine.*;

public class RCode1 {
    public static void main(String[] args) {
    	JRIEngine re;
    	 try {
             re = new JRIEngine(new String [] {"--vanilla"});
             System.out.println("Rengine created, waiting for R");   
             REXP rexp = re.parseAndEval("library(rJava)");
             System.out.println(rexp);
             rexp = re.parseAndEval(".jinit()");
             System.out.println(rexp);
             rexp = re.parseAndEval("s <- .jnew(\"java/lang/String\", \"Hello World!\")");
             System.out.println(rexp);
             rexp = re.parseAndEval(".jcall(s,\"I\",\"length\")");
             int len = rexp.asInteger();
             System.out.println("length: " + len);
             re.close();
         } catch (REngineException e) {
             e.printStackTrace();
         } catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}