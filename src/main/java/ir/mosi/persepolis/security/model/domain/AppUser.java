package ir.mosi.persepolis.security.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "appusers" , schema = "persepolis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "appusers_approles",
            joinColumns = @JoinColumn(name = "appusers_id"),
            inverseJoinColumns = @JoinColumn(name = "approles_id")
    )
    private Collection<AppRole> roles = new ArrayList<>();
}
