package org.example.factory;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.example.entity.User;

public class ExtendsObjectFactory extends DefaultObjectFactory {

    /**
     * create 中的 super.create() 仅仅只是使用无参构造函数
     * 创建一个对象，而数据的填充是在后面进行。所以现在设置对应
     * 的属性，就是该属性的默认值。
     *
     * 比如：设置 age 属性默认值为 0，数据库中查询出来的结果为
     * null。最终呈现的数据就为 0.
     */
    @Override
    public <T> T create(Class<T> type) {
        T t = super.create(type);

        if (User.class.equals(type)) {
            User user = (User) t;
            user.setAge(0);
        }

        return t;
    }
}
