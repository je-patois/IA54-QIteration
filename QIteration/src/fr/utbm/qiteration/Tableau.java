package fr.utbm.qiteration;

import java.util.Arrays;

public class Tableau {
	
	private int taille;
	private Case[][] cases;
	
	Tableau () {
		this.taille = 0;
		this.cases = null;
	}
	
	Tableau(int taille) {
		this.taille = taille;
		this.cases = new Case[taille][taille];
		for(int i = 0; i < this.getTaille(); ++i) {
			for(int j = 0; j < this.getTaille(); ++j) {
				this.cases[i][j] = new Case();
			}
		}
		this.cases[taille - 1][taille - 1].setObjective(true);
	}
	
	Tableau(Tableau tab) {
		this.taille = tab.taille;
		this.cases = new Case[taille][taille];
		for(int i = 0; i < this.getTaille(); ++i) {
			for(int j = 0; j < this.getTaille(); ++j) {
				this.cases[i][j] = new Case(tab.getCases()[i][j]);
			}
		}
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public Case[][] getCases() {
		return cases;
	}

	public void setCases(Case[][] cases) {
		this.cases = cases;
	}

	@Override
	public String toString() {
		String result = "";
		for(int i = 0; i < this.getTaille(); ++i) {
			result += "\n";
			for(int z = 0; z < 2; ++z) {
				for(int j = 0; j < this.getTaille(); ++j) {
					result += "    ";
					if(z == 0) {
						result += String.format("%.3f", this.getCases()[i][j].getValeurCase()[0][0]) + " " + String.format("%.3f", this.getCases()[i][j].getValeurCase()[0][1]);
					} else {
						result += String.format("%.3f", this.getCases()[i][j].getValeurCase()[1][0]) + " " + String.format("%.3f", this.getCases()[i][j].getValeurCase()[1][1]);
					}
				}
				result += "\n";
			}
		}
		return result;
	}

	public boolean compareTableaux(Tableau tab) {
		if(tab.getTaille() != this.getTaille()) {
			return false;
		}
		for(int i = 0; i < this.getTaille(); ++i) {
			for(int j = 0; j < this.getTaille(); ++j) {
				if(!this.getCases()[i][j].compareCases(tab.getCases()[i][j])) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void updateTableau(int r, float g, Tableau tempTableau) {
		for(int i = 0; i < this.getTaille(); ++i) {
			for(int j = 0; j < this.getTaille(); ++j) {
				//System.out.println("(" + i + ", " + j + ")");
				this.getCases()[i][j].updateCase(r, g, tempTableau.getCases()[i][j]);
			}
		}
	}
	
	public void initCases() {
		for(int i = 0; i < this.getTaille(); ++i) {
			for(int j = 0; j < this.getTaille(); ++j) {
				if(i == 0 && j == 0) {
					this.getCases()[i][j].setVoisineHaut(null);
					this.getCases()[i][j].setVoisineBas(this.getCases()[i+1][j]);
					this.getCases()[i][j].setVoisineGauche(null);
					this.getCases()[i][j].setVoisineDroite(this.getCases()[i][j+1]);
				} else if(i == 0 && j == this.getTaille() - 1) {
					this.getCases()[i][j].setVoisineHaut(null);
					this.getCases()[i][j].setVoisineBas(this.getCases()[i+1][j]);
					this.getCases()[i][j].setVoisineGauche(this.getCases()[i][j-1]);
					this.getCases()[i][j].setVoisineDroite(null);
				} else if(i == 0) {
					this.getCases()[i][j].setVoisineHaut(null);
					this.getCases()[i][j].setVoisineBas(this.getCases()[i+1][j]);
					this.getCases()[i][j].setVoisineGauche(this.getCases()[i][j-1]);
					this.getCases()[i][j].setVoisineDroite(this.getCases()[i][j+1]);
				} else if(i == this.getTaille() - 1 && j == 0) {
					this.getCases()[i][j].setVoisineHaut(this.getCases()[i-1][j]);
					this.getCases()[i][j].setVoisineBas(null);
					this.getCases()[i][j].setVoisineGauche(null);
					this.getCases()[i][j].setVoisineDroite(this.getCases()[i][j+1]);
				} else if(i == this.getTaille() - 1 && j == this.getTaille() - 1) {
					this.getCases()[i][j].setVoisineHaut(this.getCases()[i-1][j]);
					this.getCases()[i][j].setVoisineBas(null);
					this.getCases()[i][j].setVoisineGauche(this.getCases()[i][j-1]);
					this.getCases()[i][j].setVoisineDroite(null);
				} else if(i == this.getTaille() - 1) {
					this.getCases()[i][j].setVoisineHaut(this.getCases()[i-1][j]);
					this.getCases()[i][j].setVoisineBas(null);
					this.getCases()[i][j].setVoisineGauche(this.getCases()[i][j-1]);
					this.getCases()[i][j].setVoisineDroite(this.getCases()[i][j+1]);
				} else if(j == 0) {
					this.getCases()[i][j].setVoisineHaut(this.getCases()[i-1][j]);
					this.getCases()[i][j].setVoisineBas(this.getCases()[i+1][j]);
					this.getCases()[i][j].setVoisineGauche(null);
					this.getCases()[i][j].setVoisineDroite(this.getCases()[i][j+1]);
				} else if(j == this.getTaille() - 1) {
					this.getCases()[i][j].setVoisineHaut(this.getCases()[i-1][j]);
					this.getCases()[i][j].setVoisineBas(this.getCases()[i+1][j]);
					this.getCases()[i][j].setVoisineGauche(this.getCases()[i][j-1]);
					this.getCases()[i][j].setVoisineDroite(null);
				}
				else {
					this.getCases()[i][j].setVoisineHaut(this.getCases()[i-1][j]);
					this.getCases()[i][j].setVoisineBas(this.getCases()[i+1][j]);
					this.getCases()[i][j].setVoisineGauche(this.getCases()[i][j-1]);
					this.getCases()[i][j].setVoisineDroite(this.getCases()[i][j+1]);
				}
			}
		}
	}
}
