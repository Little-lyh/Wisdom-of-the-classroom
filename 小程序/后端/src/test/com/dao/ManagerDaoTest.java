package test.com.dao; 

import com.dao.ManagerDao;
import com.pojo.Manager;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* ManagerDao Tester. 
* 
* @author <Authors name> 
* @since <pre>һ�� 7, 2020</pre> 
* @version 1.0 
*/ 
public class ManagerDaoTest { 

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
* Method: getManager(String username) 
* 
*/ 
@Test
public void testGetManager() throws Exception { 
//TODO: Test goes here...
    Manager manager=new ManagerDao().getManager("yang");
    System.out.println(manager.getUsername());
}
} 
