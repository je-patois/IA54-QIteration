package fr.utbm.qiteration;

import java.util.Arrays;

public class Case {
	
	// Gauche Droite
	// Haut Bas
	private float[][] valeurCase;
	private boolean isObjective;
	private Case voisineHaut;
	private Case voisineBas;
	private Case voisineDroite;
	private Case voisineGauche;
	
	Case() {
		this.valeurCase = new float[2][2];
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < 2; ++j) {
				this.valeurCase[i][j] = 0;
			}
		}
		this.isObjective = false;
		this.voisineHaut = null;
		this.voisineBas = null;
		this.voisineDroite = null;
		this.voisineGauche = null;
	}
	
	Case(float[][] valeurCase) {
		this.valeurCase = valeurCase;
		this.isObjective = false;
	}
	
	Case(Case myCase) {
		this.isObjective = myCase.isObjective();
		this.voisineHaut = myCase.getVoisineHaut();
		this.voisineBas = myCase.getVoisineBas();
		this.voisineDroite = myCase.getVoisineDroite();
		this.voisineGauche = myCase.getVoisineGauche();
		this.valeurCase = new float[2][2];
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < 2; ++j) {
				this.valeurCase[i][j] = myCase.valeurCase[i][j];
			}
		}
	}

	public float[][] getValeurCase() {
		return valeurCase;
	}

	public void setValeurCase(float[][] valeurCase) {
		this.valeurCase = valeurCase;
	}

	public boolean isObjective() {
		return isObjective;
	}

	public void setObjective(boolean isObjective) {
		this.isObjective = isObjective;
	}

	public Case getVoisineHaut() {
		return voisineHaut;
	}

	public void setVoisineHaut(Case voisineHaut) {
		this.voisineHaut = voisineHaut;
	}

	public Case getVoisineBas() {
		return voisineBas;
	}

	public void setVoisineBas(Case voisineBas) {
		this.voisineBas = voisineBas;
	}

	public Case getVoisineDroite() {
		return voisineDroite;
	}

	public void setVoisineDroite(Case voisineDroite) {
		this.voisineDroite = voisineDroite;
	}

	public Case getVoisineGauche() {
		return voisineGauche;
	}

	public void setVoisineGauche(Case voisineGauche) {
		this.voisineGauche = voisineGauche;
	}

	@Override
	public String toString() {
		return "Case [valeurCase=" + Arrays.toString(valeurCase) + "]";
	}

	public boolean compareCases(Case c) {
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < 2; ++j) {
				if(this.valeurCase[i][j] != c.valeurCase[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void updateCase(int r, float g, Case tempCase) {
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < 2; ++j) {
				if(i == 0 && j == 0) {
					//System.out.println("gauche");
					if(tempCase.getVoisineGauche() != null) {
						this.getValeurCase()[i][j] = (tempCase.getVoisineGauche().isObjective ? r : 0) + g * tempCase.getVoisineGauche().getMaxValue();
					} else {
						this.getValeurCase()[i][j] = (tempCase.isObjective ? r : 0) + g * tempCase.getMaxValue();
					}
				} else if(i == 0 && j == 1) {
					//System.out.println("droite");
					if(tempCase.getVoisineDroite() != null) {
						this.getValeurCase()[i][j] = (tempCase.getVoisineDroite().isObjective ? r : 0) + g * tempCase.getVoisineDroite().getMaxValue();
					} else {
						this.getValeurCase()[i][j] = (tempCase.isObjective ? r : 0) + g * tempCase.getMaxValue();
					}
				} else if(i == 1 && j == 0) {
					//System.out.println("haut");
					if(tempCase.getVoisineHaut() != null) {
						this.getValeurCase()[i][j] = (tempCase.getVoisineHaut().isObjective ? r : 0) + g * tempCase.getVoisineHaut().getMaxValue();
					} else {
						this.getValeurCase()[i][j] = (tempCase.isObjective ? r : 0) + g * tempCase.getMaxValue();
					}
				} else if(i == 1 && j == 1) {
					//System.out.println("bas");
					if(tempCase.getVoisineBas() != null) {
						this.getValeurCase()[i][j] = (tempCase.getVoisineBas().isObjective ? r : 0) + g * tempCase.getVoisineBas().getMaxValue();
					} else {
						this.getValeurCase()[i][j] = (tempCase.isObjective ? r : 0) + g * tempCase.getMaxValue();
					}
				}
			}
		}
	}
	
	public float getMaxValue() {
		float maxValue = 0;
		for(int i = 0; i < 2; ++i) {
			for(int j = 0; j < 2; ++j) {
				if(this.valeurCase[i][j] > maxValue) {
					maxValue = this.valeurCase[i][j];
				}
			}
		}
		//System.out.println(maxValue);
		return maxValue;
	}
}
