package fr.exia.instanevehicles.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

import fr.exia.insanevehicles.share.CAD;
import fr.exia.insanevehicles.share.IElement;
import fr.exia.insanevehicles.share.IRoad;
import fr.exia.instanevehicles.model.element.motionless.MotionlessElementsFactory;

/**
 * <h1>The Road Class.</h1>
 *
 * @author Jade
 * @version 0.3
 */
class Road extends Observable implements IRoad {

    /** The width. */
    private int          width;

    /** The height. */
    private int          height;

    /** The on the road. */
    private IElement[][] onTheRoad;
    
    private CAD cad;

    /**
     * Instantiates a new road with the content of the file fileName.
     *
     * @param fileName
     *            the file name where the map of the road is
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    Road(final String fileName, CAD cad) throws IOException {
        super();
        this.cad = cad;
        this.loadFile(fileName);
    }

    /**
     * Loads file.
     *
     * @param fileName
     *            the file name
     * @return 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Deprecated
    private void readFile(char[][] road){
    	for(int y=0; y<this.getHeight();y++){
    		for(int x=0; x<this.getWidth();x++){
    			 this.setOnTheRoadXY(MotionlessElementsFactory.getFromFileSymbol(road[x][y]), x, y);
    		}
    	}
    }
    
    private void loadFile(final String fileName) throws IOException {
        final BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String line;
        int y = 0;
        line = buffer.readLine();
        this.setWidth(Integer.parseInt(line));
        line = buffer.readLine();
        this.setHeight(Integer.parseInt(line));
        this.onTheRoad = new IElement[this.getWidth()][this.getHeight()];
        line = buffer.readLine();
        char road[][] = new char[this.getWidth()][this.getHeight()];
        while (line != null) {
            for (int x = 0; x < line.toCharArray().length; x++) {
            	
            	road [x][y] = line.toCharArray()[x]; //mettre le char lu ici
            	
               
            }
            line = buffer.readLine();
            y++;
        }
        buffer.close();
        
        //modif le save pour save if not exists
        
        cad.savemap(fileName,road, getHeight(), getWidth());
        
        //modif le read pour qu'il prenne le nom
        readFile(cad.getmap(fileName));
    }

    /*
     * (non-Javadoc)
     * @see fr.exia.insanevehicles.model.IRoad#getWidth()
     */
    @Override
    public final int getWidth() {
        return this.width;
    }

    /**
     * Sets the width.
     *
     * @param width
     *            the new width
     */
    private void setWidth(final int width) {
        this.width = width;
    }

    /*
     * (non-Javadoc)
     * @see fr.exia.insanevehicles.model.IRoad#getHeight()
     */
    @Override
    public final int getHeight() {
        return this.height;
    }

    /**
     * Sets the height.
     *
     * @param height
     *            the new height
     */
    private void setHeight(final int height) {
        this.height = height;
    }

    /*
     * (non-Javadoc)
     * @see fr.exia.insanevehicles.model.IRoad#getOnTheRoadXY(int, int)
     */
    @Override
    public final IElement getOnTheRoadXY(final int x, final int y) {
        return this.onTheRoad[x][y];
    }

    /**
     * Sets the on the road XY.
     *
     * @param element
     *            the element
     * @param x
     *            the x
     * @param y
     *            the y
     */
    private void setOnTheRoadXY(final IElement element, final int x, final int y) {
        this.onTheRoad[x][y] = element;
    }

    /*
     * (non-Javadoc)
     * @see fr.exia.insanevehicles.model.IRoad#setMobileHasChanged()
     */
    @Override
    public final void setMobileHasChanged() {
        this.setChanged();
        this.notifyObservers();
    }

    /*
     * (non-Javadoc)
     * @see fr.exia.insanevehicles.model.IRoad#getObservable()
     */
    @Override
    public Observable getObservable() {
        return this;
    }
}
