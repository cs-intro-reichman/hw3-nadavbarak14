public class Algebra {
    public static void main(String args[]) {
        // Tests some of the operations
        System.out.println(plus(2,3));   // 2 + 3
        System.out.println(minus(7,2));  // 7 - 2
           System.out.println(minus(2,7));  // 2 - 7
         System.out.println(times(3,4));  // 3 * 4
           System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
           System.out.println(pow(5,3));      // 5^3
           System.out.println(pow(3,5));      // 3^5
           System.out.println(div(12,3));   // 12 / 3    
           System.out.println(div(5,5));    // 5 / 5  
           System.out.println(div(25,7));   // 25 / 7
           System.out.println(mod(25,7));   // 25 % 7
           System.out.println(mod(120,6));  // 120 % 6    
           System.out.println(sqrt(36));
        System.out.println(sqrt(263169));
           System.out.println(sqrt(76123));
    }  

    // Returns x1 + x2
    public static int plus(int a, int b) {
        int sum = a;
        if (b > 0) {
            for (int i = 0; i < b; i++) {
                sum++;
            }
        } else if (b < 0) {
            for (int i = 0; i < minus(0, b); i++) {
                sum--;
            }
        }
        return sum;
    }

    // Returns x1 - x2
    public static int minus(int a, int b) {
        int difference = a;
        if (b > 0) {
            for (int i = 0; i < b; i++) {
                difference--;
            }
        } else if (b < 0) {
            for (int i = b; i < 0; i++) {
                difference++;
            }
        }
        return difference;
    }

    // Returns x1 * x2
    public static int times(int a, int b) {
        int product = 0;
        if (a > 0 && b < 0) {
            int temp = b;
            b = a;
            a = temp;
        } else if (a < 0 && b < 0) {
            a = minus(0, a);
            b = minus(0, b);
        } else if (a == 0 || b == 0) {
            return 0;
        }

        for (int i = 0; i < b; i++) {
            product = plus(product, a);
        }
        return product;
    }

    // Returns x^n (for n >= 0)
    public static int pow(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        } else if (base == 0) {
            return 0;
        }

        int result = base;
        for (int i = 0; i < exponent - 1; i++) {
            result = times(result, base);
        }
        return result;
    }

    // Returns the integer part of x1 / x2 
    public static int div(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }

        boolean isNegative = false;
        if (dividend < 0 && divisor < 0) {
            dividend = minus(0, dividend);
            divisor = minus(0, divisor);
        } else if (dividend < 0 && divisor > 0) {
            dividend = minus(0, dividend);
            isNegative = true;
        } else if (divisor < 0 && dividend > 0) {
            divisor = minus(0, divisor);
            isNegative = true;
        }

        int quotient = 0;
        while (dividend >= divisor) {
            dividend = minus(dividend, divisor);
            quotient++;
        }
        while (dividend < 0) {
            dividend = plus(dividend, divisor);
            quotient--;
        }

        return isNegative ? minus(0, quotient) : quotient;
    }

    // Returns x1 % x2
    public static int mod(int dividend, int divisor) {
        boolean isNegative = dividend < 0;
        dividend = isNegative ? minus(0, dividend) : dividend;
        while (dividend > 0 && (dividend > divisor || dividend == divisor)) {
            dividend = minus(dividend, divisor);
        }
        return isNegative ? minus(0, dividend) : dividend;
    }	

    // Returns the integer part of sqrt(x) 
    public static int sqrt(int number) {
        int guess = 1;
        while (times(guess, guess) < number) {
            guess++;
        }
        return times(guess, guess) == number ? guess : guess - 1;
    }	  	  
}