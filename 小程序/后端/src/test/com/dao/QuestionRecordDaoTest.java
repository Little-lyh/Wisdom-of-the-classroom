package test.com.dao; 

import com.dao.QuestionRecordDao;
import com.pojo.Q_record;
import com.pojo.Question;
import com.pojo.Students;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.Date;

/** 
* QuestionRecordDao Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ���� 30, 2019</pre> 
* @version 1.0 
*/ 
public class QuestionRecordDaoTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getCurrentSession() 
* 
*/ 
@Test
public void testGetCurrentSession() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: closeSession() 
* 
*/ 
@Test
public void testCloseSession() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getQuestions() 
* 
*/ 
@Test
public void testGetQuestions() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getQuestions(String username) 
* 
*/ 
@Test
public void testGetQuestionsUsername() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: insertRecord(Q_record q) 
* 
*/ 
@Test
public void testInsertRecord() throws Exception { 
//TODO: Test goes here...
    Students students=new Students();
    students.setUsername("yang");
    Question question=new Question();
    question.setId(1);
    Q_record q_record=new Q_record();
    q_record.setStudents(students);
    q_record.setQuestion(question);
    q_record.setDate(new Date());
    q_record.setId(1);
    new QuestionRecordDao().insertRecord(q_record);
}


} 
