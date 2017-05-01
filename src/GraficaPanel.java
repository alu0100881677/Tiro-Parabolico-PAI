/**
* Subject: 	Programación de Aplicaciones Interactivas
* Práctica: P12 Trayectoria de Proyectiles
* E-mail:   alu0100881677@ull.edu.es 
* Date:     28/4/2017
* Program:  Esta clase extiene de JPanel y define un panel en el que se dibujarán las trayectorias de los cohetes.
* @author 	Guillermo Esquivel González
* @version 	1.0.0
*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JPanel;

public class GraficaPanel extends JPanel {
	//atributos privados de la clase
	  private int escala = 0;
	  private Hashtable<Integer, ArrayList<Point>> pintados = new Hashtable<Integer, ArrayList<Point>>();
	  private Color[] colores = { Color.RED, Color.BLUE, Color.YELLOW, Color.CYAN,Color.GREEN, Color.PINK, Color.ORANGE, Color.BLACK, Color.WHITE };
	  private boolean ejes = false;
	  private Point puntoActual = new Point(-5,-5);
	  private int disparoActual = 0;
	  private int vectorXA = 0;
	  private int vectorYA = 0;
	  private int vectorXB = 0;
	  private int vectorYB = 0;
	  private boolean vector = false;
	  private final double POS_EJE = 0.05;
	  private final double POS_EJE2 = 0.9;
	  private final double ESCALAX = 0.8;
	  private final double ES_MUESCAS_X = 0.01;
	  private final double ES_MUESCAS_Y = 0.005;
	  private final int CADVERX = 40; //cadenas verticales x
	  private final int CADVERY = 5;  // cadenas verticales y
	  private final int CADHORX = 10;  //cadenas horizontales x
	  private final int CADHORY = 25;  //cadenas horizontales y
	  private final int TAM_MUESCA = 10;
	  private final int DIAM_PUNTO = 6;
	  private final int POINT_OFF = DIAM_PUNTO / 2;
	  private final int COOR_MIN = 1;
	  private final int COOR_MED = 10;
	  private final int COOR_MAX = 100;
	  private final int ESCALA_MAX = 3;
	  private final int ESCALA_MED = 20;
	  
	  /**
	   * Constructor de la clase
	   */
	  public GraficaPanel() {
	  }
  
	  //Getters y setters
	  private int getEscala() {
	    return escala;
	  }
	
	  private Hashtable<Integer, ArrayList<Point>> getPintados() {
	    return pintados;
	  }
	
	  private Color[] getColores() {
	    return colores;
	  }
	
	  private boolean isEjes() {
	    return ejes;
	  }
	
	  private Point getPuntoActual() {
	    return puntoActual;
	  }
	
	  private int getDisparoActual() {
	    return disparoActual;
	  }
	
	  /*private int getVectorXA() {
	    return vectorXA;
	  }
	
	  private int getVectorYA() {
	    return vectorYA;
	  }
	
	  private int getVectorXB() {
	    return vectorXB;
	  }
	
	  private int getVectorYB() {
	    return vectorYB;
	  }
	
	  private boolean isVector() {
	    return vector;
	  }*/
	
	  private void setEscala(int escala) {
	    this.escala = escala;
	  }
	
	  private void setEjes(boolean ejes) {
	    this.ejes = ejes;
	  }
	
	  private void setPuntoActual(Point puntoActual) {
	    this.puntoActual = puntoActual;
	  }
	
	  private void setActualShoot(int disparoActual) {
	    this.disparoActual = disparoActual;
	  }
	
	  /*private void setVectorXA(int vectorXA) {
	    this.vectorXA = vectorXA;
	  }
	
	  private void setVectorYA(int vectorYA) {
	    this.vectorYA = vectorYA;
	  }
	
	  private void setVectorXB(int vectorXB) {
	    this.vectorXB = vectorXB;
	  }
	
	  private void setVectorYB(int vectorYB) {
	    this.vectorYB = vectorYB;
	  }
	
	  private void setVector(boolean vector) {
	    this.vector = vector;
	  }*/
	  /**
	   * Definición del paintComponent
	   */
	  public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    g.setColor(getColores()[getDisparoActual() % getColores().length]);
		    g.fillOval((getPuntoActual().x) - POINT_OFF, (getPuntoActual().y) - POINT_OFF, DIAM_PUNTO, DIAM_PUNTO);
		    
		    for (int tiro : getPintados().keySet()) {
			      g.setColor(getColores()[tiro % getColores().length]);
			      for (Point p : getPintados().get(tiro)) {
			        g.fillOval((p.x) - POINT_OFF, (p.y) - POINT_OFF, DIAM_PUNTO, DIAM_PUNTO);
			      }
		    }
		    
		    if (isEjes()) {
		    	g.setColor(Color.BLACK);
		    	dibujarEjes(g);
		    }
		    
		    /*if (isVector()) {
		    	g.setColor(Color.BLACK);
		    	g.drawLine(getVectorXA(), getVectorYA(), getVectorXB(), getVectorYB());
		    	g.drawLine(getVectorXB(), getVectorYB(), getVectorXB(), (getVectorYB() + AH_OFF));
		    	g.drawLine(getVectorXB(), getVectorYB(), (getVectorXB() - AH_OFF), getVectorYB());
		    }*/
	
	  }

	  /**
	   * Dibuja un punto en el panel
	   * @param x
	   * @param y
	   * @param recorrido
	   * @param numDisparo
	   */
	  public void dibujarPunto(double x, double y, boolean recorrido, int numDisparo) {
		  	int i = (int) ((x * getEscala()) + (getWidth() * POS_EJE));
		  	int j = (int) ((getHeight() * POS_EJE2) - (y * getEscala()));
		    setPuntoActual(new Point(i, j));
		    setActualShoot(numDisparo);
		    if (recorrido) {
		    	if (getPintados().containsKey(numDisparo)) {
		    		getPintados().get(numDisparo).add(new Point(i, j));
		    	} 
		    	else {
		    		getPintados().put(numDisparo, new ArrayList<Point>());
		    		getPintados().get(numDisparo).add(new Point(i, j));
		    	}
		    }
		    repaint();
	  }

	  /**
	   * Dibuja los ejes de la gráfica
	   * @param g
	   */
	  private void dibujarEjes(Graphics g) {
		    g.drawLine((int) (getWidth() * POS_EJE), (int) (getHeight() * POS_EJE2), (int) (getWidth() * POS_EJE2), (int) (getHeight() * POS_EJE2));
		    g.drawLine((int) (getWidth() * POS_EJE), (int) (getHeight() * POS_EJE), (int) (getWidth() * POS_EJE), (int) (getHeight() * POS_EJE2));
		    int xpos = (int) (getWidth() * POS_EJE);
		    int yposA = (int) (getHeight() * POS_EJE2);
		    int yposB = (int) (getHeight() * (POS_EJE2 + ES_MUESCAS_X));
		    int muescas = 0;
		    if (getEscala() <= ESCALA_MAX) {
		    	muescas = COOR_MAX;
		    } 
		    else if (getEscala() <= ESCALA_MED) {
		    	muescas = COOR_MED;
		    } 
		    else {
		    	muescas = COOR_MIN;
		    }
		    int muescasContador = 0;
		    //pintar las muescas del eje x
		    while (xpos < getWidth() * POS_EJE2) {
		    	g.drawLine(xpos, yposA, xpos, yposB);
		    	if (muescasContador % muescas == 0) {
		    		g.drawLine(xpos, yposA, xpos, yposB + TAM_MUESCA);
		    		g.drawString("" + muescasContador, xpos - CADHORX, yposB + CADHORY);  
		    	}
		    	xpos += getEscala();
		    	muescasContador++;
		    }
		    int ypos = (int) (getHeight() * POS_EJE2);
		    int xposA = (int) (getWidth() * POS_EJE);
		    int xposB = (int) (getWidth() * (POS_EJE - ES_MUESCAS_Y));
		    muescasContador = 0;
		    //pintar las muescas del eje y
		    while (ypos > getHeight() * POS_EJE) {
		    	g.drawLine(xposA, ypos, xposB, ypos);
		
		    	if (muescasContador % muescas == 0) {
		    		g.drawLine(xposA, ypos, xposB - TAM_MUESCA, ypos);
		    		g.drawString("" + muescasContador, xposB - CADVERX, ypos + CADVERY);
		        
		    	}
		    	ypos -= getEscala();
		    	muescasContador++;
		    }
	  }

	  /**
	   * Configura los ejes
	   * @param maxX
	   * @param maxY
	   */
	  public void setEjes(double maxX, double maxY) {
		  if (!isEjes()) {
			  int maxXpx = (int) (getWidth() * ESCALAX);
			  int maxYpx = (int) (getHeight() * ESCALAX);
			  setEscala((int) Math.min((maxXpx / maxX), (maxYpx / maxY)));
		  }
		  setEjes(true);
		  repaint();
	  }

	  /**
	   * Vuelve a los valores iniciales
	   */
	  public void resetear() {
		  getPintados().clear();
		  setEjes(false);
		  setPuntoActual(new Point(-5,-5));
		  //setVector(false);
		  repaint();
	  }

	  /*//Method to set movement initial vector
	  public void setVector(double vx, double vy) {
		  setVectorXA((int) (getWidth() * POS_EJE));
		  setVectorYA((int) (getHeight() * POS_EJE2));
	      setVectorXB(vectorXA + (int) vx);
	      setVectorYB(vectorYA - (int) vy);
	      setVector(true);
	  }*/
}
