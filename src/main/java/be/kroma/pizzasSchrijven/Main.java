package be.kroma.pizzasSchrijven;

import java.io.Writer;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

class Main {
	
	// ------------------------------------------------------------------------------
	// 1.1 PIZZAS SCHRIJVEN 
	// ------------------------------------------------------------------------------
	private static final Path PATH = Paths.get("/dataXML/taken/pizzas.xml");
	public static void main(String[] args) {
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try (Scanner scanner = new Scanner(System.in);Writer bufferedWriter = Files.newBufferedWriter(PATH)){
			XMLStreamWriter writer = null;
			try {
				writer = factory.createXMLStreamWriter(bufferedWriter);
				writer.writeStartDocument();
				writer.writeStartElement("pizzas");
				System.out.print("Geef pizza naam: ");
				for (String naam; !"stop".equalsIgnoreCase(naam = scanner.next());){
					System.out.print("Geef prijs: ");
					BigDecimal prijs = scanner.nextBigDecimal();
					writer.writeStartElement("pizza");
					writer.writeAttribute("prijs", prijs.toString());
					writer.writeStartElement("naam");
					writer.writeCharacters(naam);
					writer.writeEndElement();
					writer.writeEndElement();
					System.out.print("Geef pizza naam: ");
				}
				writer.writeEndElement();
				writer.writeEndDocument();
			}finally{
				if (writer != null){
					try {
						writer.close();
					} catch (XMLStreamException ex) {
						ex.printStackTrace();
					}
				}
			}
			
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
