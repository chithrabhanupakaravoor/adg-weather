package com.adg.weather;


public class WeatherCode {
	
    int code;
	
    public WeatherCode() {
        code = 0;
    }
	
    public WeatherCode(int code) {
        this.code = code;
    }
	
    public int getDrawableIconSmall() {
        int draw = 0;
        String desc = "";
		
        switch (code) {
        case 395:
            desc = "Moderate or heavy snow in area with thunder";
            draw = R.drawable.snow64;
            break;

        case 392:
            desc = "Patchy light snow in area with thunder";
            draw = R.drawable.lightsnow64;
            break;

        case 389:
            desc = "Moderate or heavy rain in area with thunder";
            draw = R.drawable.thunderstorms64;
            break;

        case 386:
            desc = "Patchy light rain in area with thunder";
            draw = R.drawable.thunderstorms64;
            break;

        case 377:
            desc = "Moderate or heavy showers of ice pellets";
            draw = R.drawable.snow64;
            break;

        case 374:
            desc = "Light showers of ice pellets";
            draw = R.drawable.lightsnow64;
            break;

        case 371:
            desc = "Moderate or heavy snow showers";
            draw = R.drawable.snow64;
            break;

        case 368:
            desc = "Light snow showers";
            draw = R.drawable.lightsnow64;
            break;

        case 365:
            desc = "Moderate or heavy sleet showers";
            draw = R.drawable.showers64;
            break;

        case 362:
            desc = "Light sleet showers";
            draw = R.drawable.lightshowers64;
            break;

        case 359:
            desc = "Torrential rain shower";
            draw = R.drawable.showers64;
            break;

        case 356:
            desc = "Moderate or heavy rain shower";
            draw = R.drawable.showers64;
            break;

        case 353:
            desc = "Light rain shower";
            draw = R.drawable.lightshowers64;
            break;

        case 350:
            desc = "Ice pellets";
            draw = R.drawable.snow64;
            break;

        case 338:
            desc = "Heavy snow";
            draw = R.drawable.snow64;
            break;

        case 335:
            desc = "Patchy heavy snow";
            draw = R.drawable.snow64;
            break;

        case 332:
            desc = "Moderate snow";
            draw = R.drawable.snow64;
            break;

        case 329:
            desc = "Patchy moderate snow";
            draw = R.drawable.snow64;
            break;

        case 326:
            desc = "Light snow";
            draw = R.drawable.lightsnow64;
            break;

        case 323:
            desc = "Patchy light snow";
            draw = R.drawable.lightsnow64;
            break;

        case 320:
            desc = "Moderate or heavy sleet";
            draw = R.drawable.snow64;
            break;

        case 317:
            desc = "Light sleet";
            draw = R.drawable.lightsnow64;
            break;

        case 314:
            desc = "Moderate or Heavy freezing rain";
            draw = R.drawable.rain64;
            break;

        case 311:
            desc = "Light freezing rain";
            draw = R.drawable.lightrain64;
            break;

        case 308:
            desc = "Heavy rain";
            draw = R.drawable.rain64;
            break;

        case 305:
            desc = "Heavy rain at times";
            draw = R.drawable.rain64;
            break;

        case 302:
            desc = "Moderate rain";
            draw = R.drawable.rain64;
            break;

        case 299:
            desc = "Moderate rain at times";
            draw = R.drawable.rain64;
            break;

        case 296:
            desc = "Light rain";
            draw = R.drawable.lightrain64;
            break;

        case 293:
            desc = "Patchy light rain";
            draw = R.drawable.lightrain64;
            break;

        case 284:
            desc = "Heavy freezing drizzle";
            draw = R.drawable.showers64;
            break;

        case 281:
            desc = "Freezing drizzle";
            draw = R.drawable.showers64;
            break;

        case 266:
            desc = "Light drizzle";
            draw = R.drawable.lightshowers64;
            break;

        case 263:
            desc = "Patchy light drizzle";
            draw = R.drawable.lightshowers64;
            break;

        case 260:
            desc = "Freezing fog";
            draw = R.drawable.fog64;
            break;

        case 248:
            desc = "Fog";
            draw = R.drawable.fog64;
            break;

        case 230:
            desc = "Blizzard";
            draw = R.drawable.snow64;
            break;

        case 227:
            desc = "Blowing snow";
            draw = R.drawable.lightsnow64;
            break;

        case 200:
            desc = "Thundery outbreaks in nearby";
            draw = R.drawable.thunder64;
            break;

        case 185:
            desc = "Patchy freezing drizzle nearby";
            draw = R.drawable.lightshowers64;
            break;

        case 182:
            desc = "Patchy sleet nearby";
            draw = R.drawable.lightshowers64;
            break;

        case 179:
            desc = "Patchy snow nearby";
            draw = R.drawable.lightsnow64;
            break;

        case 176:
            desc = "Patchy rain nearby";
            draw = R.drawable.lightrain64;
            break;

        case 143:
            desc = "Mist";
            draw = R.drawable.fog64;
            break;

        case 122:
            desc = "Overcast";
            draw = R.drawable.overcast64;
            break;

        case 119:
            desc = "Cloudy";
            draw = R.drawable.cloudy64;
            break;

        case 116:
            desc = "Partly Cloudy";
            draw = R.drawable.sunnyinterval64;
            break;

        case 113:
            desc = "Clear/Sunny";
            draw = R.drawable.sunny64;
            break;

        default:
            desc = "No description available";
            break;
			
        }
		
        return draw;
    }

