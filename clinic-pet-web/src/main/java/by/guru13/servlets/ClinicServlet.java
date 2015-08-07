package by.guru13.servlets;

import by.guru13.lessons.clinic.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by ASUS on 04.08.2015.
 */
public class ClinicServlet extends HttpServlet {
    final List<Pet> pets = new CopyOnWriteArrayList<Pet>();
    final List<Client> clients = new CopyOnWriteArrayList<Client>();
    String requestSearch;

    /**
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.append(
                "<!DOCTYPE html>" +
                        "<html>"  +
                        "<head>" +
                        "      <title>Clinic Pets</title>" +
                        "</head>" +
                        "<body>" +
                        "       <form action='" + req.getContextPath() + "/' method='post'>" +
                        "           Client's name : <input type='text' name = 'clientName'>" +
                        "</br>" +
                        "<p></p>" +
                        "           Pet's name : <input type='text' name = 'petName'>" +
                        "</br>" +
                        "<p></p>" +
                        "            <input type = 'radio' name = 'types' value = 'dog' checked> Dog" +
                        "            <input type = 'radio' name = 'types' value = 'cat'> Cat" +
                        "            <input type = 'radio' name = 'types' value = 'parrot'> Parrot" +
                        "</br>" +
                        "<p></p>" +
                        "           <input type='submit' value='Submit'>" +
                        "</br>" +
                                "</form>" +
                        this.viewClients() +
                        "</br>" +
                        "<p></p>" +
                        "       <form action='" + req.getContextPath() + "/' method='post'>" +
                        "           Search pet by client's name: <input type = 'text' name = 'search'>" +
                        "           <input type='submit' value='Search'>" +
                        "       </form>" +
                        this.searchPets() +
                        "</body>" +
                        "</html>"
        );
        writer.flush();
    }

    /**
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pet pet = null;
        if (req.getParameter("types") != null) {
            if (req.getParameter("types").equals("dog")) {
                pet = new Dog(req.getParameter("petName"));
            } else if (req.getParameter("types").equals("cat")) {
                pet = new Cat(req.getParameter("petName"));
            } else if (req.getParameter("types").equals("parrot")) {
                pet = new Parot(req.getParameter("petName"));
            }
            this.clients.add(new Client(req.getParameter("clientName"), pet));
        }

//        this.pets.add(new Dog(req.getParameter("name")));

        requestSearch = req.getParameter("search");
        doGet(req, resp);
    }
    private String viewClients(){
        StringBuilder sb = new StringBuilder();
        sb.append("<p>Clients</p>");
        sb.append("<table style = 'border : 1px solid black' cellpadding = '10'  cellspacing = '0px'>");
        sb.append("<tr>");
        sb.append("<td style = 'border : 1px solid black'>Client</td>");
        sb.append("<td style = 'border : 1px solid black'>Pet</td>");
        sb.append("<td style = 'border : 1px solid black'>Type</td>");
        sb.append("<tr>");
        for (Client client : this.clients){
            if (!client.getId().isEmpty() && !client.getPet().getName().isEmpty()) {
                sb.append("<tr>");
                sb.append("<td style = 'border : 1px solid black'>").append(client.getId()).append("</td>");
                sb.append("<td style = 'border : 1px solid black'>").append(client.getPet().getName()).append("</td>");
                sb.append("<td style = 'border : 1px solid black'>").append(client.getPet().getClass().getSimpleName()).append("</td>");
                sb.append("</tr>");
            }
        }
        sb.append("</table>");
        return sb.toString();
    }
    private String searchPets(){
        StringBuilder sb = new StringBuilder();
        sb.append("<p>Pets</p>");
        sb.append("<table style = 'border : 1px solid black' cellpadding = '10'  cellspacing = '0px'>");
        sb.append("<tr>");
        sb.append("<td style = 'border : 1px solid black'>Pet</td>");
        sb.append("<td style = 'border : 1px solid black'>Type</td>");
        sb.append("<tr>");
        for (Client client : this.clients){
            if (client.getId().equals(requestSearch) && !client.getId().isEmpty() && !client.getPet().getName().isEmpty()) {
                sb.append("<tr>");
                sb.append("<td style = 'border : 1px solid black'>").append(client.getPet().getName()).append("</td>");
                sb.append("<td style = 'border : 1px solid black'>").append(client.getPet().getClass().getSimpleName()).append("</td>");
                sb.append("</tr>");
            }
        }
        sb.append("</table>");
        return sb.toString();
    }

}
