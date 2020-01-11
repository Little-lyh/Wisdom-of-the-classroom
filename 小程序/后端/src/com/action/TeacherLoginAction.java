package com.action;

import com.dao.TeacherDao;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.Teachers;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class TeacherLoginAction extends ActionSupport {
    public String execute(){
        HttpServletRequest request= ServletActionContext.getRequest();
        String username=request.getParameter("username");
        String password=request.getParameter("pass");
        boolean result=false;
        Map map = new HashMap();
        Teachers teacher=new TeacherDao().getTeacher(username);
        if(teacher==null){
            result=false;
        }
        else if(password.equals(teacher.getPassword())){
            result=true;
        }else{
            result=false;
        }
        map.put("res",result);
        HttpServletResponse response= ServletActionContext.getResponse();
        try {
            System.out.println(JSONObject.fromObject(map).toString());
            response.getWriter().print(JSONObject.fromObject(map).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return NONE;
    }
}
