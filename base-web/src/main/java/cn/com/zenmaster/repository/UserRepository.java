package cn.com.zenmaster.repository;

import cn.com.zenmaster.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<User, Integer> {

    /**
     * 根据昵称来查找客户
     * @param nickname
     * @return
     */
    List<User> findByNickname(String nickname);


}
