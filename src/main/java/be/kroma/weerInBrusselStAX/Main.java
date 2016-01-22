package be.kroma.weerInBrusselStAX;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

class Main {

	// ------------------------------------------------------------------------------
	// 1.2 WEER IN BRUSSEL
	// ------------------------------------------------------------------------------
	private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=Belgium,be&mode=xml&APPID=4d1d9a4e4cfb4a28395195cdec62f14f";
	private static final BigDecimal KELVIN_NAAR_CELSIUS = BigDecimal.valueOf(273.15);

	public static void main(String[] args) {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try (InputStream stream = new URL(URL).openStream();
				InputStream bufferedStream = new BufferedInputStream(stream)) {
			XMLStreamReader reader = null;
			try {
				reader = factory.createXMLStreamReader(bufferedStream);
				while (reader.hasNext()) {
					if (reader.next() == XMLStreamReader.START_ELEMENT
							&& "temperature".equalsIgnoreCase(reader.getLocalName())) {
						BigDecimal kelvin = new BigDecimal(reader.getAttributeValue(null, "value"));
						BigDecimal celsius = kelvin.subtract(KELVIN_NAAR_CELSIUS);
						System.out.println(celsius);
						break;
					}
				}
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (XMLStreamException ex) {
						ex.printStackTrace();
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
