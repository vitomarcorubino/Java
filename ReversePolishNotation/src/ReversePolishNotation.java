import java.util.Stack;

public class ReversePolishNotation {
	private String expression;
	private int n; // lunghezza di expression
	private Stack <Integer> operands; // Pila in cui sono inseriti gli operandi

	public ReversePolishNotation(String expression) {
		this.expression = expression;
		n = expression.length();

		operands = new Stack <Integer>();
	}	

	private boolean isOperator(char operator) {
		boolean isOperator;

		switch (operator) {
		case '+':
			isOperator = true;
			break;
		case '-':
			isOperator = true;
			break;
		case '*':
			isOperator = true;
			break;
		case '/':
			isOperator = true;
			break;
		default:
			isOperator = false;
			break;
		}

		return isOperator;
	}
	
	private Integer getOperation(int operand1, int operand2, char operator) {
		Integer operation;
		
		switch (operator) {
		case '+':
			operation = operand1+operand2;
			break;
		case '-':
			operation = operand1-operand2;
			break;
		case '*':
			operation = operand1*operand2;
			break;
		case '/':
			operation = operand1/operand2;
			break;
		default:
			operation = null;
			break;
		}
		
		return operation;
	}

	/*
	 * Questo metodo restituisce il risultato (intero) dell'espressione con notazione postfissa
	 * Per restituire un risultato corretto l'espressione deve essere scritta con uno spazio fra ciascun termine dell'espressione
	 * ( e.g.: 5 3 4 + * = 5*(3+4) )
	 */
	public int getResult() {
		int i, j, operand1, operand2;
		char c, operator;
		Integer result;

		result = null;
		j = 0;
		operator = ' ';
		for (i=0; i<n; i++) {
		
			c = expression.charAt(i);
	
			if (Character.isWhitespace(c)) {
				if (operator=='-') {
					/*
					 * Se il carattere è uno spazio ed il segno del termine è meno,
					 * sarà aggiunto alla pila degli operandi l'Integer della sottostringa compresa tra j+1 ed i concatenata al segno meno
					 * ( e.g.: |0| |1| |2| 
					 * 		   |-| |5| | | 
					 * 			   j+1  i
					 * 
					 * termine della sottostringa: 5
					 * segno: -
					 * intero: -5 (dato dalla concatenzione di segno+sottostringa)
					 * )
					 */
					operands.push(Integer.valueOf(operator+expression.substring(j+1, i)));
				} else {
					/*
					 * Solo se prima del termine non c'è un altro operatore, viene aggiunto il termine alla pila degli operandi
					 * (e.g.: ... * + 5 3 + ...
					 * 	5 verrà aggiunto alla pila degli operandi solo quando verrà analizzato lo spazio ad esso successivo.
					 * 	Lo spazio precedente a 5 non aggiungerà nulla alla pila degli operandi poichè prima c'è un operatore.
					 * )
					 */
					if (operator==' ') {
						operands.push(Integer.valueOf(expression.substring(j, i)));
					}
				}
				operator = ' ';
				j = i+1;
			} else {
				if (isOperator(c)) {
					
					operator = expression.charAt(i);
					
					if (operands.size()>=2) {
						// Il secondo operando sarà quello in cima alla pila, mentre il primo sarà quello successivo
						operand2 = operands.pop();
						operand1 = operands.pop();

						// Il risultato ottenuto dall'operazione tra operand1 e operand2 sarà inserito nella pila in modo da utilizzarlo come termine per le operazioni seguenti
						result = getOperation(operand1, operand2, operator);
						operands.push(result);
					}	
				}
				
			}
		}

		return result;
	}
}
