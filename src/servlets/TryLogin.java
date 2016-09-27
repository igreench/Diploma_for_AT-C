package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Olcha on 24.06.2016.
 */
@WebServlet("/login")
public class TryLogin extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Получаем Json и читаем его
        //JsonReader reader = Json.createReader(req.getInputStream());
        //JsonObject newJson = reader.readObject();

        // reader.close();
        String[] s = req.getParameterValues("json[]");

        for (String a: s) {
            System.out.print(a + " ");
        }

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print("Hey pidor");

    }
}
