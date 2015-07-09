package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import javax.servlet.http.HttpSession;  

import util.ValidateCode;
  
public class ValidateCodeServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
  
    @Override  
    protected void doGet(HttpServletRequest reqeust,  
            HttpServletResponse response) throws ServletException, IOException {  
        // ������Ӧ�����͸�ʽΪͼƬ��ʽ  
        response.setContentType("image/jpeg");  
        //��ֹͼ�񻺴档  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
          
        HttpSession session = reqeust.getSession();  
          
        ValidateCode vCode = new ValidateCode(120,40,4,100);  
        session.setAttribute("validateCode", vCode.getCode());  
        vCode.write(response.getOutputStream());  
    }  

  
}  