import java.util.*;

class board {
	private char[][] arr;
	
	public board() {
		arr = new char[3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++)
				arr[i][j]=' ';
		}
	}
	void print() {
		System.out.println("  1   2   3");
		for(int i=0;i<3;i++) {
			System.out.print(i+1+" ");
			for(int j=0;j<3;j++) {
				System.out.print(arr[i][j]);
				if(j<3)System.out.print(" | ");
			}
			
			System.out.println();
			System.out.println("--------------");
		}
	}
	boolean isWinner(char ch) {
		for(int i=0;i<3;i++) {
			if((arr[i][0]==ch&&arr[i][1]==ch&&arr[i][2]==ch)||
				(arr[0][i]==ch&&arr[1][i]==ch&&arr[2][i]==ch)) return true;
		}
		if((arr[0][0]==ch&&arr[1][1]==ch&&arr[2][2]==ch)||
				(arr[0][2]==ch&&arr[1][1]==ch&&arr[2][0]==ch)) return true;
		return false;
	}
	boolean isFull() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++)
				if(arr[i][j]==' ') return false;
		}
		return true;
	}
	boolean placed(int row, int col, char ch) {
		if((row<0||row>=3||col<0||col>=3||arr[row][col]!=' ')) return false;
		
		arr[row][col]=ch;
		return true;
	}
}

class player{
	private String name;
	private char symbol;
	
	public player(String name, char symbol) {
		this.name = name;
		this.symbol = symbol;
	}
	String getName() {
		return this.name;
	}
	char getSymbol() {
		return this.symbol;
	}
}
public class Main{
	public static void main(String[] args) {
		board b1 = new board();
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Player1 (X)'s name : ");
		String p1name = scan.next();
		player p1 = new player(p1name,'X');
		
		System.out.println("Enter Player2 (O)'s name : ");
		String p2name = scan.next();
		player p2 = new player(p2name,'O');
		
		boolean endgame = false;
		boolean winnerDoesntExist = true;
		b1.print();
		player currentplayer = p1;
		while(!endgame) {
			
			System.out.println("it is "+currentplayer.getName()+"'s turn, choose in range [1-3][1-3] : ");
			int row = scan.nextInt();
			int col = scan.nextInt();
			if(!(b1.placed(row-1,col-1,currentplayer.getSymbol()))) {
				System.out.println("Invalid input, try again.");
				continue;
			}
			b1.print();
			if(b1.isWinner(currentplayer.getSymbol())) {
				winnerDoesntExist = false; 
				System.out.println("Congratulations! "+currentplayer.getName()+" is the winner");
				break;
			}
			endgame = b1.isFull();
			currentplayer = currentplayer == p1 ? p2 : p1;
		}
		if(endgame&&winnerDoesntExist) {
			System.out.println("The match is a draw, try a new game");
		}
		scan.close();
	}
}