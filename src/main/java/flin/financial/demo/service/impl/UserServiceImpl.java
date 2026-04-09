package flin.financial.demo.service.impl;

import flin.financial.demo.model.MUser;
import flin.financial.demo.model.dto.request.UserLoginRequest;
import flin.financial.demo.model.dto.request.UserProfileRequest;
import flin.financial.demo.model.dto.request.UserRegisterRequest;
import flin.financial.demo.model.dto.response.LoginResponse;
import flin.financial.demo.model.dto.response.UserProfileResponse;
import flin.financial.demo.model.dto.response.UserRegisterResponse;
import flin.financial.demo.repository.UserRepository;
import flin.financial.demo.service.AuthService;
import flin.financial.demo.service.JwtService;
import flin.financial.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserProfileResponse updateProfile(UUID userId, UserProfileRequest request) {
        MUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getFullName() != null) {
            user.setFullName(request.getFullName());
        }

        if (request.getMonthlyIncome() != null) {
            user.setMonthlyIncome(request.getMonthlyIncome());
        }

        if (request.getMonthlyExpenses() != null) {
            user.setMonthlyExpenses(request.getMonthlyExpenses());
        }

        if (request.getTotalDebt() != null) {
            user.setTotalDebt(request.getTotalDebt());
        }

        if (request.getTotalSaving() != null) {
            user.setTotalSaving(request.getTotalSaving());
        }

        user.setFinancialHealthScore(calculateScore(user));

        userRepository.save(user);

        return mapToResponse(user);
    }

    @Override
    public UserProfileResponse getProfileById(UUID userId) {
        MUser user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);
    }

    private Integer calculateScore(MUser user) {
        BigInteger income = defaultZero(user.getMonthlyIncome());
        BigInteger expenses = defaultZero(user.getMonthlyExpenses());
        BigInteger debt = defaultZero(user.getTotalDebt());
        BigInteger savings = defaultZero(user.getTotalSaving());

        if (income.equals(BigInteger.ZERO)) return 0;

        int savingsScore = savings.multiply(BigInteger.valueOf(100))
                .divide(income).intValue();

        int expenseScore = expenses.multiply(BigInteger.valueOf(100))
                .divide(income).intValue();

        int debtScore = debt.multiply(BigInteger.valueOf(100))
                .divide(income).intValue();

        int finalScore = 100
                - expenseScore
                - (debtScore / 2)
                + (savingsScore / 2);

        return Math.max(0, Math.min(finalScore, 100));
    }

    private BigInteger defaultZero(BigInteger val) {
        return val == null ? BigInteger.ZERO : val;
    }

    private UserProfileResponse mapToResponse(MUser user) {
        return UserProfileResponse.builder()
                .fullName(user.getFullName())
                .monthlyIncome(user.getMonthlyIncome())
                .monthlyExpenses(user.getMonthlyExpenses())
                .totalDebt(user.getTotalDebt())
                .totalSaving(user.getTotalSaving())
                .financialHealthScore(user.getFinancialHealthScore())
                .build();
    }
}
