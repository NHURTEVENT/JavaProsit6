package fr.exia.insanevehicles.cad;

import fr.exia.insanevehicles.share.CAD;
import java.sql.*;

public class JDBC implements CAD {
	static int width;
	static int height;
	private int numerocircuit;

	/*
	 * public static void main(String[] args) { //sauverEnBase("River SONG");
	 * char road[][] = lireBDD(1);// fonction pas duuuuu tout suceptible aux
	 * injections sql afficherRoute(road);
	 * 
	 * }
	 */

	public void savemap(char[][] road, int height, int width) {
		String url = "jdbc:mysql://localhost/javaprosit6";
		String login = "root";
		String password = "0000";
		Connection cn = null;
		Statement st = null;

		try {

			// Etape l : Charqement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : récupération de la connexion
			cn = DriverManager.getConnection(url, login, password);

			// Etape 3 : Création d'un statement
			st = cn.createStatement();

			/*
			 * statements a faire:
			 * 
			 * set le nb de ligne set le nb de colone
			 * 
			 * aller en mode double for set l element
			 * 
			 */

			String setNbLigneEtColone = "INSERT INTO `infocircuit` (longueur, largeur) VALUES (" + height + ", " + width
					+ ")";
			st.executeUpdate(setNbLigneEtColone);

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					String setElement = "INSERT INTO `circuit` (numerocircuit,element,ligne,colone) VALUES (2,'"
							+ road[j][i] + "', " + (i + 1) + ", " + (j + 1) + ")";
					Statement st2 = cn.createStatement();
					st2.executeUpdate(setElement);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} finally {
			try {

				// Etape 5 : libérer ressources de la mémoire
				cn.close();
				st.close();
			}

			catch (SQLException e) {
				e.printStackTrace();
				return;
			}

		}
	}

	public char[][] getmap(int numeroCircuit) {
		String url = "jdbc:mysql://localhost/javaprosit6";
		String login = "root";
		String password = "0000";
		Connection cn = null;
		Statement st = null;
		ResultSet rsH = null;
		ResultSet rsW = null;

		try {

			// Etape l : Charqement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : récupération de la connexion
			cn = DriverManager.getConnection(url, login, password);

			// Etape 3 : Création des statement pour la taille
			st = cn.createStatement();
			String heightCircuit = "SELECT `longueur` FROM `infocircuit` WHERE `numerocircuit` = " + numeroCircuit;
			String widthCircuit = "SELECT `largeur` FROM `infocircuit` WHERE `numerocircuit` = " + numeroCircuit;

			// Etape 4 : éxé de les requettes

			// on récup la longueur de la route
			rsH = st.executeQuery(heightCircuit);
			while (rsH.next()) {
				height = Integer.parseInt(rsH.getString("longueur"));
				System.out.println("height: " + rsH.getString("longueur"));
			}

			// on récup la largeur de la route
			rsW = st.executeQuery(widthCircuit);
			while (rsW.next()) {
				width = Integer.parseInt(rsW.getString("largeur"));
				System.out.println("width: " + rsW.getInt("largeur"));
			}

			// on set la size de la route
			char[][] road = new char[width][height];

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					// on créé la string de statement
					String getChar = "SELECT * FROM `circuit` WHERE ligne =" + (i + 1) + " AND colone =" + (j + 1);
					Statement stRoad = cn.createStatement();

					// on lexé
					ResultSet rsRoad = stRoad.executeQuery(getChar);

					// on l add au tableau
					while (rsRoad.next()) {
						// System.out.println(rsRoad.getString("element"));
						road[j][i] = rsRoad.getString("element").charAt(0);
					}
					// je devrais peut être close le statement

				}
			}
			// c'est pas grave d'avoir le return ici il va quand même close la
			// connexion dans finally

