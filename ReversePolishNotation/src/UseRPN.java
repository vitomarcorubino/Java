import java.util.Scanner;

public class UseRPN {

	public static void main(String[] args) {
		Scanner keyb = new Scanner(System.in);
		ReversePolishNotation rpn;
		String espressione;

		System.out.println("NOTAZIONE POLACCA POSTFISSA (Reverse Polish Notation)");
		System.out.print("\nEspressione: ");
		espressione = keyb.nextLine();

		rpn = new ReversePolishNotation(espressione);

		System.out.print("Risultato: "+rpn.getResult());

		keyb.close();
	}
}
