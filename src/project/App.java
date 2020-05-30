package project;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class App {
    public static HashMap request(String command) {
        JsonNode jsonNode = null;
        HashMap<String,String> resp = new HashMap<>();
        try {
            //TABLE1
            /*HttpResponse<String> response = Unirest.post("https://ujotr0oxumy70qr-db202003262116.adb.eu-frankfurt-1.oraclecloudapps.com/ords/admin/_/sql")
                    .header("content-type", "application/sql")
                    .header("authorization", "Basic QURNSU46T3JhY2xlQ2xvdWQzMjE=")
                    .body(command)
                    .asString();*/
            //EHUNTER DATABASE
            HttpResponse<String> response = Unirest.post("https://nsynwle8h0h9vhx-ehunter.adb.eu-frankfurt-1.oraclecloudapps.com/ords/admin/_/sql")
                    .header("Content-Type", "application/sql")
                    .header("Authorization", "Basic QURNSU46VUVNcHJveWVjdG8y")
                    .body(command)
                    .asString();

            ObjectMapper objectMapper = new ObjectMapper();
            jsonNode = objectMapper.readTree(response.getBody());
        }catch(Exception e){
            System.out.println(e);
        }
        String key = "";
        String value = "";
        String key2 = "exito";
        String value2 = "false";

        if (jsonNode.at("/items/0/statementType").asText().equals("query")) {
            //System.out.println("this is a query");
            key = "data";
            value = jsonNode.at("/items/0/resultSet/items").toString();
            value2 = "true";
        } else {
            if (jsonNode.at("/items/0/result").toString().equals("0")) {
                //System.out.println("this is a command with error");
                key = "info";
                value = "No se han producido cambios en la base de datos." +
                        "\nRazón: " + jsonNode.at("/items/0/response/0").asText();
            } else {
                //System.out.println("this is a command without error");
                key = "info";
                value = jsonNode.at("/items/0/response/0").asText();
                value2 = "true";
            }
        }
        resp.put(key,value);
        resp.put(key2,value2);
        return resp;
    }

    public static ArrayList<ArrayList> stringToArray(String s){
        JsonNode jsonNode = null;
        //s = "[{\"id\":1,\"nombre\":\"Pepe\",\"sueldo\":235.4},{\"id\":2,\"nombre\":\"Paco\",\"sueldo\":119},{\"id\":3,\"nombre\":\"Pedro\",\"sueldo\":226.3}]";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonNode = objectMapper.readTree(s);
        }catch (Exception e){}
        ArrayList<String> fields = new ArrayList<>();
        ArrayList<ArrayList> values = new ArrayList<>();
        traverse(jsonNode,fields,values);
        //System.out.println(fields);
        //System.out.println(values);
        ArrayList<ArrayList> response = new ArrayList<>();
        response.add(fields);
        response.add(values);
        return response;
    }
    public static void traverse(JsonNode root, ArrayList<String> fields, ArrayList<ArrayList> values){

        if(root.isObject()){
            Iterator<String> fieldNames = root.fieldNames();
            ArrayList<String> row = new ArrayList<>();
            while(fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                row.add(root.get(fieldName).asText());
                if(!fields.contains(fieldName)){
                    fields.add(fieldName);
                }
            }
            values.add(row);
        } else if(root.isArray()){
            ArrayNode arrayNode = (ArrayNode) root;
            for(int i = 0; i < arrayNode.size(); i++) {
                JsonNode arrayElement = arrayNode.get(i);
                traverse(arrayElement,fields,values);
            }
        }
    }
}
