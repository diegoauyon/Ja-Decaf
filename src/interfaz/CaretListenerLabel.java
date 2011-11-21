package interfaz;

import java.awt.Rectangle;

import javax.swing.JLabel;

import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

//This listens for and reports caret movements.
public class CaretListenerLabel extends JLabel
                                   implements CaretListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2143745171579883941L;
	private JTextComponent textPane;

	public CaretListenerLabel(String label, JTextComponent textPane) 
	{
        super(label);
        this.textPane = textPane;
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

						Rectangle caretCoords = textPane.modelToView(dot);
                        int x=((caretCoords.x-3)/7)+1;
                        int y=((caretCoords.y-3)/15)+1;
                        int lenght= textPane.getText().length();
                        
                          //Convert it to view coordinates.
                        setText(" Columna: " +x  + "  Fila:" +y
                        		+ "   Caracteres: "+ dot + "/" +lenght
                        		+ "    Ver locacion = ["
                                + caretCoords.x + ", "
                                + caretCoords.y + "]"
                                
                                );
                    } catch (BadLocationException ble) {
                        setText("Caret: Posicion Texto: " + dot );
                    }
                } else if (dot < mark) {
                    setText("Seleccion desde: " + dot
                            + " a " + mark );
                } else {
                    setText("Seleccion desde: " + mark
                            + " a " + dot );
                }
            }
        });
    }
}

