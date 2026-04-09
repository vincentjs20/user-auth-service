package flin.financial.demo.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {

    private String fullName;

    private BigInteger monthlyIncome;

    private BigInteger monthlyExpenses;

    private BigInteger totalDebt;

    private BigInteger totalSaving;

    private Integer financialHealthScore;

}
