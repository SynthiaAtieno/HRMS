package com.example.erpnext.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaySlip {

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

public class Data {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("creation")
    @Expose
    private String creation;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("modified_by")
    @Expose
    private String modifiedBy;
    @SerializedName("idx")
    @Expose
    private Integer idx;
    @SerializedName("docstatus")
    @Expose
    private Integer docstatus;
    @SerializedName("posting_date")
    @Expose
    private String postingDate;
    @SerializedName("employee")
    @Expose
    private String employee;
    @SerializedName("employee_name")
    @Expose
    private String employeeName;
    @SerializedName("payroll_cost_center")
    @Expose
    private String payrollCostCenter;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("journal_entry")
    @Expose
    private String journalEntry;
    @SerializedName("payroll_entry")
    @Expose
    private String payrollEntry;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("exchange_rate")
    @Expose
    private Double exchangeRate;
    @SerializedName("letter_head")
    @Expose
    private String letterHead;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("salary_structure")
    @Expose
    private String salaryStructure;
    @SerializedName("salary_slip_based_on_timesheet")
    @Expose
    private Integer salarySlipBasedOnTimesheet;
    @SerializedName("payroll_frequency")
    @Expose
    private String payrollFrequency;
    @SerializedName("total_working_days")
    @Expose
    private Double totalWorkingDays;
    @SerializedName("unmarked_days")
    @Expose
    private Double unmarkedDays;
    @SerializedName("leave_without_pay")
    @Expose
    private Double leaveWithoutPay;
    @SerializedName("absent_days")
    @Expose
    private Double absentDays;
    @SerializedName("payment_days")
    @Expose
    private Double paymentDays;
    @SerializedName("total_working_hours")
    @Expose
    private Double totalWorkingHours;
    @SerializedName("hour_rate")
    @Expose
    private Double hourRate;
    @SerializedName("base_hour_rate")
    @Expose
    private Double baseHourRate;
    @SerializedName("mode_of_payment")
    @Expose
    private String modeOfPayment;
    @SerializedName("deduct_tax_for_unclaimed_employee_benefits")
    @Expose
    private Integer deductTaxForUnclaimedEmployeeBenefits;
    @SerializedName("deduct_tax_for_unsubmitted_tax_exemption_proof")
    @Expose
    private Integer deductTaxForUnsubmittedTaxExemptionProof;
    @SerializedName("gross_pay")
    @Expose
    private Double grossPay;
    @SerializedName("base_gross_pay")
    @Expose
    private Double baseGrossPay;
    @SerializedName("gross_year_to_date")
    @Expose
    private Double grossYearToDate;
    @SerializedName("base_gross_year_to_date")
    @Expose
    private Double baseGrossYearToDate;
    @SerializedName("total_deduction")
    @Expose
    private Double totalDeduction;
    @SerializedName("base_total_deduction")
    @Expose
    private Double baseTotalDeduction;
    @SerializedName("total_principal_amount")
    @Expose
    private Double totalPrincipalAmount;
    @SerializedName("total_interest_amount")
    @Expose
    private Double totalInterestAmount;
    @SerializedName("total_loan_repayment")
    @Expose
    private Double totalLoanRepayment;
    @SerializedName("net_pay")
    @Expose
    private Double netPay;
    @SerializedName("base_net_pay")
    @Expose
    private Double baseNetPay;
    @SerializedName("year_to_date")
    @Expose
    private Double yearToDate;
    @SerializedName("base_year_to_date")
    @Expose
    private Double baseYearToDate;
    @SerializedName("rounded_total")
    @Expose
    private Double roundedTotal;
    @SerializedName("base_rounded_total")
    @Expose
    private Double baseRoundedTotal;
    @SerializedName("month_to_date")
    @Expose
    private Double monthToDate;
    @SerializedName("base_month_to_date")
    @Expose
    private Double baseMonthToDate;
    @SerializedName("total_in_words")
    @Expose
    private String totalInWords;
    @SerializedName("base_total_in_words")
    @Expose
    private String baseTotalInWords;
    @SerializedName("doctype")
    @Expose
    private String doctype;
    @SerializedName("timesheets")
    @Expose
    private List<Object> timesheets;
    @SerializedName("earnings")
    @Expose
    private List<Earning> earnings;
    @SerializedName("deductions")
    @Expose
    private List<Deduction> deductions;
    @SerializedName("loans")
    @Expose
    private List<Object> loans;
    @SerializedName("leave_details")
    @Expose
    private List<Object> leaveDetails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreation() {
        return creation;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Integer getDocstatus() {
        return docstatus;
    }

    public void setDocstatus(Integer docstatus) {
        this.docstatus = docstatus;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPayrollCostCenter() {
        return payrollCostCenter;
    }

    public void setPayrollCostCenter(String payrollCostCenter) {
        this.payrollCostCenter = payrollCostCenter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJournalEntry() {
        return journalEntry;
    }

    public void setJournalEntry(String journalEntry) {
        this.journalEntry = journalEntry;
    }

    public String getPayrollEntry() {
        return payrollEntry;
    }

    public void setPayrollEntry(String payrollEntry) {
        this.payrollEntry = payrollEntry;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getLetterHead() {
        return letterHead;
    }

    public void setLetterHead(String letterHead) {
        this.letterHead = letterHead;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSalaryStructure() {
        return salaryStructure;
    }

    public void setSalaryStructure(String salaryStructure) {
        this.salaryStructure = salaryStructure;
    }

    public Integer getSalarySlipBasedOnTimesheet() {
        return salarySlipBasedOnTimesheet;
    }

    public void setSalarySlipBasedOnTimesheet(Integer salarySlipBasedOnTimesheet) {
        this.salarySlipBasedOnTimesheet = salarySlipBasedOnTimesheet;
    }

    public String getPayrollFrequency() {
        return payrollFrequency;
    }

    public void setPayrollFrequency(String payrollFrequency) {
        this.payrollFrequency = payrollFrequency;
    }

    public Double getTotalWorkingDays() {
        return totalWorkingDays;
    }

    public void setTotalWorkingDays(Double totalWorkingDays) {
        this.totalWorkingDays = totalWorkingDays;
    }

    public Double getUnmarkedDays() {
        return unmarkedDays;
    }

    public void setUnmarkedDays(Double unmarkedDays) {
        this.unmarkedDays = unmarkedDays;
    }

    public Double getLeaveWithoutPay() {
        return leaveWithoutPay;
    }

    public void setLeaveWithoutPay(Double leaveWithoutPay) {
        this.leaveWithoutPay = leaveWithoutPay;
    }

    public Double getAbsentDays() {
        return absentDays;
    }

    public void setAbsentDays(Double absentDays) {
        this.absentDays = absentDays;
    }

    public Double getPaymentDays() {
        return paymentDays;
    }

    public void setPaymentDays(Double paymentDays) {
        this.paymentDays = paymentDays;
    }

    public Double getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public void setTotalWorkingHours(Double totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }

    public Double getHourRate() {
        return hourRate;
    }

    public void setHourRate(Double hourRate) {
        this.hourRate = hourRate;
    }

    public Double getBaseHourRate() {
        return baseHourRate;
    }

    public void setBaseHourRate(Double baseHourRate) {
        this.baseHourRate = baseHourRate;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public Integer getDeductTaxForUnclaimedEmployeeBenefits() {
        return deductTaxForUnclaimedEmployeeBenefits;
    }

    public void setDeductTaxForUnclaimedEmployeeBenefits(Integer deductTaxForUnclaimedEmployeeBenefits) {
        this.deductTaxForUnclaimedEmployeeBenefits = deductTaxForUnclaimedEmployeeBenefits;
    }

    public Integer getDeductTaxForUnsubmittedTaxExemptionProof() {
        return deductTaxForUnsubmittedTaxExemptionProof;
    }

    public void setDeductTaxForUnsubmittedTaxExemptionProof(Integer deductTaxForUnsubmittedTaxExemptionProof) {
        this.deductTaxForUnsubmittedTaxExemptionProof = deductTaxForUnsubmittedTaxExemptionProof;
    }

    public Double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(Double grossPay) {
        this.grossPay = grossPay;
    }

    public Double getBaseGrossPay() {
        return baseGrossPay;
    }

    public void setBaseGrossPay(Double baseGrossPay) {
        this.baseGrossPay = baseGrossPay;
    }

    public Double getGrossYearToDate() {
        return grossYearToDate;
    }

    public void setGrossYearToDate(Double grossYearToDate) {
        this.grossYearToDate = grossYearToDate;
    }

    public Double getBaseGrossYearToDate() {
        return baseGrossYearToDate;
    }

    public void setBaseGrossYearToDate(Double baseGrossYearToDate) {
        this.baseGrossYearToDate = baseGrossYearToDate;
    }

    public Double getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(Double totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    public Double getBaseTotalDeduction() {
        return baseTotalDeduction;
    }

    public void setBaseTotalDeduction(Double baseTotalDeduction) {
        this.baseTotalDeduction = baseTotalDeduction;
    }

    public Double getTotalPrincipalAmount() {
        return totalPrincipalAmount;
    }

    public void setTotalPrincipalAmount(Double totalPrincipalAmount) {
        this.totalPrincipalAmount = totalPrincipalAmount;
    }

    public Double getTotalInterestAmount() {
        return totalInterestAmount;
    }

    public void setTotalInterestAmount(Double totalInterestAmount) {
        this.totalInterestAmount = totalInterestAmount;
    }

    public Double getTotalLoanRepayment() {
        return totalLoanRepayment;
    }

    public void setTotalLoanRepayment(Double totalLoanRepayment) {
        this.totalLoanRepayment = totalLoanRepayment;
    }

    public Double getNetPay() {
        return netPay;
    }

    public void setNetPay(Double netPay) {
        this.netPay = netPay;
    }

    public Double getBaseNetPay() {
        return baseNetPay;
    }

    public void setBaseNetPay(Double baseNetPay) {
        this.baseNetPay = baseNetPay;
    }

    public Double getYearToDate() {
        return yearToDate;
    }

    public void setYearToDate(Double yearToDate) {
        this.yearToDate = yearToDate;
    }

    public Double getBaseYearToDate() {
        return baseYearToDate;
    }

    public void setBaseYearToDate(Double baseYearToDate) {
        this.baseYearToDate = baseYearToDate;
    }

    public Double getRoundedTotal() {
        return roundedTotal;
    }

    public void setRoundedTotal(Double roundedTotal) {
        this.roundedTotal = roundedTotal;
    }

    public Double getBaseRoundedTotal() {
        return baseRoundedTotal;
    }

    public void setBaseRoundedTotal(Double baseRoundedTotal) {
        this.baseRoundedTotal = baseRoundedTotal;
    }

    public Double getMonthToDate() {
        return monthToDate;
    }

    public void setMonthToDate(Double monthToDate) {
        this.monthToDate = monthToDate;
    }

    public Double getBaseMonthToDate() {
        return baseMonthToDate;
    }

    public void setBaseMonthToDate(Double baseMonthToDate) {
        this.baseMonthToDate = baseMonthToDate;
    }

    public String getTotalInWords() {
        return totalInWords;
    }

    public void setTotalInWords(String totalInWords) {
        this.totalInWords = totalInWords;
    }

    public String getBaseTotalInWords() {
        return baseTotalInWords;
    }

    public void setBaseTotalInWords(String baseTotalInWords) {
        this.baseTotalInWords = baseTotalInWords;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public List<Object> getTimesheets() {
        return timesheets;
    }

    public void setTimesheets(List<Object> timesheets) {
        this.timesheets = timesheets;
    }

    public List<Earning> getEarnings() {
        return earnings;
    }

    public void setEarnings(List<Earning> earnings) {
        this.earnings = earnings;
    }

    public List<Deduction> getDeductions() {
        return deductions;
    }

    public void setDeductions(List<Deduction> deductions) {
        this.deductions = deductions;
    }

    public List<Object> getLoans() {
        return loans;
    }

    public void setLoans(List<Object> loans) {
        this.loans = loans;
    }

    public List<Object> getLeaveDetails() {
        return leaveDetails;
    }

    public void setLeaveDetails(List<Object> leaveDetails) {
        this.leaveDetails = leaveDetails;
    }

}

public class Deduction {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("creation")
    @Expose
    private String creation;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("modified_by")
    @Expose
    private String modifiedBy;
    @SerializedName("parent")
    @Expose
    private String parent;
    @SerializedName("parentfield")
    @Expose
    private String parentfield;
    @SerializedName("parenttype")
    @Expose
    private String parenttype;
    @SerializedName("idx")
    @Expose
    private Integer idx;
    @SerializedName("docstatus")
    @Expose
    private Integer docstatus;
    @SerializedName("salary_component")
    @Expose
    private String salaryComponent;
    @SerializedName("abbr")
    @Expose
    private String abbr;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("year_to_date")
    @Expose
    private Double yearToDate;
    @SerializedName("is_recurring_additional_salary")
    @Expose
    private Integer isRecurringAdditionalSalary;
    @SerializedName("statistical_component")
    @Expose
    private Integer statisticalComponent;
    @SerializedName("depends_on_payment_days")
    @Expose
    private Integer dependsOnPaymentDays;
    @SerializedName("exempted_from_income_tax")
    @Expose
    private Integer exemptedFromIncomeTax;
    @SerializedName("is_tax_applicable")
    @Expose
    private Integer isTaxApplicable;
    @SerializedName("is_flexible_benefit")
    @Expose
    private Integer isFlexibleBenefit;
    @SerializedName("variable_based_on_taxable_salary")
    @Expose
    private Integer variableBasedOnTaxableSalary;
    @SerializedName("do_not_include_in_total")
    @Expose
    private Integer doNotIncludeInTotal;
    @SerializedName("deduct_full_tax_on_selected_payroll_date")
    @Expose
    private Integer deductFullTaxOnSelectedPayrollDate;
    @SerializedName("amount_based_on_formula")
    @Expose
    private Integer amountBasedOnFormula;
    @SerializedName("default_amount")
    @Expose
    private Double defaultAmount;
    @SerializedName("additional_amount")
    @Expose
    private Double additionalAmount;
    @SerializedName("tax_on_flexible_benefit")
    @Expose
    private Double taxOnFlexibleBenefit;
    @SerializedName("tax_on_additional_salary")
    @Expose
    private Double taxOnAdditionalSalary;
    @SerializedName("doctype")
    @Expose
    private String doctype;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreation() {
        return creation;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getParentfield() {
        return parentfield;
    }

    public void setParentfield(String parentfield) {
        this.parentfield = parentfield;
    }

    public String getParenttype() {
        return parenttype;
    }

    public void setParenttype(String parenttype) {
        this.parenttype = parenttype;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Integer getDocstatus() {
        return docstatus;
    }

    public void setDocstatus(Integer docstatus) {
        this.docstatus = docstatus;
    }

    public String getSalaryComponent() {
        return salaryComponent;
    }

    public void setSalaryComponent(String salaryComponent) {
        this.salaryComponent = salaryComponent;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getYearToDate() {
        return yearToDate;
    }

    public void setYearToDate(Double yearToDate) {
        this.yearToDate = yearToDate;
    }

    public Integer getIsRecurringAdditionalSalary() {
        return isRecurringAdditionalSalary;
    }

    public void setIsRecurringAdditionalSalary(Integer isRecurringAdditionalSalary) {
        this.isRecurringAdditionalSalary = isRecurringAdditionalSalary;
    }

    public Integer getStatisticalComponent() {
        return statisticalComponent;
    }

    public void setStatisticalComponent(Integer statisticalComponent) {
        this.statisticalComponent = statisticalComponent;
    }

    public Integer getDependsOnPaymentDays() {
        return dependsOnPaymentDays;
    }

    public void setDependsOnPaymentDays(Integer dependsOnPaymentDays) {
        this.dependsOnPaymentDays = dependsOnPaymentDays;
    }

    public Integer getExemptedFromIncomeTax() {
        return exemptedFromIncomeTax;
    }

    public void setExemptedFromIncomeTax(Integer exemptedFromIncomeTax) {
        this.exemptedFromIncomeTax = exemptedFromIncomeTax;
    }

    public Integer getIsTaxApplicable() {
        return isTaxApplicable;
    }

    public void setIsTaxApplicable(Integer isTaxApplicable) {
        this.isTaxApplicable = isTaxApplicable;
    }

    public Integer getIsFlexibleBenefit() {
        return isFlexibleBenefit;
    }

    public void setIsFlexibleBenefit(Integer isFlexibleBenefit) {
        this.isFlexibleBenefit = isFlexibleBenefit;
    }

    public Integer getVariableBasedOnTaxableSalary() {
        return variableBasedOnTaxableSalary;
    }

    public void setVariableBasedOnTaxableSalary(Integer variableBasedOnTaxableSalary) {
        this.variableBasedOnTaxableSalary = variableBasedOnTaxableSalary;
    }

    public Integer getDoNotIncludeInTotal() {
        return doNotIncludeInTotal;
    }

    public void setDoNotIncludeInTotal(Integer doNotIncludeInTotal) {
        this.doNotIncludeInTotal = doNotIncludeInTotal;
    }

    public Integer getDeductFullTaxOnSelectedPayrollDate() {
        return deductFullTaxOnSelectedPayrollDate;
    }

    public void setDeductFullTaxOnSelectedPayrollDate(Integer deductFullTaxOnSelectedPayrollDate) {
        this.deductFullTaxOnSelectedPayrollDate = deductFullTaxOnSelectedPayrollDate;
    }

    public Integer getAmountBasedOnFormula() {
        return amountBasedOnFormula;
    }

    public void setAmountBasedOnFormula(Integer amountBasedOnFormula) {
        this.amountBasedOnFormula = amountBasedOnFormula;
    }

    public Double getDefaultAmount() {
        return defaultAmount;
    }

    public void setDefaultAmount(Double defaultAmount) {
        this.defaultAmount = defaultAmount;
    }

    public Double getAdditionalAmount() {
        return additionalAmount;
    }

    public void setAdditionalAmount(Double additionalAmount) {
        this.additionalAmount = additionalAmount;
    }

    public Double getTaxOnFlexibleBenefit() {
        return taxOnFlexibleBenefit;
    }

    public void setTaxOnFlexibleBenefit(Double taxOnFlexibleBenefit) {
        this.taxOnFlexibleBenefit = taxOnFlexibleBenefit;
    }

    public Double getTaxOnAdditionalSalary() {
        return taxOnAdditionalSalary;
    }

    public void setTaxOnAdditionalSalary(Double taxOnAdditionalSalary) {
        this.taxOnAdditionalSalary = taxOnAdditionalSalary;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

}

public class Earning {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("creation")
    @Expose
    private String creation;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("modified_by")
    @Expose
    private String modifiedBy;
    @SerializedName("parent")
    @Expose
    private String parent;
    @SerializedName("parentfield")
    @Expose
    private String parentfield;
    @SerializedName("parenttype")
    @Expose
    private String parenttype;
    @SerializedName("idx")
    @Expose
    private Integer idx;
    @SerializedName("docstatus")
    @Expose
    private Integer docstatus;
    @SerializedName("salary_component")
    @Expose
    private String salaryComponent;
    @SerializedName("abbr")
    @Expose
    private String abbr;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("year_to_date")
    @Expose
    private Double yearToDate;
    @SerializedName("is_recurring_additional_salary")
    @Expose
    private Integer isRecurringAdditionalSalary;
    @SerializedName("statistical_component")
    @Expose
    private Integer statisticalComponent;
    @SerializedName("depends_on_payment_days")
    @Expose
    private Integer dependsOnPaymentDays;
    @SerializedName("exempted_from_income_tax")
    @Expose
    private Integer exemptedFromIncomeTax;
    @SerializedName("is_tax_applicable")
    @Expose
    private Integer isTaxApplicable;
    @SerializedName("is_flexible_benefit")
    @Expose
    private Integer isFlexibleBenefit;
    @SerializedName("variable_based_on_taxable_salary")
    @Expose
    private Integer variableBasedOnTaxableSalary;
    @SerializedName("do_not_include_in_total")
    @Expose
    private Integer doNotIncludeInTotal;
    @SerializedName("deduct_full_tax_on_selected_payroll_date")
    @Expose
    private Integer deductFullTaxOnSelectedPayrollDate;
    @SerializedName("amount_based_on_formula")
    @Expose
    private Integer amountBasedOnFormula;
    @SerializedName("default_amount")
    @Expose
    private Double defaultAmount;
    @SerializedName("additional_amount")
    @Expose
    private Double additionalAmount;
    @SerializedName("tax_on_flexible_benefit")
    @Expose
    private Double taxOnFlexibleBenefit;
    @SerializedName("tax_on_additional_salary")
    @Expose
    private Double taxOnAdditionalSalary;
    @SerializedName("doctype")
    @Expose
    private String doctype;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCreation() {
        return creation;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getParentfield() {
        return parentfield;
    }

    public void setParentfield(String parentfield) {
        this.parentfield = parentfield;
    }

    public String getParenttype() {
        return parenttype;
    }

    public void setParenttype(String parenttype) {
        this.parenttype = parenttype;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Integer getDocstatus() {
        return docstatus;
    }

    public void setDocstatus(Integer docstatus) {
        this.docstatus = docstatus;
    }

    public String getSalaryComponent() {
        return salaryComponent;
    }

    public void setSalaryComponent(String salaryComponent) {
        this.salaryComponent = salaryComponent;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getYearToDate() {
        return yearToDate;
    }

    public void setYearToDate(Double yearToDate) {
        this.yearToDate = yearToDate;
    }

    public Integer getIsRecurringAdditionalSalary() {
        return isRecurringAdditionalSalary;
    }

    public void setIsRecurringAdditionalSalary(Integer isRecurringAdditionalSalary) {
        this.isRecurringAdditionalSalary = isRecurringAdditionalSalary;
    }

    public Integer getStatisticalComponent() {
        return statisticalComponent;
    }

    public void setStatisticalComponent(Integer statisticalComponent) {
        this.statisticalComponent = statisticalComponent;
    }

    public Integer getDependsOnPaymentDays() {
        return dependsOnPaymentDays;
    }

    public void setDependsOnPaymentDays(Integer dependsOnPaymentDays) {
        this.dependsOnPaymentDays = dependsOnPaymentDays;
    }

    public Integer getExemptedFromIncomeTax() {
        return exemptedFromIncomeTax;
    }

    public void setExemptedFromIncomeTax(Integer exemptedFromIncomeTax) {
        this.exemptedFromIncomeTax = exemptedFromIncomeTax;
    }

    public Integer getIsTaxApplicable() {
        return isTaxApplicable;
    }

    public void setIsTaxApplicable(Integer isTaxApplicable) {
        this.isTaxApplicable = isTaxApplicable;
    }

    public Integer getIsFlexibleBenefit() {
        return isFlexibleBenefit;
    }

    public void setIsFlexibleBenefit(Integer isFlexibleBenefit) {
        this.isFlexibleBenefit = isFlexibleBenefit;
    }

    public Integer getVariableBasedOnTaxableSalary() {
        return variableBasedOnTaxableSalary;
    }

    public void setVariableBasedOnTaxableSalary(Integer variableBasedOnTaxableSalary) {
        this.variableBasedOnTaxableSalary = variableBasedOnTaxableSalary;
    }

    public Integer getDoNotIncludeInTotal() {
        return doNotIncludeInTotal;
    }

    public void setDoNotIncludeInTotal(Integer doNotIncludeInTotal) {
        this.doNotIncludeInTotal = doNotIncludeInTotal;
    }

    public Integer getDeductFullTaxOnSelectedPayrollDate() {
        return deductFullTaxOnSelectedPayrollDate;
    }

    public void setDeductFullTaxOnSelectedPayrollDate(Integer deductFullTaxOnSelectedPayrollDate) {
        this.deductFullTaxOnSelectedPayrollDate = deductFullTaxOnSelectedPayrollDate;
    }

    public Integer getAmountBasedOnFormula() {
        return amountBasedOnFormula;
    }

    public void setAmountBasedOnFormula(Integer amountBasedOnFormula) {
        this.amountBasedOnFormula = amountBasedOnFormula;
    }

    public Double getDefaultAmount() {
        return defaultAmount;
    }

    public void setDefaultAmount(Double defaultAmount) {
        this.defaultAmount = defaultAmount;
    }

    public Double getAdditionalAmount() {
        return additionalAmount;
    }

    public void setAdditionalAmount(Double additionalAmount) {
        this.additionalAmount = additionalAmount;
    }

    public Double getTaxOnFlexibleBenefit() {
        return taxOnFlexibleBenefit;
    }

    public void setTaxOnFlexibleBenefit(Double taxOnFlexibleBenefit) {
        this.taxOnFlexibleBenefit = taxOnFlexibleBenefit;
    }

    public Double getTaxOnAdditionalSalary() {
        return taxOnAdditionalSalary;
    }

    public void setTaxOnAdditionalSalary(Double taxOnAdditionalSalary) {
        this.taxOnAdditionalSalary = taxOnAdditionalSalary;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

}



}