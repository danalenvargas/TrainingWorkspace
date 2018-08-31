import java.util.Scanner;

public class Calcutor {
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		String decision = "n";
		int ans = 0;
		int x;
		int fTime = 0;
		String operation;
		
		
		
		
		System.out.println("Welcome to SimCal!  A really hastily made calculator program lmao");
		System.out.println("Operators are: +, -, &, /, %");
		
		
		while (decision.equalsIgnoreCase("n"))
		{
			if(decision.equalsIgnoreCase("n") && fTime == 0)
			{
				System.out.print("Input First Num: ");
				ans = s.nextInt();
				
				System.out.print("Operator type: ");
				operation = s.next();
				
				System.out.print("Input Second: ");
				x = s.nextInt();
		
				//case 
				if (operation.equals("+"))
				{
					ans = ans + x;
				}
				
				else if (operation.equals("-"))
				{
					ans = ans - x;
				}
				
				else if (operation.equals("*"))
				{
					ans = ans * x;
				}
				
				else if (operation.equals("/"))
				{
					ans = ans / x;
				}
				
				else if (operation.equals("%"))
				{
					ans = ans % x;
				}
				
				
				System.out.println("Answer: " + ans);
				System.out.print("Is this your final desired result? (y/n): ");
				fTime=1;
				decision = s.next();
			}	
			
			else if(decision.equalsIgnoreCase("n") && fTime == 1)
			{
				System.out.print("Input Second: ");
				x = s.nextInt();

				System.out.print("Operator type: ");
				operation = s.next();
				
				//case 
				if (operation.equals("+"))
				{
					ans = ans + x;
				}
				
				else if (operation.equals("-"))
				{
					ans = ans - x;
				}
				
				else if (operation.equals("*"))
				{
					ans = ans * x;
				}
				
				else if (operation.equals("/"))
				{
					ans = ans / x;
				}
				
				else if (operation.equals("%"))
				{
					ans = ans % x;
				}
				
				System.out.println("Answer: " + ans);
				System.out.print("Is this your final desired result? (y/n): ");
				decision = s.next();
			}	
			
			
		}
		
		
		
		
		
	}
}
