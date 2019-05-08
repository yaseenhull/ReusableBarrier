// ReusableBarrier Assignment
// Yaseen Hull
// May 2019 

package molecule;

public class Carbon extends Thread {
	
	private static int carbonCounter =0;
	private int id;
	private Methane sharedMethane;
	
	public Carbon(Methane methane_obj) {
		Carbon.carbonCounter+=1;
		id=carbonCounter;
		this.sharedMethane = methane_obj;
	}
	
	public void run() {
	    try {	 
	    	 // you will need to fix below

	    	sharedMethane.mutex.acquire(); // first thread to reach mutex gains exclussive access
	    	sharedMethane.addCarbon(); // increment carbon counter
	    	//if one carbon thread arrives before four hydrogen threads arrive it has to wait for the 4 hydrogen 
	    	if( sharedMethane.getHydrogen() >= 4) { 
	    		System.out.println("---Group ready for bonding---");
	    		sharedMethane.hydrogensQ.release(4); //signal hydrogen threads to leave queue
	    		sharedMethane.removeHydrogen(4);
	    		sharedMethane.carbonQ.release(); //signal carbon threads to leave queue
	    		sharedMethane.removeCarbon(1);
	    		
	    	}
	    	
	    	else {
	    		sharedMethane.mutex.release();
	    	}
	    	
	    	sharedMethane.carbonQ.acquire(); // threads join the carbon queue
	    	//System.out.println("---Group ready for bonding---");	
	    	sharedMethane.bond("C"+ this.id);  //bond
	    	
	    	sharedMethane.barrier.b_wait(); //set of 5 threads meet after invoking bond
	    	sharedMethane.mutex.release();
	    	 
	    	   	 
	    }
	    catch (InterruptedException ex) { /* not handling this  */}
	   // System.out.println(" ");
	}

}