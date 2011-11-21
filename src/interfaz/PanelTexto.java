package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;



import com.Ostermiller.Syntax.HighlightedDocument;

public class PanelTexto extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4117005437589641517L;
	private JTextPane textPane = new JTextPane();;
	private HighlightedDocument document = new HighlightedDocument();
	
	
	public PanelTexto()
	{
		JScrollPane scroll = new JScrollPane(this.textPane);
	    this.textPane.setEditable(true);
		document.setHighlightStyle( HighlightedDocument.C_STYLE);
        
                
        textPane.setStyledDocument(document);
        textPane.setBackground(new Color(51,51,51));  
        textPane.setCaretColor(Color.WHITE);
        textPane.setEditable(true);
        textPane.setAutoscrolls(true);
        
        /**
        JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL,30,20,0,300);
        hbar.setUnitIncrement(2);
        hbar.setBlockIncrement(1);
        this.add(hbar,BorderLayout.SOUTH);
        //jp.add(vbar,BorderLayout.EAST);
        this.add(textPane,BorderLayout.CENTER);
        hbar.setVisible(false);
        */
        
        // Create a scroll pane wrapped around the text pane
      //  JScrollPane scrollPane = new JScrollPane(textPane);
       // scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        

        LineNumber lineNumber = new LineNumber( textPane );
        lineNumber.setPreferredSize(999);
        scroll.setRowHeaderView( lineNumber );
        
       
        
        this.setLayout(new BorderLayout());
        this.add(scroll);
        
        //textPane.addCaretListener(caretListenerLabel);
        
      

	}
	
	public String getText()
	{
		return this.textPane.getText();
	}
	
	public void setText(String texto)
	{
		this.textPane.setText(texto);
		
	}
	
	public JTextPane getTextPane()
	{
		return this.textPane;
	}
	
	
	 
	 
	
}
