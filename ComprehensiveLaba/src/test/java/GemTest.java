import com.senyk.comprehensiveLaba.gems.entity.Gem;
import com.senyk.comprehensiveLaba.gems.entity.GemEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GemTest {
    Gem gem;
    @Before
    public void setUp(){
        gem=new Gem("diamond",12.3,10000.0,"cube");
        gem.setId(1L);
    }
    @Test
    public void testGetName(){
        Assert.assertEquals(gem.getName(),"diamond");
    }
    @Test
    public void testGetPrice(){
        Assert.assertEquals(gem.getPrice(), Double.valueOf(10000.0));
    }
    @Test
    public void testShape(){
        Assert.assertEquals(gem.getShape(), "cube");
    }
    @Test
    public void testColor(){
        Assert.assertEquals(gem.getColor(), GemEnum.Diamond.getColor());
    }
    @Test
    public void test_density(){
        Assert.assertEquals(gem.getDensity(), GemEnum.Diamond.getDensity());
    }
    @Test
    public void test_hardness(){
        Assert.assertEquals(gem.getHardness(), GemEnum.Diamond.getHardness());
    }
    @Test
    public void test_density1(){
        Assert.assertNotSame(gem.getDensity(), GemEnum.Ruby.getDensity());
    }
    @Test
    public void test_toString(){Assert.assertNotSame(gem.toString(), "Gems{" +
            "id=" + 1L +
            ", name='" + "diamond" + '\'' +
            ", carat=" + 12.3 +
            ", price=" + 10000.0 +
            ", shape='" + "cube" + '\'' +
            ", color='" + GemEnum.Diamond.getColor() + '\'' +
            ", density=" + GemEnum.Diamond.getDensity() +
            ", hardness=" + GemEnum.Diamond.getHardness() +
            ", status='" + "No used" + '\'' +
            '}');}
}
