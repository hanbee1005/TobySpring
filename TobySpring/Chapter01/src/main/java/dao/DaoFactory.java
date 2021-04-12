package dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao(getConnection());
    }

    // 추가로 다른 Dao들이 생겼을 때를 중복을 방지하기 위한 메소드 분리
    @Bean
    public ConnectionMaker getConnection() {
        return new DConnectionMaker();
    }
}
