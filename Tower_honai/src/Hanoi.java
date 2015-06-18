import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Hanoi {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		int n; 
        BufferedReader buf; 
        buf = new BufferedReader(new InputStreamReader(System.in)); 

        System.out.print("請輸入盤數："); 
        n = Integer.parseInt(buf.readLine()); 

        Hanoi hanoi = new Hanoi(); 

        hanoi.move(n, 'A', 'B', 'C'); 
	} 
	
	/*
	 * method Move:使用三個變數的 hanoi.與 method move做對照。
	 */

	//public void Move(int disks, int from, int to) {
	public void move(int n, char a, char b, char c) {
	    //if(disks == 1)    
		if(n == 1) 
			//System.out.println("Move from " + from + " to " + to);
            System.out.println("盤 " + n + " 由 " + a + " 移至 " + c);
			//return;
       
		else {
        	//int relay = 6- from - to; //由第 1柱(from)移動到第 3柱(to); relay = 6 - 1 - 3 = 2.
			//***注意每次 call 自己時 ，relay會改變。。
        	//Move(disks-1, from, relay); //from =>a; to =>b.
            move(n - 1, a, c, b); 
            
            //Move(1, from, to); from =>a; to =>c.
            System.out.println("盤 " + n + " 由 " + a + " 移至 " + c);
            
            //Move(disks - 1, relay ,to); relay =>b; to =>c.
            move(n - 1, b, a, c); 
        } 
	}
}//end class 