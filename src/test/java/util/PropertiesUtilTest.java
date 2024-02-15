package util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesUtilTest {
    @ParameterizedTest
    @MethodSource("getPropertyArguments")
    void checkGet(String key, String expectedValue){
        String actualResult = PropertiesUtil.get(key);
        assertEquals(expectedValue, actualResult);
    }

     static Stream<Arguments> getPropertyArguments(){
        return Stream.of(
                Arguments.of("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver"),
                Arguments.of("hibernate.connection.url", "jdbc:mysql://localhost:3306/expense_tracker_db"),
                Arguments.of("hibernate.connection.username", "root"),
                Arguments.of("hibernate.connection.password", "1122"),
                Arguments.of("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"),
                Arguments.of("hibernate.hbm2ddl.auto", "update"),
                Arguments.of("hibernate.show_sql", "true")
        );
    }

}