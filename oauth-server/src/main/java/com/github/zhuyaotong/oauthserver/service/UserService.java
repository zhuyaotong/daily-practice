package com.github.zhuyaotong.oauthserver.service;

import com.github.zhuyaotong.oauthserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private List<User> userList;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initData() {
        String password = passwordEncoder.encode("123456");
        userList = new ArrayList<>();
        userList.add(new User("macro", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
        userList.add(new User("zyt", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
        userList.add(new User("andy", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
        userList.add(new User("jay", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
    }

    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
        List<User> result = userList.stream()
                .filter(user -> user.getUsername().equals(s)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(result)) {
            return result.get(0);
        } else {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
    }

}
