package cn.com.zenmaster.entity.mapstruct;

import cn.com.zenmaster.entity.base.PageBaseEntity;
import com.github.pagehelper.PageInfo;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-04T19:31:55+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
public class PageInfoMapImpl implements PageInfoMap {

    @Override
    public PageBaseEntity pageInfoToPageVo(PageInfo pageInfo) {
        if ( pageInfo == null ) {
            return null;
        }

        PageBaseEntity pageBaseEntity = new PageBaseEntity();

        pageBaseEntity.setPageSize( pageInfo.getPageSize() );
        pageBaseEntity.setTotal( pageInfo.getTotal() );
        pageBaseEntity.setPages( pageInfo.getPages() );
        pageBaseEntity.setPageNo( pageInfo.getPageNum() );

        return pageBaseEntity;
    }
}
