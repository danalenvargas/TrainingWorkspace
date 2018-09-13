import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class EmployeeTestRunner {

	public EmployeeTestRunner() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(EmployeeTest.class);
		
		for(Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		
		if(result.wasSuccessful()) {
			System.out.println("test successful");
		}
	}

}
