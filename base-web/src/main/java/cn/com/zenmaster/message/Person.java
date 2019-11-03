package cn.com.zenmaster.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable  {

    private static final long serialVersionUID = 1941368539867886722L;

    private String name;
    private Integer age;


}
