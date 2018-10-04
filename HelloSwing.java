import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.*;

public class HelloSwing extends JFrame{


	public HelloSwing(String title){
		super(title);
		_titulo = title;
	}//constructora

	public void init(){
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton boton = new JButton("Pulseme wei"); 
		add(boton);
		boton.addActionListener((ActionEvent e)	-> System.out.println("TURUTURU"));
	}//init

	public static void main(String[] args){
		HelloSwing ventana;

		ventana = new HelloSwing("Hola Mundo");
		ventana.init();
		ventana.setVisible(true);

		System.out.println("XD");
	}//main
	private String _titulo;
	private final static String MSJ = "Ay lmao!!";

}//class HelloSwing