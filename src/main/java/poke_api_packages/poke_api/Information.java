package poke_api_packages.poke_api;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

// responsible for grabbing the information from either the online api or the local db (cache)
@Slf4j
public class Information {

    private static String get(BufferedReader bufferedReader) {
        try {
            StringBuilder stringBuilder = new StringBuilder();

            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
                stringBuilder.append(System.lineSeparator());
            }

            bufferedReader.close();
            return stringBuilder.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String fromInternet(String targetURL) {
        String str;
        // if CACHE is on, and exists in db, get it from db
        if (Client.CACHE && ((str = Database.getInstance().getByUrl(targetURL)) != null)) {
            return str;
        }

        try {
            URL url = new URL(targetURL);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.addRequestProperty("User-Agent", "Mozilla/4.76");

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            str = Information.get(bufferedReader);

            if (Client.CACHE) {
                if (Database.getInstance().insert(targetURL, str)) {
                    log.info("Saved to db: {}, {}", targetURL);
                } else {
                    log.error("Error saving to db");
                }
            }

            return str;
        } catch (Exception e) {
            //e.printStackTrace();
            if ((str = Database.getInstance().getByUrl(targetURL)) != null) {
                log.error("URL request failed, falling back to cache: {}", e.getMessage());
                return str;
            }

            log.info("Couldn't reach the API: {}", e.getMessage());
            return "";
        }
    }
}
