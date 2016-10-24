package com.wt.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wt.po.User;
import com.wt.po.UserType;
import com.wt.service.UserService;
import com.util.Md5Utils;
import com.util.TextUtils;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String action = request.getParameter("action");
		if ("user".equals(action)) {
			doUserEdit(request, response);
		} else if ("password".equals(action)) {
			doPasswordEdit(request, response);
		}
	}

	private void doPasswordEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se = request.getSession();
		User user = (User) se.getAttribute("user");

		User one = new User();
		populatePassword(request, one);

		String msg = validate(one);
		if (TextUtils.isEmpty(msg)) {
			if (Md5Utils.md5(one.getPasswordOld()).equals(user.getPassword())) {
				UserService userService = UserService.getInstance();
				one.setId(user.getId());
				one.setPassword(Md5Utils.md5(one.getPassword()));
				userService.updateUser(one);

				// 更新Session中User
				user.setPassword(one.getPassword());

				request.getRequestDispatcher("/user/main.jsp")
						.forward(request, response);
			} else {
				msg = "原密码不正确";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/user/UserPassword_Edit.jsp")
						.forward(request, response);
			}
		} else {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/user/UserPassword_Edit.jsp")
					.forward(request, response);
		}
	}

	private void doUserEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession se = request.getSession();
		User user = (User) se.getAttribute("user");

		User one = new User();
		populate(request, one);

		UserService userService = UserService.getInstance();
		one.setId(user.getId());
		userService.updateUser(one);

		// 更新Session中User
		User dbUser = userService.findUser(one);
		se.setAttribute("user", dbUser);

		request.getRequestDispatcher("/user/UserManageInfo.jsp").forward(
				request, response);
	}

	private void populate(HttpServletRequest request, User user) {
		String userTypeId = request.getParameter("userType");
		String content = request.getParameter("content");

		// UserType
		UserType userType = new UserType();
		userType.setId(Integer.parseInt(userTypeId));
		user.setUserType(userType);

		// content
		user.setContent(content);

		// PasswordEdit
		String passwordOld = request.getParameter("passwordOld");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		user.setPasswordOld(passwordOld);
		user.setPassword(password);
		user.setPassword2(password2);
	}

	private void populatePassword(HttpServletRequest request, User user) {
		String passwordOld = request.getParameter("passwordOld");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		user.setPasswordOld(passwordOld);
		user.setPassword(password);
		user.setPassword2(password2);
	}

	private String validate(User user) {
		String errorMsg = null;
		if (TextUtils.isEmpty(user.getPasswordOld())) {
			errorMsg = "请输入原密码";
		} else if (TextUtils.isEmpty(user.getPassword())) {
			errorMsg = "请输入新密码";
		} else if (TextUtils.isEmpty(user.getPassword2())) {
			errorMsg = "请输入确认密码";
		} else if (!user.getPassword().equals(user.getPassword2())) {
			errorMsg = "两次密码不相等";
		}
		return errorMsg;
	}
}
