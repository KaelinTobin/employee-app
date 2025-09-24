package ie.setu

import jdk.jfr.Percentage

class Employee(var firstName: String, var lastName: String, var gender: Char,var employeeID: Int,var grossSalary:Double,
    var payePercentage:Double, var prsiPercentage:Double,var annualBonus:Double,var cycleToWorkMonthlyDeduction:Double) {

    fun getPayslip() = """                       
    |_______________________________________________________________________                                                                 
    |     Monthly Payslip:             ${getFullName()}, ID: ${employee.employeeID}                                                                                 
    |_______________________________________________________________________                                                                 
    |     PAYMENT DETAILS (gross pay: ${getGrossMonthlyPay()})     
    |          Salary: ${getMonthlySalary()}
    |          Bonus:  ${getMonthlyBonus()}
    |_______________________________________________________________________
    |     DEDUCTION DETAILS (total Deductions: ${getTotalMonthlyDeductions()})    
    |          PAYE: ${getMonthlyPAYE()}
    |          PRSI: ${getMonthlyPRSI()}
    |          Cycle To Work: ${employee.cycleToWorkMonthlyDeduction}
    |_______________________________________________________________________
    |    NET PAY:${getNetMonthlyPay()}
    |_______________________________________________________________________""".trimMargin("|")

    fun getFullName() = when (employee.gender) {
        'm', 'M' -> "Mr. ${employee.firstName} ${employee.lastName}"
        'f', 'F' -> "Ms. ${employee.firstName} ${employee.lastName}"
        else -> "${employee.firstName} ${employee.lastName}"
    }

    fun getMonthlySalary() = roundTwoDecimals(employee.grossSalary / 12)
    fun getMonthlyPRSI() = roundTwoDecimals(getMonthlySalary() * (employee.prsiPercentage / 100))
    fun getMonthlyPAYE() = roundTwoDecimals(getMonthlySalary() * (employee.payePercentage / 100))
    fun getMonthlyBonus() = roundTwoDecimals((employee.annualBonus / 12))
    fun getGrossMonthlyPay() = roundTwoDecimals(getMonthlySalary() + (employee.annualBonus / 12))
    fun getTotalMonthlyDeductions() =
        roundTwoDecimals((getMonthlyPRSI() + getMonthlyPAYE() + employee.cycleToWorkMonthlyDeduction))

    fun getNetMonthlyPay() = roundTwoDecimals(getGrossMonthlyPay() - getTotalMonthlyDeductions())
    override fun toString(): String {
        return "Employee(firstName='$firstName', lastName='$lastName', gender=$gender, employeeID=$employeeID, grossSalary=$grossSalary, payePercentage=$payePercentage, prsiPercentage=$prsiPercentage, annualBonus=$annualBonus, cycleToWorkMonthlyDeduction=$cycleToWorkMonthlyDeduction)"
    }

}