/**
* Subject: 	Programación de Aplicaciones Interactivas
* Práctica: P12 Trayectoria de Proyectiles
* E-mail:   alu0100881677@ull.edu.es 
* Date:     28/4/2017
* Program:  En esta clase se define un panel que contiene ciertos botones y slider capaces de controlar el programa.
* @author 	Guillermo Esquivel González
* @version 	1.0.0
*/
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ResourceBundle.Control;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelControl extends JPanel{
	//atributos privados de la clase
	private JButton lanzar;
	private JButton pausar;
	private JButton borrar;
	private JLabel velocidad;
	private JLabel altura;
	private JLabel angulo;
	private JSlider velocidadSlider;
	private JSlider anguloSlider;
	private JSlider alturaSlider;
	private JLabel velocidadValor;
	private JLabel alturaValor;
	private JLabel anguloValor;
	private JRadioButton recorrido;
	private final int ROWS = 3;
	private final int COLS = 1;
	private double alt = 0, vel = 50, ang = 45;
	private boolean rastro = true;

	/**
	 * Consturctor de la clase
	 */
	public PanelControl(){
		setBorder(new LineBorder(new Color(0, 0, 0)));
		BorderLayout borderLayout = new BorderLayout();
		setLayout(borderLayout);
		JPanel control = new JPanel();
		control.setLayout(new GridLayout(ROWS, COLS, 1, 1));		
		
		lanzar = new JButton("Lanzar");
		control.add(lanzar);
		
		velocidad = new JLabel("Velocidad");
		control.add(velocidad);
		
		velocidadSlider = new JSlider();
		velocidadSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				velocidadValor.setText(String.valueOf(velocidadSlider.getValue()));
				vel = velocidadSlider.getValue();
			}
		});
		velocidadSlider.setMajorTickSpacing(10);
		velocidadSlider.setPaintTicks(true);
	    velocidadSlider.setMinimum(0);
	    velocidadSlider.setMaximum(100);
	    control.add(velocidadSlider);
	    
	    velocidadValor = new JLabel("50");
	    control.add(velocidadValor);
	    
	    pausar = new JButton("Pausar");
	    control.add(pausar);
	    
	    angulo = new JLabel("Angulo");
	    control.add(angulo);
	    
	    anguloSlider = new JSlider();
	    anguloSlider.setMajorTickSpacing(10);
	    anguloSlider.setValue(45);
	    anguloSlider.setPaintTicks(true);
	    anguloSlider.setMinimum(0);
	    anguloSlider.setMaximum(90);
	    control.add(anguloSlider);
	    anguloSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				anguloValor.setText(String.valueOf(anguloSlider.getValue()));
				ang = anguloSlider.getValue();
			}
		});
	    
	    anguloValor = new JLabel("45");
	    control.add(anguloValor);

	    JPanel recorridoPanel = new JPanel();
		recorridoPanel.setLayout(new BorderLayout(0, 0));
		recorrido = new JRadioButton("Mostrar Rastro");
		recorrido.setFont(new Font("Apple Color Emoji", Font.PLAIN, 13));
		recorrido.setSelected(true);
		recorrido.setForeground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		recorridoPanel.add(recorrido);
		add(recorridoPanel, BorderLayout.EAST);
		recorrido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(recorrido.isSelected()){
					rastro = true;
				}
				else{
					rastro = false;
				}
			}
		});
	    
	    
	    borrar = new JButton("Borrar");
	    control.add(borrar);
	    
	    altura = new JLabel("Altura");
	    control.add(altura);
	    
	    alturaSlider = new JSlider();
	    alturaSlider.setMajorTickSpacing(10);
	    alturaSlider.setValue(0);
	    alturaSlider.setPaintTicks(true);
	    alturaSlider.setMinimum(0);
	    alturaSlider.setMaximum(100);
	    control.add(alturaSlider);
	    alturaSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				alturaValor.setText(String.valueOf(alturaSlider.getValue()));
				alt = alturaSlider.getValue();
				
			}
		});
	    
	    alturaValor = new JLabel("0");
	    control.add(alturaValor);
	    add(control, BorderLayout.CENTER);
	    setVisible(true);
	}

	//Getters y setters
	public JButton getLanzar() {
		return lanzar;
	}


	public void setLanzar(JButton lanzar) {
		this.lanzar = lanzar;
	}


	public JButton getPausar() {
		return pausar;
	}


	public void setPausar(JButton pausar) {
		this.pausar = pausar;
	}


	public JButton getBorrar() {
		return borrar;
	}


	public void setBorrar(JButton borrar) {
		this.borrar = borrar;
	}


	public JLabel getVelocidad() {
		return velocidad;
	}


	public void setVelocidad(JLabel velocidad) {
		this.velocidad = velocidad;
	}


	public JLabel getAltura() {
		return altura;
	}


	public void setAltura(JLabel altura) {
		this.altura = altura;
	}


	public JLabel getAngulo() {
		return angulo;
	}


	public void setAngulo(JLabel angulo) {
		this.angulo = angulo;
	}


	public JSlider getVelocidadSlider() {
		return velocidadSlider;
	}


	public void setVelocidadSlider(JSlider velocidadSlider) {
		this.velocidadSlider = velocidadSlider;
	}


	public JSlider getAnguloSlider() {
		return anguloSlider;
	}


	public void setAnguloSlider(JSlider anguloSlider) {
		this.anguloSlider = anguloSlider;
	}


	public JSlider getAlturaSlider() {
		return alturaSlider;
	}


	public void setAlturaSlider(JSlider alturaSlider) {
		this.alturaSlider = alturaSlider;
	}


	public JLabel getVelocidadValor() {
		return velocidadValor;
	}


	public void setVelocidadValor(JLabel velocidadValor) {
		this.velocidadValor = velocidadValor;
	}


	public JLabel getAlturaValor() {
		return alturaValor;
	}


	public void setAlturaValor(JLabel alturaValor) {
		this.alturaValor = alturaValor;
	}


	public JLabel getAnguloValor() {
		return anguloValor;
	}


	public void setAnguloValor(JLabel anguloValor) {
		this.anguloValor = anguloValor;
	}


	public JRadioButton getRecorrido() {
		return recorrido;
	}


	public void setRecorrido(JRadioButton recorrido) {
		this.recorrido = recorrido;
	}


	public double getAlt() {
		return alt;
	}


	public void setAlt(double alt) {
		this.alt = alt;
	}


	public double getVel() {
		return vel;
	}


	public void setVel(double vel) {
		this.vel = vel;
	}


	public double getAng() {
		return ang;
	}


	public void setAng(double ang) {
		this.ang = ang;
	}


	public boolean isRastro() {
		return rastro;
	}


	public void setRastro(boolean rastro) {
		this.rastro = rastro;
	}
	
	
	
}




























