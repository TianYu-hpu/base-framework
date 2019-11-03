package cn.com.zenmaster.entity.mapstruct;

import cn.com.zenmaster.entity.po.User;
import cn.com.zenmaster.entity.vo.request.UserAddVo;
import cn.com.zenmaster.entity.vo.request.UserQueryVo;
import cn.com.zenmaster.entity.vo.resposne.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author tianyu
 * @date 2018-01-07 15:23:57
 */
@Mapper
public interface UserMap {

    UserMap INSTANCE = Mappers.getMapper(UserMap.class);

    @Mappings({
        @Mapping(source = "username", target = "username"),
        @Mapping(source = "email", target = "email"),
        @Mapping(source = "phone", target = "phone"),
        @Mapping(source = "page.pageNo", target = "page.pageNo"),
        @Mapping(source = "page.pageSize", target = "page.pageSize")
    })
    User voToPo(UserQueryVo record);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "activeFlag", target = "activeFlag"),
            @Mapping(source = "createTime", target = "createTime"),
            @Mapping(source = "createBy", target = "createBy"),
            @Mapping(source = "updateTime", target = "updateTime"),
            @Mapping(source = "updateBy", target = "updateBy"),
    })
    UserResponse poToUserResponse(User po);

    List<UserResponse> poListToVoList(List<User> pageList);

    @Mappings({
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "phone", target = "phone"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password")
    })
    User addVoToPo(UserAddVo userAddVo);
}
