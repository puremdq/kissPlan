import com.aojiaoo.core.mybatis.plugins.paging.Page;
import com.aojiaoo.study.mybatis.Test;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.study.mybatis.TestMapper;
import com.aojiaoo.modules.sys.mapper.UserMapper;
import com.aojiaoo.modules.sys.mapper.UserRoleMapper;
import com.aojiaoo.modules.sys.service.RoleService;
import com.aojiaoo.modules.sys.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

//告诉spring容器运行在虚拟机中
@RunWith(SpringJUnit4ClassRunner.class)

//配置文件的位置
//若当前配置文件名=当前测试类名-context.xml 就可以在当前目录中查找@ContextConfiguration()
@ContextConfiguration("classpath:context/application-context.xml")
public class SpringTest {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Resource
    UserMapper userMapper;

    @Resource
    TestMapper stMapper;

    @Resource
    UserRoleMapper userRoleMapper;

    //自动装配
    @org.junit.Test
    public void test1() {
        User user = userService.get(1);
        System.out.println(user);

    }

    @org.junit.Test
    public void test2() {
//        userService.
        Page<User> userPage = new Page<>();
        userPage.setPageSize(1);
        userPage.setPageNo(2);
        System.out.println(userService.findPage(new User(), userPage));
    }

    @org.junit.Test
    public void test3() {
//        User user=new User();
//        user.setUsername("");
//        user.setId(4);
//        userMapper.selectByPrimaryKey(user);
        Test st = new Test();
        st.setName("sdfsdfsd");
        st.setId("5b2f193a7d1f4fbaa3b056fc0dfa6e46");
//        UserRole userRole = new UserRole();
//        Map map = new HashMap();

    }


}