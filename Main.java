import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        String number = url.substring(url.lastIndexOf("?") + 1); // obj=3.14&name=Amigo
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> lines2 = new ArrayList<>();
        
        Pattern pattern = Pattern.compile("[^&]+");
        Matcher matcher = pattern.matcher(number);
        while (matcher.find()) {
            lines.add(number.substring(matcher.start(), matcher.end()));
        }
        int c = 0;
        int t = 0;
        double p = 0;
        String s = null;
        for (String x : lines) {
            Pattern pattern2 = Pattern.compile("[^=]+");
            Matcher matcher2 = pattern2.matcher(x);
            int i = 0;
            int n = 0;
            
            while (matcher2.find()) {
                if (i == 1) {
                    if (n == 1) {
                        if (isDouble(x.substring(matcher2.start(), matcher2.end())) == true) {
                            p = Double.parseDouble(x.substring(matcher2.start(), matcher2.end()));
                            c++;
                        } else {
                            s = x.substring(matcher2.start(), matcher2.end());
                            t++;
                        }

                    }

                    break;
                }

                lines2.add(x.substring(matcher2.start(), matcher2.end()));
                if ((x.substring(matcher2.start(), matcher2.end())).equals("obj")) n++;
                i++;
            }

        }
        for (String x : lines2) System.out.println(x);
        if (c >= 1) alert(p);
        if (t >= 1) alert(s);

    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }

    public static boolean isDouble(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            ++i;
        }
        int integerPartSize = 0;
        int exponentPartSize = -1;
        while (i < length) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                if (c == '.' && integerPartSize > 0 && exponentPartSize == -1) {
                    exponentPartSize = 0;
                } else {
                    return false;
                }
            } else if (exponentPartSize > -1) {
                ++exponentPartSize;
            } else {
                ++integerPartSize;
            }
            ++i;
        }
        if ((str.charAt(0) == '0' && i > 1 && exponentPartSize < 1)
                || exponentPartSize == 0 || (str.charAt(length - 1) == '.')) {
            return false;
        }
        return true;
    }
}
