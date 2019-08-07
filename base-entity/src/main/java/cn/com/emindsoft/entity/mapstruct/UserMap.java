package cn.com.emindsoft.entity.mapstruct;

import cn.com.emindsoft.entity.po.User;
import cn.com.emindsoft.entity.vo.request.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author tianyu
 * @date 2018-01-07 15:23:57
 */
@Mapper
public interface UserMap {

    UserMap INSTANCE = Mappers.getMapper(UserMap.class);

    @Mappings({
        @Mapping(source = "username", target = "username"),
        @Mapping(source = "password", target = "password")
    })
    UserVo voToPo(User record);

}
