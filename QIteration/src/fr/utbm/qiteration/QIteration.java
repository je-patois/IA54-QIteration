package fr.utbm.qiteration;

public class QIteration {

	public static void main(String[] args) {
		Tableau myTableau = null;
		if(args.length != 0) { // Pas de vérification si args[0] est bien un nombre...
			myTableau = new Tableau(Integer.valueOf(args[0]));
		} else {
			myTableau = new Tableau(3); // Tableau par défaut
		}
			
		System.out.println(myTableau.toString());
		Tableau tempTableau = null;
		myTableau.initCases();
		
		int reward = 1;
		float gamma = 0.5f;
		int iteration = 1;
		
		do {
			tempTableau = new Tableau(myTableau);
			tempTableau.initCases();
			myTableau.updateTableau(reward, gamma, tempTableau);
			System.out.println("Iteration #" + iteration);
			System.out.println(myTableau.toString());
			//System.out.println("tempTableau");
			//System.out.println(tempTableau.toString());
			++iteration;
		} while(!myTableau.compareTableaux(tempTableau));
	}

}
