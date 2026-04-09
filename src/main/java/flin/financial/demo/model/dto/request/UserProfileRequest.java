package flin.financial.demo.model.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileRequest {

    private String fullName;

    private BigInteger monthlyIncome;

    private BigInteger monthlyExpenses;

    private BigInteger totalDebt;

    private BigInteger totalSaving;


}
