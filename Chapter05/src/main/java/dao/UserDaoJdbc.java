package dao;

import com.mysql.cj.exceptions.MysqlErrorNumbers;
import domain.Level;
import domain.User;
import exception.DuplicateUserIdException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDaoJdbc implements UserDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setLevel(Level.valueOf(rs.getInt("level")));
            user.setLogin(rs.getInt("login"));
            user.setRecommend(rs.getInt("recommend"));
            return user;
        }
    };

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    public void add(final User user) throws DuplicateUserIdException {
        try {
            this.jdbcTemplate.update("insert into users(id, name, password, level, login, recommend) values(?,?,?,?,?,?)",
                    user.getId(), user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend());
        } catch (DuplicateKeyException e) {
            throw new DuplicateUserIdException(e);
        }
    }

    public User get(String id) throws SQLException {
        // jdbcTemplate 사용하는 경우
        // return this.jdbcTemplate.queryForObject("select * from users where id = ?", new Object[] {id}, this.userMapper);

        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        // 4. 조회의 경우 SQL 쿼리의 실행 결과를 ResultSet으로 받아서 정보를 저장할 오브젝트(여기서는 User)에 옮겨준다.
        ResultSet rs = ps.executeQuery();

        User user = null;
        if (rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
            user.setLevel(Level.valueOf(rs.getInt("level")));
            user.setLogin(rs.getInt("login"));
            user.setRecommend(rs.getInt("recommend"));
        }

        rs.close();
        ps.close();
        c.close();

        if (user == null) throw new EmptyResultDataAccessException(1);

        return user;
    }

    public void deleteAll() {
        this.jdbcTemplate.update("delete from users");
    }

    public int getCount() {
        return this.jdbcTemplate.query("select count(*) from users",
                new ResultSetExtractor<Integer>() {
                    @Override
                    public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                        rs.next();
                        return rs.getInt(1);
                    }
                });
    }

    public List<User> getAll() {
        return this.jdbcTemplate.query("select * from users order by id", this.userMapper);
    }

    public void update(User user) {
        this.jdbcTemplate.update("update users set name = ?, password = ?, level = ?, login = ?, recommend = ? where id = ?",
                user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(), user.getId());
    }
}
