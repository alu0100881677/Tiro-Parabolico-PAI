/**
* Subject: 	Programación de Aplicaciones Interactivas
* Práctica: P12 Trayectoria de Proyectiles
* E-mail:   alu0100881677@ull.edu.es 
* Date:     28/4/2017
* Program:  Esta clase contiene la implementacion necesaria para poder ejecutar nuestro programa como applet o en modo standalone
* @author 	Guillermo Esquivel González
* @version 	1.0.0
*/

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Interfaz extends JApplet{
	//atributos privados de la clase
	private PanelControl controlador;
	private GraficaPanel grafica;
	private DataPanel datos;
	private ParabolaModelo parabolas;
	private JPanel infoPanel;
	private Timer cronometro;
	private boolean disparando = false;
	private boolean recorrido = false;
	private ArrayList<Hashtable<String, Double>> cohete;
	private int disparos, tiempo;

		
	/**
	 * Sobreescribimos el método necesario para que nuestro programa funcione en modo applet
	 */
	@Override
	public void init() {
		cronometro = new Timer(100, new MyEventListener());
		infoPanel = new JPanel();
		datos = new DataPanel();
		grafica = new GraficaPanel();
		controlador = new PanelControl();
		parabolas = new ParabolaModelo();
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(controlador, BorderLayout.SOUTH);
		infoPanel.setLayout(new BorderLayout());
		infoPanel.add(grafica, BorderLayout.CENTER);
		infoPanel.add(datos, BorderLayout.EAST);
		getContentPane().add(infoPanel, BorderLayout.CENTER);
		controlador.getBorrar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDisparando(false);
				getCronometro().stop();
				setTiempo(0);
				getGrafica().resetear();
				setDisparos(0);
				
			}
		});
		controlador.getPausar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCronometro().stop();
			}
		});
		
		controlador.getLanzar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PUESTO AQUI
				
				if(!isDisparando()){
					setTiempo(0);
					double alturaInicial = getControlador().getAlt();
					double velocidadInicial = getControlador().getVel();
					double angulo = getControlador().getAng();
					setCohete(getParabolas().calcularTrayectoria(alturaInicial, velocidadInicial, angulo));
					//getGrafica().setVector(getCohete().get(0).get("vx"), getCohete().get(0).get("vy"));
					setRecorrido(getControlador().isRastro());
					if(getDisparos() == 0){
						getGrafica().setEjes(getCohete().get(getCohete().size() - 1).get("x"), 
						                      getCohete().get(getCohete().size() - 1).get("ymax"));
					}
					setDisparos(getDisparos() + 1); //disparos++;
					setDisparando(true);
				}
				getCronometro().start();
			}
		});
		cronometro.addActionListener(new MyEventListener());
	}
	/**
	 * Definción del programa principal, standalone.
	 * @param args
	 */
	public static void main (String[] args){
		JFrame marco = new JFrame("cohetes");
		Interfaz ventana = new Interfaz();
		marco.add(ventana, BorderLayout.CENTER);
		ventana.init();
		ventana.start();
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		marco.setSize( pantalla);
		marco.setVisible(true);
	}
	/**
	 * Constructor de la clase
	 */
	public Interfaz() {
		
	}
	
	/**
	 * Clase interna para los eventos lanzados por el timer.
	 * @author Guille
	 *
	 */
	  class MyEventListener implements ActionListener {
		  @Override
		  public void actionPerformed(ActionEvent e){
			  if(e.getSource() == getCronometro()){
				  if(getCohete().size() > getTiempo()){
					  getGrafica().dibujarPunto(getCohete().get(getTiempo()).get("x"), getCohete().get(getTiempo()).get("y"), isRecorrido(), getDisparos());
					  getDatos().setDatos(getCohete().get(getTiempo()));
					  //System.out.println("Final: " + getCohete().get(getTiempo()));
					  //getDatos().setDatos(getCohete().get(cohete.size() - 1));
					  setTiempo(getTiempo() + 1);
					  //System.out.println(getCohete().size() + "   " + getTiempo());
				  }
				  else{
					  //System.out.println("Final: " + getCohete().get(cohete.size() -1));
					  setDisparando(false);
					  getCronometro().stop();
					  //setTiempo(0);
				  }
			  }
		  }
	  }
	 
	//Getters y Setters
	public PanelControl getControlador() {
		return controlador;
	}

	public void setControlador(PanelControl controlador) {
		this.controlador = controlador;
	}

	public GraficaPanel getGrafica() {
		return grafica;
	}

	public void setGrafica(GraficaPanel grafica) {
		this.grafica = grafica;
	}

	public DataPanel getDatos() {
		return datos;
	}

	public void setDatos(DataPanel datos) {
		this.datos = datos;
	}

	public ParabolaModelo getParabolas() {
		return parabolas;
	}

	public void setParabolas(ParabolaModelo parabolas) {
		this.parabolas = parabolas;
	}

	public JPanel getInfoPanel() {
		return infoPanel;
	}

	public void setInfoPanel(JPanel infoPanel) {
		this.infoPanel = infoPanel;
	}

	public Timer getCronometro() {
		return cronometro;
	}

	public void setCronometro(Timer cronometro) {
		this.cronometro = cronometro;
	}

	public boolean isDisparando() {
		return disparando;
	}

	public void setDisparando(boolean disparando) {
		this.disparando = disparando;
	}

	public boolean isRecorrido() {
		return recorrido;
	}

	public void setRecorrido(boolean recorrido) {
		this.recorrido = recorrido;
	}

	public ArrayList<Hashtable<String, Double>> getCohete() {
		return cohete;
	}

	public void setCohete(ArrayList<Hashtable<String, Double>> cohete) {
		this.cohete = cohete;
	}

	public int getDisparos() {
		return disparos;
	}

	public void setDisparos(int disparos) {
		this.disparos = disparos;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	  
	  
	  

}
