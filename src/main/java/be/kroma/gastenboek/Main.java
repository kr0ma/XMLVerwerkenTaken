package be.kroma.gastenboek;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

class Main {
	private static final Path PATH = Paths.get("/dataXML/gastenboek.xml");

	public static void main(String[] args) {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			Element gastenboek = document.createElement("gastenboek");
			try (Scanner scanner = new Scanner(System.in)) {
				System.out.print("naam: ");
				for (String naam; !"stop".equalsIgnoreCase(naam = scanner.nextLine());) {

					Element entry = document.createElement("entry");
					Element naamElement = document.createElement("naam");
					naamElement.setTextContent(naam);

					Element opmerkingElement = document.createElement("opmerking");
					System.out.print("opmerking: ");
					String opmerking = scanner.nextLine();
					opmerkingElement.setTextContent(opmerking);

					entry.appendChild(naamElement);
					entry.appendChild(opmerkingElement);
					gastenboek.appendChild(entry);
					System.out.print("naam: ");
				}
			}
			document.appendChild(gastenboek);
			DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			DOMImplementationLS implementation = (DOMImplementationLS) registry.getDOMImplementation("LS");
			LSSerializer serializer = implementation.createLSSerializer();
			serializer.getDomConfig().setParameter("format-pretty-print", true);
			LSOutput output = implementation.createLSOutput();
			try (Writer bufferedWriter = Files.newBufferedWriter(PATH)) {
				output.setCharacterStream(bufferedWriter);
				serializer.write(document, output);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
