/*
 * This file is part of the programmer editor demo
 * Copyright (C) 2005 Stephen Ostermiller
 * http://ostermiller.org/contact.pl?regarding=Syntax+Highlighting
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * See COPYING.TXT for details.
 */
package com.Ostermiller.Syntax;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

class TokenStyles {
	private TokenStyles() { } // disable constructor
	
	/**
	 * A hash table containing the text styles. Simple attribute sets are hashed
	 * by name (String)
	 */
	private static HashMap styles = new HashMap();

	/**
	 * Create the styles and place them in the hash table.
	 */
	static {
		Color maroon = new Color(0xB03060);
	//	Color darkBlue = new Color(0x000080);
		Color darkGreen = Color.GREEN.darker();
		Color darkPurple = new Color(0xA020F0).darker();
		Color background = new Color(41,38,33);
		Color text = new Color(204,204,204);
		Color id= new Color(135,206,235);
		Color signos = new Color(255,229,0);
		Color text2 = new Color(152,251,152);
		Color num = new Color(255,168,35);

		addStyle("body", background, Color.WHITE, false, false);
		addStyle("tag", background, signos, true, false);
		addStyle("endtag", background, signos, false, false);
		addStyle("reference", background, text, false, false);
		addStyle("name", background, signos, true, false);
		addStyle("value", background, num, false, true);
		addStyle("text", background, text, true, false);
		addStyle("reservedWord", background, id, false, false);
		addStyle("identifier", background, text, false, false);
		addStyle("literal", background, num, false, false);
		addStyle("separator", background, signos, false, false);
		addStyle("operator", background, signos, true, false);
		addStyle("comment", background, darkGreen, false, false);
		addStyle("preprocessor", background, darkPurple, false, false);
		addStyle("whitespace", background, text, false, false);
		addStyle("error", background, Color.RED, false, false);
		addStyle("unknown", background, Color.ORANGE, false, false);
		addStyle("grayedOut", background, Color.GRAY, false, false);
	}
	
	private static void addStyle(String name, Color bg, Color fg, boolean bold,
			boolean italic) {
		SimpleAttributeSet style = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style, "Monospaced");
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setBackground(style, bg);
		StyleConstants.setForeground(style, fg);
		StyleConstants.setBold(style, bold);
		StyleConstants.setItalic(style, italic);
		styles.put(name, style);
	}

	/**
	 * Retrieve the style for the given type of token.
	 * 
	 * @param styleName
	 *            the label for the type of text ("tag" for example) or null if
	 *            the styleName is not known.
	 * @return the style
	 */
	public static AttributeSet getStyle(String styleName) {
		return (AttributeSet) styles.get(styleName);
	}
}
