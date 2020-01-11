package com.action;

import com.dao.TeacherDao;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.Teachers;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class TeacherMessageAction extends ActionSupport {
    public String execute(){
        HttpServletRequest request= ServletActionContext.getRequest();
        String username=request.getParameter("username");
        Teachers teacher=new TeacherDao().getTeacher(username);
        Map map = new HashMap();
        map.put("teacher",teacher);
        HttpServletResponse response= ServletActionContext.getResponse();
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"questionList"});
        try {
            System.out.println(JSONObject.fromObject(map,config).toString());
            response.getWriter().print(JSONObject.fromObject(map,config).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return NONE;
    }
}
