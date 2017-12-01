package fr.utbm.qiteration;

public class QIteration {

	public static void main(String[] args) {
		Tableau myTableau = new Tableau(Integer.valueOf(args[0]));
		System.out.println(myTableau.toString());
		Tableau tempTableau = null;
		myTableau.initCases();
		
		int reward = 1;
		float gamma = 0.5f;
		int i = 1;
		
		do {
			tempTableau = new Tableau(myTableau);
			tempTableau.initCases();
			myTableau.updateTableau(reward, gamma, tempTableau);
			System.out.println("Tableau " + i);
			System.out.println(myTableau.toString());
			//System.out.println("tempTableau");
			//System.out.println(tempTableau.toString());
			++i;
		} while(!myTableau.compareTableaux(tempTableau));
	}

}
