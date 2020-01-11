package test.com.dao; 

import com.dao.PrizeRecordDao;
import com.pojo.P_record;
import com.pojo.Prize;
import com.pojo.Students;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.Date;

/** 
* PrizeRecordDao Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ���� 30, 2019</pre> 
* @version 1.0 
*/ 
public class PrizeRecordDaoTest { 

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
* Method: insertRecord(P_record p) 
* 
*/ 
@Test
public void testInsertRecord() throws Exception { 
//TODO: Test goes here...
    Students students=new Students();
    Prize prize=new Prize();
    students.setUsername("yang");
    prize.setId(1);
    P_record p_record=new P_record();
    p_record.setStudents(students);
    p_record.setPrize(prize);
    p_record.setDate(new Date());
    new PrizeRecordDao().insertRecord(p_record);
}
}
