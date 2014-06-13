package controle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Grade {
	Rectangle[][] grade;
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
	}
	
	public void desenhar(int mx, int my, Graphics g){
		for (int i = 0; i < dimx; i++) {
			for (int j = 0; j < dimy; j++) {
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
