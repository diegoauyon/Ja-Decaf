/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: BarraMenu.java,v 1.1 2007/03/22 16:13:00 p-marque Exp $ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Todos los derechos reservados 2005 
 *
 * Proyecto Cupi2 
 * Ejercicio: n10_mapaCiudad 
 * Autor: Mario S�nchez - 27/09/2005 
 * Modificada por: Daniel Romero - 31/01/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package interfaz;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/**
 * Esta es la barra que contiene los men�s de la aplicaci�n
 */
public class BarraMenu extends JMenuBar implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
	// Constantes para Menu Archivo
    private static final String NUEVO = "Nuevo";
    private static final String ABRIR = "Abrir";
    private static final String GUARDAR = "Guardar";
    private static final String GUARDAR_COMO = "GuardarComo";
    private static final String SALIR = "Salir";
    
    // Constantes para Menu Gramatica
    private static final String GOLDP = "GoldP";
    private static final String ANTLR = "ANTlr";
    
 // Constantes para Menu Opciondx
    private static final String OPCIONES = "Opciones";
    private static final String ACERCA = "Acerca";
    private static final String CORRER = "Correr";
    private static final String LIMPIAR1 = "Limpiar1";
    private static final String LIMPIAR2 = "Limpiar2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private IDecaf principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

      // Menu Archivo
    private JMenu menuArchivo;
    private JMenuItem itemNuevo;
    private JMenuItem itemAbrir;
    private JMenuItem itemGuardar;
    private JMenuItem itemSalvarComo;
    private JMenuItem itemSalir;
    
      // Menu Gramatica
    
    private JMenu menuGramatica;
    private JRadioButtonMenuItem itemANT;
    private JRadioButtonMenuItem itemGoldP;
    private JMenuItem escogerGoldP;
    
    // Menu Opciones
    
   private JMenu menuOpciones,submenu;
   private JMenuItem itemOp;
   private JMenuItem itemAcercaDe;
   private JMenuItem itemCorrer;
   private JMenuItem itemLimpiar1;
   private JMenuItem itemLimpiar2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la barra de men�
     * @param ip Es una referencia a la clase principal de la interfaz
     */
    public BarraMenu( IDecaf ip )
    {
        principal = ip;
        
        this.setBackground(Color.gray);

        // #############################################
        // Menu Archivo
        // #############################################
        
        menuArchivo = new JMenu( "Archivo" );
        menuArchivo.setMnemonic( KeyEvent.VK_A );
        menuArchivo.getAccessibleContext().setAccessibleDescription(
                "Abre opciones de archivo");
        add( menuArchivo );

        itemNuevo = new JMenuItem( "Nuevo" );
        itemNuevo.setActionCommand( NUEVO );
        itemNuevo.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_N, ActionEvent.CTRL_MASK ) );
        itemNuevo.setMnemonic( KeyEvent.VK_N );
        itemNuevo.addActionListener( this );
        menuArchivo.add( itemNuevo );

        itemAbrir = new JMenuItem( "Abrir" );
        itemAbrir.setActionCommand( ABRIR );
        itemAbrir.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_Q, ActionEvent.CTRL_MASK ) );
        itemAbrir.setMnemonic( KeyEvent.VK_Q );
        itemAbrir.addActionListener( this );
        menuArchivo.add( itemAbrir );

        itemGuardar = new JMenuItem( "Guardar" );
        itemGuardar.setActionCommand( GUARDAR );
        itemGuardar.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_S, ActionEvent.CTRL_MASK ) );
        itemGuardar.setMnemonic( KeyEvent.VK_S );
        itemGuardar.addActionListener( this );
        menuArchivo.add( itemGuardar );

        itemSalvarComo = new JMenuItem( "Guardar Como" );
        itemSalvarComo.setActionCommand( GUARDAR_COMO );
        itemSalvarComo.setMnemonic( KeyEvent.VK_W );
        itemSalvarComo.addActionListener( this );
        menuArchivo.add( itemSalvarComo );

        menuArchivo.addSeparator( );

        itemSalir = new JMenuItem( "Salir" , new ImageIcon("libs/data/salir1.png"));
        itemSalir.setActionCommand( SALIR );
        itemSalir.addActionListener( this );
        menuArchivo.add( itemSalir );
        
        // #############################################
        // Menu Gramatica
        // #############################################
        
        menuGramatica = new JMenu( "Gramática" );
        menuGramatica.setMnemonic( KeyEvent.VK_G );
        menuGramatica.getAccessibleContext().setAccessibleDescription(
                "Escoge el motor para procesar el código");
        add( menuGramatica );

      //a group of radio button menu items
        
        ButtonGroup group = new ButtonGroup();
        itemGoldP = new JRadioButtonMenuItem("Gold Parser");
        itemGoldP.setSelected(true);
        itemGoldP.setActionCommand( GOLDP );
        itemGoldP.addActionListener( this );
        group.add(itemGoldP);
        menuGramatica.add(itemGoldP);

        itemANT = new JRadioButtonMenuItem("ANTlr");
        itemANT.setActionCommand( ANTLR );
        itemANT.addActionListener(this);
        group.add(itemANT);
        menuGramatica.add(itemANT);
        
        menuGramatica.addSeparator();
        
        
        // #############################################
        // Menu Opciones
        // #############################################
        
        menuOpciones = new JMenu( "Opciones" );
        menuOpciones.setMnemonic( KeyEvent.VK_O );
        menuOpciones.getAccessibleContext().setAccessibleDescription(
                "Acceder a Opciones del IDE");
        add( menuOpciones );

        itemOp = new JMenuItem( "Opciones de GUI" );
        itemOp.setActionCommand( OPCIONES );
        itemOp.addActionListener( this );
        
        
        
        menuOpciones.add( itemOp );
        
        menuOpciones.addSeparator();
        
        submenu = new JMenu("Limpiar");
        submenu.setIcon(new ImageIcon("libs/data/limpiar.png"));

        
        itemLimpiar1 = new JMenuItem( " Limpiar Terminal");
        itemLimpiar1.setActionCommand(LIMPIAR1);
        itemLimpiar1.addActionListener(this);
        
        itemLimpiar2 = new JMenuItem( " Limpiar Notas");
        itemLimpiar2.setActionCommand(LIMPIAR2);
        itemLimpiar2.addActionListener(this);
        
        submenu.add(itemLimpiar1);
        submenu.add(itemLimpiar2);
        
        menuOpciones.add(submenu);
        
        menuOpciones.addSeparator( );

        itemAcercaDe = new JMenuItem( "Acerca De", new ImageIcon("libs/data/acercade.png") );
        itemAcercaDe.setActionCommand( ACERCA );
        itemAcercaDe.addActionListener( this );
        menuOpciones.add( itemAcercaDe );
        
        menuOpciones.addSeparator( );
        
 

    }

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------

    /**
     * Ejecuta la accion que corresponde a la opcion del mene que fue seleccionada
     * @param evento Es el evento de seleccionar una opcion del mene
     */
    
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( NUEVO.equals( comando ) )
        {
            principal.reiniciar( );
        }
        else if( ABRIR.equals( comando ) )
        {
        	StringBuilder texto = principal.obtenerArchivo();
        	if (texto!= null)
        	{
        		principal.cambiarTexto(texto.toString());	
        	}
            
        }
        else if( GUARDAR.equals( comando ) )
        {
            principal.salvar( );
        }
        else if( GUARDAR_COMO.equals( comando ) )
        {
            principal.salvarComo( );
        }
        else if( SALIR.equals( comando ) )
        {
            principal.dispose( );
            
        }
        else if (GOLDP.equals(comando))
        {
        	System.out.println("GoldParser");
        	principal.cambiarMotor(GOLDP);
        }
        else if (ANTLR.equals(comando))
        {
        	System.out.println("AntLR");
        	principal.cambiarMotor(ANTLR);
        }
        else if (ACERCA.equals(comando))
        {
        	AcercaDe aD = new AcercaDe(principal);
        	
        }
        else if (LIMPIAR1.equals(comando))
        {
        	principal.limpiarTerminal1();
        	
        }
        
        else if (LIMPIAR2.equals(comando))
        {
        	principal.limpiarTerminal2();
        	
        }
        
        
    }

}