    public int getDrawableIcon() {
        int draw = 0;
        String desc = "";
		
        switch (code) {
        case 395:
            desc = "Moderate or heavy snow in area with thunder";
            draw = R.drawable.snow128;
            break;

        case 392:
            desc = "Patchy light snow in area with thunder";
            draw = R.drawable.lightsnow128;
            break;

        case 389:
            desc = "Moderate or heavy rain in area with thunder";
            draw = R.drawable.thunderstorms128;
            break;

        case 386:
            desc = "Patchy light rain in area with thunder";
            draw = R.drawable.thunderstorms128;
            break;

        case 377:
            desc = "Moderate or heavy showers of ice pellets";
            draw = R.drawable.snow128;
            break;

        case 374:
            desc = "Light showers of ice pellets";
            draw = R.drawable.lightsnow128;
            break;

        case 371:
            desc = "Moderate or heavy snow showers";
            draw = R.drawable.snow128;
            break;

        case 368:
            desc = "Light snow showers";
            draw = R.drawable.lightsnow128;
            break;

        case 365:
            desc = "Moderate or heavy sleet showers";
            draw = R.drawable.showers128;
            break;

        case 362:
            desc = "Light sleet showers";
            draw = R.drawable.lightshowers128;
            break;

        case 359:
            desc = "Torrential rain shower";
            draw = R.drawable.showers128;
            break;

        case 356:
            desc = "Moderate or heavy rain shower";
            draw = R.drawable.showers128;
            break;

        case 353:
            desc = "Light rain shower";
            draw = R.drawable.lightshowers128;
            break;

        case 350:
            desc = "Ice pellets";
            draw = R.drawable.snow128;
            break;

        case 338:
            desc = "Heavy snow";
            draw = R.drawable.snow128;
            break;

        case 335:
            desc = "Patchy heavy snow";
            draw = R.drawable.snow128;
            break;

        case 332:
            desc = "Moderate snow";
            draw = R.drawable.snow128;
            break;

        case 329:
            desc = "Patchy moderate snow";
            draw = R.drawable.snow128;
            break;

        case 326:
            desc = "Light snow";
            draw = R.drawable.lightsnow128;
            break;

        case 323:
            desc = "Patchy light snow";
            draw = R.drawable.lightsnow128;
            break;

        case 320:
            desc = "Moderate or heavy sleet";
            draw = R.drawable.snow128;
            break;

        case 317:
            desc = "Light sleet";
            draw = R.drawable.lightsnow128;
            break;

        case 314:
            desc = "Moderate or Heavy freezing rain";
            draw = R.drawable.rain128;
            break;

        case 311:
            desc = "Light freezing rain";
            draw = R.drawable.lightrain128;
            break;

        case 308:
            desc = "Heavy rain";
            draw = R.drawable.rain128;
            break;

        case 305:
            desc = "Heavy rain at times";
            draw = R.drawable.rain128;
            break;

        case 302:
            desc = "Moderate rain";
            draw = R.drawable.rain128;
            break;

        case 299:
            desc = "Moderate rain at times";
            draw = R.drawable.rain128;
            break;

        case 296:
            desc = "Light rain";
            draw = R.drawable.lightrain128;
            break;

        case 293:
            desc = "Patchy light rain";
            draw = R.drawable.lightrain128;
            break;

        case 284:
            desc = "Heavy freezing drizzle";
            draw = R.drawable.showers128;
            break;

        case 281:
            desc = "Freezing drizzle";
            draw = R.drawable.showers128;
            break;

        case 266:
            desc = "Light drizzle";
            draw = R.drawable.lightshowers128;
            break;

        case 263:
            desc = "Patchy light drizzle";
            draw = R.drawable.lightshowers128;
            break;

        case 260:
            desc = "Freezing fog";
            draw = R.drawable.fog128;
            break;

        case 248:
            desc = "Fog";
            draw = R.drawable.fog128;
            break;

        case 230:
            desc = "Blizzard";
            draw = R.drawable.snow128;
            break;

        case 227:
            desc = "Blowing snow";
            draw = R.drawable.lightsnow128;
            break;

        case 200:
            desc = "Thundery outbreaks in nearby";
            draw = R.drawable.thunder128;
            break;

        case 185:
            desc = "Patchy freezing drizzle nearby";
            draw = R.drawable.lightshowers128;
            break;

        case 182:
            desc = "Patchy sleet nearby";
            draw = R.drawable.lightshowers128;
            break;

        case 179:
            desc = "Patchy snow nearby";
            draw = R.drawable.lightsnow128;
            break;

        case 176:
            desc = "Patchy rain nearby";
            draw = R.drawable.lightrain128;
            break;

        case 143:
            desc = "Mist";
            draw = R.drawable.fog128;
            break;

        case 122:
            desc = "Overcast";
            draw = R.drawable.overcast128;
            break;

        case 119:
            desc = "Cloudy";
            draw = R.drawable.cloudy128;
            break;

        case 116:
            desc = "Partly Cloudy";
            draw = R.drawable.sunnyinterval128;
            break;

        case 113:
            desc = "Clear/Sunny";
            draw = R.drawable.sunny128;
            break;

        default:
            desc = "No description available";
            break;
			
        }
		
        return draw;
    }

    public String getDescription() {
        String desc = "";
		
        switch (code) {
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

        default:
            desc = "No description available";
            break;
			
        }
		
        return desc;
    }

}
