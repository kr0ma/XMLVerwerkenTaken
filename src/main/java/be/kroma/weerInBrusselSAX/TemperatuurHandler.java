package be.kroma.weerInBrusselSAX;

import java.math.BigDecimal;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class TemperatuurHandler extends DefaultHandler {
	private static final BigDecimal KELVIN_NAAR_CELSIUS = BigDecimal.valueOf(273.15);
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("temperature".equalsIgnoreCase(qName)){
			BigDecimal kelvin = new BigDecimal(attributes.getValue("value"));
			BigDecimal celcius = kelvin.subtract(KELVIN_NAAR_CELSIUS);
			System.out.println(celcius);
		}
	}

}
