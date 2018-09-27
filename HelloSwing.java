import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.*;

public class HelloSwing extends JFrame{
	static class MiActionListener implements ActionListener{


		public void actionPerformed(ActionEvent e){
			System.out.println(MSJ);
		}
	}

	public HelloSwing(String title){
		super(title);
	}//constructora

	public void init(){
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton boton;
		boton = new JButton("JAVA ES");
		add(boton);

		boton.addActionListener(new MiActionListener());

	}//init

	public static void main(String[] args){
		HelloSwing ventana;

		ventana = new HelloSwing("Hola Mundo");
		ventana.init();
		ventana.setVisible(true);

		System.out.println("XD");
	}//main

	private final static String MSJ = "Ay lmao!!";

}//class HelloSwing