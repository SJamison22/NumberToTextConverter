import java.io.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.TreeMap;

class NumberMaps{

    TreeMap<Integer, String> ones = new TreeMap<Integer, String>();
    TreeMap<Integer, String> tens = new TreeMap<Integer, String>();
    TreeMap<Integer, String> values = new TreeMap<Integer, String>();

    public void populateOnes(){
        ones.put(0,"Zero");
        ones.put(1,"One");
        ones.put(2,"Two");
        ones.put(3,"Three");
        ones.put(4,"Four");
        ones.put(5,"Five");
        ones.put(6,"Six");
        ones.put(7,"Seven");
        ones.put(8,"Eight");
        ones.put(9,"Nine");
        ones.put(10,"Ten");
        ones.put(11,"Eleven");
        ones.put(12,"Twelve");
        ones.put(13,"Thirteen");
        ones.put(14,"Fourteen");
        ones.put(15,"Fifteen");
        ones.put(16,"Sixteen");
        ones.put(17,"Seventeen");
        ones.put(18,"Eighteen");
        ones.put(19,"Nineteen");

    }

    public void populateTens(){
        tens.put(10,"Ten");
        tens.put(20,"Twenty");
        tens.put(30,"Thirty");
        tens.put(40,"Forty");
        tens.put(50,"Fifty");
        tens.put(60,"Sixty");
        tens.put(70,"Seventy");
        tens.put(80,"Eighty");
        tens.put(90,"Ninety");
    }

    public void populateValues(){
        values.put(0,"Dollars");
        values.put(1,"Thousand");
        values.put(2,"Million");
        values.put(3,"Billion");
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        NumberMaps numberMaps = new NumberMaps();
        numberMaps.populateOnes();
        numberMaps.populateTens();
        numberMaps.populateValues();
        String s = "123456789";
        String ans = "";
        String[] arr;
        int hundreds;
        int front;
        int remain;

        /**
         * Get comma-separated string from input.
         */
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String numString = numberFormat.format(Integer.parseInt(s));

        /**
         * Split comma-separated string at commas.
         */
        arr = numString.split(",");

        /**
         * Loop through each comma-separated number.
         */
        for (int i = 0; i < arr.length; i++) {
            int current = Integer.parseInt(arr[i]);
            /**
             * If current number is 3 digits, get the first number and
             * add "hundred" to it.  Subtract the hundreds amount and pass
             * the remaining number to the next if statement.
             */
            if (current > 99) {
                remain = current % 100;
                front = current - remain;
                hundreds = front / 100;
                ans += numberMaps.ones.get(hundreds) + "hundred";
                current = current - front;
            }

            /**
             * If current number is between 20 and 99, get tens value and
             * concatenate the ones value directly to the end of the tens
             * value.
             */
            if (current > 19 && current < 100) {
                remain = current % 10;
                front = current - remain;
                ans += numberMaps.tens.get(front) + numberMaps.ones.get(remain);
            }

            /**
             * If current number is less than 20, get the value from the ones
             * map and concatenate it to the string.
             */
            if (current > 0 && current < 20) {
                ans += numberMaps.ones.get(current);
            }

            /**
             * Only use the 'zero' value on the first iteration which would
             * truly be a zero value.
             */
            if (current == 0 && i == 0) {
                ans += numberMaps.ones.get(current);
            }

            /**
             * Depending on the total number of commas in the value and which
             * iteration it is, concatenate text values.
             */
            ans += numberMaps.values.get(arr.length - 1 - i);
        }

        System.out.println(ans);
    }

}
