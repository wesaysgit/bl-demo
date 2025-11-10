package com.person;

import java.util.*;

public class TaxCalculator {

    // 七级累进税率表
    static class TaxBracket {
        double threshold; // 起点
        double rate;      // 税率
        double quickDeduction; // 速算扣除数

        TaxBracket(double threshold, double rate, double quickDeduction) {
            this.threshold = threshold;
            this.rate = rate;
            this.quickDeduction = quickDeduction;
        }
    }

    static final TaxBracket[] taxBrackets = {
        new TaxBracket(0, 0.03, 0),
        new TaxBracket(36000, 0.10, 2520),
        new TaxBracket(144000, 0.20, 16920),
        new TaxBracket(300000, 0.25, 31920),
        new TaxBracket(420000, 0.30, 52920),
        new TaxBracket(660000, 0.35, 85920),
        new TaxBracket(960000, 0.45, 181920)
    };

    static double calculateTax(double cumulativeTaxableIncome) {
        for (int i = taxBrackets.length - 1; i >= 0; i--) {
            if (cumulativeTaxableIncome > taxBrackets[i].threshold) {
                return cumulativeTaxableIncome * taxBrackets[i].rate - taxBrackets[i].quickDeduction;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入连续月份工资
        System.out.println("请输入每月工资，用空格分隔，例如：12000 13000 12500：");
        String[] parts = scanner.nextLine().trim().split("\\s+");

        double[] monthlySalaries = Arrays.stream(parts)
                .mapToDouble(Double::parseDouble)
                .toArray();

        final double deductionPerMonth = 5000;
        final double socialSecurity = 679.84;

        double cumulativeSalary = 0;
        double cumulativeDeduction = 0;
        double cumulativeTaxPaid = 0;

        System.out.printf("%-6s %-10s %-10s %-12s %-10s %-10s%n", "月份", "应发工资", "累计工资", "累计应纳税额", "个税", "实发工资");
        for (int i = 0; i < monthlySalaries.length; i++) {
            double salary = monthlySalaries[i];
            cumulativeSalary += salary;
            cumulativeDeduction += deductionPerMonth + socialSecurity;
            double cumulativeTaxableIncome = cumulativeSalary - cumulativeDeduction;
            cumulativeTaxableIncome = Math.max(cumulativeTaxableIncome, 0); // 不可为负

            double totalTaxShouldPay = calculateTax(cumulativeTaxableIncome);
            double currentTax = totalTaxShouldPay - cumulativeTaxPaid;
            cumulativeTaxPaid = totalTaxShouldPay;

            double netSalary = salary - currentTax - socialSecurity;

            System.out.printf("%-6d %-10.2f %-10.2f %-12.2f %-10.2f %-10.2f%n",
                    (i + 1), salary, cumulativeSalary, cumulativeTaxableIncome, currentTax, netSalary);
        }

        scanner.close();
    }
}
