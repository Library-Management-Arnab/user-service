package com.lms.us.rest.model.auth;

import com.lms.svc.common.util.CommonUtil;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Data
@Entity
@Table(name="auth_scope")
public class Scope {
    public Scope() {
        this.scopeId = String.format("SCO%s", CommonUtil.generateId());
    }
    @Id
    @Column(length = 30)
    private String scopeId;

    @Column(length = 40, nullable = false)
    private String scopeName;

//    private Set<UserAPIData> userAPIDataList;

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
