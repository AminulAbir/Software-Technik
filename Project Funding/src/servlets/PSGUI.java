package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.PFApplication;
import datatypes.PSData;

/**
 * Class responsible for the GUI of the Project Starter
 * 
 * @author swe.uni-due.de
 *
 */
public class PSGUI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet is responsible for create form
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// Set pagetitle and navtype
		request.setAttribute("navtype", "ProjectStarter");
		request.setAttribute("pagetitle", "Create Project");

			// Dispatch request to template engine
			try {
				request.getRequestDispatcher("/templates/enterFundingRequest.ftl").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * doPost manages handling of submitted forms.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

		// Set attribute for navigation type.
		request.setAttribute("navtype", "ProjectStarter");

			// Insert Info of Projects into database
		if (request.getParameter("action").equals("createProject")) {
			//Append parameter of request
			String Email = (String)request.getParameter("Email");
			String PaymentInformation = (String)request.getParameter("PaymentInformation");
			String EndDate = (String)request.getParameter("EndDate");
			String PName = (String)request.getParameter("PName");
			String Pdescription = (String)request.getParameter("Pdescription");
			String ListOfRewards = (String)request.getParameter("ListOfRewards");
			String FundingLimit = (String)request.getParameter("FundingLimit");
			//call application to create project
			new PFApplication().createProject(new PSData(Email, PaymentInformation), EndDate, PName, Pdescription, ListOfRewards, FundingLimit);
				
				// Dispatch message to template engine
				try {
					request.setAttribute("pagetitle", "Create Project");
					request.setAttribute("message",
							"Project successfully created. You will receive a confirmation mail shortly");
					request.getRequestDispatcher("/templates/showConfirmMake.ftl").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			
			// If there is no page request, call doGet to show standard gui for
			// ProjectStarter
		} else
			doGet(request, response);
		}
	}
