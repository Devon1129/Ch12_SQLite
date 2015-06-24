
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
	 * 基本 type(8個)傳 value，它會 copy一份值傳入，(call by value)(擁有自己的 value)；
	 * 其他 type傳 reference，指向 instance(、object)。(call by reference)，
	 * (大家指向相同的地址，修改時大家都受影響)。
	 *** 注意 String 是特別 type，它是 reference type ，但是參數傳遞時，是傳 value。
	 */
}

