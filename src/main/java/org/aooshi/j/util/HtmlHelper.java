package org.aooshi.j.util;

public class HtmlHelper {
	
    /**
     * HTML字符转义
     * @return
     *  结果
     * @param input
     *  输入值
     */
    public static String htmlEscape(String input) {
    	if (input == null || "".equals(input))
    		return input;
    	
        input = input.replaceAll("&", "&amp;");
        input = input.replaceAll("<", "&lt;");
        input = input.replaceAll(">", "&gt;");
        input = input.replaceAll(" ", "&nbsp;");
        //IE暂不支持单引号的实体名称,而支持单引号的实体编号,故单引号转义成实体编号,其它字符转义成实体名称
        input = input.replaceAll("'", "&#39;");
        input = input.replaceAll("\"", "&quot;");
        input = input.replaceAll("\n", "<br />");
        
        return input;
    }
}
