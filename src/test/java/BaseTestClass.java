
import org.junit.BeforeClass;
import tools.GetConfig;

public class BaseTestClass {

    @BeforeClass
    public static void start(){
        GetConfig.setNameProperties("TestProp");
    }
}
