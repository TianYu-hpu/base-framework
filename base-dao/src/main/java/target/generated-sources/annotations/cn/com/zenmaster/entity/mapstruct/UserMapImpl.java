package cn.com.zenmaster.entity.mapstruct;

import cn.com.zenmaster.entity.base.PageBaseEntity;
import cn.com.zenmaster.entity.po.User;
import cn.com.zenmaster.entity.vo.request.UserAddVo;
import cn.com.zenmaster.entity.vo.request.UserQueryVo;
import cn.com.zenmaster.entity.vo.resposne.UserResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-11-03T19:51:02+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
public class UserMapImpl implements UserMap {

    @Override
    public User voToPo(UserQueryVo record) {
        if ( record == null ) {
            return null;
        }

        User user = new User();

        user.setPage( pageBaseEntityToPageBaseEntity( record.getPage() ) );
        user.setPhone( record.getPhone() );
        user.setEmail( record.getEmail() );
        user.setUsername( record.getUsername() );

        return user;
    }

    @Override
    public UserResponse poToUserResponse(User po) {
        if ( po == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setCreateBy( po.getCreateBy() );
        userResponse.setPhone( po.getPhone() );
        userResponse.setCreateTime( po.getCreateTime() );
        userResponse.setUpdateBy( po.getUpdateBy() );
        userResponse.setUpdateTime( po.getUpdateTime() );
        userResponse.setId( po.getId() );
        userResponse.setEmail( po.getEmail() );
        userResponse.setUsername( po.getUsername() );
        userResponse.setActiveFlag( po.getActiveFlag() );
        userResponse.setDelFlag( po.getDelFlag() );

        return userResponse;
    }

    @Override
    public List<UserResponse> poListToVoList(List<User> pageList) {
        if ( pageList == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( pageList.size() );
        for ( User user : pageList ) {
            list.add( poToUserResponse( user ) );
        }

        return list;
    }

    @Override
    public User addVoToPo(UserAddVo userAddVo) {
        if ( userAddVo == null ) {
            return null;
        }

        User user = new User();

        user.setPassword( userAddVo.getPassword() );
        user.setPhone( userAddVo.getPhone() );
        user.setEmail( userAddVo.getEmail() );
        user.setUsername( userAddVo.getUsername() );

        return user;
    }

    protected PageBaseEntity pageBaseEntityToPageBaseEntity(PageBaseEntity pageBaseEntity) {
        if ( pageBaseEntity == null ) {
            return null;
        }

        PageBaseEntity pageBaseEntity1 = new PageBaseEntity();

        pageBaseEntity1.setPageNo( pageBaseEntity.getPageNo() );
        pageBaseEntity1.setPageSize( pageBaseEntity.getPageSize() );
        pageBaseEntity1.setTotal( pageBaseEntity.getTotal() );
        pageBaseEntity1.setPages( pageBaseEntity.getPages() );

        return pageBaseEntity1;
    }
}
