import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EmployeeTest {
	
	public EmployeeTest(){
		
	}
	
	@Test
	public void testPrintEmpName() {
		Employee emp = new Employee("Johnny");
		assertEquals("Johnny", emp.printEmpName(), "printEmpName() should return Johnny");
	}

}
