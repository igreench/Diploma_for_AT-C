package servlets;

import javax.json.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by Olcha on 14.06.2016.
 */
@WebServlet("/request")
public class RequestSOAP extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        JsonReader reader = Json.createReader(req.getInputStream());
//        JsonArray array = reader.readArray();
//        reader.close();
//
//        String[] data = new String[array.size() - 1];
//        for (int i = 0; i < array.size() - 1; i++){
//            data[i] = array.get(i).toString();
//            System.out.println(data[i]);
//        }
//        JsonArray jsonArray = array.getJsonArray(50);
//        System.out.println(jsonArray.getJsonObject(1).getString("appDocSerial"));
//        resp.setContentType("text/html; charset=UTF-8");
//        PrintWriter pw = resp.getWriter();
//        pw.write("wtf");

    }
}
