package be.kroma.weerInBrusselDOM;

import java.math.BigDecimal;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

class Main {

	private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=Belgium,be&mode=xml&APPID=4d1d9a4e4cfb4a28395195cdec62f14f";
	private static final BigDecimal KELVIN_NAAR_CELSIUS = BigDecimal.valueOf(273.15);

	public static void main(String args[]) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(URL);
			// Weer in Brussel 3
			/*
			NodeList nodeList = document.getElementsByTagName("temperature");
			Element element = (Element) nodeList.item(0);
			BigDecimal kelvin = new BigDecimal(element.getAttribute("value"));
			BigDecimal celcius = kelvin.subtract(KELVIN_NAAR_CELSIUS);
			System.out.println(celcius);
			*/
			
			// Weer in Brussel 4 (XPATH)
			XPathFactory xPathFactory = XPathFactory.newInstance();
			XPath xPath = xPathFactory.newXPath();
			Element element = (Element) xPath.evaluate("//temperature", document, XPathConstants.NODE);
			if (element == null){
				System.out.println("element niet gevonden");
			} else {
				BigDecimal kelvin = new BigDecimal(element.getAttribute("value"));
				BigDecimal celcius = kelvin.subtract(KELVIN_NAAR_CELSIUS);
				System.out.println(celcius);
			}
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}