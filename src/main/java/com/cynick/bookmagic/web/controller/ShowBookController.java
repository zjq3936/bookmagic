package com.cynick.bookmagic.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 展示入口
 * @author CyNick
 * @date 2018年5月4日
 */
@Controller
public class ShowBookController {

//	@Autowired
//	private BookService bookService;

	@RequestMapping(value = "/showBookList")
	@ResponseBody
	public void showBookList(Integer pageNum, Integer pageSize,HttpServletResponse response) throws IOException {
//		List<Book> list = bookService.getAllBook(pageNum, pageSize);
//		pageNum += 1;
//		StringBuilder sb = new StringBuilder();
//		sb.append("<!DOCTYPE html>                                                                                                                               ");
//		sb.append("<html>                                                                                                                                        ");
//		sb.append("<head>                                                                                                                                        ");
//		sb.append("<title>Welcome to nginx!</title>                                                                                                              ");
//		sb.append("<meta charset='utf-8'>                                                                                                                        ");
//		sb.append("<meta name='viewport' content='width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes' />             ");
//		sb.append("<style>                                                                                                                                       ");
//		sb.append("    body {                                                                                                                                    ");
//		sb.append("        width: auth;                                                                                                                          ");
//		sb.append("		height: 45em;                                                                                                                            ");
//		sb.append("        margin: 0 auto;                                                                                                                       ");
//		sb.append("        font-family: Tahoma, Verdana, Arial, sans-serif;                                                                                      ");
//		sb.append("    }                                                                                                                                         ");
//		sb.append("	                                                                                                                                             ");
//		sb.append("	                                                                                                                                             ");
//		sb.append("	.mylist li{                                                                                                                                  ");
//		sb.append("		float:left;                                                                                                                              ");
//		sb.append("		margin-left: 6px;                                                                                                                        ");
//		sb.append("		margin-right: 6px;                                                                                                                       ");
//		sb.append("		list-style:none;                                                                                                                         ");
//		sb.append("	}                                                                                                                                            ");
//		sb.append("</style>                                                                                                                                      ");
//		sb.append("</head>                                                                                                                                       ");
//		sb.append("<body>                                                                                                                                        ");
//		sb.append("<h1>Welcome to books!</h1>                                                                                                                    ");
//		sb.append("<div>                                                                                                                                         ");
//		sb.append("<ul class='mylist'>                                                                                                                           ");
//		for(Book book : list){
//			sb.append("	<li>                                                                                                                                         ");
//			sb.append("		<a href='http://localhost:8081/bookhouse/getContentList?bookId'"+book.getId()+">"+book.getBookName()+"</a><br/>                                                                                           ");
//			sb.append("	</li>                                                                                                                                        ");
//		}
//		
//		sb.append("	<li>                                                                                                                                         ");
//		sb.append("		<a href='http://localhost/page-"+pageNum+"-"+30+".html'>涓嬩竴椤?</a><br/>                                                                                              ");
//		sb.append("	</li>                                                                                                                                        ");
//		sb.append("</ul>                                                                                                                                         ");
//		sb.append("</div>                                                                                                                                        ");
//		sb.append("</body>                                                                                                                                       ");
//		sb.append("</html>                                                                                                                                       ");
//		
//		response.setContentType("text/html;charset=utf-8"); 
//		PrintWriter out=response.getWriter();
//		out.println(sb.toString());
	}
}
