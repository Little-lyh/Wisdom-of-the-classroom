package com.action;

import com.dao.*;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.*;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangePointAction extends ActionSupport {
    public String execute(){
        HttpServletRequest request= ServletActionContext.getRequest();
        String username=request.getParameter("username");
        int point=Integer.parseInt(request.getParameter("point"));
        String mode=request.getParameter("mode");
        System.out.println(username+" "+point+" "+mode);
        StudentDao studentDao=new StudentDao();
        boolean succeed;
        if(mode.equals("+")){
            int q_id=Integer.parseInt(request.getParameter("q_id"));
            List<Q_record> q_records=new QuestionRecordDao().getQuestions(username);
            boolean done=false;
            if(q_records!=null){
                for(int i=0;i<q_records.size();i++){
                    if(q_records.get(i).getQuestion().getId()==q_id){
                        done=true;
                        break;
                    }
                }
            }
            if(done) {
                succeed=false;
            }else{
                succeed = studentDao.changePoint(username, point);
                Q_record qr = new Q_record();
                QuestionDao questionDao = new QuestionDao();
                Students student = studentDao.getStudent(username);
                Question question = questionDao.getQuestion(q_id);
                qr.setDate(new Date());
                qr.setQuestion(question);
                qr.setStudents(student);
                qr.setState("对");
                new QuestionRecordDao().insertRecord(qr);
            }
        }else{
            int p_id=Integer.parseInt(request.getParameter("p_id"));
            succeed=studentDao.changePoint1(username,point);
            if(succeed) {
                Prize prize = new PrizeDao().getPrize(p_id);
                Students students = studentDao.getStudent(username);
                P_record pr = new P_record();
                pr.setDate(new Date());
                pr.setPrize(prize);
                pr.setStudents(students);
                new PrizeRecordDao().insertRecord(pr);
            }
        }
        Map map = new HashMap();
        map.put("result",succeed);
        HttpServletResponse response= ServletActionContext.getResponse();
        try {
            System.out.println(JSONObject.fromObject(map).toString());
            System.out.println(map.get("result"));
            response.getWriter().print(JSONObject.fromObject(map).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return NONE;
    }
}
