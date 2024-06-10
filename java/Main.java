import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.myweb.users.UserDTO;
import com.example.myweb.users.mapper.UserMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        Reader reader = null;
        SqlSession session = null;

        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sqlSessionFactory.openSession();

            UserMapper userMapper = session.getMapper(UserMapper.class);

            // 사용자 추가
            UserDTO user1 = new UserDTO(0, "김", "kim@aaa.com");
            userMapper.addUser(user1);
            session.commit();

            // 사용자 조회
            UserDTO user = userMapper.getUserById(user1.getId());
            System.out.println(user);

            // 모든 사용자 조회
            List<UserDTO> users = userMapper.getAllUsers();
            users.forEach(System.out::println);

            // 사용자 업데이트
            user.setName("이");
            userMapper.updateUser(user);
            session.commit();

            // 업데이트된 사용자 조회
            System.out.println(userMapper.getUserById(user.getId()));

            // 사용자 삭제
            userMapper.deleteUser(user.getId());
            session.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
