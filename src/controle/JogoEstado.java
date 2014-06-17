package controle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class JogoEstado extends BasicGameState{
	private final int estado;
	private boolean derrota, vitoria;
	public Grade grade;
	public int dx = 16, dy = 16;
	public Input in;
	
	public JogoEstado(int estado) {
		this.estado = estado;
		derrota = false;
	}
	
	public void init(GameContainer gc, StateBasedGame arg1)
			throws SlickException {
		in = gc.getInput();
		grade = new Grade(10, gc.getHeight() - (gc.getWidth() - 20) * dy / dx - 10, dx, dy, (gc.getWidth() - 10) / dx);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawString("" + grade.getResto(), 100, 10);
		grade.desenhar(in.getMouseX(), in.getMouseY(), g);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int arg2)
			throws SlickException {
		if(derrota){
			sbg.enterState(2); //Derrota
		}
		if(vitoria){
			sbg.enterState(3); //Vitória
		}
	}

	@Override
	public void mouseClicked(int botão, int x, int y, int cont){
		if(botão == Input.MOUSE_LEFT_BUTTON){
			if(grade.clicar(x, y)){
				derrota = true;
			}
			if (grade.venceu()) {
				vitoria = true;
			}
		} else {
			grade.marcar(x, y);
		}
	}
	
	@Override
	public int getID() {
		return estado;
	}

}
