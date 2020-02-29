package design_pattern.kosta.behavior.Interpreter;

public class Client {

	public static void main(String[] args) {

		Evaluator eval = new Evaluator();
		System.out.println("eval = " + eval.evaluate("3 4 +"));
		System.out.println("eval = " + eval.evaluate("3 4 -"));
		System.out.println("eval = " + eval.evaluate("4 5 +"));
		System.out.println("eval = " + eval.evaluate("10 5 -"));
		System.out.println("eval = " + eval.evaluate("5 10 -"));
	}


}
