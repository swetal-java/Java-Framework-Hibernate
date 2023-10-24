package servlet;

import java.io.IOException;
import java.util.Random;

import javax.persistence.Id;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.userDao;
import model.userModel;
import services.emailservices;

/**
 * Servlet implementation class userController
 */
@WebServlet("/userController")
public class userController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("register")) {
			userModel uModel = new userModel();
			uModel.setFname(request.getParameter("fname"));
			uModel.setLname(request.getParameter("lname"));
			uModel.setEmail(request.getParameter("email"));
			uModel.setAddress(request.getParameter("address"));
			uModel.setContact(Long.parseLong(request.getParameter("contact")));
			uModel.setGender(request.getParameter("gender"));
			uModel.setPassword(request.getParameter("password"));
			userDao.insertUser(uModel);
			response.sendRedirect("loginpage.jsp");
		}else if(action.equalsIgnoreCase("login")) {
			userModel um = new userModel();
			um.setEmail(request.getParameter("email"));
			um.setPassword(request.getParameter("password"));
			userModel u = userDao.loginUser(um);
			HttpSession session = request.getSession();
			session.setAttribute("data", u);
			request.getRequestDispatcher("homepage.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("updatepaswword")) {
			userModel uModel = new userModel();
			int id = Integer.parseInt(request.getParameter("id"));
			String password = request.getParameter("password");
			String cpassword = request.getParameter("cpassword");
			String npassword = request.getParameter("npassword");
			if(cpassword.equals(npassword)) {
				userDao.updatePassword(uModel,id,npassword);
				response.sendRedirect("homepage.jsp");
			}else {
				request.setAttribute("password", "Password is Incorrect");
				request.getRequestDispatcher("changepassword.jsp").forward(request, response);
			}
		}else if(action.equalsIgnoreCase("forgotpassword")) {
			response.sendRedirect("forgotpassword.jsp");
		}else if(action.equalsIgnoreCase("sendotp")) {
			String email = request.getParameter("email");
			System.out.println(email);
			boolean flag = userDao.chkEmail(email);
			if(flag == true) {
				emailservices em =  new emailservices();
				Random random = new Random();
				int num = random.nextInt(99999);
				System.out.println(num);
				em.sendMail(email, num);
				request.setAttribute("email", email);
				request.setAttribute("otp", num);
				request.getRequestDispatcher("otp-getpage.jsp").forward(request, response);
			}else {
				request.setAttribute("email", "Your Email is not Registered..");
				request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
			}
		}else if(action.equalsIgnoreCase("verifyotp")) {
			String email = request.getParameter("email");
			System.out.println(email);
			int otp1 = Integer.parseInt(request.getParameter("otp1"));
			System.out.println(otp1);
			int otp2 = Integer.parseInt(request.getParameter("otp2"));
			if(otp1 == otp2) {
				request.setAttribute("email", email);
				request.getRequestDispatcher("customer-new-password.jsp").forward(request, response);
			}
		}else if(action.equalsIgnoreCase("update-password")) {
			String email = request.getParameter("email");
			System.out.println(email);
			String cpassword = request.getParameter("cpassword");
			String npassword = request.getParameter("npassword");
			if(cpassword.equals(npassword)) {
				userDao.updatePasswordByEmail(email, cpassword);
				response.sendRedirect("loginpage.jsp");
			}
		}
	}

}
