package com.adg.weather;

public class WeatherCode {
	
	int code;
	
	public WeatherCode() {
		code = 0;
	}
	
	public WeatherCode(int code) {
		this.code = code;
	}
	
	
	public String getDescription() {
		String desc = "";
		
		switch(code) {
		case 395:
			desc = "Moderate or heavy snow in area with thunder";
			break;
		case 392:
			desc = "Patchy light snow in area with thunder";
			break;
		case 389:
			desc = "Moderate or heavy rain in area with thunder";
			break;
		case 386:
			desc = "Patchy light rain in area with thunder";
			break;
		case 377:
			desc = "Moderate or heavy showers of ice pellets";
			break;
		case 374:
			desc = "Light showers of ice pellets";
			break;
		case 371:
			desc = "Moderate or heavy snow showers";
			break;
		case 368:
			desc = "Light snow showers";
			break;
		case 365:
			desc = "Moderate or heavy sleet showers";
			break;
		case 362:
			desc = "Light sleet showers";
			break;
		case 359:
			desc = "Torrential rain shower";
			break;
		case 356:
			desc = "Moderate or heavy rain shower";
			break;
		case 353:
			desc = "Light rain shower";
			break;
		case 350:
			desc = "Ice pellets";
			break;
		case 338:
			desc = "Heavy snow";
			break;
		case 335:
			desc = "Patchy heavy snow";
			break;
		case 332:
			desc = "Moderate snow";
			break;
		case 329:
			desc = "Patchy moderate snow";
			break;
		case 326:
			desc = "Light snow";
			break;
		case 323:
			desc = "Patchy light snow";
			break;
		case 320:
			desc = "Moderate or heavy sleet";
			break;
		case 317:
			desc = "Light sleet";
			break;
		case 314:
			desc = "Moderate or Heavy freezing rain";
			break;
		case 311:
			desc = "Light freezing rain";
			break;
		case 308:
			desc = "Heavy rain";
			break;
		case 305:
			desc = "Heavy rain at times";
			break;
		case 302:
			desc = "Moderate rain";
			break;
		case 299:
			desc = "Moderate rain at times";
			break;
		case 296:
			desc = "Light rain";
			break;
		case 293:
			desc = "Patchy light rain";
			break;
		case 284:
			desc = "Heavy freezing drizzle";
			break;
		case 281:
			desc = "Freezing drizzle";
			break;
		case 266:
			desc = "Light drizzle";
			break;
		case 263:
			desc = "Patchy light drizzle";
			break;
		case 260:
			desc = "Freezing fog";
			break;
		case 248:
			desc = "Fog";
			break;
		case 230:
			desc = "Blizzard";
			break;
		case 227:
			desc = "Blowing snow";
			break;
		case 200:
			desc = "Thundery outbreaks in nearby";
			break;
		case 185:
			desc = "Patchy freezing drizzle nearby";
			break;
		case 182:
			desc = "Patchy sleet nearby";
			break;
		case 179:
			desc = "Patchy snow nearby";
			break;
		case 176:
			desc = "Patchy rain nearby";
			break;
		case 143:
			desc = "Mist";
			break;
		case 122:
			desc = "Overcast";
			break;
		case 119:
			desc = "Cloudy";
			break;
		case 116:
			desc = "Partly Cloudy";
			break;
		case 113:
			desc = "Clear/Sunny";
			break;
			
		}
		
		
		return desc;
	}

}
