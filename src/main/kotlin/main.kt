package ie.setu

import kotlin.math.round

var employee = Employee("Joe","Soap",'m',6143,67543.21,38.5,5.2,
    1450.50,54.33)
fun menu() : Int {
    print("""
         Employee Menu for ${getFullName()}
           1. Monthly Salary
           2. Monthly PRSI
           3. Monthly PAYE
           4. Monthly Gross Pay
           5. Monthly Total Deductions
           6. Monthly Net Pay
           7. Full Payslip
          -1. Exit
         Enter Option : """)
    return readln().toInt()
}

fun main(args: Array<String>){

    var input : Int
add()
    do {
        input = menu()
        when(input) {
            1 -> println("Monthly Salary: ${getMonthlySalary()}")
            2 -> println("Monthly PRSI: ${getMonthlyPRSI()}")
            3 ->println("Monthly PAYE: ${getMonthlyPAYE()}")
            4 -> println("Monthly Gross Pay: ${getGrossMonthlyPay()}")
            5 -> println("Monthly Total Deductions: ${getTotalMonthlyDeductions()}")
            6 -> println("Monthly Net Pay: ${getNetMonthlyPay()}")
            7 -> println(getPayslip())
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}

fun getPayslip() = """                       
    |_______________________________________________________________________                                                                 
    |     Monthly Payslip:             ${getFullName()}, ID: ${employee.employeeID }                                                                                 
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

fun getFullName() = when (employee.gender){
    'm', 'M' -> "Mr. ${employee.firstName} ${employee.lastName}"
    'f', 'F' -> "Ms. ${employee.firstName} ${employee.lastName}"
    else -> "${employee.firstName} ${employee.lastName}"
}

fun getMonthlySalary() = roundTwoDecimals(employee.grossSalary / 12)
fun getMonthlyPRSI() = roundTwoDecimals(getMonthlySalary() * (employee.prsiPercentage / 100))
fun getMonthlyPAYE() = roundTwoDecimals(getMonthlySalary() * (employee.payePercentage / 100))
fun getMonthlyBonus() = roundTwoDecimals((employee.annualBonus / 12))
fun getGrossMonthlyPay() = roundTwoDecimals(getMonthlySalary() + (employee.annualBonus / 12))
fun getTotalMonthlyDeductions() = roundTwoDecimals((getMonthlyPRSI() + getMonthlyPAYE() + employee.cycleToWorkMonthlyDeduction))
fun getNetMonthlyPay() = roundTwoDecimals(getGrossMonthlyPay() - getTotalMonthlyDeductions())


fun roundTwoDecimals(number: Double) = round(number * 100) / 100
fun add(){
    println("Enter first name:")
    var firstName = readln().toString()
    println("Enter last name:")
    var lastName = readln().toString()
    println("Enter gender (m/f):")
    var gender = readln()!!.toCharArray()[0]
    println("Enter employee ID:")
    var employeeID = readln()!!.toInt()
    println("Enter gross salary:")
    var grossSalary = readln()!!.toDouble()
    println("Enter PAYE %:")
    var payePercentage = readln()!!.toDouble()
    println("Enter PRSI %:")
    var prsiPercentage = readln()!!.toDouble()
    println("Enter annual bonus:")
    var annualBonus = readLine()!!.toDouble()
    println("Enter Cycle to work deduction:")
    var cycleToWorkMonthlyDeduction = readLine()!!.toDouble()
employee = Employee(firstName, lastName, gender,employeeID,grossSalary,payePercentage,prsiPercentage,annualBonus,cycleToWorkMonthlyDeduction)
}
