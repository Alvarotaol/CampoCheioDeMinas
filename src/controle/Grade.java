package controle;

import logica.Campo;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class Grade {
	Circle[][] grade;
	Campo c;
	public static int x, y, dimx, dimy, dimq;
	private Image bomba;
	public Grade(int x, int y, int dx, int dy, int dq) {
		this.x = x;
		this.y = y;
		dimx = dx;
		dimy = dy;
		dimq = dq;
		grade = new Circle[dx][dy];
		for (int i = 0; i < dx; i++) {
			for (int j = 0; j < dy; j++) {
				grade[i][j] = new Circle(x + i*dq + dq/2, y + j*dq + dq/2, dq/2);
			}
		}
		c = new Campo(dx, dy, dx*dy/10);
		c.novoJogo();
		
		try {
			bomba = new Image("img/bomba.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void desenhar(int mx, int my, Graphics g){
		for (int i = 0; i < dimx; i++) {
			for (int j = 0; j < dimy; j++) {
				if(c.getEstado(i, j) == 1){
					g.setColor(Color.lightGray);
					g.fill(grade[i][j]);
					if(c.getCampo(i, j) != 0) {
						g.setColor(Color.red);
						g.drawString("" + c.getCampo(i, j), i*dimq + x + dimq / 4, j*dimq + y + dimq / 5);
					}
				} else if(c.getEstado(i, j) == 2) {
					//g.fillOval(i*dimq + x, j*dimq + y, dimq, dimq);
					bomba.draw(i*dimq + x + dimq*(1 - bomba.getWidth()/39f)/2, j*dimq + y + 1, (dimq - 3)/39.0f);//39 é o tamanho original da imagem
				} else {
					if (grade[i][j].contains(mx, my)) {
						g.setColor(Color.white);
						g.fill(grade[i][j]);
					} else {
						g.setColor(Color.darkGray);
						g.fill(grade[i][j]);
					}
				}
			}
		}
	}
	
	public void marcar(int mx, int my){
		if(mx > x && mx < x + dimq*dimx && my > y && my < y + dimq*dimy){
			c.marcarBomba((mx - x)/dimq, (my-y)/dimq);
		}
	}
	
	/**
	 * @param mx x do mouse
	 * @param my y do mouse
	 * @return true se o usuário clicou em bomba
	 */
	public boolean clicar(int mx, int my){
		if(mx > x && mx < x + dimq*dimx && my > y && my < y + dimq*dimy){
			return c.abrir((mx - x)/dimq, (my-y)/dimq);
		}
		return false;
	}
	
	public boolean venceu(){
		return c.getResto() == c.getBombas();
	}
	
	public int getResto(){
		return c.getResto();
	}
}
