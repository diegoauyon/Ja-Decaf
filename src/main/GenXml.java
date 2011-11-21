package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import tabla.Estructura;
import tabla.Simbolo;
import tabla.Tipo;

public class GenXml 
{
	
	private String ruta="intermedio/intermedio.xml";
	
	public GenXml(LinkedList<org.jdom.Content> elementos) 
	{
		 
		  try {
	 
			//Element company = new Element("Program");
			Element papa = new Element ("class");
            papa.setAttribute("type","Program");
            papa.setAttribute("package","");
            papa.setAttribute("access","public");
			//Document doc = new Document(papa);
			//doc.setRootElement(papa);
			
			for (org.jdom.Content element: elementos)
			{
				//doc.getRootElement().addContent(element);
				papa.addContent(element);
			}
			Document doc = new Document(papa);
			doc.setRootElement(papa);
			
	 
			/**
			Element staff = new Element("staff");
			staff.setAttribute(new Attribute("id", "1"));
			staff.addContent(new Element("firstname").setText("yong"));
			staff.addContent(new Element("lastname").setText("mook kim"));
			staff.addContent(new Element("nickname").setText("mkyong"));
			staff.addContent(new Element("salary").setText("199999"));
	 
			doc.getRootElement().addContent(staff);
	 
			Element staff2 = new Element("staff");
			staff2.setAttribute(new Attribute("id", "2"));
			staff2.addContent(new Element("firstname").setText("low"));
			staff2.addContent(new Element("lastname").setText("yin fong"));
			staff2.addContent(new Element("nickname").setText("fong fong"));
			staff2.addContent(new Element("salary").setText("188888"));
	 
			doc.getRootElement().addContent(staff2);
			**/
	 
			// new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();
	 
			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(ruta));
			System.out.println("#############################################");
			System.out.println("Xml Principal Generado");
		  } 
		  catch (IOException io) {
			System.out.println(io.getMessage());
		  }
	}
	
	public String darTipoJ(Tipo tipo)
	{
		//System.out.println("Tipo: " + tipo.darNombreTipo());
		if (tipo.darNombreSimple().equalsIgnoreCase("int"))
			return "I"; 
		if (tipo.darNombreSimple().equalsIgnoreCase("void"))
			return "V";
		if (tipo.darNombreSimple().equalsIgnoreCase("boolean"))
			return "Z";
		if (tipo.darNombreSimple().equalsIgnoreCase("char"))
			return "C";
		
		return tipo.darNombreSimple();
	}

	public void crearXMLEstructuras(LinkedList<Estructura> estructuras)
	{
		for (Estructura est : estructuras)
		{
			String nombre = est.darId();
			String rutaEstructura="intermedio/"+nombre+".xml";
			
			Element papasito = new Element ("class");
			papasito.setAttribute("type",nombre);
			papasito.setAttribute("package","");
			papasito.setAttribute("access","public");
			
			
			Element fields = new Element("fields");
			for ( Simbolo sim : est.darListaVar())
			{
				Element field = new Element("field");
				 if (sim.darTipo().esEstructura() == false)
                 	field.setAttribute("access","public");
                 else	
                 	field.setAttribute("access","public static");
                field.setAttribute("name",sim.darId());
                field.setAttribute("type",""+darTipoJ(sim.darTipo() ));
                field.setAttribute("array",""+sim.darTipo().esArreglo());
                field.setAttribute("estructure",""+ sim.darTipo().esEstructura());
                fields.setAttribute("size",""+sim.darTipo().darTamanioTipo());
                fields.addContent(field);
			}
			papasito.addContent(fields);
			Document doc = new Document(papasito);
			doc.setRootElement(papasito);
			
			try
			{
				XMLOutputter xmlOutput = new XMLOutputter();
				 
				// display nice nice
				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(doc, new FileWriter(rutaEstructura));
		 
				System.out.println("Xml Estructura: " + nombre+ "  Generado");
				
				
			}
			catch (IOException io)
			{
				System.out.println(io.getMessage());
			}
		}
	
	}

}
