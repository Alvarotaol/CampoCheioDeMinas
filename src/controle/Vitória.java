package controle;

import logica.Campo;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Vitória extends BasicGameState {
	private final int estado;
	private int tam = 100, dir = 1;
	private boolean clicado = false;
	public Vitória(int estado) {
		this.estado = estado;
	}
	
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {

	}

	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.setColor(Color.blue);
		
		g.fillRect(gc.getWidth() / 2 - tam / 2, gc.getHeight() / 2 - tam / 2, tam, tam);
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
