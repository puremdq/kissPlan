package study.mybatis;

import com.aojiaoo.core.mybatis.annotations.MyBatisDao;
import org.apache.ibatis.annotations.SelectProvider;

@MyBatisDao
public interface TestMapper {

    @SelectProvider(type = SelectMethodProvider.class, method = "select")
    Test select(Test record);

}