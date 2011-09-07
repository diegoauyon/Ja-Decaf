package interfaz;

import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;



public class PanelAbajo extends JPanel 
{
	private String numeroLineas;
	private String numeroColumnas;
	private JLabel texto;
	private int numColumna,numFila,numColumnasTotales,sel;
	private String newline = "\n";
	private JTextPane panel;
	public CaretListenerLabel caret;
	public PanelAbajo (JTextPane jtp)
	{
		panel = jtp;
		this.setLayout(new GridLayout(1, 1));
        caret = new CaretListenerLabel("Caret Status");
        panel.add(caret);
		//texto = new JLabel("Prueba");
		//add(texto);
	}
	
	
	//This listens for and reports caret movements.
    protected class CaretListenerLabel extends JLabel
                                       implements CaretListener {
        public CaretListenerLabel(String label) {
            super(label);
        }

        //Might not be invoked from the event dispatch thread.
        public void caretUpdate(CaretEvent e) {
            displaySelectionInfo(e.getDot(), e.getMark());
        }

        //This method can be invoked from any thread.  It 
        //invokes the setText and modelToView methods, which 
        //must run on the event dispatch thread. We use
        //invokeLater to schedule the code for execution
        //on the event dispatch thread.
        protected void displaySelectionInfo(final int dot,
                                            final int mark) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    if (dot == mark) {  // no selection
                        try {
                            Rectangle caretCoords = panel.modelToView(dot);
                            //Convert it to view coordinates.
                            setText("caret: text position: " + dot
                                    + ", view location = ["
                                    + caretCoords.x + ", "
                                    + caretCoords.y + "]"
                                    + newline);
                        } catch (BadLocationException ble) {
                            setText("caret: text position: " + dot + newline);
                        }
                    } else if (dot < mark) {
                        setText("selection from: " + dot
                                + " to " + mark + newline);
                    } else {
                        setText("selection from: " + mark
                                + " to " + dot + newline);
                    }
                }
            });
        }
    }
	
	

}
