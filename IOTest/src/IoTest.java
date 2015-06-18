import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class IoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
//		System.out.println("命令列輸入" + args.length + "個");
//        if(args.length > 0){
//            for(int i = 0; i < args.length; i++){
//                System.out.println("args[" + i + "]=" + args[i]);
//            }
//        }
//        else{
//            System.out.print("命令列輸入沒有元素!");
//        }
//        System.out.println();

		BufTest1();
		System.out.println();
		
		BufTest2();
		System.out.println();
		
		BufWrtTxtTest();
		System.out.println();
		
		BufKeyboardTest();
		System.out.println();
		
		BufRedFileTest();
		System.out.println();
	}
	
	public static void BufTest1() throws IOException{
		 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //輸出資料流入緩衝器後再輸出到系統標準輸出
        String s = "字元輸出資料流 BufferedWriter 測試!";
        bw.write(s, 0, s.length()); //用write 方法將s 輸出
        bw.flush(); //用 flush方法清除
	}
	
	public static void BufTest2() throws IOException{
		//前面三個字不輸出
		BufferedWriter bs = new BufferedWriter(new OutputStreamWriter(System.out));
        //輸出資料流入緩衝器後再輸出到 test.txt檔案
        String s = "字元輸出資料流 BufferedWrite測試!";
        bs.write(s, 3 ,s.length() - 3); //用 write方法將 s輸出
        bs.flush(); //用 flush方法清除
	}
	
	public static void BufWrtTxtTest() throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));
	    //輸出資料流入緩衝器後再輸出到 test.txt檔案中
	    String s = "字元輸出資料流 BufferedWriter測試!";
	    bw.write(s, 0, s.length()); // 用 write方法將s 輸出
	    bw.close(); //用 close方法輸出完後關閉
	}
	
	public static void BufKeyboardTest() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 字元輸入資料流入緩衝器類別物件 br
        String line;
        System.out.print("請輸入一個字串:");
        System.out.flush(); // 清除System.out中的資料
        line = br.readLine(); //讀入後指定給字串 line
        System.out.println(line);
	}
	
	public static void BufRedFileTest() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        //從檔案輸入資料流入緩衝器類別物件 br
        String line;
        System.out.println("從檔案內讀入的資料如下");
        line = br.readLine(); //將 br讀入後指定給字串line
        System.out.println(line);

	}
}
