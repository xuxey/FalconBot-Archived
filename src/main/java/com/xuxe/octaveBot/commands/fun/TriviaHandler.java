package com.xuxe.octaveBot.commands.fun;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;

class TriviaHandler
{
    TriviaInstance generate()
    {
        try
        {

            String json = Jsoup.connect("https://opentdb.com/api.php?amount=1").ignoreContentType(true).execute().body();
            JSONObject jsonObject = (JSONObject) JSONValue.parse(json);

            String arrayS = jsonObject.get("results").toString();
            arrayS = arrayS.substring(1,arrayS.length()-1);

            Gson gson = new GsonBuilder().create();
            return gson.fromJson(arrayS,TriviaInstance.class);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
class TriviaInstance //POJO away from POJO
{
    String getCategory() {
        return category;
    }

    String getType() {
        return type;
    }

    String getDifficulty() {
        return difficulty;
    }

    String getQuestion() {
        question = question.replace("&quot;","\"");//&quot;	"	Quotation Mark
        question = question.replace("&amp;","&");//&#38;	&amp;	&	Ampersand
        question = question.replace("&frasl;","/");//&#47;	&frasl;	/	Slash
        question = question.replace("&lt;","<");//&#60;	&lt;	<	Less Than Sign
        question = question.replace("&gt;",">");//&#62;	&gt;	>	Greater Than Sign
        question = question.replace("&#039;","\'");
        return question;
    }

    String getCorrect_answer() {
        return correct_answer;
    }

    String getAll_answers()
    {
        String[] all_answers_arr = new String[4];
        all_answers_arr[random] = correct_answer;
        int j=0;
        for(int i = 0; i<4; i++)
        {
            if(i==random) continue;
            all_answers_arr[i] = incorrect_answers[j];
            j++;
        }
        String all_answers = "";
        for(int i = 0; i<4; i++)
        {
            all_answers = all_answers.concat( (char)(65+i) + "." + all_answers_arr[i] + "\n");
        }
        return all_answers;
    }

    int getBrowniePoints() {
        switch (difficulty) {
            case "hard":
                browniePoints = 3;
                break;
            case "medium":
                browniePoints = 2;
                break;
            case "easy":
                browniePoints = 1;
                break;
        }
        return browniePoints;
    }

    char getCorrectAnswerLetter()
    {
        switch (random)
        {
            case 0: return 'a';
            case 1: return 'b';
            case 2: return 'c';
            case 3: return 'd';
            default:return 'x';
        }
    }

    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private String[] incorrect_answers;
    private int browniePoints = 0;
    private int random = (int) (Math.random()*3);
}
