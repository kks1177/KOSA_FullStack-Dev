package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
                                                    throws ServletException, IOException {
      response.setContentType("text/html;charset=utf-8");
      PrintWriter out=response.getWriter();	
      MemberDAO dao=new MemberDAO();
      List<MemberVO> list=dao.listMembers();
	
      out.print("<html><body>");
      //테이블 태그 시작
      out.print("<table  border=1><tr align='center' bgcolor='lightgreen'>");
      out.print("<td>아이디</td><td>비밀번호</td><td>이름</td><td>이메일</td><td>가입일</td></tr>");
     
     for (int i=0; i<list.size();i++){
		MemberVO memberVO=(MemberVO) list.get(i);
		
		String id=memberVO.getId();
		String pwd = memberVO.getPwd();
		String name=memberVO.getName();
		String email=memberVO.getEmail();
		Date joinDate = memberVO.getJoinDate();
		//table 태그 출력
		out.print("<tr><td>"+id+"</td><td>"+
			                pwd+"</td><td>"+
			                name+"</td><td>"+
			                email+"</td><td>"+
			                joinDate+"</td></tr>");		
      }
     //테이블 태그 끝
      out.print("</table></body></html>");
   }
}
