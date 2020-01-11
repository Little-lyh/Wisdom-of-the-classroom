package com.action;

import com.dao.StudentDao;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.Students;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentMessageAction extends ActionSupport {
    public String getStudentMessage(){
        HttpServletRequest request= ServletActionContext.getRequest();
        String username=request.getParameter("username");
        Students student=new StudentDao().getStudent(username);
        Map map = new HashMap();
        map.put("student",student);
        JsonConfig config = new JsonConfig();
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        HttpServletResponse response= ServletActionContext.getResponse();
        try {
            System.out.println(JSONObject.fromObject(map,config).toString());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JSONObject.fromObject(map,config).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return NONE;
    }
    public String getStudentRank(){
        List<Students> student=new StudentDao().getStudentRank();
        Map map = new HashMap();
        map.put("students",student);
        JsonConfig config = new JsonConfig();
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        HttpServletResponse response= ServletActionContext.getResponse();
        try {
            System.out.println(JSONObject.fromObject(map,config).toString());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JSONObject.fromObject(map,config).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return NONE;
    }
}
