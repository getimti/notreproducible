package com.trimble.hack2017.internal;

public class enums {
	public enum Keys {

		GOOGLE("AIzaSyC-O_DZ6npRy9zfwyN8_58OS_Ry79Gdxq0"),BING("AgcKaKQPHSfDCEorQ9c7iSkC0DpcrhQDj15Ryg9D3XyQMdFqM0rjVs5MY2JjnWRG");

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
