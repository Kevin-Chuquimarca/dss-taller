package ec.edu.espe.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "user", schema = "dss_taller")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 40)
    private String name;
    @Basic
    @Column(name = "email", nullable = false, length = 30)
    private String email;
    @Basic
    @Column(name = "password", nullable = false)
    private String password;
    @Basic
    @Column(name = "phone", nullable = false, length = 10)
    private String phone;
    @Basic
    @Column(name = "roles", nullable = false)
    private String roles;
}
