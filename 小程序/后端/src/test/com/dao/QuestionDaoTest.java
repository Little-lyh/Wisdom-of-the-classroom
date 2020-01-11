package test.com.dao; 

import com.dao.QuestionDao;
import com.pojo.Question;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.List;

/** 
* QuestionDao Tester. 
* 
* @author <Authors name> 
* @since <pre>Ê®¶þÔÂ 16, 2019</pre> 
* @version 1.0 
*/ 
public class QuestionDaoTest { 

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
* Method: getQuestion(int id) 
* 
*/ 
@Test
public void testGetQuestion() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addQuestion(Question question) 
* 
*/ 
@Test
public void testAddQuestion() throws Exception {
    List<Question> questionList=new QuestionDao().getQuestions();
    for(int i=0;i<questionList.size();i++){
        Question question=questionList.get(i);
        System.out.println(question.getQuestion());
    }
//TODO: Test goes here... 
}
} 
