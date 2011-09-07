package interfaz;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

public class AcercaDe 
{
	public AcercaDe(Component padre)
	{
		JDialog dialogo= dameNuevoJDialog(padre);
		
        dialogo.setTitle("Acerca De");
        dialogo.setIconImage(new ImageIcon("libs/data/cafe.png").getImage());
        dialogo.setModal(true);
        dialogo.getContentPane().add(new JTextField(20));
        dialogo.pack();
        dialogo.setVisible(true);
        
	}
	
	


	public JDialog dameNuevoJDialog (Component padre)
	{
		JDialog dialogo;
    	// Obtenemos la ventana que contiene al botón que se ha
    	// pulsado.
    	// e.getSource() nos devuelve ese botón.
    	// SwingUtilities.getWindowAncestor() nos devuelve la ventana
    	// que contiene ese botón, es decir, en este caso el
    	// JFrame principal.
        Window ventanaPadre = SwingUtilities.getWindowAncestor(padre);

        // Codigo general para crear el JDialog en función del
        // tipo de ventana padre.
        if (ventanaPadre instanceof Frame)
        {
            dialogo = new JDialog((Frame) ventanaPadre);
        }
        else if (ventanaPadre instanceof Dialog)
        {
            dialogo = new JDialog((Dialog) ventanaPadre);
        }
        else
        {
            dialogo = new JDialog();
        }

        return dialogo;
	}
	

}
