// ReusableBarrier Assignment
// Yaseen Hull
// May 2019 

package molecule;

public class Hydrogen extends Thread {

	private static int carbonCounter =0;
	private int id;
	private Methane sharedMethane;
	
	
	public Hydrogen(Methane methane_obj) {
		Hydrogen.carbonCounter+=1;
		id=carbonCounter;
		this.sharedMethane = methane_obj;
		
	}
	
	public void run() {
	    try {
	    	 // you will need to fix below
	    	sharedMethane.mutex.acquire(); // first thread to reach mutex gains exclussive access
	    	sharedMethane.addHydrogen();
	    	//if one hydrogen thread arrives before the other hydrogen and carbon threads it has to wait for 3 more hydrogen and 1 carbon thread to arrive
	    	
	    	if (sharedMethane.getHydrogen() >= 4 & sharedMethane.getCarbon() >= 1) { //molecules conditions are satisfied
	    		System.out.println("---Group ready for bonding---");
	    		sharedMethane.hydrogensQ.release(4); //signal hydrogen threads to leave queue 
	    		sharedMethane.removeHydrogen(4); //decrement hydrogen counter
	    		sharedMethane.carbonQ.release(); //signal carbon threads to leave queue
	    		sharedMethane.removeCarbon(1); //decremnet carbon counter
	    		
	    	}
	    	else {
	    			
	    		sharedMethane.mutex.release(); 
	    	}
	    	
	    	sharedMethane.hydrogensQ.acquire(); // threads join the hydrogen queue
	    			 
	    	sharedMethane.bond("H"+ this.id);
	    	sharedMethane.barrier.b_wait(); //set of 5 threads meet after invoking bond
	    }
	   catch (InterruptedException ex) { /* not handling this  */}
	    //System.out.println(" ");
	}

}
