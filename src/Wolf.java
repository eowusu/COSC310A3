import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.wolfram.alpha.WAEngine;
import com.wolfram.alpha.WAException;
import com.wolfram.alpha.WAImage;
import com.wolfram.alpha.WAPlainText;
import com.wolfram.alpha.WAPod;
import com.wolfram.alpha.WAQuery;
import com.wolfram.alpha.WAQueryResult;
import com.wolfram.alpha.WASubpod;

public class Wolf {

	private static String myurl = "http://api.wolframalpha.com/v2/query?input=";
	private String charset = "UTF-8";
	//private String appid = "KH2TXA-P884RH5W7G";
	private WAEngine en;
	private WAQuery qu;

	public Wolf() {
		en = makeEngine();
	}

	private WAEngine makeEngine() {
		WAEngine en = new WAEngine();
		en.setAppID("KH2TXA-P884RH5W7G");
		en.addFormat("plaintext");
		en.addFormat("image");
		en.createQuery();
		return en;
	}

	private WAQuery makeQuery(String str) {
		qu = en.createQuery(str);
		return qu;
	}

	private void printQ(WAQuery q) {
		System.out.println(en.toURL(qu)+" STOP ");
		try {
			WAQueryResult res = en.performQuery(qu);
			System.out.println(en.toURL(qu)+" STOP ");
			if (res.isError()) {
				System.out.println("Error occured");
				  System.out.println("  error code: " + res.getErrorCode());
	                System.out.println("  error message: " + res.getErrorMessage());

			} else if (!res.isSuccess()) {
				System.out.println("Even WolframAlpha cant help you.");
			} else {
				for (WAPod p : res.getPods()) {
					if (!p.isError()) {
						System.out.println(p.getTitle());
						for (WASubpod sp : p.getSubpods()) {
							for (Object el : sp.getContents()) {								
								if (el instanceof WAPlainText) {
									System.out.println(((WAPlainText) el)
											.getText());
								} else if (el instanceof WAImage) {
									WAImage img = (WAImage) el;
									URL iurl;
									try {
										iurl = new URL(img.getURL());

										Icon ic = new ImageIcon(iurl);
										JLabel lab = new JLabel(ic);

										JFrame j = new JFrame(img.getTitle());
										j.getContentPane().add(lab);
										j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
										j.pack();
										j.setLocationRelativeTo(null);
										j.setVisible(true);
									} catch (MalformedURLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}
							}
						}
					}
				}
			}
		} catch (WAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void trythis(String str){
		WAQuery wq = makeQuery(str);
		printQ(wq);
	}
}

/*
 * private String makeQuery(String str) { try { String query =
 * URLEncoder.encode(str, charset); String qurl = url + query + "&appid=" +
 * appid; return qurl; } catch (UnsupportedEncodingException e) {
 * e.printStackTrace(); return "COULDNT MAKE QUERY"; } }
 * 
 * private void wolfCon(String url){ URLConnection con = new
 * URL(url).openConnection(); con.setRequestProperty("Accept-Charset", charset);
 * InputStream reply = con.getInputStream();
 * 
 * }
 * 
 * public getSyn(String str){ String q = makeQuery(str);
 * 
 * }
 */

