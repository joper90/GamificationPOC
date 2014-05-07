package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.GameStatics;

public class DisplayUser extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DisplayUser()
	{
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("Working");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String save = request.getParameter("checkbox");
		
		System.out.println(username);
		System.out.println(password);
		System.out.println(save);
		
		RequestDispatcher newView = null;
		
		if (username.equals("tibco-admin") && password.equals(GameStatics.DEFAULT_PASSWORD))
		{
			newView = request.getRequestDispatcher("myinfo.html");
		}
		else
		{
			newView = request.getRequestDispatcher("error.jsp");
		}
		request.setAttribute("json", "RANDOMJSONSTRING");
		newView.forward(request, response);
	}

}
