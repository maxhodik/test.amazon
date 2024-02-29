package com.example.maxhodik.test.amazon.test.amazon.security;


import com.example.maxhodik.test.amazon.test.amazon.model.User;
import com.example.maxhodik.test.amazon.test.amazon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(userName).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists username=" + userName));
        return SecurityUser.fromUser(user);
    }
}