		//	afficherRoute(road);
			return road;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;

	}

	public static void lireBDD() {
		String url = "jdbc:mysql://localhost/javaprosit6";
		String login = "root";
		String password = "0000";
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			// Etape l : Charqement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : récupération de la connexion
			cn = DriverManager.getConnection(url, login, password);

			// Etape 3 : Création d'un statement
			st = cn.createStatement();
			String sql = "SELECT * FROM `test`";

			// Etape 4 : éxé de la requette

			rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getString("machin"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void afficherRoute(char[][] route) {
		for (int i = 0; i < 31; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(route[j][i]);
			}
			System.out.println();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public char[][] getmap(String fileName) {
		String url = "jdbc:mysql://localhost/javaprosit6";
		String login = "root";
		String password = "0000";
		Connection cn = null;
		Statement st = null;
		ResultSet rsH = null;
		ResultSet rsW = null;
		ResultSet rsNum = null;

		try {

			// Etape l : Charqement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : récupération de la connexion
			cn = DriverManager.getConnection(url, login, password);

			// Etape 3 : Création des statement pour la taille
			st = cn.createStatement();
			String heightCircuit = "SELECT longueur FROM `infocircuit` INNER JOIN infonomcircuit ON infocircuit.numerocircuit = infonomcircuit.numerocircuit WHERE fileName =\""
					+ fileName + "\"";
			String widthCircuit = "SELECT largeur FROM `infocircuit` INNER JOIN infonomcircuit ON infocircuit.numerocircuit = infonomcircuit.numerocircuit WHERE fileName =\""
					+ fileName + "\"";

			/*String getNumero ="SELECT numerocircuit WHERE file = \""+fileName+"\"";
			
			rsNum = st.executeQuery(getNumero);
				while(rsNum.next()){
					this.numerocircuit = rsNum.getInt(numerocircuit);
				}*/
			
			
			
			rsH = st.executeQuery(heightCircuit);
			while (rsH.next()) {
				height = Integer.parseInt(rsH.getString("longueur"));
				System.out.println("height: " + rsH.getString("longueur"));
			}

			// on récup la largeur de la route
			rsW = st.executeQuery(widthCircuit);
			while (rsW.next()) {
				width = Integer.parseInt(rsW.getString("largeur"));
				System.out.println("width: " + rsW.getInt("largeur"));
			}

			// on set la size de la route
			char[][] road = new char[width][height];

			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					// on créé la string de statement
				//	System.out.println("numéro : "+numerocircuit);
					String getChar = "SELECT * FROM `circuit` WHERE numerocircuit = 2 AND ligne = " + (i + 1) + " AND colone = " + (j + 1) ;
					Statement stRoad = cn.createStatement();

					// on lexé
					ResultSet rsRoad = stRoad.executeQuery(getChar);

					// on l add au tableau
					while (rsRoad.next()) {
						// System.out.println(rsRoad.getString("element"));
						road[j][i] = rsRoad.getString("element").charAt(0);
					}
					// je devrais peut être close le statement

				}
			}
			// c'est pas grave d'avoir le return ici il va quand même close la
			// connexion dans finally

			afficherRoute(road);
			return road;

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;

	}

	@Override
	public void savemap(String fileName, char[][] road, int height, int width) {

		String url = "jdbc:mysql://localhost/javaprosit6";
		String login = "root";
		String password = "0000";
		Connection cn = null;
		Statement st = null;
		ResultSet exists = null;

		try {

			// Etape l : Charqement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : récupération de la connexion
			cn = DriverManager.getConnection(url, login, password);

			// Etape 3 : Création d'un statement
			st = cn.createStatement();

			/*
			 * statements a faire:
			 * 
			 * set le nb de ligne set le nb de colone
			 * 
			 * aller en mode double for set l element
			 * 
			 */

			String getIfexists = "SELECT * FROM `infonomcircuit` WHERE fileName = \"" + fileName + "\"";
			exists = st.executeQuery(getIfexists);
			if (exists.next()) {
				System.out.println("exists");				
			}else{
			
							
				String setNbLigneEtColone = "INSERT INTO `infocircuit` (longueur, largeur) VALUES (" + height + ", "
						+ width + ")";
				st.executeUpdate(setNbLigneEtColone);

				for (int i = 0; i < height; i++) {
					for (int j = 0; j < width; j++) {
						/** TODO réussir mettre l'auto-incrément à numerocircuit*/
						String setElement = "INSERT INTO `circuit` (numerocircuit,element,ligne,colone) VALUES (3,'"
								+ road[j][i] + "', " + (i + 1) + ", " + (j + 1) + ")";
						Statement st3 = cn.createStatement();
						st3.executeUpdate(setElement);
					}
				}
				
				//stocker le nom du file avec le bon id
			}	
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} finally {
			try {

				// Etape 5 : libérer ressources de la mémoire
				cn.close();
				st.close();
			}

			catch (SQLException e) {
				e.printStackTrace();
				return;
			}

		}
	}
}
