import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JsonWorker {
    private static String data;
    public static List<Users> UsersList = new ArrayList<>();
    public static List<Users> WeekList = new ArrayList<>();

    public static void DataProcessing(String jsondata) throws Exception {
        JSONObject jsonObject = new JSONObject(jsondata);
        JSONArray jsonArray = jsonObject.getJSONArray("worklog");

        int i = 0;
        while (i <= jsonObject.length()) {
            JSONObject keyObject = jsonArray.getJSONObject(i);
            JSONArray entriesArray = keyObject.getJSONArray("entries");

            int o=0;
            while(o<entriesArray.length()){
                    JSONObject logs = entriesArray.getJSONObject(o);
                    Users users = new Users(logs.get("id").toString(),logs.get("comment").toString(),logs.get("timeSpent").toString(),
                            logs.get("author").toString(),logs.get("authorFullName").toString(),logs.get("created").toString(),logs.get("startDate").toString(),
                            logs.get("updateAuthor").toString(),logs.get("updateAuthorFullName").toString(),logs.get("updated").toString());
                    UsersList.add(users);
                    WeekList.add(users);
            o++;
            }

        i++;
        }

        //LogWork();

    }
    public static void LogWork(){
        JSONObject finaljsonObject = new JSONObject(); // {}
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();    // []


            jsonObject.put("id",UsersList.get(0).getId())
                    .put("comment",UsersList.get(0).getComment())
                    .put("timeSpent",UsersList.get(0).getTimeSpent())
                    .put("authorFullName",UsersList.get(0).getAuthorFullName())
                    .put("created",UsersList.get(0).getCreated())
                    .put("startDate",UsersList.get(0).getStartDate())
                    .put("updateAuthor",UsersList.get(0).getUpdateAuthor())
                    .put("updateAuthorFullName",UsersList.get(0).getUpdateAuthorFullName())
                    .put("updated",UsersList.get(0).getUpdated());
            jsonArray.put(jsonObject);

        int i=0;int y=1;
        while (i<UsersList.size()) {
            while(y<UsersList.size()){

                if(UsersList.get(i).getAuthor().equals(UsersList.get(y).getAuthor())){
                    jsonObject=new JSONObject();
                    jsonObject.put("id",UsersList.get(y).getId())
                            .put("comment",UsersList.get(y).getComment())
                            .put("timeSpent",UsersList.get(y).getTimeSpent())
                            .put("authorFullName",UsersList.get(y).getAuthorFullName())
                            .put("created",UsersList.get(y).getCreated())
                            .put("startDate",UsersList.get(y).getStartDate())
                            .put("updateAuthor",UsersList.get(y).getUpdateAuthor())
                            .put("updateAuthorFullName",UsersList.get(y).getUpdateAuthorFullName())
                            .put("updated",UsersList.get(y).getUpdated());
                    jsonArray.put(jsonObject);
                    if(UsersList.size()==2){
                        jsonObject=new JSONObject();
                        jsonObject.put("id",UsersList.get(i).getId())
                                .put("comment",UsersList.get(i).getComment())
                                .put("timeSpent",UsersList.get(i).getTimeSpent())
                                .put("authorFullName",UsersList.get(i).getAuthorFullName())
                                .put("created",UsersList.get(i).getCreated())
                                .put("startDate",UsersList.get(i).getStartDate())
                                .put("updateAuthor",UsersList.get(i).getUpdateAuthor())
                                .put("updateAuthorFullName",UsersList.get(i).getUpdateAuthorFullName())
                                .put("updated",UsersList.get(i).getUpdated());
                        jsonArray.put(jsonObject);
                    }
                    UsersList.remove(y);

                }else{
                    y++;
                }

            }
            if(jsonArray.isEmpty()){
                jsonObject=new JSONObject();
                jsonObject.put("id",UsersList.get(i).getId())
                        .put("comment",UsersList.get(i).getComment())
                        .put("timeSpent",UsersList.get(i).getTimeSpent())
                        .put("authorFullName",UsersList.get(i).getAuthorFullName())
                        .put("created",UsersList.get(i).getCreated())
                        .put("startDate",UsersList.get(i).getStartDate())
                        .put("updateAuthor",UsersList.get(i).getUpdateAuthor())
                        .put("updateAuthorFullName",UsersList.get(i).getUpdateAuthorFullName())
                        .put("updated",UsersList.get(i).getUpdated());
                jsonArray.put(jsonObject);

            }

            finaljsonObject.put(UsersList.get(i).getAuthor(),jsonArray);
            jsonArray= new JSONArray();
            UsersList.remove(i);
            y=1;
        }

        try(FileWriter fileWriter = new FileWriter("c:\\00\\oujson.json")){
            finaljsonObject.put("Weeks",WeekSelector());
            fileWriter.write(finaljsonObject.toString());
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static JSONArray WeekSelector(){
        JSONArray jsonArray = new JSONArray();
        JSONObject finaljsonObject = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        int Iweekofyear=0;
        int Yweekofyear=0;

        jsonObject.put("id",WeekList.get(0).getId())
                .put("comment",WeekList.get(0).getComment())
                .put("timeSpent",WeekList.get(0).getTimeSpent())
                .put("authorFullName",WeekList.get(0).getAuthorFullName())
                .put("created",WeekList.get(0).getCreated())
                .put("startDate",WeekList.get(0).getStartDate())
                .put("updateAuthor",WeekList.get(0).getUpdateAuthor())
                .put("updateAuthorFullName",WeekList.get(0).getUpdateAuthorFullName())
                .put("updated",WeekList.get(0).getUpdated());
        jsonArray.put(jsonObject);

        int i=0;int y=1;
        while (i<WeekList.size()) {
            while(y<WeekList.size()){
                Iweekofyear= WeekConverter(WeekList.get(i).getStartDate());
                Yweekofyear= WeekConverter(WeekList.get(y).getStartDate());

                if(Iweekofyear==Yweekofyear){
                    jsonObject=new JSONObject();
                    jsonObject.put("id",WeekList.get(y).getId())
                            .put("comment",WeekList.get(y).getComment())
                            .put("timeSpent",WeekList.get(y).getTimeSpent())
                            .put("authorFullName",WeekList.get(y).getAuthorFullName())
                            .put("created",WeekList.get(y).getCreated())
                            .put("startDate",WeekList.get(y).getStartDate())
                            .put("updateAuthor",WeekList.get(y).getUpdateAuthor())
                            .put("updateAuthorFullName",WeekList.get(y).getUpdateAuthorFullName())
                            .put("updated",WeekList.get(y).getUpdated());
                    jsonArray.put(jsonObject);
                    if(WeekList.size()==2){
                        jsonObject=new JSONObject();
                        jsonObject.put("id",WeekList.get(i).getId())
                                .put("comment",WeekList.get(i).getComment())
                                .put("timeSpent",WeekList.get(i).getTimeSpent())
                                .put("authorFullName",WeekList.get(i).getAuthorFullName())
                                .put("created",WeekList.get(i).getCreated())
                                .put("startDate",WeekList.get(i).getStartDate())
                                .put("updateAuthor",WeekList.get(i).getUpdateAuthor())
                                .put("updateAuthorFullName",WeekList.get(i).getUpdateAuthorFullName())
                                .put("updated",WeekList.get(i).getUpdated());
                        jsonArray.put(jsonObject);
                    }
                    WeekList.remove(y);

                }else{
                    y++;
                }

            }
            if(jsonArray.isEmpty()){
                jsonObject=new JSONObject();
                jsonObject.put("id",WeekList.get(i).getId())
                        .put("comment",WeekList.get(i).getComment())
                        .put("timeSpent",WeekList.get(i).getTimeSpent())
                        .put("authorFullName",WeekList.get(i).getAuthorFullName())
                        .put("created",WeekList.get(i).getCreated())
                        .put("startDate",WeekList.get(i).getStartDate())
                        .put("updateAuthor",WeekList.get(i).getUpdateAuthor())
                        .put("updateAuthorFullName",WeekList.get(i).getUpdateAuthorFullName())
                        .put("updated",WeekList.get(i).getUpdated());
                jsonArray.put(jsonObject);

            }
            Iweekofyear= WeekConverter(WeekList.get(i).getStartDate());
            finaljsonObject.put("Week of "+ Iweekofyear,jsonArray);
            jsonArray= new JSONArray();
            WeekList.remove(i);
            y=1;
        }
        /*
        try(FileWriter fileWriter = new FileWriter("c:\\00\\weekjson.json")){
            fileWriter.write(finaljsonObject.toString());
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        jsonArray.put(finaljsonObject);
        return jsonArray;
    }

    public static String JsnReader(File filename) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(filename);
        byte[] readbyte = new byte[fileInputStream.available()];

        while (fileInputStream.read(readbyte) != -1) {
            data = new String(readbyte);
        }
        fileInputStream.close();
        return data;
    }

    public static int WeekConverter(String epoch){

        Date date = new Date(Long.parseLong(epoch));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekofyear;
        weekofyear = calendar.get(Calendar.WEEK_OF_YEAR);

      /*  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Europe/Budapest"));
        String string_date = format.format(date);*/
        return weekofyear;
    }


    public static void MessageBox(String Message, String titleBar){
        JOptionPane.showMessageDialog(null, Message, titleBar, JOptionPane.ERROR_MESSAGE);
    }

}
