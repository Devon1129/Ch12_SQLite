
public class SystemIn_Char {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//��J��y
		java.io.InputStream in = System.in;
		char c;
		try{
			System.out.print("�п�J�r��: ");
			while((c = (char) in.read()) > 0){
				System.out.print(c);
				
				if(c == '\n'){
					System.out.print("�п�J�r��: ");
				}
					
			}
		}catch(java.io.IOException ex){
			ex.printStackTrace();
		}

	}

}
