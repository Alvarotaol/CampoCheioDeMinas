package logica;

import java.awt.Point;
import java.util.Random;
import java.util.ArrayDeque;


public class Campo {
	private int x, y, bombas, resto;
	public int[][] estado, grade;
	public static Campo inst;
	
	public Campo(int x, int y, int bombas) {
		this.x = x;
		this.y = y;
		this.bombas = bombas;
		grade = new int[x][y];
		estado = new int[x][y];
		inst = this;
	}

	public boolean abrir(int i, int j) {
		if(estado[i][j] == 2) return false;
		if(grade[i][j] == 9){
			estado[i][j] = 1;
			resto--;
			return true;
		}
		ArrayDeque<Point> fila = new ArrayDeque<Point>();
		fila.addLast(new Point(i, j));
		estado[i][j] = 1;
		while(!fila.isEmpty()){
			Point p = fila.pollFirst();
			resto--;
			if(grade[p.x][p.y] == 0){
				if(p.x > 0 && estado[p.x-1][p.y] == 0) {
					fila.addLast(new Point(p.x-1, p.y));
					estado[p.x-1][p.y] = 1;
					if(p.y > 0 && estado[p.x-1][p.y-1] == 0) {
						fila.addLast(new Point(p.x-1, p.y - 1));
						estado[p.x-1][p.y-1] = 1;
					}
					if(p.y < y-1 && estado[p.x-1][p.y+1] == 0) {
						fila.addLast(new Point(p.x-1, p.y + 1));
						estado[p.x-1][p.y+1] = 1;
					}
				}
				
				if(p.x < x-1 && estado[p.x+1][p.y] == 0) {
					fila.addLast(new Point(p.x+1, p.y));
					estado[p.x+1][p.y] = 1;
					if(p.y > 0 && estado[p.x+1][p.y-1] == 0) {
						fila.addLast(new Point(p.x+1, p.y - 1));
						estado[p.x+1][p.y-1] = 1;
					}
					if(p.y < y-1 && estado[p.x+1][p.y+1] == 0) {
						fila.addLast(new Point(p.x+1, p.y + 1));
						estado[p.x+1][p.y+1] = 1;
					}
				}
				
				if(p.y > 0 && estado[p.x][p.y-1] == 0) {
					fila.addLast(new Point(p.x, p.y - 1));
					estado[p.x][p.y-1] = 1;
				}
				
				if(p.y < y-1 && estado[p.x][p.y+1] == 0) {
					fila.addLast(new Point(p.x, p.y + 1));
					estado[p.x][p.y+1] = 1;
				}
			}
		}
		return false;
	}
	
	public void marcarBomba(int i, int j){
		if(estado[i][j] == 0){
			estado[i][j] = 2;
		} else if(estado[i][j] == 2){
			estado[i][j] = 0;
		}
	}
	
	public int getEstado(int i, int j){
		return estado[i][j];//(grade[i][j] == 0)?1:0;
	}
	
	public int getCampo(int i, int j){
		return grade[i][j];
	}
	
	public int getResto(){
		return resto;
	}
	
	public int getBombas(){
		return bombas;	
	}
	
	public void novoJogo(){
		int i = 0;
		Random gerar = new Random();
		while(i < bombas){
			int nx = gerar.nextInt(x), ny = gerar.nextInt(y);
			while(grade[nx][ny] >= 9){
				nx = gerar.nextInt(x);
				ny = gerar.nextInt(y);
			}
			grade[nx][ny] = 9;
			somar(nx, ny);
			i++;
		}
		for(i = 0; i < x; i++){
			for(int j = 0; j < y; j++){
				if (grade[i][j] > 9) {
					grade[i][j] = 9;
				}
			}
		}
		resto = x*y;
	}
	
	private void somar(int i, int j){
		if(i != 0){
			if(j != 0){
				grade[i-1][j-1]++;
			}
			if(j < y - 1){
				grade[i-1][j+1]++;
			}
			grade[i-1][j]++;
		}
		if(j != 0){
			grade[i][j-1]++;
		}
		if(i != y - 1){
			if(j != 0){
				grade[i+1][j-1]++;
			}
			if(j < y - 1){
				grade[i+1][j+1]++;
			}
			grade[i+1][j]++;
		}
		if(j != y-1){
			grade[i][j+1]++;
		}
	}
	
	/*public static void main(String[] args) {
		Campo c = new Campo(5, 5, 10);
		c.novoJogo();
		int cont = 0;
		for(int i = 0; i < c.x; i++){
			for(int j = 0; j < c.y; j++){
				if (c.grade[i][j] >= 9) {
					cont++;
				}
				System.out.print("" + c.grade[i][j] + " ");
			}
			System.out.println("-> " + cont);
		}
		c.abrir(0, 0);
	}*/
}
