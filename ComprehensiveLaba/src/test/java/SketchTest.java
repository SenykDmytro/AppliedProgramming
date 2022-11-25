import com.senyk.comprehensiveLaba.gems.entity.Sketch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SketchTest {
    Sketch sketch;
    @Before
    public void setUp(){
        sketch=new Sketch(1L,"necklace-1");
    }
    @Test
    public void testGetName(){
        Assert.assertEquals(sketch.getName(),"necklace-1");
    }
    @Test
    public void testGetNameFalse(){
        Assert.assertNotSame(sketch.getName(),"necklace-2");
    }
    @Test
    public void testGetId(){
        Assert.assertEquals(sketch.getId().longValue(), 1L);
    }
}



