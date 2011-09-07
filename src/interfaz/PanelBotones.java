package interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import main.Main;



/**
 * Este es el panel donde se encuentran los botones y controles de la aplicaci�n
 */
public class PanelBotones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String ABRIR = "Abrir";

    private static final String GUARDAR = "Guardar";

    private static final String NUEVO = "Nuevo";
    
    private static final String CORRER = "Correr";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    private IDecaf principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------


    private JButton botonAbrir;

    private JButton botonGuardar;

    private JButton botonNuevo;

    private JButton botonCorrer;

   
    private JComboBox cbbTipoLinea;

    /**
    private JButton botonColorLinea;

    private JButton botonColorFondo;

    private JButton botonBorrar;

    private ButtonGroup grupo;
    
    */

    private JLabel etiquetaColorLinea;

    private JLabel etiquetaColorFondo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param ip Es una referencia a la clase principal de la interfaz
     */
    public PanelBotones( IDecaf ip )
    {
        principal = ip;

        //setBorder( new TitledBorder( "" ) );
        this.setBackground(Color.black);

        botonAbrir = new JButton( new ImageIcon( "./libs/data/abrir.png" ) );
        botonAbrir.setPreferredSize( new Dimension( 40, 40 ) );
        botonAbrir.setToolTipText( "Abrir" );
        botonAbrir.setActionCommand(ABRIR);
        botonAbrir.addActionListener(this);

        botonGuardar = new JButton( new ImageIcon( "./libs/data/guardar.png" ) );
        botonGuardar.setPreferredSize( new Dimension( 40, 40 ) );
        botonGuardar.setToolTipText( "Guardar" );
        botonGuardar.setActionCommand(GUARDAR);
        botonGuardar.addActionListener(this);

        botonNuevo = new JButton( new ImageIcon( "./libs/data/nuevo.png" ) );
        botonNuevo.setPreferredSize( new Dimension( 40, 40 ) );
        botonNuevo.setToolTipText( "Nuevo" );
        botonNuevo.setActionCommand(NUEVO);
        botonNuevo.addActionListener(this);

        botonCorrer = new JButton( new ImageIcon( "./libs/data/correr.png" ) );
        botonCorrer.setPreferredSize( new Dimension( 40, 40 ) );
        botonCorrer.setToolTipText( "Run" );
        botonCorrer.setActionCommand(CORRER);
        botonCorrer.addActionListener(this);

        /**
        botonColorFondo = new JButton( " " );
        botonColorFondo.setActionCommand( ABRIR );
        botonColorFondo.setBackground( Color.RED );
        botonColorFondo.addActionListener( this );

        botonColorLinea = new JButton( " " );
        botonColorLinea.setActionCommand( GUARDAR );
        botonColorLinea.setBackground( Color.BLACK );
        botonColorLinea.addActionListener( this );
*/

        /**
        botonBorrar = new JButton("");
        //if (i != null) 
        botonBorrar.setIcon(new ImageIcon( "./data/del.gif" ) );
        botonBorrar.setContentAreaFilled(false);
        botonBorrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonBorrar.setFocusPainted(false);
        botonBorrar.setBorderPainted(false);
        botonBorrar.setHorizontalAlignment(JButton.LEFT);
        botonBorrar.setIconTextGap(5);
        botonBorrar.setActionCommand( NUEVO );
        botonBorrar.addActionListener( this );
        botonBorrar.setToolTipText( "Borrar" );
        botonBorrar.addMouseListener(new JDUnderlinedText(botonBorrar));
*/
      

   //     etiquetaColorLinea = new JLabel( "Color L�nea" );
   //     etiquetaColorFondo = new JLabel( "Color Fondo" );
/**  grupo = new ButtonGroup( );
        grupo.add( botonAbrir );
        grupo.add( botonGuardar );
        grupo.add( botonNuevo );
        grupo.add( botonCorrer );
        */

      //  setLayout( new GridBagLayout( ) );
      //  GridBagConstraints gbc = new GridBagConstraints( 0, 0, 1, 1, 0, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 3, 3, 3, 3 ), 0, 0 );

        add( botonNuevo );
        add( botonAbrir );
        add( botonGuardar);
   //     gbc.gridx = -3;
        //add( botonBorrar);

        //     gbc.gridx = 0;
        //gbc.gridy = 2;
        

        //gbc.gridx = 1;
        

        //gbc.gridx = 0;
        //gbc.gridy = 3;
        add( botonCorrer );

        //gbc.gridx = 1;

        //gbc.gridx = 0;
        //gbc.gridy = 4;

        //gbc.gridx = 1;

        //gbc.gridx = 0;
        //gbc.gridy = 5;
        //gbc.gridwidth = 2;


        // Botones de Colores
        //gbc.weighty = 0;
        //gbc.gridx = 0;
        //gbc.anchor = GridBagConstraints.SOUTH;
        //gbc.gridy = 7;

     //   add( botonColorLinea );
        //gbc.gridy = 9;
        //gbc.gridx = 0;
   //     add( botonColorFondo );

        // Etiquetas
        //gbc.weighty = 0;
        //gbc.anchor = GridBagConstraints.SOUTH;

        //gbc.gridy = 6;
        //gbc.gridx = 0;

   //     add( etiquetaColorLinea);

        //gbc.gridy = 8;
        //gbc.gridx = 0;
    //    add( etiquetaColorFondo);

 
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    
 
    /**
     * Es el m�todo que se llama cuando se hace click sobre un bot�n
     * @param evento Es el evento del click sobre un bot�n- evento!=null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( GUARDAR.equals( comando ) )
        {
         /**   
         Color colorLinea = JColorChooser.showDialog( this, "Color de la l�nea", botonColorLinea.getBackground( ) );
            if( colorLinea != null )
                botonColorLinea.setBackground( colorLinea );
                
                */
        	principal.salvar();
        }
        else if( ABRIR.equals( comando ) )
        {
        	/**
            Color colorFondo = JColorChooser.showDialog( this, "Color del fondo", botonColorFondo.getBackground( ) );
            if( colorFondo != null )
                botonColorFondo.setBackground( colorFondo );
                */
        	StringBuilder texto = principal.obtenerArchivo();
        	if (texto!= null)
        	{
        		principal.cambiarTexto(texto.toString());	
        	}
            
        }
        else if( NUEVO.equals( comando ) )
        {
        	principal.reiniciar();
        }
        else if (CORRER.equals(comando))
        {
        	//principal.guardarArchivo(false);
        	String ruta = principal.darRutaArchivo();
        	if (ruta!=null)
        	{
        		if (!ruta.equalsIgnoreCase(""))
        		{
        			principal.corrrer();
        		}
        		
        	}
        	else
        	{
        		principal.imprimir("ERROR ARCHIVO: No hay ruta, Grabe el archivo",1);
        		principal.salvarComo();
        		principal.corrrer();
        		
        	}
        	
        }
    }

}