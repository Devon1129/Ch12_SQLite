
public class Example {
	String str=new String("good"); 
	char[] ch={'a','b','c'}; 
	
	public static void main(String args[]){ 
		Example ex=new Example(); 
		ex.change(ex.str,ex.ch); 
		System.out.print(ex.str+" and "); 
		System.out.println(ex.ch);
					
		int myInt = 77;
		ex.change_ver2(ex.str, myInt);
		System.out.println("test 2:");
		System.out.print(ex.str+" and "); 
		System.out.print(myInt); 
	}	 

	public void change(String str,char ch[]){ 
		str="test ok";
        ch[0]='g'; 
	} 
	
	public void change_ver2(String str, int a){
		str = "hello";
		a = 100;
	}
	/*
	 * �� type(8��)�� value�A���| copy�@���ȶǤJ�A(call by value)(�֦��ۤv�� value)�F
	 * ��L type�� reference�A���V instance(�Bobject)�C(call by reference)�A
	 * (�j�a���V�ۦP���a�}�A�ק�ɤj�a�����v�T)�C
	 *** �`�N String �O�S�O type�A���O reference type �A���O�Ѽƶǻ��ɡA�O�� value�C
	 */
}

