package aaa;


import java.io.*;
import java.net.*;

	public class ftpclient implements Runnable {

		static String serverip="130.30.3.55";
	    Socket cs;
	    String fname="C:\\kk.mp3";
	    byte data[]=null;

	    public ftpclient() {
	       
	        Thread thread = new Thread(this);
	        thread.start();

	    }

	    public static void main(String[] args) {	    	
	       
	        	ftpclient csmode = new ftpclient();

	    }

	    
	    public void run() {
	    	
	            try {	            	
	                cs = new Socket(serverip, 5550);
	                InputStream inp=cs.getInputStream(); 	             
	                data=new byte[8192];
	                FileOutputStream fo=new FileOutputStream(fname); 
	             while(true)
	             {
	            	 int read = 0;
	            	 
	            	 if(inp!=null)
	            	  {
	            		read= inp.read(data);	                            
	            	  }
	            	 if (read == -1) {
	                     break;
	                 }
	            	 System.out.println("客户端每次接收的字节数：====*"+read);
	            	 fo.write(data, 0, read);
	             
	             }
	             
	             fo.close();
	          
	            } catch (Exception ex1) {
	            	System.out.println("客服端出错了！！！"+ex1.toString());
	            }

}
}