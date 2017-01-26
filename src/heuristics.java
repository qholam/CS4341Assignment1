import java.util.HashMap;

import javax.swing.plaf.multi.MultiTableHeaderUI;

public class heuristics {
	/*
	 * Potentially store heuristic values in a hashmap where the key is the Terrain Object and the associated value is the heuristic cost for that Terrain.
	 * If we store it this way, we will need functions to generate these hashmaps. 
	 */
	HashMap<Terrain, Integer> heuristic1;
	HashMap<Terrain, Integer> heuristic2;
	HashMap<Terrain, Integer> heuristic3;
	HashMap<Terrain, Integer> heuristic4;
	HashMap<Terrain, Integer> heuristic5;
	HashMap<Terrain, Integer> heuristic6;
	
	NewWorld world;
	
	public heuristics(NewWorld world){
		this.world = world;
	}
	
	public void setUp1(){
		HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
		for(int i = 0; i < terrains.size(); i++){
			HashMap<Integer, Terrain> aterrain = terrains.get(i);
			for(int j = 0; j < aterrain.size(); j++){
				Terrain t = aterrain.get(j);
				heuristic1.put(t, 0);
			}
		}
	}
	public void setUp2(){
		HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
		for(int i = 0; i < terrains.size(); i++){
			HashMap<Integer, Terrain> aterrain = terrains.get(i);
			for(int j = 0; j < aterrain.size(); j++){
				Terrain t = aterrain.get(j);
				int terrainRow = t.getRow();
				int terrainCol = t.getCol();
				int goalRow = world.goalRow;
				int goalCol = world.goalColumn;
				
				int futureCost = Math.min(Math.abs(terrainRow - goalRow), Math.abs(terrainCol - goalCol));
				
				heuristic2.put(t, futureCost);
			}
		}
	}
	public void setUp3(){
		HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
		for(int i = 0; i < terrains.size(); i++){
			HashMap<Integer, Terrain> aterrain = terrains.get(i);
			for(int j = 0; j < aterrain.size(); j++){
				Terrain t = aterrain.get(j);
				int terrainRow = t.getRow();
				int terrainCol = t.getCol();
				int goalRow = world.goalRow;
				int goalCol = world.goalColumn;
				
				int futureCost = Math.max(Math.abs(terrainRow - goalRow), Math.abs(terrainCol - goalCol));
				
				heuristic3.put(t, futureCost);
			}
		}
	}
	public void setUp4(){
		HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
		for(int i = 0; i < terrains.size(); i++){
			HashMap<Integer, Terrain> aterrain = terrains.get(i);
			for(int j = 0; j < aterrain.size(); j++){
				Terrain t = aterrain.get(j);
				int terrainRow = t.getRow();
				int terrainCol = t.getCol();
				int goalRow = world.goalRow;
				int goalCol = world.goalColumn;
				
				int futureCost = Math.abs(goalRow - terrainRow) + Math.abs(goalCol - terrainCol);
				
				heuristic4.put(t, futureCost);
			}
		}
	}
	public void setUp5(){
		HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
		for(int i = 0; i < terrains.size(); i++){
			HashMap<Integer, Terrain> aterrain = terrains.get(i);
			for(int j = 0; j < aterrain.size(); j++){
				Terrain t = aterrain.get(j);
				int terrainRow = t.getRow();
				int terrainCol = t.getCol();
				int goalRow = world.goalRow;
				int goalCol = world.goalColumn;
				
				double power = Math.pow((goalRow - terrainRow),2) + Math.pow((goalCol - terrainCol), 2);
				int futureCost = (int) Math.round(Math.sqrt(power));
				heuristic5.put(t, futureCost);
			}
		}
	}
	public void setUp6(){
		HashMap<Integer, HashMap<Integer, Terrain>> terrains = world.getAllTerrains();
		for(int i = 0; i < terrains.size(); i++){
			HashMap<Integer, Terrain> aterrain = terrains.get(i);
			for(int j = 0; j < aterrain.size(); j++){
				Terrain t = aterrain.get(j);
				
				int futureCost = heuristic5.get(t) * 3;
				heuristic6.put(t, futureCost);
			}
		}
	}
}
