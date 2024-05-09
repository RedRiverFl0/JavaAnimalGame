package animalProgram;



public class animalDriver {

	public static void main(String[] args) {
		animalGuess gameTree = new animalGuess();
		Boolean state = true;
		while (state) {
			
			if (!gameTree.start()) {
				state = false;
			}
		}
		
	}

}
