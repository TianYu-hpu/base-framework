package cn.com.zenmaster.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Document(indexName = "user_index", type = "src/test/resources/user")
public class User implements Serializable {

    private static final long serialVersionUID = 8143386626083694084L;

    private Integer id;

    @Field(type = FieldType.Keyword)
    private String username;
    @Field(type = FieldType.Keyword)
    private String password;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String nickname;
    @Field(type = FieldType.Keyword)
    private String email;
    @Field(type = FieldType.Integer)
    private Integer status;
    @Field(type = FieldType.Keyword)
    private String createUser;
    @Field(type = FieldType.Keyword)
    private String updateUser;
    @Field(type = FieldType.Double)
    private Double age;

}
