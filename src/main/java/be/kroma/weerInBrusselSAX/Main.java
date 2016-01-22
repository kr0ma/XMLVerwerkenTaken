package be.kroma.weerInBrusselSAX;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

class Main {

	// ------------------------------------------------------------------------------
	// 1.3 WEER IN BRUSSEL 2
	// ------------------------------------------------------------------------------
	private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=Belgium,be&mode=xml&APPID=4d1d9a4e4cfb4a28395195cdec62f14f";

	public static void main(String[] args) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(URL, new TemperatuurHandler());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
