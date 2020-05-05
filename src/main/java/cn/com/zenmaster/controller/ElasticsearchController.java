//package cn.com.zenmaster.controller;
//
//
//import cn.com.zenmaster.entity.User;
//import cn.com.zenmaster.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//public class ElasticsearchController {
//
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @PostMapping("/createIndex")
//    public Boolean createIndex(@RequestParam String indexName) {
//        return elasticsearchTemplate.createIndex(indexName);
//    }
//
//    @DeleteMapping("/deleteIndex")
//    public Boolean deleteIndex(@RequestParam String indexName) {
//        return elasticsearchTemplate.deleteIndex(indexName);
//    }
//
//    @GetMapping("createIndex")
//    public Boolean createIndex() {
//        return elasticsearchTemplate.createIndex(User.class);
//    }
//
//    @GetMapping("/user/{nickname}")
//    public List<User> findByNickname(@PathVariable String nickname) {
//        return userRepository.findByNickname(nickname);
//    }
//
//    @PostMapping("/user/save")
//    public User save(@RequestBody User user) {
//        return userRepository.save(user);
//    }
//
//
//}
