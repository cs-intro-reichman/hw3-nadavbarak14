// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
    
    static double epsilon = 0.001;  // Approximation accuracy
    static int iterationCounter;    // Number of iterations 
    
    // Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
    public static void main(String[] args) {		
        // Gets the loan data
        double loanAmount = Double.parseDouble(args[0]);
        double interestRate = Double.parseDouble(args[1]);
        int numberOfPayments = Integer.parseInt(args[2]);
        System.out.println("Loan = " + loanAmount + ", interest rate = " + interestRate + "%, periods = " + numberOfPayments);
        // Computes the periodical payment using brute force search
        System.out.print("\nPeriodical payment, using brute force: ");
        System.out.println((int) bruteForceSolver(loanAmount, interestRate, numberOfPayments, epsilon));
        System.out.println("number of iterations: " + iterationCounter);

        // Computes the periodical payment using bisection search
        System.out.print("\nPeriodical payment, using bi-section search: ");
        System.out.println((int) bisectionSolver(loanAmount, interestRate, numberOfPayments, epsilon));
        System.out.println("number of iterations: " + iterationCounter);
    }
    // Computes the ending balance of a loan, given the loan amount, the periodical
    // interest rate (as a percentage), the number of periods (n), and the periodical payment.
    private static double endBalance(double loanAmount, double interestRate, int numberOfPeriods, double payment) {	
        for (int i = 0; i < numberOfPeriods; i++) {
            loanAmount = loanAmount - payment;
            loanAmount = loanAmount * (1 + (interestRate / 100));
        }
        return loanAmount;
    }
    
    // Uses sequential search to compute an approximation of the periodical payment
    // that will bring the ending balance of a loan close to 0.
    // Given: the sum of the loan, the periodical interest rate (as a percentage),
    // the number of periods (n), and epsilon, the approximation's accuracy
    // Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loanAmount, double interestRate, int numberOfPeriods, double epsilon) {
        double guess;
        guess = loanAmount / numberOfPeriods;
        iterationCounter = 0;
        while (endBalance(loanAmount, interestRate, numberOfPeriods, guess) > 0) {
            guess = guess + epsilon;
            iterationCounter++;
        }
        return guess;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
    // that will bring the ending balance of a loan close to 0.
    // Given: the sum of the loan, the periodical interest rate (as a percentage),
    // the number of periods (n), and epsilon, the approximation's accuracy
    // Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loanAmount, double interestRate, int numberOfPeriods, double epsilon) {  
        double lowerBound, upperBound, guess;
        lowerBound = 0;
        upperBound = loanAmount;
        guess = (upperBound + lowerBound) / 2;
        iterationCounter = 0;
        while (upperBound - lowerBound > epsilon) {
            if (endBalance(loanAmount, interestRate, numberOfPeriods, guess) * endBalance(loanAmount, interestRate, numberOfPeriods, lowerBound) > 0) {
                lowerBound = guess;
            } else {
                upperBound = guess;
            }
            guess = (upperBound + lowerBound) / 2;
            iterationCounter++;
        }
        return guess;
    }
}