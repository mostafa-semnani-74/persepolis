package ir.mosi.persepolis.security.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "approles" , schema = "persepolis")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<AppUser> users = new ArrayList<>();
}
