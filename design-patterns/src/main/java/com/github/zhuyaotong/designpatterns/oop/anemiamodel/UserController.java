package com.github.zhuyaotong.designpatterns.oop.anemiamodel;

////////// Controller+VO(View Object) //////////
class UserController {
//    private UserService userService; //通过构造函数或者IOC框架注入
//
//    public UserVo getUserById(Long userId) {
//        UserBo userBo = userService.getUserById(userId);
//        UserVo userVo = [...convert userBo to userVo...];
//        return userVo;
//    }
}

class UserVo {//省略其他属性、get/set/construct方法
    private Long id;
    private String name;
    private String cellphone;
}

////////// Service+BO(Business Object) //////////
class UserService {
    private UserRepository userRepository; //通过构造函数或者IOC框架注入

//    public UserBo getUserById(Long userId) {
//        UserEntity userEntity = userRepository.getUserById(userId);
//        UserBo userBo = [...convert userEntity to userBo...];
//        return userBo;
//    }
}

class UserBo {//省略其他属性、get/set/construct方法
    private Long id;
    private String name;
    private String cellphone;
}

////////// Repository+Entity //////////
class UserRepository {
//    public UserEntity getUserById(Long userId) {
//        //...
//    }
}

class UserEntity {//省略其他属性、get/set/construct方法
    private Long id;
    private String name;
    private String cellphone;
}
