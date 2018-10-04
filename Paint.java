import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Paint extends JFrame {

	public Paint(String title) {
		super(title);
	}
	
	public void init() {
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			_logo = javax.imageio.ImageIO.read(
								new java.io.File("Java-logo.png") );
			_imageWidth = _logo.getWidth(null);
		}
		catch(IOException ioe) {
			System.out.println(ioe);
		}
		
	}

	public void update(double elapsedTime) {
		_x += _incX * elapsedTime;
		if (_x < 0) {
			_x = -_x;
			_incX *= -1;
		}
		else if (_x + _imageWidth > getWidth()) {
			_x = 2*(getWidth() - _imageWidth) - _x;
			_incX *= -1;
		}
	}
	
	public void render(Graphics g) {
		// aquí pintamos
		g.setColor(Color.blue);
		g.fillRect(0, 0, getWidth(), getHeight());


		g.drawImage(_logo, (int)_x, 100, null);
		/*
		try {
			Thread.sleep(16);
		}
		catch(Exception e) {
		}
		*/		
	}
	
	public static void main(String[] args) {
	
		Paint ventana = new Paint("Paint");
		ventana.init();
		ventana.setIgnoreRepaint(true);
		ventana.setVisible(true);
		long lastFrameTime = System.nanoTime();
		long currentTime, nanoElapsedTime;
		double elapsedTime;
		Graphics g;
		while(true) {
			currentTime = System.nanoTime();
			nanoElapsedTime = currentTime - lastFrameTime;
			elapsedTime = (double) nanoElapsedTime / 1E09;
			lastFrameTime = currentTime;

			// TODO: Gestionar input (que no tenemos)
			ventana.update(elapsedTime); // Lógica.
			g = ventana.getGraphics();
			ventana.render(g);
			g.dispose();
		}

	}
	
	Image _logo;

	double _x = 0;
	int _incX = 50;

	int _imageWidth;
		
}