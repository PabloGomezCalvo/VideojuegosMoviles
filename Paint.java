import javax.swing.*;
import java.awt.*;
import java.io.*
public class Paint extends JFrame {


	public Paint(String title){
		super(title);
	}
	public void init(){
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try{
		_logo = javax.imageio.ImageIO.read(new File ("prueba.png"));
		_imageWidth = _logo.getWidth(null);
	}
		catch(IOException ioe){
			System.out.println(ioe);
		}
		_lastFrameTime = System.nanoTime();
}
	public void render(Graphics g){
		//magisterio infantil simulator
		/*g.setColor(Color.blue);
		g.fillRect(100,100,200,200);
		g.setColor(new Color(255,0,0,128));
		g.fillRect(0,0,200,200);*/

		g.setColor(Color.blue);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(_logo,(int)_x,100,null);
		
		/*try{
		Thread.sleep(16);
		}
		catch(Exception e){

		}
		*/
		

	}

	public void update(double elapsedTime){
		_x += _vX;
		if(_x <0){
			_x = - _x;
			_vX *= -1;
		}
		else if(_x + _imageWidth > getWidth() ){
			_x = 2*(getWidth() - _imageWidth) - _x;
			_vX *= -1;

		}

	}
	public static void main (String [] args){

		Paint ventana = new Paint("Paint");
		ventana.init();
		ventana.setIgnoreRepaint(true);
		ventana.setVisible(true);
		long nanoElapsedTime;
		long currentTime;
		double elapsedTime;
		Graphics g;
		while(true){

		currentTime = System.nanoTime();
		nanoElapsedTime = currentTime - _lastFrameTime;
		elapsedTime = (double) nanoElapsedTime/1E09;
		_lastFrameTime = currentTime;

		ventana.update(elapsedTime);  //logica

		g = ventana.getGraphics();
		ventana.render(g);	//render
		g.dispose();
		
		}

	}
	Image _logo;
	double _x =0;
	int _vX = 50;
	int _imageWidth =0;

	long _lastFrameTime;
}