package servlets;

import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.PFApplication;
import datatypes.SData;
import dbadapter.Project;

/**
 * Class responsible for the GUI of the guest
 * 
 * @author swe.uni-due.de
 *
 */
public class SupporterGUI extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * doGet is responsible for search form
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		// Set navtype
				request.setAttribute("navtype", "Supporter");

				// Catch error if there is no page contained in the request
				String action = (request.getParameter("action") == null) ? "" : request.getParameter("action");

				// Case: Request donation form
				if (action.equals("selectProject")) {
					// Set request attributes
					request.setAttribute("pagetitle", "Donate Project");
					request.setAttribute("Pid", request.getParameter("Pid"));

					// Dispatch request to template engine
					try {
						request.getRequestDispatcher("/templates/showDonateForm.ftl").forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// Otherwise show search form
				} else {

					// Set request attributes
					request.setAttribute("pagetitle", "Search Projects");

					// Dispatch request to template engine
					try {
						request.getRequestDispatcher("/templates/SearchForProject.ftl").forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

	/**
	 * doPost manages handling of submitted forms.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		// Set attribute for navigation type.
				request.setAttribute("navtype", "Supporter");

				// Generate and show results of a search
				if (request.getParameter("action").equals("searchProject")) {
					request.setAttribute("pagetitle", "Search Projects");
					List<Project> searchedProjects = null;
					
					// Call application to request the results
					try {
						searchedProjects = PFApplication.getInstance().getProject(
								request.getParameter("PName"));

						// Dispatch results to template engine
						request.setAttribute("searchedProjects", searchedProjects);
						request.getRequestDispatcher("/templates/offersRepresentation.ftl").forward(request, response);
					} catch (Exception e1) {
						try {
							request.setAttribute("errormessage", "Database error: please contact the administator");
							request.getRequestDispatcher("/templates/error.ftl").forward(request, response);
						} catch (Exception e) {
							request.setAttribute("errormessage", "System error: please contact the administrator");
							e.printStackTrace();
						}
						e1.printStackTrace();
					}
					// Insert donate into database
				} else if (request.getParameter("action").equals("donateForProject")) {
					// Decide whether donation was successful or not
					if (PFApplication.getInstance().createDonate(request.getParameter("Pid"),
							new SData(request.getParameter("Email"), request.getParameter("PaymentInformation")),
							request.getParameter("amount")) != null) {

						// Set request attributes
						request.setAttribute("pagetitle", "Donation Successful");
						request.setAttribute("message",
								"Donation successfully created. You will receive a confirmation mail shortly");

						// Dispatch to template engine
						try {
							request.getRequestDispatcher("/templates/okRepresentation.ftl").forward(request, response);
						} catch (ServletException | IOException e) {
							e.printStackTrace();
						}

						// Catch donation error and print an error on the gui
					} else {
						request.setAttribute("pagetitle", "Donation failed");
						request.setAttribute("message",
								"Donation failed. The selected offer is no longer available for your selected parameters.");

						try {
							request.getRequestDispatcher("/templates/failInfoRepresentation.ftl").forward(request,
									response);
						} catch (ServletException | IOException e) {
							e.printStackTrace();
						}

					}	
				
			// If there is no page request, call doGet to show standard gui for
			// guest
		} else
			doGet(request, response);
	}
}
