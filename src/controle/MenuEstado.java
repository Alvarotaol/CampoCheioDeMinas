package controle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuEstado extends BasicGameState {
	public static Rectangle novo;
	private final int estado;
	private final int largura = 250, altura = 50;
	private int n, verm;
	private boolean clicado;
	
	public MenuEstado(int estado) {
		this.estado = estado;
		clicado = false;
	}
	
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		//in = gc.getInput();
		novo = new Rectangle(gc.getWidth()/2 - largura/2, gc.getHeight()/2 - altura/2, largura, altura);
		//psbg = sbg;
	}

	public void render(GameContainer gc, StateBasedGame arg1, Graphics g)
			throws SlickException {
		g.setBackground(Color.gray);
		g.setColor(new Color(verm, 0, 0));
		g.fill(novo);
		g.setColor(Color.darkGray);
		g.drawString("Nova Partida", novo.getCenterX() - 50, novo.getCenterY() - 7);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int fps)
			throws SlickException {
		n = fps;
		if(novo.contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())){
			verm += fps * 2.5;
			if (verm > 200){
				verm = 200;
			}
		} else {
			verm -= fps;
			if (verm < 0){
				verm = 0;
			}
		}
		if(clicado){
			clicado = false;
			sbg.getState(1).init(gc, sbg);
			sbg.enterState(1); //Sei que n�o � recomend�vel
		}
	}

	@Override
	public void mouseClicked(int bot�o, int x, int y, int cont){
		if(bot�o == Input.MOUSE_LEFT_BUTTON){
			if(novo.contains(x, y)){
				clicado = true;
			}
		}
	}
	
	@Override
	public int getID() {
		return estado;
	}

}
