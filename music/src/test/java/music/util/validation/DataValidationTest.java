package music.util.validation;

import by.nyurush.music.util.validation.DataValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DataValidationTest {

    @DataProvider(name = "positiveEmails")
    public Object[] createPositiveEmails() {
        return new Object[]{
                "natallia.yurush@gmail.com",
                "nika.koko@yandex.ru",
                "kokosik@mail.ru"
        };
    }

    @DataProvider(name = "negativeEmails")
    public Object[] createNegativeEmails() {
        return new Object[]{
                "",
                "olga.com",
                "kokos@gmail",
                ".@gmail.com",
                "sad@mail."
        };
    }

    @DataProvider(name = "positivePassword")
    public Object[] createPositivePasswords() {
        return new Object[]{
                "QQww1122",
                "FFhowh67hjF",
                "Foiej34jjF",
                "ASDFs1234"
        };
    }

    @DataProvider(name = "negativePassword")
    public Object[] createNegativePasswords() {
        return new Object[]{
                "",
                "QQww112",
                "kjhgfd123456",
                "!!!qwerR",
                "12345678",
                "qwertyui",
                "QWERtyui"
        };
    }

    @DataProvider(name = "positiveLogin")
    public Object[] createPositiveLogins() {
        return new Object[]{
                "natasha",
                "natallia-yurush",
                "natallia.yurush",
                "natallia_yurush"
        };
    }

    @DataProvider(name = "negativeLogin")
    public Object[] createNegativeLogins() {
        return new Object[]{
                "",
                "a",
                "sdkfjdsflkfj dslfjslskfhlkshflaskjfhalkfh",
                "sf df",
                "natasha!!!"
        };
    }

    @DataProvider(name = "positiveName")
    public Object[] createPositiveNames() {
        return new Object[]{
                "natasha",
                "Natallia",
                "Yurush"
        };
    }

    @DataProvider(name = "negativeName")
    public Object[] createNegativeNames() {
        return new Object[]{
                "",
                "A",
                "sdkfjdsflkfj dslfjslskfhlkshflaskjfhalkfh",
                "1234",
                "natasha!!!",
                "asdf123"
        };
    }

    @Test(dataProvider = "positiveEmails")
    public void isCorrectEmailTest(String email) {
        assertTrue(DataValidator.isCorrectEmail(email));
    }

    @Test(dataProvider = "negativeEmails")
    public void isNotCorrectEmailTest(String email) {
        assertFalse(DataValidator.isCorrectEmail(email));
    }

    @Test(dataProvider = "positivePassword")
    public void isCorrectPasswordTest(String password) {
        assertTrue(DataValidator.isCorrectPassword(password));
    }

    @Test(dataProvider = "negativePassword")
    public void isNotCorrectPasswordTest(String password) {
        assertFalse(DataValidator.isCorrectPassword(password));
    }

    @Test(dataProvider = "positiveLogin")
    public void isCorrectLoginTest(String login) {
        assertTrue(DataValidator.isCorrectLogin(login));
    }

    @Test(dataProvider = "negativeLogin")
    public void isNotCorrectLoginTest(String login) {
        assertFalse(DataValidator.isCorrectLogin(login));
    }

    @Test(dataProvider = "positiveName")
    public void isCorrectNameTest(String name) {
        assertFalse(DataValidator.isIncorrectNameSurname(name));
    }

    @Test(dataProvider = "negativeName")
    public void isNotCorrectNameTest(String name) {
        assertTrue(DataValidator.isIncorrectNameSurname(name));
    }

}
