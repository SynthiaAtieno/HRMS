package com.example.erpnext.models;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SalarySlipData {
    @SerializedName("docs")
    @Expose
    private List<Doc> docs;
    @SerializedName("docinfo")
    @Expose
    private Docinfo docinfo;
    @SerializedName("_link_titles")
    @Expose
    private LinkTitles linkTitles;

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    public Docinfo getDocinfo() {
        return docinfo;
    }

    public void setDocinfo(Docinfo docinfo) {
        this.docinfo = docinfo;
    }

    public LinkTitles getLinkTitles() {
        return linkTitles;
    }

    public void setLinkTitles(LinkTitles linkTitles) {
        this.linkTitles = linkTitles;
    }


    public static class Doc {

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
        @SerializedName("docstatus")
        @Expose
        private Integer docstatus;
        @SerializedName("idx")
        @Expose
        private Integer idx;
        @SerializedName("employee")
        @Expose
        private String employee;
        @SerializedName("employee_name")
        @Expose
        private String employeeName;
        @SerializedName("department")
        @Expose
        private String department;
        @SerializedName("designation")
        @Expose
        private String designation;
        @SerializedName("posting_date")
        @Expose
        private String postingDate;
        @SerializedName("letter_head")
        @Expose
        private String letterHead;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("company")
        @Expose
        private String company;
        @SerializedName("currency")
        @Expose
        private String currency;
        @SerializedName("exchange_rate")
        @Expose
        private Double exchangeRate;
        @SerializedName("payroll_frequency")
        @Expose
        private String payrollFrequency;
        @SerializedName("start_date")
        @Expose
        private String startDate;
        @SerializedName("end_date")
        @Expose
        private String endDate;
        @SerializedName("salary_structure")
        @Expose
        private String salaryStructure;
        @SerializedName("payroll_entry")
        @Expose
        private String payrollEntry;
        @SerializedName("mode_of_payment")
        @Expose
        private String modeOfPayment;
        @SerializedName("salary_slip_based_on_timesheet")
        @Expose
        private Integer salarySlipBasedOnTimesheet;
        @SerializedName("deduct_tax_for_unclaimed_employee_benefits")
        @Expose
        private Integer deductTaxForUnclaimedEmployeeBenefits;
        @SerializedName("deduct_tax_for_unsubmitted_tax_exemption_proof")
        @Expose
        private Integer deductTaxForUnsubmittedTaxExemptionProof;
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
        @SerializedName("rounded_total")
        @Expose
        private Double roundedTotal;
        @SerializedName("base_rounded_total")
        @Expose
        private Double baseRoundedTotal;
        @SerializedName("year_to_date")
        @Expose
        private Double yearToDate;
        @SerializedName("base_year_to_date")
        @Expose
        private Double baseYearToDate;
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
        @SerializedName("ctc")
        @Expose
        private Double ctc;
        @SerializedName("income_from_other_sources")
        @Expose
        private Double incomeFromOtherSources;
        @SerializedName("total_earnings")
        @Expose
        private Double totalEarnings;
        @SerializedName("non_taxable_earnings")
        @Expose
        private Double nonTaxableEarnings;
        @SerializedName("standard_tax_exemption_amount")
        @Expose
        private Double standardTaxExemptionAmount;
        @SerializedName("tax_exemption_declaration")
        @Expose
        private Double taxExemptionDeclaration;
        @SerializedName("deductions_before_tax_calculation")
        @Expose
        private Double deductionsBeforeTaxCalculation;
        @SerializedName("annual_taxable_amount")
        @Expose
        private Double annualTaxableAmount;
        @SerializedName("income_tax_deducted_till_date")
        @Expose
        private Double incomeTaxDeductedTillDate;
        @SerializedName("current_month_income_tax")
        @Expose
        private Double currentMonthIncomeTax;
        @SerializedName("future_income_tax_deductions")
        @Expose
        private Double futureIncomeTaxDeductions;
        @SerializedName("total_income_tax")
        @Expose
        private Double totalIncomeTax;
        @SerializedName("doctype")
        @Expose
        private String doctype;
        @SerializedName("loans")
        @Expose
        private List<Object> loans;
        @SerializedName("deductions")
        @Expose
        private List<Deduction> deductions;
        @SerializedName("earnings")
        @Expose
        private List<Earning> earnings;
        @SerializedName("timesheets")
        @Expose
        private List<Object> timesheets;
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

        public Integer getDocstatus() {
            return docstatus;
        }

        public void setDocstatus(Integer docstatus) {
            this.docstatus = docstatus;
        }

        public Integer getIdx() {
            return idx;
        }

        public void setIdx(Integer idx) {
            this.idx = idx;
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

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getDesignation() {
            return designation;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public String getPostingDate() {
            return postingDate;
        }

        public void setPostingDate(String postingDate) {
            this.postingDate = postingDate;
        }

        public String getLetterHead() {
            return letterHead;
        }

        public void setLetterHead(String letterHead) {
            this.letterHead = letterHead;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getPayrollFrequency() {
            return payrollFrequency;
        }

        public void setPayrollFrequency(String payrollFrequency) {
            this.payrollFrequency = payrollFrequency;
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

        public String getPayrollEntry() {
            return payrollEntry;
        }

        public void setPayrollEntry(String payrollEntry) {
            this.payrollEntry = payrollEntry;
        }

        public String getModeOfPayment() {
            return modeOfPayment;
        }

        public void setModeOfPayment(String modeOfPayment) {
            this.modeOfPayment = modeOfPayment;
        }

        public Integer getSalarySlipBasedOnTimesheet() {
            return salarySlipBasedOnTimesheet;
        }

        public void setSalarySlipBasedOnTimesheet(Integer salarySlipBasedOnTimesheet) {
            this.salarySlipBasedOnTimesheet = salarySlipBasedOnTimesheet;
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

        public Double getCtc() {
            return ctc;
        }

        public void setCtc(Double ctc) {
            this.ctc = ctc;
        }

        public Double getIncomeFromOtherSources() {
            return incomeFromOtherSources;
        }

        public void setIncomeFromOtherSources(Double incomeFromOtherSources) {
            this.incomeFromOtherSources = incomeFromOtherSources;
        }

        public Double getTotalEarnings() {
            return totalEarnings;
        }

        public void setTotalEarnings(Double totalEarnings) {
            this.totalEarnings = totalEarnings;
        }

        public Double getNonTaxableEarnings() {
            return nonTaxableEarnings;
        }

        public void setNonTaxableEarnings(Double nonTaxableEarnings) {
            this.nonTaxableEarnings = nonTaxableEarnings;
        }

        public Double getStandardTaxExemptionAmount() {
            return standardTaxExemptionAmount;
        }

        public void setStandardTaxExemptionAmount(Double standardTaxExemptionAmount) {
            this.standardTaxExemptionAmount = standardTaxExemptionAmount;
        }

        public Double getTaxExemptionDeclaration() {
            return taxExemptionDeclaration;
        }

        public void setTaxExemptionDeclaration(Double taxExemptionDeclaration) {
            this.taxExemptionDeclaration = taxExemptionDeclaration;
        }

        public Double getDeductionsBeforeTaxCalculation() {
            return deductionsBeforeTaxCalculation;
        }

        public void setDeductionsBeforeTaxCalculation(Double deductionsBeforeTaxCalculation) {
            this.deductionsBeforeTaxCalculation = deductionsBeforeTaxCalculation;
        }

        public Double getAnnualTaxableAmount() {
            return annualTaxableAmount;
        }

        public void setAnnualTaxableAmount(Double annualTaxableAmount) {
            this.annualTaxableAmount = annualTaxableAmount;
        }

        public Double getIncomeTaxDeductedTillDate() {
            return incomeTaxDeductedTillDate;
        }

        public void setIncomeTaxDeductedTillDate(Double incomeTaxDeductedTillDate) {
            this.incomeTaxDeductedTillDate = incomeTaxDeductedTillDate;
        }

        public Double getCurrentMonthIncomeTax() {
            return currentMonthIncomeTax;
        }

        public void setCurrentMonthIncomeTax(Double currentMonthIncomeTax) {
            this.currentMonthIncomeTax = currentMonthIncomeTax;
        }

        public Double getFutureIncomeTaxDeductions() {
            return futureIncomeTaxDeductions;
        }

        public void setFutureIncomeTaxDeductions(Double futureIncomeTaxDeductions) {
            this.futureIncomeTaxDeductions = futureIncomeTaxDeductions;
        }

        public Double getTotalIncomeTax() {
            return totalIncomeTax;
        }

        public void setTotalIncomeTax(Double totalIncomeTax) {
            this.totalIncomeTax = totalIncomeTax;
        }

        public String getDoctype() {
            return doctype;
        }

        public void setDoctype(String doctype) {
            this.doctype = doctype;
        }

        public List<Object> getLoans() {
            return loans;
        }

        public void setLoans(List<Object> loans) {
            this.loans = loans;
        }

        public List<Deduction> getDeductions() {
            return deductions;
        }

        public void setDeductions(List<Deduction> deductions) {
            this.deductions = deductions;
        }

        public List<Earning> getEarnings() {
            return earnings;
        }

        public void setEarnings(List<Earning> earnings) {
            this.earnings = earnings;
        }

        public List<Object> getTimesheets() {
            return timesheets;
        }

        public void setTimesheets(List<Object> timesheets) {
            this.timesheets = timesheets;
        }

        public List<Object> getLeaveDetails() {
            return leaveDetails;
        }

        public void setLeaveDetails(List<Object> leaveDetails) {
            this.leaveDetails = leaveDetails;
        }

    }

    public static class Deduction {

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
        @SerializedName("docstatus")
        @Expose
        private Integer docstatus;
        @SerializedName("idx")
        @Expose
        private Integer idx;
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
        @SerializedName("parent")
        @Expose
        private String parent;
        @SerializedName("parentfield")
        @Expose
        private String parentfield;
        @SerializedName("parenttype")
        @Expose
        private String parenttype;
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

        public Integer getDocstatus() {
            return docstatus;
        }

        public void setDocstatus(Integer docstatus) {
            this.docstatus = docstatus;
        }

        public Integer getIdx() {
            return idx;
        }

        public void setIdx(Integer idx) {
            this.idx = idx;
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

        public String getDoctype() {
            return doctype;
        }

        public void setDoctype(String doctype) {
            this.doctype = doctype;
        }

    }


    public static class Docinfo {

        @SerializedName("user_info")
        @Expose
        private UserInfo userInfo;
        @SerializedName("comments")
        @Expose
        private List<Object> comments;
        @SerializedName("shared")
        @Expose
        private List<Object> shared;
        @SerializedName("assignment_logs")
        @Expose
        private List<Object> assignmentLogs;
        @SerializedName("attachment_logs")
        @Expose
        private List<Object> attachmentLogs;
        @SerializedName("info_logs")
        @Expose
        private List<Object> infoLogs;
        @SerializedName("like_logs")
        @Expose
        private List<Object> likeLogs;
        @SerializedName("workflow_logs")
        @Expose
        private List<Object> workflowLogs;
        @SerializedName("doctype")
        @Expose
        private String doctype;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("attachments")
        @Expose
        private List<Object> attachments;
        @SerializedName("communications")
        @Expose
        private List<Object> communications;
        @SerializedName("automated_messages")
        @Expose
        private List<Object> automatedMessages;
        @SerializedName("total_comments")
        @Expose
        private Integer totalComments;
        @SerializedName("versions")
        @Expose
        private List<Object> versions;
        @SerializedName("assignments")
        @Expose
        private List<Object> assignments;
        @SerializedName("permissions")
        @Expose
        private Permissions permissions;
        @SerializedName("views")
        @Expose
        private List<Object> views;
        @SerializedName("energy_point_logs")
        @Expose
        private List<Object> energyPointLogs;
        @SerializedName("additional_timeline_content")
        @Expose
        private List<Object> additionalTimelineContent;
        @SerializedName("milestones")
        @Expose
        private List<Object> milestones;
        @SerializedName("is_document_followed")
        @Expose
        private Object isDocumentFollowed;
        @SerializedName("tags")
        @Expose
        private String tags;
        @SerializedName("document_email")
        @Expose
        private Object documentEmail;

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public List<Object> getComments() {
            return comments;
        }

        public void setComments(List<Object> comments) {
            this.comments = comments;
        }

        public List<Object> getShared() {
            return shared;
        }

        public void setShared(List<Object> shared) {
            this.shared = shared;
        }

        public List<Object> getAssignmentLogs() {
            return assignmentLogs;
        }

        public void setAssignmentLogs(List<Object> assignmentLogs) {
            this.assignmentLogs = assignmentLogs;
        }

        public List<Object> getAttachmentLogs() {
            return attachmentLogs;
        }

        public void setAttachmentLogs(List<Object> attachmentLogs) {
            this.attachmentLogs = attachmentLogs;
        }

        public List<Object> getInfoLogs() {
            return infoLogs;
        }

        public void setInfoLogs(List<Object> infoLogs) {
            this.infoLogs = infoLogs;
        }

        public List<Object> getLikeLogs() {
            return likeLogs;
        }

        public void setLikeLogs(List<Object> likeLogs) {
            this.likeLogs = likeLogs;
        }

        public List<Object> getWorkflowLogs() {
            return workflowLogs;
        }

        public void setWorkflowLogs(List<Object> workflowLogs) {
            this.workflowLogs = workflowLogs;
        }

        public String getDoctype() {
            return doctype;
        }

        public void setDoctype(String doctype) {
            this.doctype = doctype;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Object> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<Object> attachments) {
            this.attachments = attachments;
        }

        public List<Object> getCommunications() {
            return communications;
        }

        public void setCommunications(List<Object> communications) {
            this.communications = communications;
        }

        public List<Object> getAutomatedMessages() {
            return automatedMessages;
        }

        public void setAutomatedMessages(List<Object> automatedMessages) {
            this.automatedMessages = automatedMessages;
        }

        public Integer getTotalComments() {
            return totalComments;
        }

        public void setTotalComments(Integer totalComments) {
            this.totalComments = totalComments;
        }

        public List<Object> getVersions() {
            return versions;
        }

        public void setVersions(List<Object> versions) {
            this.versions = versions;
        }

        public List<Object> getAssignments() {
            return assignments;
        }

        public void setAssignments(List<Object> assignments) {
            this.assignments = assignments;
        }

        public Permissions getPermissions() {
            return permissions;
        }

        public void setPermissions(Permissions permissions) {
            this.permissions = permissions;
        }

        public List<Object> getViews() {
            return views;
        }

        public void setViews(List<Object> views) {
            this.views = views;
        }

        public List<Object> getEnergyPointLogs() {
            return energyPointLogs;
        }

        public void setEnergyPointLogs(List<Object> energyPointLogs) {
            this.energyPointLogs = energyPointLogs;
        }

        public List<Object> getAdditionalTimelineContent() {
            return additionalTimelineContent;
        }

        public void setAdditionalTimelineContent(List<Object> additionalTimelineContent) {
            this.additionalTimelineContent = additionalTimelineContent;
        }

        public List<Object> getMilestones() {
            return milestones;
        }

        public void setMilestones(List<Object> milestones) {
            this.milestones = milestones;
        }

        public Object getIsDocumentFollowed() {
            return isDocumentFollowed;
        }

        public void setIsDocumentFollowed(Object isDocumentFollowed) {
            this.isDocumentFollowed = isDocumentFollowed;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public Object getDocumentEmail() {
            return documentEmail;
        }

        public void setDocumentEmail(Object documentEmail) {
            this.documentEmail = documentEmail;
        }

    }

    public static class Earning {

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
        @SerializedName("docstatus")
        @Expose
        private Integer docstatus;
        @SerializedName("idx")
        @Expose
        private Integer idx;
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
        @SerializedName("parent")
        @Expose
        private String parent;
        @SerializedName("parentfield")
        @Expose
        private String parentfield;
        @SerializedName("parenttype")
        @Expose
        private String parenttype;
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

        public Integer getDocstatus() {
            return docstatus;
        }

        public void setDocstatus(Integer docstatus) {
            this.docstatus = docstatus;
        }

        public Integer getIdx() {
            return idx;
        }

        public void setIdx(Integer idx) {
            this.idx = idx;
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

        public String getDoctype() {
            return doctype;
        }

        public void setDoctype(String doctype) {
            this.doctype = doctype;
        }

    }

    public static class IfOwner {


    }

    public static class LinkTitles {


    }

    public static class Permissions {

        @SerializedName("if_owner")
        @Expose
        private IfOwner ifOwner;
        @SerializedName("has_if_owner_enabled")
        @Expose
        private Boolean hasIfOwnerEnabled;
        @SerializedName("select")
        @Expose
        private Integer select;
        @SerializedName("read")
        @Expose
        private Integer read;
        @SerializedName("write")
        @Expose
        private Integer write;
        @SerializedName("create")
        @Expose
        private Integer create;
        @SerializedName("delete")
        @Expose
        private Integer delete;
        @SerializedName("submit")
        @Expose
        private Integer submit;
        @SerializedName("cancel")
        @Expose
        private Integer cancel;
        @SerializedName("amend")
        @Expose
        private Integer amend;
        @SerializedName("print")
        @Expose
        private Integer print;
        @SerializedName("email")
        @Expose
        private Integer email;
        @SerializedName("report")
        @Expose
        private Integer report;
        @SerializedName("import")
        @Expose
        private Integer _import;
        @SerializedName("export")
        @Expose
        private Integer export;
        @SerializedName("set_user_permissions")
        @Expose
        private Integer setUserPermissions;
        @SerializedName("share")
        @Expose
        private Integer share;

        public IfOwner getIfOwner() {
            return ifOwner;
        }

        public void setIfOwner(IfOwner ifOwner) {
            this.ifOwner = ifOwner;
        }

        public Boolean getHasIfOwnerEnabled() {
            return hasIfOwnerEnabled;
        }

        public void setHasIfOwnerEnabled(Boolean hasIfOwnerEnabled) {
            this.hasIfOwnerEnabled = hasIfOwnerEnabled;
        }

        public Integer getSelect() {
            return select;
        }

        public void setSelect(Integer select) {
            this.select = select;
        }

        public Integer getRead() {
            return read;
        }

        public void setRead(Integer read) {
            this.read = read;
        }

        public Integer getWrite() {
            return write;
        }

        public void setWrite(Integer write) {
            this.write = write;
        }

        public Integer getCreate() {
            return create;
        }

        public void setCreate(Integer create) {
            this.create = create;
        }

        public Integer getDelete() {
            return delete;
        }

        public void setDelete(Integer delete) {
            this.delete = delete;
        }

        public Integer getSubmit() {
            return submit;
        }

        public void setSubmit(Integer submit) {
            this.submit = submit;
        }

        public Integer getCancel() {
            return cancel;
        }

        public void setCancel(Integer cancel) {
            this.cancel = cancel;
        }

        public Integer getAmend() {
            return amend;
        }

        public void setAmend(Integer amend) {
            this.amend = amend;
        }

        public Integer getPrint() {
            return print;
        }

        public void setPrint(Integer print) {
            this.print = print;
        }

        public Integer getEmail() {
            return email;
        }

        public void setEmail(Integer email) {
            this.email = email;
        }

        public Integer getReport() {
            return report;
        }

        public void setReport(Integer report) {
            this.report = report;
        }

        public Integer getImport() {
            return _import;
        }

        public void setImport(Integer _import) {
            this._import = _import;
        }

        public Integer getExport() {
            return export;
        }

        public void setExport(Integer export) {
            this.export = export;
        }

        public Integer getSetUserPermissions() {
            return setUserPermissions;
        }

        public void setSetUserPermissions(Integer setUserPermissions) {
            this.setUserPermissions = setUserPermissions;
        }

        public Integer getShare() {
            return share;
        }

        public void setShare(Integer share) {
            this.share = share;
        }

    }

    public static class UserInfo {


    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements  {
    }
}

