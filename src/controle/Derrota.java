package controle;

import logica.Campo;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Derrota extends BasicGameState {
	private final int estado;
	private int tam = 100, dir = 1;
	private boolean clicado = false;
	public Derrota(int estado) {
		this.estado = estado;
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {

	}

	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.setColor(Color.red);
		
		g.fillOval(gc.getWidth() / 2 - tam / 2, gc.getHeight() / 2 - tam / 2, tam, tam);
	}

	public void update(GameContainer arg0, StateBasedGame sbg, int fps)
			throws SlickException {
		tam += dir * fps / 4;
		if(tam < 0){
			tam = 0;
			dir = 1;
		}
		if (tam > 200) {
			tam = 200;
			dir = -1;
		}
		if (clicado) {
			sbg.enterState(0); //Estado Menu
		}
	}

	@Override
	public void mouseClicked(int botão, int x, int y, int cont){
		clicado = true;
	}
	
	@Override
	public int getID() {
		return estado;
	}

}
