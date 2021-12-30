import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonWorker {
    private static String data;
    public static List<Users> UsersList = new ArrayList<>();

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
            o++;
            }

        i++;
        }

        //LogWork();

    }
    public static void LogWork(){
        JSONObject finaljsonObject = new JSONObject(); // {}
        JSONObject jsonObject = new JSONObject();;
        JSONArray jsonArray = new JSONArray();    // []
        // Objektummal kezdünk, belerakom az Authort-t
        // Utána tömbként kell hozáadnom a logolásokat
        List<String> done = new ArrayList(UsersList.size());


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
            fileWriter.write(finaljsonObject.toString());
            fileWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

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



    public static void MessageBox(String Message, String titleBar){
        JOptionPane.showMessageDialog(null, Message, titleBar, JOptionPane.ERROR_MESSAGE);
    }

}
