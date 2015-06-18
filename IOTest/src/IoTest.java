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
//		System.out.println("�R�O�C��J" + args.length + "��");
//        if(args.length > 0){
//            for(int i = 0; i < args.length; i++){
//                System.out.println("args[" + i + "]=" + args[i]);
//            }
//        }
//        else{
//            System.out.print("�R�O�C��J�S������!");
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
        //��X��Ƭy�J�w�ľ���A��X��t�μзǿ�X
        String s = "�r����X��Ƭy BufferedWriter ����!";
        bw.write(s, 0, s.length()); //��write ��k�Ns ��X
        bw.flush(); //�� flush��k�M��
	}
	
	public static void BufTest2() throws IOException{
		//�e���T�Ӧr����X
		BufferedWriter bs = new BufferedWriter(new OutputStreamWriter(System.out));
        //��X��Ƭy�J�w�ľ���A��X�� test.txt�ɮ�
        String s = "�r����X��Ƭy BufferedWrite����!";
        bs.write(s, 3 ,s.length() - 3); //�� write��k�N s��X
        bs.flush(); //�� flush��k�M��
	}
	
	public static void BufWrtTxtTest() throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));
	    //��X��Ƭy�J�w�ľ���A��X�� test.txt�ɮפ�
	    String s = "�r����X��Ƭy BufferedWriter����!";
	    bw.write(s, 0, s.length()); // �� write��k�Ns ��X
	    bw.close(); //�� close��k��X��������
	}
	
	public static void BufKeyboardTest() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // �r����J��Ƭy�J�w�ľ����O���� br
        String line;
        System.out.print("�п�J�@�Ӧr��:");
        System.out.flush(); // �M��System.out�������
        line = br.readLine(); //Ū�J����w���r�� line
        System.out.println(line);
	}
	
	public static void BufRedFileTest() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        //�q�ɮ׿�J��Ƭy�J�w�ľ����O���� br
        String line;
        System.out.println("�q�ɮפ�Ū�J����Ʀp�U");
        line = br.readLine(); //�N brŪ�J����w���r��line
        System.out.println(line);

	}
}
