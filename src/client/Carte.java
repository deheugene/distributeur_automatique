/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 
 * @author DEH EUGENE
 */
package client;

import service.BD;

public class Carte {
	/* attribut */
	private String nom;
	private String prenom;
	private String numCompt;
	private String code = null;
	private int solde = 0;
	private int etat = 0;
	private BD bd;

	/* Constructeurs */
	public Carte(String numCompt, String nom, String prenom, String code,
			int solde, int etat) {
		this.nom = nom;
		this.prenom = prenom;
		this.numCompt = numCompt;
		this.code = code;
		this.solde = solde;
		this.etat = etat;
	}

	public Carte() {
		this.nom = null;
		this.prenom = null;
		this.numCompt = null;
		this.code = null;
		this.solde = 0;
		this.etat = 0;
	}

	/* Méthodes */
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNumCompt() {
		return numCompt;
	}

	public void setNumCompt(String numCompt) {
		this.numCompt = numCompt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public boolean authentifierCarte(Carte cr) {
		if (this.numCompt.toLowerCase().trim()
				.equals(cr.numCompt.toLowerCase())) {
			return true;
		} else {
			return false;
		}

	}

	public void read(String numCompte) {
		Carte cr = new Carte();
		bd = new BD();
		cr = bd.lireCarte(numCompte);
		if (cr != null) {
			this.nom = cr.getNom();
			this.prenom = cr.getPrenom();
			this.numCompt = cr.getNumCompt();
			this.code = cr.getCode();
			this.solde = cr.getSolde();
			this.etat = cr.getEtat();
		}
	}

	public String toString() {
		return "Carte [nom=" + nom + ", prenom=" + prenom + ", numCompt="
				+ numCompt + ", code=" + code + ", solde=" + solde + ", etat="
				+ etat + ", bd=" + bd + "]";
	}

	public void saveSolde() {
		bd = new BD();
		bd.saveSolde(solde, numCompt);

	}

	public void bloquerCarte() {
		bd = new BD();
		bd.verrouCarte(numCompt);
	}

}
