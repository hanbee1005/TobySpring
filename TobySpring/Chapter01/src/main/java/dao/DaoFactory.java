package dao;

public class DaoFactory {
    public UserDao userDao() {
        return new UserDao(getConnection());
    }

    // 추가로 다른 Dao들이 생겼을 때를 중복을 방지하기 위한 메소드 분리
    public ConnectionMaker getConnection() {
        return new DConnectionMaker();
    }
}
