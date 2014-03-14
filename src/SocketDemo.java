
public class SocketDemo {
	static Thread t1 = new Thread(){
		ServerRun server = new ServerRun();

	};
	static Thread t2 = new Thread(){
		ClientRun client = new ClientRun();

	};
	
	public static void run(){
		t1.start();
		t2.start();
	}
	public static void main(String[] args) {
		run();
	}

}
