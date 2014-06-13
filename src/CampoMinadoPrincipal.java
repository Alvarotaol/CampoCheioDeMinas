import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.lwjgl.*;

public class CampoMinadoPrincipal extends BasicGame{
	public static Circle bola;
	public static Vector2f vel, ace;
    public static AppGameContainer app;
	public CampoMinadoPrincipal(String title) {
		super(title);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.draw(bola);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		vel = new Vector2f(5, 0);
		ace = new Vector2f(0, 0);
		bola = new Circle(320, 60, 10);
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		bola.setLocation(bola.getLocation().add(vel));
		vel.add(ace);
		ace.set(320 - bola.getCenterX(), 240 - bola.getCenterY());
		ace.normalise();
	}
	
	public static void main(String[] args) {
		int largura = 640, altura = 480, limiteFps = 50;
		boolean telaCheia = false;
		try {
			app = new AppGameContainer(new CampoMinadoPrincipal("Janela"));
			app.setDisplayMode(largura, altura, telaCheia);
		    app.setTargetFrameRate(limiteFps);
		    app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyPressed(int key, char c){
		app.exit();
	}
}
