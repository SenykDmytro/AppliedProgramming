import com.senyk.comprehensiveLaba.gems.entity.Necklace;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NecklaceTest {
    Necklace necklace;
    @Before
    public void setUp(){
        necklace=new Necklace(1L,"necklace-1");
    }
    @Test
    public void testGetName(){
        Assert.assertEquals(necklace.getName(),"necklace-1");
    }
    @Test
    public void testGetNameFalse(){
        Assert.assertNotSame(necklace.getName(),"necklace-2");
    }
    @Test
    public void testGetId(){
        Assert.assertEquals(necklace.getId().longValue(), 1L);
    }
}
