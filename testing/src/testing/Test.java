package testing;

import java.util.HashMap;

public class Test {

	public static void main(String[] args) {
		
	    // Creating an empty HashMap
	    HashMap<Integer, Unit> hash_map = new HashMap<Integer, Unit>();
	    HashMap<Integer, Unit> hash_map2 = new HashMap<Integer, Unit>();
	 
	    // Mapping string values to int keys
	    hash_map.put(10, new Unit(1,0));
	    hash_map.put(15, new Unit(2,0));
	    hash_map.put(20, new Unit(3,0));
	    hash_map.put(25, new Unit(4,0));
	    hash_map.put(30, new Unit(5,0));
	 
	    // Displaying the HashMap
	    System.out.println("Initial Mappings are: " + hash_map);
	    
	    // Removing the existing key mapping
	    hash_map2.put(20, hash_map.get(20));
	    hash_map.remove(20);
	 
	    // Verifying the returned value
	 
	    // Displaying the new map
	    System.out.println("New map is: "+ hash_map);
	    System.out.println("New map is: "+ hash_map2);
	}

}
