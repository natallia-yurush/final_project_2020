package music.util.validation;

import by.nyurush.music.util.validation.StringUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class StringUtilTest {

    @DataProvider(name = "positiveStrings")
    public Object[] createPositiveStrings() {
        return new Object[]{
                "kokosik",
                "   kokosik   ",
                "."
        };
    }

    @DataProvider(name = "negativeStrings")
    public Object[] createNegativeStrings() {
        return new Object[]{
                " ",
                "         ",
                "",
                null
        };
    }

    @Test(dataProvider = "positiveStrings")
    public void isNullOrEmptyPositiveTest(String str) {
        assertFalse(StringUtil.isNullOrEmpty(str));
    }

    @Test(dataProvider = "negativeStrings")
    public void isNullOrEmptyNegativeTest(String str) {
        assertTrue(StringUtil.isNullOrEmpty(str));
    }

}
