import com.aojiaoo.core.mybatis.plugins.paging.Page;
import com.aojiaoo.modules.sys.entity.User;
import com.aojiaoo.modules.sys.mapper.UserMapperTest;
import com.aojiaoo.modules.sys.service.RoleService;
import com.aojiaoo.modules.sys.service.TestService;
import com.aojiaoo.modules.sys.service.UserService;
import org.junit.Test;
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

    @Autowired
    TestService testService;

    @Resource
    UserMapperTest userMapperTest;

    //自动装配
    @Test
    public void test1() {
//        Role role = new Role();
//        Page page = new Page();
//        System.out.println(roleService.getMapper().selectBySelective(role, page));
        System.out.println(roleService.getMapper().selectByPrimaryKey(1));
    }

    @Test
    public void test2() {
//        userService.
        Page<User> userPage = new Page<>();
        userPage.setPageSize(1);
        userPage.setPageNo(2);
        System.out.println(userService.findPage(new User(), userPage));
    }

    @Test
    public void test3() {
        System.out.println(userMapperTest.selectAll());
    }


}