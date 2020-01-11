package com.action;

import com.dao.QuestionDao;
import com.dao.QuestionRecordDao;
import com.dao.StudentDao;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.Q_record;
import com.pojo.Question;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class GetProblemListAction extends ActionSupport {
    public String getProblem(){
        HttpServletRequest request= ServletActionContext.getRequest();
        System.out.println("题目id"+request.getParameter("x"));
        System.out.println(request.getParameter("y"));
        int p_id=Integer.parseInt(request.getParameter("x"));
        Question problem=new QuestionDao().getQuestion(p_id);
        Map map = new HashMap();
        map.put("problem",problem);
        HttpServletResponse response= ServletActionContext.getResponse();
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"teachers","q_records"});
        try {
            System.out.println(JSONObject.fromObject(map,config).toString());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JSONObject.fromObject(map,config).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return NONE;
    }
    public String getProblem1(){
        HttpServletRequest request= ServletActionContext.getRequest();
        System.out.println("题目id"+request.getParameter("x"));
        System.out.println(request.getParameter("y"));
        int p_id=Integer.parseInt(request.getParameter("x"));
        Question problem=new QuestionDao().getQuestion(p_id);
        List<Question> question=new ArrayList<>();
        if(problem!=null){
            question.add(0,problem);
        }
        Map map = new HashMap();
        map.put("problem",question);
        HttpServletResponse response= ServletActionContext.getResponse();
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"teachers","q_records"});
        try {
            System.out.println(JSONObject.fromObject(map,config).toString());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JSONObject.fromObject(map,config).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return NONE;
    }
    public String getAllProblem(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String x=request.getParameter("x");
        System.out.println(x);
        List<Question> problems=new QuestionDao().getQuestions();
        for(int i=0;i<problems.size();i++){
            Question question=problems.get(i);
            System.out.println(question.getQuestion()+" "+question.getOption_A()+" "+question.getOption_B()+" "+question.getOption_C()+" "+question.getOption_D()+" "+question.getPoint());
        }
        if(problems==null){
            System.out.println("null");
        }
        Map map = new HashMap();
        map.put("problems",problems);
        HttpServletResponse response= ServletActionContext.getResponse();
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"teachers"});
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        System.out.println("获取成功");
        try {
            System.out.println(JSONObject.fromObject(map,config).toString());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JSONObject.fromObject(map,config).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return NONE;
    }
    public String getTeacherProblem(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String username=request.getParameter("username");
        System.out.println(username);
        List<Question> problems=new QuestionDao().getQuestions(username);
        for(int i=0;i<problems.size();i++){
            Question question=problems.get(i);
            System.out.println(question.getQuestion()+" "+question.getOption_A()+" "+question.getOption_B()+" "+question.getOption_C()+" "+question.getOption_D()+" "+question.getPoint());
        }
        if(problems==null){
            System.out.println("null");
        }
        Map map = new HashMap();
        map.put("problems",problems);
        HttpServletResponse response= ServletActionContext.getResponse();
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"teachers"});
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        System.out.println("获取成功");
        try {
            System.out.println(JSONObject.fromObject(map,config).toString());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JSONObject.fromObject(map,config).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return NONE;
    }
    public String changeQuestion(){
        return NONE;
    }
    public String getUserQuestion(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String username=request.getParameter("username");
        System.out.println(username);
        List<Q_record> problems=new QuestionRecordDao().getQuestions(username);
        if(problems==null){
            System.out.println("null");
        }
        Map map = new HashMap();
        map.put("problems",problems);
        HttpServletResponse response= ServletActionContext.getResponse();
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"students"});
        config.registerJsonValueProcessor(Date.class, new com.pojo.JsonDateValueProcessor());
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        System.out.println("获取成功");
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
