package fr.exia.insanevehicles.share;

public interface CAD extends Runnable{

	//r�cup chaque �l�ment
	/*et c'est a nous de faire la bdd?
	 * parceque moi je fait une table avec en cl�s primaires le num�ro de circuit, la colone et la ligne o� je stock l �l�ment
	 * et une avec le nom et la size du circuit (et �ventuellement l'indice de la ligne a laquelle il commence dans l'autre table)
	 */
	public char[][] getmap(String fileName);
		
	
	
	//public void savemap(char[][] road,int height, int width);



	public void savemap(String fileName, char[][] road, int height, int width);
	
}
