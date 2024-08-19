import java.io.*; import jakarta.servlet.http.HttpServlet; import jakarta.servlet.http.HttpServletRequest; import jakarta.servlet.http.HttpServletResponse; import jakarta.servlet.http.Cookie;
public class FirstServlet extends HttpServlet {
 public void doPost(HttpServletRequest request, HttpServletResponse response){
try{
response.setContentType("text/html"); PrintWriter out = response.getWriter(); String n=request.getParameter("userName"); out.print("Welcome "+n); Cookie ck=new Cookie("uname",n); response.addCookie(ck); out.print("<form action='servlet2' method='post'>"); out.print("<input type='submit' value='go'>"); out.print("</form>"); out.close();
 }catch(Exception e){System.out.println(e);}
 }}
