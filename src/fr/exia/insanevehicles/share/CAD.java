package fr.exia.insanevehicles.share;

public interface CAD extends Runnable{

	//récup chaque élément
	/*et c'est a nous de faire la bdd?
	 * parceque moi je fait une table avec en clés primaires le numéro de circuit, la colone et la ligne où je stock l élément
	 * et une avec le nom et la size du circuit (et éventuellement l'indice de la ligne a laquelle il commence dans l'autre table)
	 */
	public char[][] getmap(String fileName);
		
	
	
	//public void savemap(char[][] road,int height, int width);



	public void savemap(String fileName, char[][] road, int height, int width);
	
}
