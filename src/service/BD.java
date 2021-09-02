/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import client.Carte;

/**
 * 
 * @author DEH EUGENE
 */
public class BD {
	String user = "root";
	String passwd = "";
	String url = "jdbc:mysql://localhost:3306/banque";
	String strClassName = "com.mysql.jdbc.Driver";
	Connection conn = null;
	java.sql.Statement stmt = null;
	private Carte carte = null;
	private String requete;

	public BD() {
		try {
			Class.forName(strClassName);
		} catch (ClassNotFoundException ex) {
			Service.erreur("Veuiller vérifer la config de la base de données de vvotre application");
		}

		try {
			if (conn == null) {
				conn = DriverManager.getConnection(url, user, passwd);
			}

		} catch (SQLException ex) {
			Service.erreur("Le driver n'a pas pu être chargé");
		}

		try {
			stmt = conn.createStatement();
		} catch (SQLException ex) {
			Service.erreur("erreur : connection non établie");
		}
	}

	public ResultSet executeQuerry(String requete) {
		try {
			stmt.executeQuery(requete);
		} catch (SQLException ex) {
			Service.erreur(ex.getSQLState());
		}
		ResultSet rs = null;
		try {
			rs = stmt.getResultSet();
		} catch (SQLException ex) {
			Service.erreur("erreur : résutats non retourné");
		}
		return rs;
	}

	void close() {
		try {
			conn.close();
		} catch (SQLException ex) {
			Service.erreur("erreur 7");
		}
	}

	public Carte lireCarte(String numcompte) {
		requete = "SELECT * FROM CLIENTS WHERE numcompte='" + numcompte.trim()
				+ "'";
		// String requete = "SELECT * FROM CARTE";
		ResultSet rs = executeQuerry(requete);
		if (rs != null) {
			try {
				while (rs.next()) {
					carte = new Carte(rs.getString("numcompte").trim(), rs
							.getString("nom").trim(), rs.getString("prenom")
							.trim(), rs.getString("code").trim(),
							rs.getInt("solde"), rs.getInt("etat"));
				}
			} catch (SQLException ex) {
				Service.erreur("erreur : acces aux champ");
			}
		}
		close();
		return carte;
	}

	public void saveCarte() {
	}

	public void saveSolde(int solde, String numCompt) {
		requete = "UPDATE CLIENTS SET solde='" + solde + "' WHERE numcompte='"
				+ numCompt + "'";
		executeUpdate(requete);
	}

	private void executeUpdate(String requete2) {
		try {
			stmt.executeUpdate(requete2);
		} catch (SQLException e) {
			Service.erreur("Errreur de mise à jour");
		}
		close();
	}

	public void verrouCarte(String numCompt) {
		requete = "UPDATE CLIENTS SET etat='" + 0 + "' WHERE numcompte='"
				+ numCompt + "'";
		executeUpdate(requete);

	}
}
