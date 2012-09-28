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
			
		}
		
		
		return desc;
	}

}
