package com.trimble.hack2017.internal;

public class enums {
	public enum Keys {

		GOOGLE("AIzaSyC-O_DZ6npRy9zfwyN8_58OS_Ry79Gdxq0"),
		BING("AgcKaKQPHSfDCEorQ9c7iSkC0DpcrhQDj15Ryg9D3XyQMdFqM0rjVs5MY2JjnWRG"),
		TW_KEY("UyMCykdR4BwcWBXB1mDubsVTQ"),
		TW_SECRET("V3tL74lFvqe4shVkSLYgW2TFOdDRTrY759ERKuN5k6r7LAfxld"),
		TW_TOKEN("1044207192-REd4d5HwcgnZdSzBCVL8AwPoiybsjk8ZdqpUXNt"), 
		TW_TOKEN_SECRET("aglyG0kY2yP3En2CUmJGMI52fnNnOxI3R5v0xSq9NRDPc"),
		EB_TOKEN("4472PMIWHTUD2J5CDRB6");

		private String value;
		Keys(String value) {
			this.value = value;
		}
		
		@Override
		public String toString() {
			return value;
		}

	}
}
