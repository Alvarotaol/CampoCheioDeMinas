package controle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class JogoEstado extends BasicGameState{
	private final int estado;
	public static Circle bola;
	public static Vector2f vel, ace;
	public Grade grade;
	public int dx = 16, dy = 16;
	public Input in;
	
	public JogoEstado(int estado) {
		this.estado = estado;
	}
	
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		in = gc.getInput();
		vel = new Vector2f(5, 0);
		ace = new Vector2f(0, 0);
		bola = new Circle(320, 60, 10);
		grade = new Grade(10, gc.getHeight() - (gc.getWidth() - 20) * dy / dx - 10, dx, dy, (gc.getWidth() - 10) / dx);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.draw(bola);
		grade.desenhar(in.getMouseX(), in.getMouseY(), g);
	}

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		
		return estado;
	}

}
