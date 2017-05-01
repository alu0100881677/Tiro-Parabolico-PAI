/**
* Subject: 	Programación de Aplicaciones Interactivas
* Práctica: P12 Trayectoria de Proyectiles
* E-mail:   alu0100881677@ull.edu.es 
* Date:     28/4/2017
* Program:  Esta clase es la encargada de generar la trayectoria de un cohete.
* @author 	Guillermo Esquivel González
* @version 	1.0.0
*/
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JApplet;

public class ParabolaModelo extends JApplet {
	//atributos privados de la clase
	private double velocidadX = 0;
	private double velocidadInicial = 0;
	private double velocidadY = 0;
	private double tiempoFinal = 0;
	private double alturaInicial = 0;
	private final double ITER = 0.1;
	private final double G = 9.81;
	private double alturaMax = 0;

	//Getters y setters
	private double getVelx() {
		return velocidadX;
	}

	private double getVelocidadInicial() {
		return velocidadInicial;
	}

	private double getVelocidadY() {
		return velocidadY;
	}

	private double getTiempoFinal() {
		return tiempoFinal;
	}

	private double getAlturaIncial() {
		return alturaInicial;
	}

	private void setVelx(double velX) {
		this.velocidadX = velX;
	}

	private void setVelocidadInicial(double velocidadInicial) {
		this.velocidadInicial = velocidadInicial;
	}

	private void setVelocidadY(double velocidadY) {
		this.velocidadY = velocidadY;
	}

	private void settFinal(double tFinal) {
		this.tiempoFinal = tFinal;
	}

	private void setAlturaInicial(double alturaInicial) {
		this.alturaInicial = alturaInicial;
	}

	/**
	 * Constructor de la clase
	 */
	public ParabolaModelo() {
	}

	/**
	 * Método que calcula los datos de la trayectoria de un cohete 
	 * @param alturaInicial
	 * @param velocidadInicial
	 * @param anguloIncial
	 * @return
	 */
  	public ArrayList<Hashtable<String, Double>> calcularTrayectoria (double alturaInicial, double velocidadInicial, double anguloIncial) {
  		setAlturaInicial(alturaInicial);
  		setVelx(velocidadInicial * Math.cos(Math.toRadians(anguloIncial)));
  		setVelocidadInicial(getVelx() * getVelx());
  		setVelocidadY(velocidadInicial * Math.sin(Math.toRadians(anguloIncial)));
  		settFinal(calcularTiempoFinal());
	  	ArrayList<Hashtable<String, Double>> aux = new ArrayList<Hashtable<String, Double>>();
	  	
	  	for (double t = 0; t <= getTiempoFinal(); t += ITER) {
	  		Hashtable<String, Double> auxHash = new Hashtable<String, Double>();
	  		auxHash.put("t", t);
	  		auxHash.put("x", posXT(t));
	  		double yp = posYT(t);
	  		if (yp > alturaMax) {
	  			alturaMax = yp;
	  		}
	  		auxHash.put("y", yp);
	  		auxHash.put("vx", getVelx());
	  		double vy = VelocidadYT(t);
	  		auxHash.put("vy", vy);
		  	auxHash.put("v", velocidadT(vy));
		  	auxHash.put("ymax", alturaMax);
		  	aux.add(auxHash);
	  	}
	  	
	  	Hashtable<String, Double> auxHash = new Hashtable<String, Double>();
	  	auxHash.put("t", getTiempoFinal());
	  	auxHash.put("x", posXT(getTiempoFinal()));
	  	double yp = posYT(getTiempoFinal());
	  	if (yp > alturaMax) {
	  		alturaMax = yp;
	  	}
	  	auxHash.put("y", yp);
	  	auxHash.put("vx", getVelx());
	  	double vy = VelocidadYT(getTiempoFinal());
	  	auxHash.put("vy", vy);
	  	auxHash.put("v", velocidadT(vy));
	  	auxHash.put("ymax", alturaMax);
	  	//System.out.println(auxHash);
	  	aux.add(auxHash);
	  	
	  	return aux;
  	}

  	/**
  	 * Calcula la velocidad en y en función de t 	
  	 * @param t
  	 * @return
  	 */
  	private double VelocidadYT(double t) {
  		return getVelocidadY() - (G * t);
  	}

  	/**
  	 * Calcula la posición de x en función de t
  	 * @param t
  	 * @return
  	 */
  	private double posXT(double t) {
  		return getVelx() * t;
  	}

  	/**
  	 * calcula la posición de y en función de t
  	 * @param t
  	 * @return
  	 */
  	private double posYT(double t) {
  		return (-0.5 * G * t * t) + (getVelocidadY() * t) + getAlturaIncial();
  	}

  	/**
  	 * Calcula la velocidad en función de la velocidad en y y la velocidad inicial
  	 * @param vyt
  	 * @return
  	 */
  	private double velocidadT(double vyt) {
  		return Math.sqrt((vyt * vyt) + getVelocidadInicial());
  	}

  	/**
  	 * Calcula el tiempo que tardará el cohete en llegar al suelo
  	 * @return
  	 */
  	private double calcularTiempoFinal() {
  		return Math.max(
  					((-getVelocidadY() + Math.sqrt((getVelocidadY() * getVelocidadY()) + (2 * G * getAlturaIncial()))) / -G),
  					((-getVelocidadY() - Math.sqrt((getVelocidadY() * getVelocidadY()) + (2 * G * getAlturaIncial()))) / -G));
  }
}