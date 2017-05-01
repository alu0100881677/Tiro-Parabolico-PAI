/**
* Subject: 	Programación de Aplicaciones Interactivas
* Práctica: P12 Trayectoria de Proyectiles
* E-mail:   alu0100881677@ull.edu.es 
* Date:     28/4/2017
* Program:  En esta clase se define un panel que contiene una estructura de componentes en los que presentar la información de la trayectoria de un cohete.
* @author 	Guillermo Esquivel González
* @version 	1.0.0
*/

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DataPanel extends JPanel{
	//atributos privados de la clase
	private JLabel tiempo;
	private JLabel posX;
	private JLabel posY;
	private JLabel velX;
	private JLabel velY;
	private JLabel vel;
	private JLabel alturaMax;
	private final Dimension dimension = new Dimension(140, 400);
	
	
	//Getters y setters
	public JLabel getTiempo() {
		return tiempo;
	}



	public void setTiempo(JLabel tiempo) {
		this.tiempo = tiempo;
	}



	public JLabel getPosX() {
		return posX;
	}



	public void setPosX(JLabel posX) {
		this.posX = posX;
	}



	public JLabel getPosY() {
		return posY;
	}



	public void setPosY(JLabel posY) {
		this.posY = posY;
	}



	public JLabel getVelX() {
		return velX;
	}



	public void setVelX(JLabel velX) {
		this.velX = velX;
	}



	public JLabel getVelY() {
		return velY;
	}



	public void setVelY(JLabel velY) {
		this.velY = velY;
	}



	public JLabel getVel() {
		return vel;
	}



	public void setVel(JLabel vel) {
		this.vel = vel;
	}



	public JLabel getAlturaMax() {
		return alturaMax;
	}



	public void setAlturaMax(JLabel alturaMax) {
		this.alturaMax = alturaMax;
	}


	/**
	 * Constructor de la clase
	 */
	public DataPanel(){
		setLayout(new GridLayout(10, 0, 1, 1));
		tiempo  = new JLabel("Tiempo : 0 s.");
		posX = new JLabel("PosX : 0 m.");
		posY = new JLabel("PosY : 0 m.");
		velX = new JLabel("VelX : 0 m/s.");
		velY = new JLabel("VelY : 0 m/s.");
		vel = new JLabel("Vel : 0 m/s.");
		alturaMax = new JLabel("Altura Max : 0 m.");
		add(tiempo);
		add(posX);
		add(posY);
		add(velX);
		add(velY);
		add(vel);
		add(alturaMax);
		setVisible(true);
	}
	/**
	 * Cambia los datos que muestra el panel, le pasamos un hastable con los nuevos datos.
	 * @param datos
	 */
	public void setDatos(Hashtable<String, Double> datos){
		getTiempo().setText("t: " + String.format("%1$,.1f", datos.get("t")) + "s.");
	    getPosX().setText("Pos X: " + String.format("%1$,.1f", datos.get("x")) + "m.");
	    getPosY().setText("Pos Y: " + String.format("%1$,.1f", datos.get("y")) + "m.");
	    getVelX().setText("Vx: " + String.format("%1$,.1f", datos.get("vx")) + "m/s.");
	    getVelY().setText("Vy: " + String.format("%1$,.1f", datos.get("vy")) + "m/s.");
	    getVel().setText("V: " + String.format("%1$,.1f", datos.get("v")) + "m/s.");
	    getAlturaMax().setText("Altura max: " + String.format("%1$,.1f", datos.get("ymax")) + "m.");
	}
}
