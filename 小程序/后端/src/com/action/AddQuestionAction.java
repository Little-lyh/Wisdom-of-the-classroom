package com.action;

import com.dao.QuestionDao;
import com.dao.StudentDao;
import com.dao.TeacherDao;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.Question;
import com.pojo.Teachers;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AddQuestionAction extends ActionSupport {
    public String addQuestion(){
        HttpServletRequest request= ServletActionContext.getRequest();
        System.out.println(request.getParameter("problem"));
        System.out.println(request.getParameter("optionA"));
        System.out.println(request.getParameter("optionB"));
        System.out.println(request.getParameter("optionC"));
        System.out.println(request.getParameter("optionD"));
        System.out.println(request.getParameter("answer"));
        System.out.println(request.getParameter("point"));
        System.out.println(request.getParameter("username"));
        Question question=new Question();
        question.setAnswer(request.getParameter("answer"));
        question.setOption_A(request.getParameter("optionA"));
        question.setOption_B(request.getParameter("optionB"));
        question.setOption_C(request.getParameter("optionC"));
        question.setOption_D(request.getParameter("optionD"));
        question.setQuestion(request.getParameter("problem"));
        question.setPoint(request.getParameter("point"));
        Teachers t=new TeacherDao().getTeacher(request.getParameter("username"));
        question.setTeachers(t);
        boolean result=new QuestionDao().addQuestion(question);
        Map map = new HashMap();
        map.put("result",result);
        HttpServletResponse response= ServletActionContext.getResponse();
        try {
            System.out.println(JSONObject.fromObject(map).toString());
            response.getWriter().print(JSONObject.fromObject(map).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return NONE;
    }
    public String changeQuestion(){
        HttpServletRequest request= ServletActionContext.getRequest();
        System.out.println(request.getParameter("id"));
        System.out.println(request.getParameter("problem"));
        System.out.println(request.getParameter("optionA"));
        System.out.println(request.getParameter("optionB"));
        System.out.println(request.getParameter("optionC"));
        System.out.println(request.getParameter("optionD"));
        System.out.println(request.getParameter("answer"));
        System.out.println(request.getParameter("point"));
        System.out.println(request.getParameter("username"));
        Question question=new QuestionDao().getQuestion(Integer.parseInt(request.getParameter("id")));
        question.setAnswer(request.getParameter("answer"));
        question.setOption_A(request.getParameter("optionA"));
        question.setOption_B(request.getParameter("optionB"));
        question.setOption_C(request.getParameter("optionC"));
        question.setOption_D(request.getParameter("optionD"));
        question.setQuestion(request.getParameter("problem"));
        question.setPoint(request.getParameter("point"));
        Teachers t=new TeacherDao().getTeacher(request.getParameter("username"));
        question.setTeachers(t);
        boolean result=new QuestionDao().changeQuestion(question);
        Map map = new HashMap();
        map.put("result",result);
        HttpServletResponse response= ServletActionContext.getResponse();
        try {
            System.out.println(JSONObject.fromObject(map).toString());
            response.getWriter().print(JSONObject.fromObject(map).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return NONE;
    }
    public String delQuestion(){
        HttpServletRequest request= ServletActionContext.getRequest();
        System.out.println(request.getParameter("id"));
        int q_id=Integer.parseInt(request.getParameter("id"));
        QuestionDao questionDao=new QuestionDao();
        Question question=questionDao.getQuestion(q_id);
        boolean result=questionDao.delQuestion(question);
        Map map = new HashMap();
        map.put("result",result);
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
