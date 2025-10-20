package com.automation.e2eTests.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnector {

	private static Connection connection;

	/**
	 * Établit une connexion à la base de données.
	 */
	public static void connect(String dbUrl, String username, String password) {
		try {
			if (connection == null || connection.isClosed()) {
				Class.forName("com.mysql.cj.jdbc.Driver");// TODO add maven dependancy
				connection = DriverManager.getConnection(dbUrl, username, password);
				System.out.println("Connexion à la base établie !");
			}
		} catch (Exception e) {
			throw new RuntimeException("Erreur de connexion à la base : " + e.getMessage(), e);
		}
	}

	/**
	 * Exécute une requête SELECT et renvoie le ResultSet.
	 */
	public static ResultSet executeQuery(String query) {
		try {
			if (connection == null || connection.isClosed()) {
				throw new IllegalStateException("La connexion n’est pas initialisée. Appelle connect() avant.");
			}
			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			return stmt.executeQuery(query);
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de l’exécution de la requête : " + query, e);
		}
	}

	/**
	 * Exécute une requête de mise à jour (INSERT, UPDATE, DELETE).
	 */
	public static int executeUpdate(String query) {
		try {
			if (connection == null || connection.isClosed()) {
				throw new IllegalStateException("La connexion n’est pas initialisée. Appelle connect() avant.");
			}
			Statement stmt = connection.createStatement();
			return stmt.executeUpdate(query);
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la mise à jour : " + query, e);
		}
	}

	/**
	 * Vérifie si une valeur donnée existe dans le résultat d'une requête SQL.
	 * Exemple d'usage : SQLConnector.verifyValueExists("SELECT email FROM users",
	 * "test@example.com");
	 */
	public static boolean verifyValueExists(String query, String expectedValue) {
		try (ResultSet rs = executeQuery(query)) {
			while (rs.next()) {
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					String value = rs.getString(i);
					if (value != null && value.trim().equalsIgnoreCase(expectedValue.trim())) {
						System.out.println("Valeur trouvée dans la base : " + value);
						return true;
					}
				}
			}
			System.out.println("Valeur non trouvée dans le résultat SQL : " + expectedValue);
			return false;
		} catch (SQLException e) {
			throw new RuntimeException("Erreur lors de la vérification SQL : " + query, e);
		}
	}

	/**
	 * Ferme proprement la connexion.
	 */
	public static void disconnect() {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				System.out.println("Connexion fermée avec succès.");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Impossible de fermer la connexion.", e);
		}
	}
}
