package myservlet.control;

import mybean.data.Bean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Calendar;

/**
 * Created by lei02 on 2019/4/14.
 */
public class Servlet extends HttpServlet{
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        Bean resultBean = null;
        try {
            resultBean = (Bean)request.getAttribute("resultBean");
            if (resultBean == null) {
                resultBean = new Bean();
                request.setAttribute("resultBean", resultBean);
            }
        } catch (Exception ex) {
            resultBean = new Bean();
            request.setAttribute("resultBean", resultBean);
        }

        String dataBase = request.getParameter("dataBase");
        String tableName = request.getParameter("tableName");
        String number = request.getParameter("number");
        String name = request.getParameter("name");
        String madeTime = request.getParameter("madeTime");
        String pr = request.getParameter("price");
        if(number == null || number.length() == 0) {
            fail(request, response, "添加记录失败，必须给出记录");
            return;
        }
        float price = Float.parseFloat(pr);

        Connection conn;
        Statement sql;
        ResultSet rs;
        try {
            String uri = "jdbc:mysql://172.0.0.1/" + dataBase
                    + "?user=root&password=021191&characterEncoding=utf-8";
            conn = DriverManager.getConnection(uri);
            sql = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = sql.executeQuery("select * from " + tableName);
            rs.moveToInsertRow();
            rs.updateString(1, number);
            rs.updateString(2, name);

            String[] str = madeTime.split("[-/]");
            int year = Integer.parseInt(str[0]);
            int month = Integer.parseInt(str[1]);
            int day = Integer.parseInt(str[2]);
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day);
            Date date = new Date(calendar.getTimeInMillis());
            rs.updateDate(3, date);
            rs.updateDouble(4, price);
            rs.insertRow();

            rs = sql.executeQuery("select * from " + tableName);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnName = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnName[1] = metaData.getColumnName(i + 1);
            }
            resultBean.setColumnName(columnName);

            rs.last();
            int rowNumber = rs.getRow();
            String[][] tableRecord = new String[rowNumber][columnCount];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                for (int k = 0; k < columnCount; k++) {
                    tableRecord[i][k] = rs.getString(k + 1);
                }
                i++;
            }
            resultBean.setTableReord(tableRecord);
            conn.close();

            RequestDispatcher dispatcher = request.getRequestDispatcher("showRecord.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            ex.printStackTrace();
            fail(request, response, "添加记录失败" + ex.toString());
        }
    }

    //显示错误信息
    public void fail(HttpServletRequest request, HttpServletResponse response, String info){
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.print("<html> <body>");
            out.print("<h2>" + info + "</h2>");
            out.print("返回");
            out.print("<a href=index.jsp>输入记录</a>");
            out.print("</body> </html>");
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
