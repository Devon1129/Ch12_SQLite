
public class SystemIn_Char {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//輸入串流
		java.io.InputStream in = System.in;
		char c;
		try{
			System.out.print("請輸入字串: ");
			while((c = (char) in.read()) > 0){
				System.out.print(c);
				
				if(c == '\n'){
					System.out.print("請輸入字串: ");
				}
					
			}
		}catch(java.io.IOException ex){
			ex.printStackTrace();
		}

	}

}
