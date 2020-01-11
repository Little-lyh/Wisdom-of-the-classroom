package com.action;

import com.dao.StudentDao;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.Students;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class StudentLoginAction extends ActionSupport {
    public String execute(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String x=request.getParameter("x");
        String y=request.getParameter("y");
        System.out.println("x:"+x);
        System.out.println("y:"+y);
        Map map = new HashMap();
        boolean result=false;
        Students students=new StudentDao().getStudent(x);
        if(students==null){
            result=false;
        }
        else if(y.equals(students.getPassword())){
            result=true;
        }else{
            result=false;
        }
        map.put("res",result);
        System.out.println(result);
        System.out.println("获取链表成功");
        //JSONArray.fromObject(reports);
        HttpServletResponse response= ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        try {
            //response.getWriter().print(JSONObject.fromObject(map).toString());
            System.out.println(JSONObject.fromObject(map).toString());
            response.getWriter().print(JSONObject.fromObject(map).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return NONE;
    }
}
