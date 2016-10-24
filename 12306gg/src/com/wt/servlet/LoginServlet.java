package com.wt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.Md5Utils;
import com.util.TextUtils;
import com.wt.po.User;
import com.wt.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action=request.getParameter("action");
		if(action==null||"login".equals(action)){
			doLogin(request,response);
		}else if("autoLogin".equals(action)){
			doAutoLogin(request,response);
		}
	}

	private void doAutoLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String username="";
		String autoLogin="";
		
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			for(Cookie c:cookies){
				if("username".equals(c.getName())){
					username=c.getValue();
				}else if("autoLogin".equals(c.getName())){
					autoLogin=c.getValue();
				}
			}
		}
		
		/*没有登录信息，或者登录者没有勾选自动登录，跳到登录页面*/
		if(TextUtils.isEmpty(username)||TextUtils.isEmpty(autoLogin)){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
		else{
			UserService userService=UserService.getInstance();
			User one=new User();
			one.setUsername(username);
			User dbUser=userService.findUser(one);
			if(autoLogin.equals(Md5Utils.md5(username+","+dbUser.getPassword()))){
				
				//更新Login
				User user =new User();
				populate(request,user);
				
				one=new User();
				one.setId(dbUser.getId());
				one.setLoginip(user.getLoginip());
				userService.updateUser(one);
				
				dbUser.setLoginip(user.getLoginip());
				HttpSession se=request.getSession();
				se.setAttribute("user",dbUser);
				
				System.out.println("path="+request.getContextPath());
				response.sendRedirect(request.getContextPath()+"/mainall.jsp");
			}
			else{
				Cookie c=new Cookie("username","");
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
				
				Cookie cLogin =new Cookie("autoLogin","");
				cLogin.setMaxAge(0);
				cLogin.setPath("/");
				response.addCookie(cLogin);
				
				response.sendRedirect(request.getContextPath()+"/login.jsp");
			}
		}
	}

	private void populate(HttpServletRequest request, User user) {
		// TODO Auto-generated method stub
		String loginIp=request.getRemoteAddr();
		
		String username=request.getParameter("usernaem");
		String password=request.getParameter("password");
		String autoLogin=request.getParameter("autoLogin");
		String code=request.getParameter("code");
		
		user.setLoginip(loginIp);
		user.setUsername(username);
		if(password!=null){
			user.setPassword(Md5Utils.md5(password));
		}
		if(TextUtils.isEmpty(autoLogin)){
			user.setAutoLogin(false);
		}
		else{
			user.setAutoLogin(true);
		}
		user.setCode(code);
	}

	private void doLogin(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession se=request.getSession();
		
		//获取请求参数
		User user =new User();
		populate(request,user);
		
		String msg=validate(user);
		if(TextUtils.isEmpty(msg)){
			String code=(String) se.getAttribute("code");
			
			//图片文件的验证码和页面中文本框输入的验证比较
			if(!user.getCode().equalsIgnoreCase(code)){
				msg="验证码错误";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("/login.jsp").forward(request,response);
			}
			else{
				//调用Service方法
				UserService userService=UserService.getInstance();
				
				//检查用户是否存在
				User dbUser=userService.login(user.getUsername(),user.getPassword());
				
			}
		}
	}

	private String validate(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
