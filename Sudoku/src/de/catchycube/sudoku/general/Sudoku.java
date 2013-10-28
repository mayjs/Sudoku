package de.catchycube.sudoku.general;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of a sudoku game.
 * @author Kemiren
 *
 */
public class Sudoku {
	private byte[][] values = new byte[9][9];
	private boolean[][] preSet = new boolean[9][9];
	
	public Sudoku(byte[][] values, boolean[][] preSet){
		this.values = values;
		this.preSet = preSet;
	}
	public Sudoku(byte[][] values){
		for(int x = 0; x < 9; x++){
			for(int y = 0; y < 9; y++){
				this.values[x][y] = values[x][y];
				this.preSet[x][y] = values[x][y]!=0;
			}
		}
	}
	public Sudoku(){}
	
	public static Sudoku parse(String[] input){
		Sudoku ret = new Sudoku();
		for(int y = 0; y < 9; y++){
			String current = input[y];
			for(int x = 0; x < 9; x++){
				int n = (int)current.charAt(x)-48;
				if(n >0&&n<10){
					ret.preSetNumber(x, y, (byte)n);
				}
			}
		}
		return ret;
	}
	
	public void preSetNumber(int x, int y, byte val){
		values[x][y] = val;
		preSet[x][y] = true;
	}
	public void preSetNumber(int index, byte val){
		int[] coords = this.transformIDtoCoords(index);
		preSetNumber(coords[0],coords[1],val);
	}
	
	public boolean remove(int x, int y){
		if(!preSet[x][y]) values[x][y] = 0;
		return !preSet[x][y];
	}
	
	public boolean set(int x, int y, byte value){
		if(!preSet[x][y]) values[x][y] = value;
		return !preSet[x][y];
	}
	
	public byte get(int x, int y){
		return values[x][y];
	}
	
	public boolean remove(int id){
		int[] coords = transformIDtoCoords(id);
		return remove(coords[0],coords[1]);
	}
	public boolean set(int id, byte value){
		int[] coords = transformIDtoCoords(id);
		return set(coords[0], coords[1],  value);
	}
	public byte get(int id){
		int[] coords = transformIDtoCoords(id);
		return get(coords[0], coords[1]);
	}
	
	public boolean isPreset(int x, int y){
		return preSet[x][y];
	}
	public boolean isPreset(int index){
		int[] coords = this.transformIDtoCoords(index);
		return isPreset(coords[0],coords[1]);
	}
	
	//id = x+y*9
	public int[] transformIDtoCoords(int id){
		int y = id/9;
		int x = id-y*9;
		return new int[]{x,y};
	}
	public int transformCoordstoID(int[] coords){
		return coords[0]+coords[1]*9;
	}
	
	public boolean isValid(){
		//Check each 3x3 box
		for(int bx = 0; bx < 3; bx++){
			for(int by = 0; by < 3; by++){
				int[] counter = new int[10];
				for(int x = bx*3; x < (bx+1)*3; x++){
					for(int y = by*3; y < (by+1)*3;y++){
						if(counter[values[x][y]]++ > 1) return false;
					}
				}
				if(counter[0] > 0) return false;
			}
		}
		
		//Check each row and line
		for(int x = 0; x < 9; x++){
			int[] counterR = new int[9];
			int[] counterL = new int[9];
			for(int y = 0; y < 9; y++){
				if(counterR[values[x][y]]++ > 1) return false;
				if(counterL[values[y][x]]++ > 1) return false;
			}
			if(counterR[0] > 0 || counterL[0] > 0) return false;
		}
		
		return true;
	}
	
	public List<Integer> getFreeIDs(){
		List<Integer> ret = new LinkedList<Integer>();
		for(int x = 0; x < 9; x++){
			for(int y = 0; y < 9; y++){
				if(get(x, y) == 0) ret.add(this.transformCoordstoID(new int[]{x,y}));
			}
		}
		return ret;
	}
	
	public boolean isValid(int x, int y, byte value){
		//Check box
		int boxX = x/3, boxY = y/3;
		for(int tx = boxX * 3; tx < (boxX+1)*3; tx++){
			for(int ty = boxY * 3; ty < (boxY+1)*3; ty++){
				if(values[tx][ty] == value && tx!=x && ty!=y) return false;
			}
		}
		
		//Check row / line
		for(int v = 0; v < 9; v++){
			if(values[v][y] == value && v != x) return false;
			if(values[x][v] == value && v != y) return false;
		}
		
		return true;
	}
	
	public boolean isValid(int id, byte value){
		int[] coords = this.transformIDtoCoords(id);
		return this.isValid(coords[0], coords[1], value);
	}
	
	public Sudoku clone(){
		Sudoku res = new Sudoku();
		for(int i = 0; i < 81; i++){
			if(isPreset(i)) res.preSetNumber(i, new Byte(this.get(i)));
			else res.set(i, new Byte(this.get(i)));
		}
		return res;
	}
}
