import java.util.HashMap;
import java.util.Map;

class Solution1 {
    public String fractionToDecimal(int numerator, int denominator) {
        // 处理除数为0的情况
        if (denominator == 0) {
            return "undefined";
        }

        // 处理被除数为0的情况
        if (numerator == 0) {
            return "0";
        }

        long numeratorLong = (long) numerator;
        long denominatorLong = (long) denominator;

        // 如果能整除，直接返回结果
        if (numeratorLong % denominatorLong == 0) {
            return String.valueOf(numeratorLong / denominatorLong);
        }

        StringBuffer sb = new StringBuffer();

        // 处理符号
        if ((numeratorLong < 0) ^ (denominatorLong < 0)) {
            sb.append('-');
        }

        // 取绝对值
        numeratorLong = Math.abs(numeratorLong);
        denominatorLong = Math.abs(denominatorLong);

        // 整数部分（修正：使用除法）
        long integerPart = numeratorLong / denominatorLong;
        sb.append(integerPart);
        sb.append('.');  // 修正：使用小数点

        // 小数部分
        StringBuffer fractionPart = new StringBuffer();
        Map<Long, Integer> remainderIndexMap = new HashMap<Long, Integer>();
        long remainder = numeratorLong % denominatorLong;
        int index = 0;

        // 修正循环条件
        while (remainder != 0 && !remainderIndexMap.containsKey(remainder)) {
            remainderIndexMap.put(remainder, index);
            remainder *= 10;
            fractionPart.append(remainder / denominatorLong);
            remainder %= denominatorLong;
            index++;
        }

        // 处理循环节
        if (remainder != 0) {
            int insertIndex = remainderIndexMap.get(remainder);
            fractionPart.insert(insertIndex, '(');
            fractionPart.append(')');  // 添加右括号
        }

        sb.append(fractionPart.toString());
        return sb.toString();
    }
}