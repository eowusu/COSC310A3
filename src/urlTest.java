import java.awt.Desktop;
import java.net.URL;


public class urlTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String query = "is+batman?";
		
		try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URL oURL = new URL("https://www.google.com/#q="+query);
            System.out.println("https://www.google.com/#q="+query);
            desktop.browse(oURL.toURI());
            }
		catch (Exception e) {
            e.printStackTrace();
            

	}

}
}
