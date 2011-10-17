package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class GenXml 
{
	
	private String ruta="intermedio/intermedio.xml";
	public GenXml(LinkedList<org.jdom.Content> elementos) {
		 
		  try {
	 
			Element company = new Element("Program");
			Document doc = new Document(company);
			doc.setRootElement(company);
			
			for (org.jdom.Content element: elementos)
			{
				doc.getRootElement().addContent(element);
			}
	 
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
	 
			System.out.println("Xml Generado");
		  } catch (IOException io) {
			System.out.println(io.getMessage());
		  }
		}

}
