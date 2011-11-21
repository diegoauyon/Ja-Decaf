package interfaz.XML;

import javax.swing.*;


import java.io.*;
import javax.swing.tree.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import org.apache.xerces.parsers.*;

public class XMLTreeView{

       private SAXTreeBuilder saxTree = null;
       private static String file = "";
       private JTree tree;
       
      
       
       @SuppressWarnings("static-access")
	public XMLTreeView(String texto){
    	   	  this.file = texto;
                
              DefaultMutableTreeNode root = new DefaultMutableTreeNode("Codigo Intermedio");
//              DefaultMutableTreeNode top = new DefaultMutableTreeNode("XML Document"); 
              
              saxTree = new SAXTreeBuilder(root); 
              
              try {             
              SAXParser saxParser = new SAXParser();
              saxParser.setContentHandler(saxTree);
              saxParser.parse(new InputSource(new FileInputStream(file)));
              }catch(Exception ex){
                 root.add(new DefaultMutableTreeNode(ex.getMessage()));
              }
              tree = new JTree(saxTree.getTree());
              
              //scrollPane = new JScrollPane(tree);
                     
              
        } 
       
       public JTree getTree()
       {
    	   return tree;
       }
        
     
}
class SAXTreeBuilder extends DefaultHandler{
       
       private DefaultMutableTreeNode currentNode = null;
       private DefaultMutableTreeNode previousNode = null;
       private DefaultMutableTreeNode rootNode = null;

       public SAXTreeBuilder(DefaultMutableTreeNode root){
              rootNode = root;
       }
       public void startDocument(){
              currentNode = rootNode;
       }
       public void endDocument(){
       }
       public void characters(char[] data,int start,int end){
              String str = new String(data,start,end);              
              if (!str.equals("") && Character.isLetter(str.charAt(0)))
                  currentNode.add(new DefaultMutableTreeNode(str));           
       }
       public void startElement(String uri,String qName,String lName,Attributes atts){
              previousNode = currentNode;
              currentNode = new DefaultMutableTreeNode(lName);
              // Add attributes as child nodes //
              attachAttributeList(currentNode,atts);
              previousNode.add(currentNode);              
       }
       public void endElement(String uri,String qName,String lName){
              if (currentNode.getUserObject().equals(lName))
                  currentNode = (DefaultMutableTreeNode)currentNode.getParent();              
       }
       public DefaultMutableTreeNode getTree(){
              return rootNode;
       }
       
       private void attachAttributeList(DefaultMutableTreeNode node,Attributes atts){
               for (int i=0;i<atts.getLength();i++){
                    String name = atts.getLocalName(i);
                    String value = atts.getValue(name);
                    
                    node.add(new DefaultMutableTreeNode(name + " = " + value));
               }
       }
       
}
//games.xml
/*
<?xml version="1.0"?>

<games>
  <game genre="rpg">XML Invaders</game>
  <game genre="rpg">A Node in the XPath</game>
  <game genre="rpg">XPath Racers</game>
</games>
*/

           
         
