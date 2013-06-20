import java.util.ArrayList;
import java.util.List;

import org.rosuda.REngine.REXP;
import org.rosuda.JRI.Rengine;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REXPReference;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.JRI.JRIEngine;



public class RCode2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JRIEngine re; 
		try {
			re = new JRIEngine(new String [] {"--vanilla"});
			System.out.println("Rengine created, waiting for R");
			re.getRni().eval("{library(rJava);.jinit()}",false);
			List<Integer> o = new ArrayList<Integer>();
			o.add(1);
			o.add(2);
			o.add(3);
			REXPReference ref = re.createRJavaRef(o);
			re.assign("o", ref);
			REXP rexp;
			rexp = re.parseAndEval(".jmethods(o)");
			String[] list = rexp.asStrings();
			for (String s:list)
				System.out.println(s);
			re.close();
		} catch (REngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
