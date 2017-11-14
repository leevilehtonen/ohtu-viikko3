package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;

import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "011120775";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";
        String courseUrl = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        String statsUrl = "https://studies.cs.helsinki.fi/ohtustats/stats";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        String courseBodyText = Request.Get(courseUrl).execute().returnContent().asString();
        String statsBodyText = Request.Get(statsUrl).execute().returnContent().asString();

        Gson mapper = new Gson();
        JsonParser parser = new JsonParser();

        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course crs = mapper.fromJson(courseBodyText, Course.class);
        JsonObject statsObj = parser.parse(statsBodyText).getAsJsonObject();

        System.out.println("Kurssi: " + crs.getName() + ", " + crs.getTerm() + "\n");
        System.out.println("opiskelijanumero: " + studentNr + "\n");
        for (Submission submission : subs) {
            String done = "";
            for (int i = 0; i < submission.getExercises().length; i++) {
                done+=submission.getExercises()[i];
                done+=" ";
            }
            System.out.println("viikko " + submission.getWeek() + ":");
            System.out.println("  tehtyjä tehtäviä yhteensä: " + submission.getExercises().length + " (maksimi "+crs.getExercises()[submission.getWeek()-1]+"), aikaa kului " + submission.getHours() + " tuntia, tehdyt tehtävät: " + done);
        }
        int done = 0;
        int tunnit = 0;
        for (int i = 0; i < subs.length; i++) {
            done += subs[i].getExercises().length;
            tunnit += subs[i].getHours();
        }
        System.out.println("\nyhteensä: " + done + " tehtävää " + tunnit + " tuntia");
        int crsHrs = 0, crsExrs = 0;
        for (int i = 0; i<statsObj.size(); i++) {
            crsHrs += statsObj.get((i+1) + "").getAsJsonObject().get("hour_total").getAsInt();
            crsExrs += statsObj.get((i+1) + "").getAsJsonObject().get("exercise_total").getAsInt();
        } 

        System.out.println("\nkurssilla yhteensä " + crsExrs+ " tehtävää " + crsHrs+ " tuntia");

    }
}