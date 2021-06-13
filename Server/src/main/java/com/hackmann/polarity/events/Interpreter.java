package com.hackmann.polarity.events;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hackmann.polarity.Globals;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Interpreter {
    private Gson gson = new Gson();

    public Interpreter() {

    }

    public int getLength(byte[] bytes) {
        int headerLength = 0;

        for (int i = 0; i < Globals.HEADER_SIZE; i++) {
            if ((char) bytes[i] == '-') {
                headerLength = i;
                break;
            }
        }

        String lengthString = new String(Arrays.copyOfRange(bytes, 0, headerLength));

        return lengthString.equals("") ? 0 : Integer.parseInt(lengthString);
    }

    public String bytesToText(byte[] bytes) {
        return new String(bytes);
    }

    public byte[] textToBytes(String string) {
        return string.getBytes();
    }

    public JSONObject stringToJSON(String string) {
        return new JSONObject(string);
    }

    public Event jsonToEvent(String json) {
        JSONObject jsonObject = this.stringToJSON(json);
        try {
            System.out.println(jsonObject.getString("className"));
            return (Event) this.gson.fromJson(json, Class.forName("com.hackmann.polarity.events.serverEvents."+jsonObject.getString("className")));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String eventToJson(Event event) {
        return this.gson.toJson(event);
    }

    public Event bytesToEvent(byte[] bytes){
        return this.jsonToEvent(this.bytesToText(bytes));
    }

    public byte[] eventToBytes(Event event){
        return this.textToBytes(this.eventToJson(event));
    }
}
