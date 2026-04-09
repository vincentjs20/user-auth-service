package flin.financial.demo.model;


import flin.financial.demo.util.BigIntegerEncryptConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "flin")
public class MUser implements UserDetails {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "monthly_income")
    @Convert(converter = BigIntegerEncryptConverter.class)
    private BigInteger monthlyIncome;

    @Column(name = "monthly_expenses")
    @Convert(converter = BigIntegerEncryptConverter.class)
    private BigInteger monthlyExpenses;

    @Column(name = "total_debt")
    @Convert(converter = BigIntegerEncryptConverter.class)
    private BigInteger totalDebt;

    @Column(name = "total_savings")
    @Convert(converter = BigIntegerEncryptConverter.class)
    private BigInteger totalSaving;

    @Column(name = "financial_health_score")
    private Integer financialHealthScore;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
