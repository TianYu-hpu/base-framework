package cn.com.emindsoft.entity.mapstruct;

import cn.com.emindsoft.entity.base.PageBaseEntity;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author tianyu
 * @date 2018-01-07 15:23:57
 */
@Mapper
public interface PageInfoMap {

    PageInfoMap INSTANCE = Mappers.getMapper(PageInfoMap.class);

    @Mappings({
        @Mapping(source = "total", target = "total"),
        @Mapping(source = "pages", target = "pages"),
        @Mapping(source = "pageNum", target = "pageNo"),
        @Mapping(source = "pageSize", target = "pageSize")
    })
    PageBaseEntity pageInfoToPageVo(PageInfo pageInfo);

}
