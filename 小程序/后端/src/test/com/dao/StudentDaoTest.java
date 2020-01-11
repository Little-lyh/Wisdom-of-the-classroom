package test.com.dao; 

import com.dao.StudentDao;
import com.pojo.Students;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.List;

/** 
* StudentDao Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ���� 17, 2019</pre> 
* @version 1.0 
*/ 
public class StudentDaoTest { 

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
* Method: login(Students students) 
* 
*/ 
@Test
public void testLogin() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getStudent(String username) 
* 
*/ 
@Test
public void testGetStudent() throws Exception { 
//TODO: Test goes here...
    Students students=new StudentDao().getStudent("Sugar");
    System.out.println(students.getName()+" "+students.getPassword()+" "+students.getAddress()+" "+students.getPhone()+" "+students.getPoint());
} 

/** 
* 
* Method: changePoint(String username, int new_point) 
* 
*/ 
@Test
public void testChangePoint() throws Exception { 
//TODO: Test goes here...
    List<Students> list=new StudentDao().getStudentRank();
    for(int i=0;i<list.size();i++){
        Students students=list.get(i);
        System.out.println(students.getName()+":"+students.getPoint());
    }
}
} 
