package animalProgram;

import java.util.Scanner;
import java.io.*;



public class animalGuess {
	static String filename = "answers.txt";
	static class Node{
		public String data;
		public Node left;
		public Node right;
	
	
	public Node(String data, Node left, Node right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	}
	
	protected Node root;
	protected int numElt;
	
	public animalGuess(){
		this.root = new Node("owl", null, null);
		this.numElt = 0;
		
	}
	
	public Boolean start() {
		this.root = loadData();
		
		//System.out.println(this.root.data.length());
		
		Node current = this.root;
		Boolean gameState = true;
		
	
		
		while (gameState) {
			System.out.println("Think of an animal: \n");
			
			if(current.data.contains("?")) {
				Node leaf = goThrough();
				
				if (question(leaf.data)) {
					System.out.println("Yay I got it right! \n");
				}else {
					add(leaf);
				}
				
				
				if (question("Do you want to play again?\n") == false) {
					saveData(this.root);
					return false;
				}
				else{saveData(this.root); return true;}
				
				
			}else {
				
			
			if (question(current.data)) {
				System.out.println("Yay I got it right! \n");
			}else {
				add(current);
			}
			
			
			if (question("Do you want to play again? \n") == false) {
				saveData(this.root);
				return false;
			}else{saveData(this.root); return true;}
		}
		
		}
		
		return true;
		
		
	}
	
	public Node goThrough() {

		
		Node current = this.root;
		
		while (current.data.contains("?")) {
			System.out.println(current.data);
			
			if(question(current.data)) {
				current = current.left;
			}else {
				current = current.right;
			}
			
		}
		return current;
		
	}
	
	public Boolean question(String prompt) {
		
		if (!prompt.contains("?")) {
			System.out.println("Is your animal a "+ prompt);
		}
		else if(prompt.contains("want to play")) {System.out.println(prompt);}
	
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Type (yes/no). \n" );
		
		String decide = scan.nextLine().toLowerCase();
		
		if (decide.equals("yes")) {
			
			return true;
		}
		else {
			
			return false;
		}
		
	}
	
	
	
	public void add(Node current) {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Aw shucks, what is your animal? \n");
		
		String newAnimal = in.nextLine();
		
		System.out.println("ok, now make a new question that would be yes for "+newAnimal+ " and no for "+current.data+" (add ? at end of your question.)");
		
		String question = in.nextLine();
		current.right = new Node(current.data, null, null);
		current.left = new Node(newAnimal, null, null);
		
		current.data = question;
		
	
	}
	
	static Node loadData() {
		
		try {
			Scanner animalsTXT = new Scanner(new File("answers.txt"));
			Node data = recursion(animalsTXT);
			if (data == null || data.data == null) {
				Node animal = new Node("owl", null, null);
			
				return animal;
			}else {
				return data;
			}
				
			
		}catch(FileNotFoundException except) {
			
			return new Node("owl", null, null);
		}
	}
	
	static Node recursion(Scanner animalsTXT) {
		if(animalsTXT.hasNextLine() == false) {
			return null;
		}
		String found = animalsTXT.nextLine();
		Node current = new Node(found, null, null);
		if(found.contains("?")) {
			current.left = recursion(animalsTXT);
			current.right = recursion(animalsTXT);
		}
		return current;
		}
	
	
	static void saveData(Node current) {
		try {
			PrintWriter write = new PrintWriter(new FileWriter("answers.txt",false));
			recursionSave(current, write);
			write.close();
			
		}
		catch(IOException except) {
			
		}
	}
	
	static void recursionSave(Node current, PrintWriter write) {
		if (current == null) {
			return;
		}
		write.println(current.data);
		
		recursionSave(current.left, write);
		recursionSave(current.right, write);
		
	}
	
	
	

	}

