package com.example.practice.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>practice</h3>
 * <p>将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。  比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，
 * 排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，
 * 你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：  string convert(string s, int numRows);
 * 示例 1:  输入: s = "LEETCODEISHIRING", numRows = 3 输出: "LCIRETOESIIGEDHN"
 * 示例 2:  输入: s = "LEETCODEISHIRING", numRows = 4 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。</p>
 *
 * @author : Yang Jian
 * @date : 2020/7/29 11:19
 */
public class Solution {
    public static String convert(String s, int rows) {
        if (rows == 1) {
            return s;
        }
        List<StringBuilder> builderList = new ArrayList<>();
        for (int i = 0; i < Math.min(rows, s.length()); i++) {
            builderList.add(new StringBuilder());
        }
        int curRows = 0;
        boolean goDown = false;
        for (char c : s.toCharArray()) {
            builderList.get(curRows).append(c);
            if (curRows == 0 || curRows == rows - 1) {
                goDown = !goDown;
            }
            curRows += goDown ? 1 : -1;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (StringBuilder builder :builderList){
            stringBuilder.append(builder);
        }
        return stringBuilder.toString();
    }

    public static int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        int reverse = reverse(123);
        System.out.println(reverse);
        String convert = Solution.convert("LEETCODEISHIRING", 4);
        System.out.println(convert);
    }
}
