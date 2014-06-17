package controle;

import logica.Campo;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Grade {
	Rectangle[][] grade;
	Campo c;
	public static int x, y, dimx, dimy, dimq;
	public Grade(int x, int y, int dx, int dy, int dq) {
		this.x = x;
		this.y = y;
		dimx = dx;
		dimy = dy;
		dimq = dq;
		grade = new Rectangle[dx][dy];
		for (int i = 0; i < dx; i++) {
			for (int j = 0; j < dy; j++) {
				grade[i][j] = new Rectangle(x + i*dq, y + j*dq, dq, dq);
			}
		}
		c = new Campo(dx, dy, dx*dy/10);
		c.novoJogo();
	}
	
	public void desenhar(int mx, int my, Graphics g){
		for (int i = 0; i < dimx; i++) {
			for (int j = 0; j < dimy; j++) {
				if(c.getEstado(i, j) == 1){
					g.drawString("" + c.getCampo(i, j), i*dimq + x + dimq / 4, j*dimq + y + dimq / 5);
				} else if(c.getEstado(i, j) == 2) {
					g.setColor(Color.blue);
					g.fillOval(i*dimq + x, j*dimq + y, dimq, dimq);
					g.setColor(Color.lightGray);
				} else {
					if (grade[i][j].contains(mx, my)) {
						g.setColor(Color.lightGray);
						g.fill(grade[i][j]);
					} else {
						g.draw(grade[i][j]);
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
	
	public int getResto(){
		return c.getResto();
	}
}
