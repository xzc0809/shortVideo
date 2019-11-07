import com.xzc.mapper.UsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiaozhichao
 * @Date 2019/11/3
 * @Time 11:49
 */
public class test {
    @Autowired
    UsersMapper usersMapper;
    @Test
    public void test1() throws Exception{
        System.out.println(usersMapper.getUsersById("1001").getNickname());
    }
}
