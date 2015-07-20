
public class Honai {

	static int disks;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		disks = 3;
		//Move(7, 1, 3, "");
		System.out.println("移動" + disks + "盤:" );
		Move(disks, 1, 3);
		

	}
		
	public static void Move(int disks, int from, int to)
	{
	    if(disks == 1)
	    {
	    	System.out.println(" Move from " + from + " to " + to);
	        return;
	    }
	    
	    int relay = 6 - from - to;
	    Move(disks - 1, from, relay);
	    Move(1, from, to);
	    Move(disks - 1, relay, to);
	    
	}

}
