package de.catchycube.sudoku.solver;

import java.util.LinkedList;
import java.util.List;

import de.catchycube.sudoku.general.Sudoku;

public class Solver {
	public Sudoku solve(Sudoku orig){
		Sudoku clone = orig.clone();
		List<Integer> freeSpots = clone.getFreeIDs();
		for(; !freeSpots.isEmpty(); freeSpots = clone.getFreeIDs()){
			for(Integer id : freeSpots){
				//Determine all numbers avaiable for this spot
				List<Byte> avaiable = new LinkedList<Byte>();
				for(byte value = 1; value < 10; value++){
					if(clone.isValid(id, value)) avaiable.add(value);
				}
				
				//This is a problem: Theres no number that could be placed on this field
				if(avaiable.isEmpty()){
					System.out.println("Problem: no av");
				}
				//If there's only one number, insert this number and continue to the next iteration
				if(avaiable.size() == 1){
					clone.set(id, avaiable.get(0));
					break;
				}
				
				//Determine the numbers that can't go anywhere else
				int[] coords = clone.transformIDtoCoords(id);
				List<Byte> onlyHere = new LinkedList<Byte>();
				for(Byte value : avaiable){
					//3x3 area check
					boolean noOtherPlace = true;
					int bx = coords[0]/3, by = coords[1]/3;
					for(int x = bx*3; x < (bx+1)*3 && noOtherPlace;x++){
						for(int y = by*3; y < (by+1)*3; y++){
							if(coords[0]==x&&coords[1]==y) continue;
							if(clone.isValid(x, y, value)) noOtherPlace = false;
						}
					}
					if(noOtherPlace){
						onlyHere.add(value);
						continue;
					}
					
					//Line check
					noOtherPlace = true;
					for(int x = 0; x < 9 && noOtherPlace; x++){
						if(coords[0] == x) continue;
						if(clone.isValid(x, coords[1], value)) noOtherPlace = false;
					}
					if(noOtherPlace){
						onlyHere.add(value);
						continue;
					}
					
					//Row check
					noOtherPlace = true;
					for(int y = 0; y < 9 && noOtherPlace; y++){
						if(coords[1] == y) continue;
						if(clone.isValid(coords[0],y,value)) noOtherPlace = false;
					}
					if(noOtherPlace){
						onlyHere.add(value);
						continue;
					}
				}
				//This is a problem: multiple numbers can't go anywhere else
				if(onlyHere.size() > 1){
					System.out.println("Problem: Multi number");
				}
				else if(!onlyHere.isEmpty()){
					clone.set(id, onlyHere.get(0));
				}
			}	
		}
		return clone;
	}
}
