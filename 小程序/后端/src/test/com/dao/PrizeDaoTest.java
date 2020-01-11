package test.com.dao; 

import com.dao.PrizeDao;
import com.pojo.Prize;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.List;

/** 
* PrizeDao Tester. 
* 
* @author <Authors name> 
* @since <pre>Ê®¶þÔÂ 17, 2019</pre> 
* @version 1.0 
*/ 
public class PrizeDaoTest { 

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
* Method: getPrizeList() 
* 
*/ 
@Test
public void testGetPrizeList() throws Exception { 
//TODO: Test goes here...
    List<Prize> prizes=new PrizeDao().getPrizeList();
    for(int i=0;i<prizes.size();i++){
        Prize prize=prizes.get(i);
        System.out.println(prize.getName()+" "+prize.getDetail()+" "+prize.getPoint());
    }
} 


} 
