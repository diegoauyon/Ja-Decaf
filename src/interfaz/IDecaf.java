package interfaz;

import goldengine.java.Reduction;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import main.*;



import com.Ostermiller.Syntax.HighlightedDocument;





public class IDecaf extends javax.swing.JFrame 
{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = -2452605912665547568L;
	// -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
//	protected UndoAction undoAction;
 //   protected RedoAction redoAction;
 //   protected UndoManager undo = new UndoManager();
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

	
	public Main main;
	
    /** The document holding the text being edited. */
    private HighlightedDocument document = new HighlightedDocument();
   // private AbstractDocument doc;
	private JTextPane textPane ;//= new JTextPane(document);
	private JMenuBar mb;
	private JPanel botones;
	public DefaultMutableTreeNode root,root1;
	public DefaultTreeModel tree,tree2;
	private JTextArea terminal,notas;

	//###############################################################
	private String rutaArchivo=null;
	private String ultimoDirectorio=null;
	private static String deco= "org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel";
	private String motor="GoldP";

	

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del GUI
     */
    public IDecaf() 
    {
    	
    	
        // initial set up that sets the title
        super("Decaf IDE");
       // setLocation(50, 50);
        ultimoDirectorio="Pruebas/";
        setPreferredSize(new Dimension (800,520));
       
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());        
        textPane = new JTextPane();
        textPane.setStyledDocument(document);
        textPane.setBackground(new Color(51,51,51));  
        textPane.setCaretColor(Color.WHITE);
        textPane.setEditable(true);
        textPane.setAutoscrolls(true);
        
        JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL,30,20,0,300);
        hbar.setUnitIncrement(2);
        hbar.setBlockIncrement(1);
        jp.add(hbar,BorderLayout.SOUTH);
        //jp.add(vbar,BorderLayout.EAST);
        jp.add(textPane,BorderLayout.CENTER);
        hbar.setVisible(false);
        
        // Create a scroll pane wrapped around the text pane
        JScrollPane scrollPane = new JScrollPane(jp);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        

        LineNumber lineNumber = new LineNumber( textPane );
        lineNumber.setPreferredSize(999);
        scrollPane.setRowHeaderView( lineNumber );

                
        // Add the components to the frame.
        JPanel contentPane = new JPanel(new BorderLayout());
       // contentPane.add(scrollPane, BorderLayout.CENTER);
        
        botones = new PanelBotones(this);
        contentPane.add(botones,BorderLayout.NORTH);
        
        setContentPane(contentPane);
        
        // Set up the menu bar.
        mb = new BarraMenu(this);
        setJMenuBar(mb);
        
        document.setHighlightStyle( HighlightedDocument.C_STYLE);
        
        //Set JTree
        root = new DefaultMutableTreeNode("");
        tree = new DefaultTreeModel(root);
        JTree jtree = new JTree(tree);
        jtree.setSize(50, 50);
        
                
        JScrollPane scrollTree = new JScrollPane(jtree);
        scrollTree.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        //Set JTree 2
        
        root1 = new DefaultMutableTreeNode("");
        tree2 = new DefaultTreeModel(root);
        JTree jtree2 = new JTree(tree2);
        
        JScrollPane scrollTree2 = new JScrollPane(jtree2);
        scrollTree2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        JTabbedPane tabbedPane1 = new JTabbedPane();
        ImageIcon icon3 = new ImageIcon("libs/data/arbol1.png");
        ImageIcon icon4 = new ImageIcon("libs/data/arbol2.png");
        
        
        tabbedPane1.addTab("Arbol Parser", icon3, scrollTree,
                          "Arbol despues del parseo");
        tabbedPane1.setMnemonicAt(0, KeyEvent.VK_3);
        
        tabbedPane1.addTab("Arbol Sintaxis", icon4, scrollTree2,
                "Arbol despues de la comprobacion de tipos");
        tabbedPane1.setMnemonicAt(1, KeyEvent.VK_4);
        
        
        
       // contentPane.add(jtree,BorderLayout.EAST);
        
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane,
				tabbedPane1);
		split.setDividerLocation(550);
		split.setOneTouchExpandable(true);
		
		
		terminal = new JTextArea();
		terminal.setEditable(false);
		terminal.setDisabledTextColor(textPane.getBackground());
		terminal.setBackground(textPane.getBackground());
		
		notas = new JTextArea();
		notas.setEditable(false);
		notas.setDisabledTextColor(textPane.getBackground());
		notas.setBackground(textPane.getBackground());
		
		JScrollPane scrollTer = new JScrollPane(terminal);
        scrollTer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        JScrollPane scrollTer1 = new JScrollPane(notas);
        scrollTer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = new ImageIcon("libs/data/bug.png");
        ImageIcon icon2 = new ImageIcon("libs/data/notas.png");
        
        
        tabbedPane.addTab("Terminal", icon, scrollTer,
                          "Area en donde se despliegan los errores");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
        
        tabbedPane.addTab("Notas", icon2, scrollTer1,
                "Area en donde se despliegan construccion del parser");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		JSplitPane split2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,split,tabbedPane);
		split2.setDividerLocation(700);
		split2.setOneTouchExpandable(true);

		contentPane.add(split2, BorderLayout.CENTER);
		
		
		//Create the status area.
        JPanel statusPane = new JPanel(new GridLayout(1, 1));
        CaretListenerLabel caretListenerLabel =
                new CaretListenerLabel("Caret !!! Status");
        statusPane.add(caretListenerLabel);
		//TODO
		//PanelAbajo pa= new PanelAbajo(textPane);
		contentPane.add(statusPane,BorderLayout.SOUTH);
		textPane.addCaretListener(caretListenerLabel);
        
        // Make the window so that it can close the application
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
               dispose();
            }
            public void windowActivated(WindowEvent e) {
                // focus magic
                textPane.requestFocus();
            }
        });
        

    /**    if (document instanceof AbstractDocument) {
            doc = (AbstractDocument)document;
            
        } else {
            System.err.println("Text pane's document isn't an AbstractDocument!");
            System.exit(-1);
        }
        //Start watching for undoable edits and caret changes.
        doc.addUndoableEditListener(new MyUndoableEditListener());
//        doc.addDocumentListener(new MyDocumentListener());
       **/ 
        
        this.setIconImage(new ImageIcon("libs/data/logo.png").getImage());
      
        // Put the initial text into the text pane and
        // set it's initial coloring style.
        initDocument();

        // put it all together and show it.
        pack();
        
        


        

    }
    
    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------

	
  //This listens for and reports caret movements.
    protected class CaretListenerLabel extends JLabel
                                       implements CaretListener {
        /**
		 * 
		 */
		private static final long serialVersionUID = 2143745171579883941L;

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
    /**
     * Initialize the document with some default text and set
     * they initial type of syntax highlighting.
     */
    private void initDocument() {
        String initString = (
            "/**\n " +
            " * Bienvenidos a Super IDE para Decaf.\n" +
            " */\n" 
        );
        cambiarTexto(initString);
    }

    
    public void limpiarTerminal1()
    {
    	terminal.setText("");
    }
    
    public void limpiarTerminal2()
    {
    	notas.setText("");
    }
    
    /**
     * Obtiene el archivo que se quiere abrir y lo convierte en un string
     * @return StringBuilder clase que conserva las características del texto leido
     */
	public StringBuilder obtenerArchivo() {

        File file; JFileChooser chooser = new JFileChooser("Pruebas/");
        int status = chooser.showOpenDialog(null);
        if(status !=JFileChooser.APPROVE_OPTION){
           // System.out.println("Elija archivo porfavor");
            return null;
        }
        file = chooser.getSelectedFile();
        cambiarRutaArchivoUsado( file.getPath());
        
        
        
            StringBuilder text = new StringBuilder();
            String NL = System.getProperty("line.separator");
            Scanner scanner = null;
			try {
				scanner = new Scanner(file, "UTF-8");
			    while (scanner.hasNextLine()){
	                text.append(scanner.nextLine() + NL);
			    }
			    
			} 
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			finally 
			{
				scanner.close();
			}
			
         
       
        return text;
	}
	
	
	public void guardarArchivo(String path) throws IOException
	{
		FileWriter fichero = null;
        PrintWriter pw = null;

        		try {
					fichero = new FileWriter(path);
					pw = new PrintWriter(fichero);
	        		pw.write(textPane.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally
				{
				    if (null != fichero)
						fichero.close();

			           
				}
        	
        		

        
        
       
	}
	
    /**
     * Salva la composición en el archivo del que se había cargado o donde se había salvado la última vez. <br>
     * Si se trata de una composición nueva y no se ha salvado entonces se pregunta el nombre del archivo donde se salvará. <br>
     * <b>post: </b> Se salvó la composición.
     */
    public void salvar( )
    {
        String nombreArchivo = rutaArchivo;
        if( nombreArchivo == null )
        {
            salvarComo( );
        }
        else
        {
            try
            {
                guardarArchivo(rutaArchivo);
            }
            catch( IOException e )
            {
                JOptionPane.showMessageDialog( this, "Hubo problemas salvando el archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }

    }
	
	   public void salvarComo( )
	    {
	        JFileChooser fc = new JFileChooser( ultimoDirectorio );
	        fc.setDialogTitle( "Salvar como" );
	        fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
	        fc.setMultiSelectionEnabled( false );

	        boolean termine = false;

	        int resultado = fc.showSaveDialog( this );

	        while( !termine )
	        {
	            if( resultado == JFileChooser.APPROVE_OPTION )
	            {
	                File seleccionado = fc.getSelectedFile( );
	                ultimoDirectorio = seleccionado.getParentFile( ).getAbsolutePath( );
	                cambiarRutaArchivoUsado( seleccionado.getPath());

	                int respuesta = JOptionPane.YES_OPTION;

	                // Si el archivo ya existe hay que pedir confirmación para
	                // sobreescribirlo
	                if( seleccionado.exists( ) )
	                {
	                    respuesta = JOptionPane.showConfirmDialog( this, "¿Desea sobreescribir el archivo seleccionado?", "Sobrescribir", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE );
	                }

	                // Si la respuesta fue positiva (o si no fue necesario hacer la
	                // pregunta) se graba el archivo
	                if( respuesta == JOptionPane.YES_OPTION )
	                {
	                    try
	                    {
	                        guardarArchivo( seleccionado.getAbsolutePath( ) );
	                        termine = true;
	                    }
	                    catch( IOException e )
	                    {
	                        JOptionPane.showMessageDialog( this, "Hubo problemas salvando el archivo:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
	                    }
	                }
	                else
	                {
	                    resultado = fc.showSaveDialog( this );
	                }
	            }
	            else
	            {
	                termine = true;
	            }
	        }
	    }
	
	   
	   /**
	     * Este método solicita una confirmación para realizar una acción que haría que el trabajo se perdiera. <br>
	     * Presenta una ventana con las opciones "Si","No" y "Cancelar". <br>
	     * Si se selecciona "Si", entonces se salva el archivo actual. <br>
	     * Si se selecciona "No", el archivo no se salva y se retorna true <br>
	     * Si se selecciona "Cancelar", el archivo no se salva pero se retorna false para que la acción no se realice.
	     * @return Retorna true si la acción se debe realizar; retorna false en caso contrario.
	     */
	    private boolean pedirConfirmacion( )
	    {
	        boolean cerrar = true;

	        int respuesta = JOptionPane.showConfirmDialog( this, "¿Desea guardar el archivo actual antes de continuar?", "Guardar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE );

	        if( respuesta == JOptionPane.YES_OPTION )
	        {
	            salvar( );
	        }

	        else if( respuesta == JOptionPane.NO_OPTION )
	        {
	            // No hace nada
	        }

	        else if( respuesta == JOptionPane.CANCEL_OPTION )
	        {
	            cerrar = false;
	        }

	        return cerrar;
	    }
	/**
	 * Cambiar texto del JTextPane
	 * @param cadena texto a escribir
	 */
	public void cambiarTexto(String cadena)
	{
		textPane.setText(cadena);
	}
	
	
	public void cambiarRutaArchivoUsado(String cadena)
	{
		rutaArchivo = cadena;
	}
	
	public String darRutaArchivo()
	{
		return rutaArchivo;
	}

	public static void crearArbol(DefaultTreeModel tree, DefaultMutableTreeNode root, Reduction reduccion){
        
        for(int i=0; i<reduccion.getTokenCount(); i++)
        {
            if(reduccion.getToken(i).getKind()==0){
                 Reduction red = (Reduction)reduccion.getToken(i).getData();
               /**String rule = red.getParentRule().definition();
                String rules_d = red.getParentRule().getText();
                String rule_name = reduccion.getToken(i).getName();
                String re  = reduccion.getParentRule().name();
                System.out.println(" + "+rule_name+ " + ."+rule+"." + "   ->   "+rules_d+ "   !!!!! "+ re);
            	*/
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(reduccion.getToken(i).getName());
                crearArbol(tree, child, (Reduction)reduccion.getToken(i).getData());
                tree.insertNodeInto(child, root, i);
             }
    
            else{
                String token_name = reduccion.getToken(i).getName();
                String token_lexeme = (String)reduccion.getToken(i).getData();
                String txt_in_leaf = "";
                 if(token_name.equals(token_lexeme) == true && token_name.equals("id")==false){
                    txt_in_leaf = token_name+"";
                }
                else{
                     txt_in_leaf = token_name + " = "+ token_lexeme;
                }
                //System.out.println(" - "+txt_in_leaf);
                DefaultMutableTreeNode leaf = new DefaultMutableTreeNode(txt_in_leaf);
                 tree.insertNodeInto(leaf, root, i);
            }
        }
        
        
    }

	 /**
     * Cierra la aplicación, pero antes pregunta si se quiere salvar la composición actual y se salva si el usuario así lo requiere.
     */
    public void dispose( )
    {
        boolean cerrar = true;
            setVisible( true );
            cerrar = pedirConfirmacion( );

        if( cerrar == true )
        {
            super.dispose( );
            System.exit(0);
        }
        else
        {
        	
        }
    }
    
    public String darMotor()
    {
    	return motor;
    }
    
    public void cambiarMotor(String motorA)
    {
    	motor=motorA;
    }
	
    
    public void corrrer()
    {
    	tree.reload();
		Main ma = new Main(darRutaArchivo(),this);
    	if (ma!= null && darMotor().equalsIgnoreCase("GoldP"))
    		if (ma.huboErrores()==false)
    		{
    		crearArbol(tree,root,ma.parser.currentReduction() );
    		tree.reload();
    		}
    		else
    			imprimir(" (Método correr) ERROR Compilación: No se pudo crear el árbol porque hubo errores \n",1);
    			
    	else
    	{
    		imprimir("Error Implementación: Todavía no se ha implementado otros Motores",1);
    	}
    }
    
    /**
     * Reinicia la composición después de pedir una confirmación. <br>
     * <b>post: </b> Se reinició la composición
     */
    public void reiniciar( )
    {
        boolean reiniciar = true;
         reiniciar = pedirConfirmacion( );

        if( reiniciar )
        {
            textPane.setText("");
            
        }
    }
    
    public void imprimir(String texto, int tab)
    {
    	if (tab == 1)
    	{
    		terminal.append(texto + "\n");
    		
    	}
    	else if (tab == 2)
    	{
    		notas.append(texto + "\n");
    	}
    	
    }
    
 //---------------------------------------------------------------------------
 //---------------------------------------------------------------------------   
   
    /**
     * Run the gui.
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        // create the demo
    	
    	JFrame.setDefaultLookAndFeelDecorated(true);
    	JDialog.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(deco);
			
				} catch (Exception e) {
					System.out
							.println("Substance Raven Graphite failed to initialize");
				}
				IDecaf w = new IDecaf();
				w.setVisible(true);
				
			}
		});
       // TransparentBackground tb = new TransparentBackground(frame);
    }
}
