package ec.edu.espe.demo.service;

import ec.edu.espe.demo.entity.UserEntity;
import ec.edu.espe.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userDetail = userRepository.findByName(username);
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String createUser(UserEntity userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userRepository.save(userInfo);
        return "User Added Successfully";
    }

    public UserEntity readUserById(Integer id) {
        Optional<UserEntity> userSaved = userRepository.findById(id);
        return userSaved.orElse(null);
    }

    public String updateUser(UserEntity userInfo) {
        Optional<UserEntity> userSaved = userRepository.findById(userInfo.getId());
        if (userSaved.isPresent()) {
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            userRepository.save(userInfo);
            return "User Updated Successfully";
        }
        return "Failed to Update User";
    }

    public String deleteUser(Integer id) {
        userRepository.deleteById(id);
        return "User Deleted Successfully";
    }
}
