import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.text.html.HTMLEditorKit;


public class winui {

	public JFrame frame;
	private JTextArea textArea;
	private JButton btnNewButton;
	private JEditorPane editorPane;
	private JScrollPane scrollPane;
	private HTMLEditorKit kit;
	private myAct eAct;
	
	public winui() {
		initialize();
        eAct = new myAct();
        this.textArea.getInputMap().put( KeyStroke.getKeyStroke( "ENTER" ),
                "doEnterAction" );
        this.textArea.getActionMap().put( "doEnterAction", eAct );
	}
	
	class myAct extends AbstractAction {
	    public myAct() {
	        
	    }
	    public void actionPerformed(ActionEvent e) {
			Codebot.respond(textArea.getText());
			textArea.setText("");
	    }
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		System.out.println("initializing window");
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.setBounds(6, 212, 318, 60);
		frame.getContentPane().add(textArea);
		
		btnNewButton = new JButton("Enter");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Codebot.respond(textArea.getText());
				textArea.setText("");
			}
		} );
		
		btnNewButton.setBounds(327, 212, 117, 60);
		frame.getContentPane().add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(336, 6, 108, 194);
		frame.getContentPane().add(panel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 318, 194);
		frame.getContentPane().add(scrollPane);
		
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		scrollPane.setViewportView(editorPane);
		System.out.println("window initialized");
		frame.setVisible(true);
	}
	
	public void upCon(String str){
		//System.out.println("in upCon");
		String cap1 = editorPane.getText().substring(0,38);
		String cap2 = editorPane.getText().substring(editorPane.getText().length()-16);
		String text = editorPane.getText().substring(39, editorPane.getText().length()-16) +"<br>" + str;
		//System.out.println("updated window");
		text = cap1+text+cap2;
		//System.out.println(text);
		editorPane.setText(text);
		//System.out.println("finished upCon");
	}
}
