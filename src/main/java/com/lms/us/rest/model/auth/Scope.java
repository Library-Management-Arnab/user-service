package com.lms.us.rest.model.auth;

import com.lms.svc.common.constants.ApplicationCommonConstants;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table(name="auth_scope")
public class Scope {
    public Scope() {
        this.scopeId = String.format("SCO%s", ApplicationCommonConstants.generateId());
    }
    @Id
    @Column(length = 30)
    private String scopeId;

    @Column(length = 40, nullable = false)
    private String scopeName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "api_data_scope_mapping",
            joinColumns = @JoinColumn(name = "api_record_id"),
            inverseJoinColumns = @JoinColumn(name = "scope_id"))
    private Set<UserAPIData> userAPIDataList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scope scope = (Scope) o;
        return Objects.equals(scopeName, scope.scopeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scopeName);
    }
}
