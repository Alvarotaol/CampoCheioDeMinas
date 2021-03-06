import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.StateBasedGame;
import org.lwjgl.*;

import controle.Derrota;
import controle.JogoEstado;
import controle.MenuEstado;
import controle.Vit�ria;

public class CampoMinadoPrincipal extends StateBasedGame {
    public static AppGameContainer app;
	public CampoMinadoPrincipal(String title) {
		super(title);
		this.addState(new MenuEstado(0));
		this.addState(new JogoEstado(1));
		this.addState(new Derrota(2));
		this.addState(new Vit�ria(3));
	}
	
	public static void main(String[] args) {
		int largura = 480, altura = 640, limiteFps = 50;
		boolean telaCheia = false;
		try {
			app = new AppGameContainer(new CampoMinadoPrincipal("Janela"));
			app.setDisplayMode(largura, altura, telaCheia);
		    app.setTargetFrameRate(limiteFps);
		    app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}
}
