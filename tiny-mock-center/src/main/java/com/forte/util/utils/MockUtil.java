package com.forte.util.utils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 随机数据助手,可能会用到的所有随机方法<br>
 * 此类所有的方法，只要在方法名前加上'@' 即可在Mock中作为映射指令
 *
 */
public class MockUtil {


    //静态代码块加载资源
    static {
        //加载定义域名合集
        // String domainStr = "top,xyz,xin,vip,win,red,net,org,wang,gov,edu,mil,biz,name,info,mobi,pro,travel,club,museum,int,aero,post,rec,asia";
        Properties properties = PropertiesUtils.readValue("mock.properties");
        assert properties != null;

        String domainStr = properties.getProperty("domainStr");
        DOMAINS = domainStr.split(",");

        // String telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153";
        String telFirst = properties.getProperty("telFirst");
        MOBILE = telFirst.split(",");

        // String road = "重庆大厦,黑龙江路,十梅庵街,遵义路,湘潭街,瑞金广场,仙山街,仙山东路,仙山西大厦,白沙河路,赵红广场,机场路,民航街,长城南路,流亭立交桥,虹桥广场,长城大厦,礼阳路,风岗街,中川路,白塔广场,兴阳路,文阳街,绣城路,河城大厦,锦城广场,崇阳街,华城路,康城街,正阳路,和阳广场,中城路,江城大厦,顺城路,安城街,山城广场,春城街,国城路,泰城街,德阳路,明阳大厦,春阳路,艳阳街,秋阳路,硕阳街,青威高速,瑞阳街,丰海路,双元大厦,惜福镇街道,夏庄街道,古庙工业园,中山街,太平路,广西街,潍县广场,博山大厦,湖南路,济宁街,芝罘路,易州广场,荷泽四路,荷泽二街,荷泽一路,荷泽三大厦,观海二广场,广西支街,观海一路,济宁支街,莒县路,平度广场,明水路,蒙阴大厦,青岛路,湖北街,江宁广场,郯城街,天津路,保定街,安徽路,河北大厦,黄岛路,北京街,莘县路,济南街,宁阳广场,日照街,德县路,新泰大厦,荷泽路,山西广场,沂水路,肥城街,兰山路,四方街,平原广场,泗水大厦,浙江路,曲阜街,寿康路,河南广场,泰安路,大沽街,红山峡支路,西陵峡一大厦,台西纬一广场,台西纬四街,台西纬二路,西陵峡二街,西陵峡三路,台西纬三广场,台西纬五路,明月峡大厦,青铜峡路,台西二街,观音峡广场,瞿塘峡街,团岛二路,团岛一街,台西三路,台西一大厦,郓城南路,团岛三街,刘家峡路,西藏二街,西藏一广场,台西四街,三门峡路,城武支大厦,红山峡路,郓城北广场,龙羊峡路,西陵峡街,台西五路,团岛四街,石村广场,巫峡大厦,四川路,寿张街,嘉祥路,南村广场,范县路,西康街,云南路,巨野大厦,西江广场,鱼台街,单县路,定陶街,滕县路,钜野广场,观城路,汶上大厦,朝城路,滋阳街,邹县广场,濮县街,磁山路,汶水街,西藏路,城武大厦,团岛路,南阳街,广州路,东平街,枣庄广场,贵州街,费县路,南海大厦,登州路,文登广场,信号山支路,延安一街,信号山路,兴安支街,福山支广场,红岛支大厦,莱芜二路,吴县一街,金口三路,金口一广场,伏龙山路,鱼山支街,观象二路,吴县二大厦,莱芜一广场,金口二街,海阳路,龙口街,恒山路,鱼山广场,掖县路,福山大厦,红岛路,常州街,大学广场,龙华街,齐河路,莱阳街,黄县路,张店大厦,祚山路,苏州街,华山路,伏龙街,江苏广场,龙江街,王村路,琴屿大厦,齐东路,京山广场,龙山路,牟平街,延安三路,延吉街,南京广场,东海东大厦,银川西路,海口街,山东路,绍兴广场,芝泉路,东海中街,宁夏路,香港西大厦,隆德广场,扬州街,郧阳路,太平角一街,宁国二支路,太平角二广场,天台东一路,太平角三大厦,漳州路一路,漳州街二街,宁国一支广场,太平角六街,太平角四路,天台东二街,太平角五路,宁国三大厦,澳门三路,江西支街,澳门二路,宁国四街,大尧一广场,咸阳支街,洪泽湖路,吴兴二大厦,澄海三路,天台一广场,新湛二路,三明北街,新湛支路,湛山五街,泰州三广场,湛山四大厦,闽江三路,澳门四街,南海支路,吴兴三广场,三明南路,湛山二街,二轻新村镇,江南大厦,吴兴一广场,珠海二街,嘉峪关路,高邮湖街,湛山三路,澳门六广场,泰州二路,东海一大厦,天台二路,微山湖街,洞庭湖广场,珠海支街,福州南路,澄海二街,泰州四路,香港中大厦,澳门五路,新湛三街,澳门一路,正阳关街,宁武关广场,闽江四街,新湛一路,宁国一大厦,王家麦岛,澳门七广场,泰州一路,泰州六街,大尧二路,青大一街,闽江二广场,闽江一大厦,屏东支路,湛山一街,东海西路,徐家麦岛函谷关广场,大尧三路,晓望支街,秀湛二路,逍遥三大厦,澳门九广场,泰州五街,澄海一路,澳门八街,福州北路,珠海一广场,宁国二路,临淮关大厦,燕儿岛路,紫荆关街,武胜关广场,逍遥一街,秀湛四路,居庸关街,山海关路,鄱阳湖大厦,新湛路,漳州街,仙游路,花莲街,乐清广场,巢湖街,台南路,吴兴大厦,新田路,福清广场,澄海路,莆田街,海游路,镇江街,石岛广场,宜兴大厦,三明路,仰口街,沛县路,漳浦广场,大麦岛,台湾街,天台路,金湖大厦,高雄广场,海江街,岳阳路,善化街,荣成路,澳门广场,武昌路,闽江大厦,台北路,龙岩街,咸阳广场,宁德街,龙泉路,丽水街,海川路,彰化大厦,金田路,泰州街,太湖路,江西街,泰兴广场,青大街,金门路,南通大厦,旌德路,汇泉广场,宁国路,泉州街,如东路,奉化街,鹊山广场,莲岛大厦,华严路,嘉义街,古田路,南平广场,秀湛路,长汀街,湛山路,徐州大厦,丰县广场,汕头街,新竹路,黄海街,安庆路,基隆广场,韶关路,云霄大厦,新安路,仙居街,屏东广场,晓望街,海门路,珠海街,上杭路,永嘉大厦,漳平路,盐城街,新浦路,新昌街,高田广场,市场三街,金乡东路,市场二大厦,上海支路,李村支广场,惠民南路,市场纬街,长安南路,陵县支街,冠县支广场,小港一大厦,市场一路,小港二街,清平路,广东广场,新疆路,博平街,港通路,小港沿,福建广场,高唐街,茌平路,港青街,高密路,阳谷广场,平阴路,夏津大厦,邱县路,渤海街,恩县广场,旅顺街,堂邑路,李村街,即墨路,港华大厦,港环路,馆陶街,普集路,朝阳街,甘肃广场,港夏街,港联路,陵县大厦,上海路,宝山广场,武定路,长清街,长安路,惠民街,武城广场,聊城大厦,海泊路,沧口街,宁波路,胶州广场,莱州路,招远街,冠县路,六码头,金乡广场,禹城街,临清路,东阿街,吴淞路,大港沿,辽宁路,棣纬二大厦,大港纬一路,贮水山支街,无棣纬一广场,大港纬三街,大港纬五路,大港纬四街,大港纬二路,无棣二大厦,吉林支路,大港四街,普集支路,无棣三街,黄台支广场,大港三街,无棣一路,贮水山大厦,泰山支路,大港一广场,无棣四路,大连支街,大港二路,锦州支街,德平广场,高苑大厦,长山路,乐陵街,临邑路,嫩江广场,合江路,大连街,博兴路,蒲台大厦,黄台广场,城阳街,临淄路,安邱街,临朐路,青城广场,商河路,热河大厦,济阳路,承德街,淄川广场,辽北街,阳信路,益都街,松江路,流亭大厦,吉林路,恒台街,包头路,无棣街,铁山广场,锦州街,桓台路,兴安大厦,邹平路,胶东广场,章丘路,丹东街,华阳路,青海街,泰山广场,周村大厦,四平路,台东西七街,台东东二路,台东东七广场,台东西二路,东五街,云门二路,芙蓉山村,延安二广场,云门一街,台东四路,台东一街,台东二路,杭州支广场,内蒙古路,台东七大厦,台东六路,广饶支街,台东八广场,台东三街,四平支路,郭口东街,青海支路,沈阳支大厦,菜市二路,菜市一街,北仲三路,瑞云街,滨县广场,庆祥街,万寿路,大成大厦,芙蓉路,历城广场,大名路,昌平街,平定路,长兴街,浦口广场,诸城大厦,和兴路,德盛街,宁海路,威海广场,东山路,清和街,姜沟路,雒口大厦,松山广场,长春街,昆明路,顺兴街,利津路,阳明广场,人和路,郭口大厦,营口路,昌邑街,孟庄广场,丰盛街,埕口路,丹阳街,汉口路,洮南大厦,桑梓路,沾化街,山口路,沈阳街,南口广场,振兴街,通化路,福寺大厦,峄县路,寿光广场,曹县路,昌乐街,道口路,南九水街,台湛广场,东光大厦,驼峰路,太平山,标山路,云溪广场,太清路";
        String road = properties.getProperty("road");
        ROAD = road.split(",");

        // String email_suffix = "@gmail.com,@yahoo.com,@msn.com,@hotmail.com,@aol.com,@ask.com,@live.com,@qq.com,@0355.net,@163.com,@163.net,@263.net,@3721.net,@yeah.net,@googlemail.com,@126.com,@sina.com,@sohu.com,@yahoo.com.cn";
        String email_suffix = properties.getProperty("email_suffix");
        EMAIL_SUFFIX = email_suffix.split(",");

        // String province = "北京市,天津市,上海市,重庆市,河北省,山西省,辽宁省,吉林省,黑龙江省,江苏省,浙江省,安徽省,福建省,江西省,山东省,河南省,湖北省,湖南省,广东省,海南省,四川省,贵州省,云南省,陕西省,甘肃省,青海省,台湾省,内蒙古自治区,广西壮族自治区,西藏自治区,宁夏回族自治区,新疆维吾尔自治区,香港特别行政区,澳门特别行政区";
        String province = properties.getProperty("province");
        PROVINCE = province.split(",");

        // String city = "北京,上海,广州,深圳,成都,杭州,重庆,武汉,西安,苏州,天津,南京,长沙,郑州,东莞,青岛,沈阳,宁波,昆明";
        String city = properties.getProperty("city");
        CITY = city.split(",");
    }

    /* —————————— 默认参数 ———————————— */
    /**
     * date()默认使用的格式化参数
     */
    private static final String DATE_FORMAT = "yyyy-dd-MM";

    /**
     * time()默认使用的格式化参数
     */
    private static final String TIME_FORMAT = "HH:mm:ss";

    /**
     * datetime()默认使用的格式化参数
     */
    private static final String DATETIME_FORMAT = "yyyy-dd-MM HH:mm:ss";

    /**
     * 顶级域名合集
     */
    private static final String[] DOMAINS;
    /**
     * 手机号
     */
    private static final String[] MOBILE;
    /**
     * 路牌号
     */
    private static final String[] ROAD;
    /**
     * 邮箱
     */
    private static final String[] EMAIL_SUFFIX;
    /**
     * 省份
     */
    private static final String[] PROVINCE;
    /**
     * 城市
     */
    private static final String[] CITY;

    /* —————————— name/chinese/cname —————————— */

    /**
     * 获取一个随机中文名称
     */
    public static String cname() {
        return ChineseUtil.getName();
    }


    /**
     * 获取指定数量区间[min , max]个随机中文名
     *
     * @param min 最小数量
     * @param max 最大数量
     * @return
     */
    public static String[] cnames(Integer min, Integer max) {
        //获取随机数量
        int num = RandomUtil.getNumber$right(min, max);
        String[] names = new String[num];
        //遍历并获取
        for (int i = 0; i < num; i++) {
            names[i] = cname();
        }
        //返回结果
        return names;
    }

    /**
     * 获取指定数量个随机中文名
     *
     * @return
     */
    public static String[] cnames(Integer num) {
        return cnames(num, num);
    }

    /**
     * 随机获取一个中文姓氏 - 百家姓中获取
     */
    public static String cfirstName() {
        return ChineseUtil.getFamilyName();
    }

    /**
     * 获取一个随机英文姓名-两个开头大写的英文字母(title(2,7)+" "+title(2,7))
     */
    public static String name() {
        int min = 2, max = 7;
        return title(min, max) + " " + title(min, max);
    }

    /**
     * 获取指定数量区间[min, max]个随机英文姓名
     *
     * @param min 最少数量
     * @param max 最大数量
     * @return
     */
    public static String[] names(Integer min, Integer max) {
        //获取随机数量
        int num = RandomUtil.getNumber$right(min, max);
        String[] names = new String[num];
        //遍历并获取
        for (int i = 0; i < num; i++) {
            names[i] = name();
        }
        //返回结果
        return names;
    }

    /**
     * 获取指定数量num个随机英文姓名
     *
     * @param num 获取数量
     * @return
     */
    public static String[] names(Integer num) {
        return names(num, num);
    }

    /**
     * 获取3-5个随机汉字
     */
    public static String ctitle() {
        return ctitle(3, 5);
    }


    /**
     * 获取指定数量个随机汉字
     *
     * @param num
     */
    public static String ctitle(Integer num) {
        return ctitle(num, num);
    }

    /**
     * 获取指定数量区间个随机汉字，区间[min,max]
     *
     * @param min 最少数量
     * @param max 最大数量
     */
    public static String ctitle(Integer min, Integer max) {
        Integer num = RandomUtil.getNumber$right(min, max);
        return ChineseUtil.getChinese(num);
    }


    /**
     * 获取5-10长度的英文字符串，开头大写
     */
    public static String title() {
        return title(5, 10);
    }

    /**
     * 获取指定长度的英文字符串，开头大写
     *
     * @param num
     */
    public static String title(Integer num) {
        return title(num, num);
    }

    /**
     * 获取指定长度的英文字符串，开头大写
     *
     * @param min 最小长度
     * @param max 最大长度
     */
    public static String title(Integer min, Integer max) {
        Integer num = RandomUtil.getNumber$right(min, max);
        String title = RandomUtil.getRandomString(num);
        //全部小写，开头大写
        return FieldUtils.headUpper(title.toLowerCase());
    }

    /**
     * 获取一个UUID
     */
    public static String UUID() {
        return RandomUtil.getUUID();
    }



    /* —————————— date —————————— */

    /**
     * 获取随机日期
     * 时间：1990 - 现在
     */
    public static Date date() {
        Calendar calendar = Calendar.getInstance();
        //设置年份等参数
        Integer nowYear = calendar.get(Calendar.YEAR);
        Integer nowDay = calendar.get(Calendar.DAY_OF_YEAR);

        //设置随机年份
        calendar.set(Calendar.YEAR, RandomUtil.getNumber$right(1990, nowYear));
        //设置随机日期
        calendar.set(Calendar.DAY_OF_YEAR, RandomUtil.getNumber$right(1, nowDay));
        //设置随机小时
        calendar.set(Calendar.HOUR_OF_DAY, RandomUtil.getNumber$right(1, 24));
        //设置随机分钟
        calendar.set(Calendar.MINUTE, RandomUtil.getNumber$right(1, 60));
        //设置随机秒
        calendar.set(Calendar.SECOND, RandomUtil.getNumber$right(1, 60));

        //返回随机日期
        return calendar.getTime();
    }

    /**
     * 返回一个随机日期的字符串
     *
     * @param format
     */
    public static String toDateStr(String format) {
        return new SimpleDateFormat(format).format(date());
    }

    /**
     * 返回一个随机日期的字符串，格式为yyyy-dd-MM
     */
    public static String toDateStr() {
        return toDateStr(DATE_FORMAT);
    }

    /**
     * 返回一个随机时间的字符串
     *
     * @param format
     */
    public static String time(String format) {
        return new SimpleDateFormat(format).format(date());
    }

    /**
     * 返回一个随机时间的字符串，格式为HH:mm:ss
     */
    public static String time() {
        return time(TIME_FORMAT);
    }

    /**
     * 返回一个随机时间日期的字符串
     *
     * @param format
     */
    public static String toDateTime(String format) {
        return new SimpleDateFormat(format).format(date());
    }

    /**
     * 返回一个随机日期时间的字符串，格式为yyyy-dd-MM HH:mm:ss
     */
    public static String toDateTime() {
        return toDateTime(DATETIME_FORMAT);
    }

    /* —————————— number age —————————— */

    /**
     * 获取一个随机年龄
     * 12 - 80
     */
    public static Integer age() {
        return RandomUtil.getNumber$right(12, 80);
    }

    /**
     * 获取随机数字
     * 0-9
     */
    public static Integer integer() {
        return RandomUtil.getNumber(1);
    }

    /**
     * 获取指定长度的随机数
     *
     * @param length 长度,长度请不要超过整数型上限。<br> 如果需要获取无限长度的整数请使用{@link MockUtil#getNumber(Integer)}
     */
    public static Integer integer(Integer length) {
        return RandomUtil.getNumber(length);
    }

    /**
     * 获取指定区间[a,b]的随机数
     *
     * @param a 最小值
     * @param b 最大值
     * @return
     */
    public static Integer integer(Integer a, Integer b) {
        return RandomUtil.getNumber$right(a, b);
    }


    /**
     * 获取制定区间[a,b]的小数，指定小数位数[endL,endR]，double类型
     *
     * @param a    整数部分的最小值
     * @param b    整数部分的最大值
     * @param endL 小数部分位数最小值
     * @param endR 小数部分位数最大值
     * @return
     */
    public static Double doubles(Integer a, Integer b, Integer endL, Integer endR) {
        Integer integer = integer(a, b);
        //获取小数位数值
        int end = RandomUtil.getNumber$right(endL, endR);
        Double dou = Double.parseDouble(RandomUtil.toFixed(new Random().nextDouble(), end));
        Double e = integer + dou;
        return e;
    }

    /**
     * 获取指定区间[a,b]的小数，指定小数位数[end]，double类型
     *
     * @param a
     * @param b
     * @param end
     * @return
     */
    public static Double doubles(Integer a, Integer b, Integer end) {
        return doubles(a, b, end, end);
    }

    /**
     * 获取指定区间[a,b]的小数，默认小数位数为0，double类型
     *
     * @param a
     * @param b
     * @return
     */
    public static Double doubles(Integer a, Integer b) {
        return doubles(a, b, 0, 0);
    }

    /**
     * 获取指定数值为a的小数，默认小数位数为0，double类型
     *
     * @param a
     * @return
     */
    public static Double doubles(Integer a) {
        return a * 1.0;
    }


    /**
     * 获取一个32位的随机数字
     */
    public static String UUNUM() {
        StringBuilder sb = new StringBuilder();
        Integer length = 32;
        for (Integer i = 0; i < length; i++) {
            sb.append(integer());
        }
        return sb.toString();
    }

    /**
     * 获取任意长度的随机整数
     *
     * @param length
     */
    public static String getNumber(Integer length) {
        return getNumber(length, length);
    }

    /**
     * 获取任意长度的随机整数
     *
     * @param min 最小长度
     * @param max 最大长度
     */
    public static String getNumber(Integer min, Integer max) {
        StringBuilder sb = new StringBuilder();
        //获取长度
        Integer length = RandomUtil.getNumber$right(min, max);
        for (Integer i = 0; i < length; i++) {
            sb.append(integer());
        }
        return sb.toString();
    }


    /**
     * 获取指定位的小数
     *
     * @param intLength 整数部分的长度
     * @param douLength 保留小数位数
     */
    public static String getDouble(Integer intLength, Integer douLength) {
        return getDouble(intLength, intLength, douLength, douLength);
    }


    /**
     * 获取指定位的小数的最大区间
     *
     * @param intMinLength 整数部分的长度最小值
     * @param intMaxLength 整数部分的长度最大值
     * @param douMinLength 保留小数位数最小值
     * @param douMaxLength 保留小数位数最大值
     */
    public static String getDouble(Integer intMinLength, Integer intMaxLength, Integer douMinLength, Integer douMaxLength) {
        StringBuilder sb = new StringBuilder();
        //先获取整数位
        sb.append(getNumber(intMinLength, intMaxLength))
                .append(".")
                .append(getNumber(douMinLength, douMaxLength));
        return sb.toString();
    }


    /**
     * 获取32位小数，小数为2位
     */
    public static String UUDOUBLE() {
        return getDouble(32, 2);
    }



    /* ——————————————————————String character code—————————————————————— */

    /**
     * 获取一个随机字符
     */
    public static Character character() {
        return RandomUtil.getRandomChar();
    }

    /**
     * 在提供的字典（数组中）随机 返回
     *
     * @param dic
     */
    public static Character character(Character[]... dic) {
        //合并集合
        Character[] characters = Arrays.stream(dic).flatMap(d -> Arrays.stream(d)).toArray(Character[]::new);
        return characters[RandomUtil.getNumber(characters.length)];
    }

    /**
     * 返回一个随机的假单词
     */
    public static String string() {
        return title(3, 12).toLowerCase();
    }

    /**
     * 返回一个随机的假单词,指定长度
     *
     * @param length 指定长度
     */
    public static String string(Integer length) {
        return title(length).toLowerCase();
    }

    /**
     * 返回一个随机的假单词,指定长度区间[min,max]
     *
     * @param min 最小长度
     * @param max 最大长度
     */
    public static String string(Integer min, Integer max) {
        return title(min, max).toLowerCase();
    }

    /**
     * 返回一个随机的假中文词语，指定长度区间[min,max]
     *
     * @param min 最小长度
     * @param max 最大长度
     */
    public static String cword(Integer min, Integer max) {
        return ctitle(min, max).toLowerCase();
    }

    /**
     * 返回一个随机的假中文词语，指定长度
     *
     * @param length 单词长度
     */
    public static String cword(Integer length) {
        return ctitle(length).toLowerCase();
    }

    /**
     * 返回一个随机的假中文词语,长度2-4
     */
    public static String cword() {
        return ctitle(2, 4).toLowerCase();
    }


    /* —————————————————————— color —————————————————————— */

    /**
     * 获取一个随机颜色的16进制代码
     */
    public static String color() {
        return RandomUtil.randomColor$hexString();
    }

    /* —————————————————————— boolean —————————————————————— */

    /**
     * 返回一个随机布尔值
     */
    public static Boolean bool() {
        return new Random().nextBoolean();
    }

    /**
     * 根据概率返回布尔值
     *
     * @param prob 返回true的概率，建议取值区间：0-1
     */
    public static Boolean bool(double prob) {
        return RandomUtil.getProbability(prob);
    }

    /* —————————————————————— text —————————————————————— */

    /**
     * 随机假英文句子,句子中的单词数量为参数的区间[min,max]
     *
     * @param min 单词最少数量
     * @param max 单词最多数量
     */
    public static String sentence(Integer min, Integer max) {
        StringBuilder sb = new StringBuilder();
        Integer num = RandomUtil.getNumber$right(min, max);
        for (Integer i = 1; i <= num; i++) {
            //首句子字母大写
            sb.append(i == 0 ? FieldUtils.headUpper(string()) : string());
            if (i != num) {
                sb.append(" ");
            } else {
                //30%概率为！结尾
                if (RandomUtil.getProbability(0.3)) {
                    sb.append("! ");
                    //否则30%概率？结尾
                } else if (RandomUtil.getProbability(0.3)) {
                    sb.append("? ");
                    //否则。结尾
                } else {
                    sb.append(". ");
                }
            }
        }
        return sb.toString();
    }

    /**
     * 返回指定长度的句子
     *
     * @param length
     */
    public static String sentence(Integer length) {
        return sentence(length, length);
    }

    /**
     * 返回长度为12-18长度的句子
     */
    public static String sentence() {
        return sentence(12, 18);
    }

    /**
     * 随机假中文句子,句子中的单词数量为参数的区间[min,max]
     *
     * @param min 单词最少数量
     * @param max 单词最多数量
     */
    public static String csentence(Integer min, Integer max) {
        StringBuilder sb = new StringBuilder();
        Integer num = RandomUtil.getNumber$right(min, max);
        for (Integer i = 1; i <= num; i++) {
            //首句子字母大写
            sb.append(cword());
            if (i == num) {
                //30%概率为！结尾
                if (RandomUtil.getProbability(0.3)) {
                    sb.append("！");
                    //否则30%概率？结尾
                } else if (RandomUtil.getProbability(0.3)) {
                    sb.append("？");
                    //否则。结尾
                } else {
                    sb.append("。");
                }
            }
        }
        return sb.toString();
    }


    /**
     * 返回指定长度的中文句子
     *
     * @param length
     */
    public static String csentence(Integer length) {
        return csentence(length, length);
    }

    /**
     * 返回长度为5-10长度的中文句子
     */
    public static String csentence() {
        return csentence(5, 10);
    }

    /**
     * 返回一个文本，文中句子数量为参数区间[min,max]
     *
     * @param min
     * @param max
     */
    public static String paragraph(Integer min, Integer max) {
        StringBuilder sb = new StringBuilder();
        Integer num = RandomUtil.getNumber$right(min, max);
        for (Integer i = 1; i <= num; i++) {
            sb.append(sentence());
        }
        return sb.toString();
    }

    /**
     * 返回指定句子数量的文本
     *
     * @param length
     */
    public static String paragraph(Integer length) {
        return paragraph(length, length);
    }

    /**
     * 返回一个有3-7个句子的文本
     */
    public static String paragraph() {
        return paragraph(1, 3);
    }

    /**
     * 返回一个文本，文中句子数量为参数区间[min,max]
     *
     * @param min 最小数量
     * @param max 最大数量
     */
    public static String cparagraph(Integer min, Integer max) {
        StringBuilder sb = new StringBuilder();
        Integer num = RandomUtil.getNumber$right(min, max);
        for (Integer i = 1; i <= num; i++) {
            sb.append(csentence());
        }
        return sb.toString();
    }

    /**
     * 返回指定句子数量的文本
     *
     * @param length
     */
    public static String cparagraph(Integer length) {
        return cparagraph(length, length);
    }

    /**
     * 返回一个有3-7个句子的文本
     */
    public static String cparagraph() {
        return cparagraph(1, 3);
    }


    /* —————————————————————— web —————————————————————— */

    /**
     * 获取一个随机IP
     */
    public static String ip() {
        StringBuilder sb = new StringBuilder();
        sb.append(new Random().nextInt(255) + 1)
                .append(".")
                .append(new Random().nextInt(255) + 1)
                .append(".")
                .append(new Random().nextInt(255) + 1)
                .append(".")
                .append(new Random().nextInt(255) + 1);
        return sb.toString();
    }

    /**
     * 获取一个随机的顶级域名
     */
    public static String tId() {
        return RandomUtil.getRandomElement(DOMAINS);
    }

    /**
     * 返回一个随机邮箱,可以指定邮箱的名称（@后面的名字）和顶级域名
     */
    public static String email(String emailName, String tid) {
        StringBuilder sb = new StringBuilder();

        sb.append(string())
                .append("@")
                .append(emailName)
                .append(".")
                .append(tid);

        return sb.toString();
    }

    /**
     * 返回一个随机邮箱,可以指定邮箱的名称（@后面的名字）
     */
    public static String email(String emailName) {
        return email(emailName, tId());
    }

    /**
     * 返回一个随机邮箱
     */
    public static String email() {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        int length = integer(5, 8);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * base.length());
            sb.append(base.charAt(number));
        }
        sb.append(EMAIL_SUFFIX[(int) (Math.random() * EMAIL_SUFFIX.length)]);
        return sb.toString();
    }

    /**
     * 随机生成一个域名，可指定顶级域名
     *
     * @param tid 指定顶级域名
     */
    public static String domain(String tid) {
        if (new Random().nextBoolean()) {
            return "www." + string() + "." + tid;
        }
        return string() + "." + tid;
    }

    /**
     * 随机生成一个域名
     */
    public static String domain() {
        return domain(tId());
    }

    /**
     * 随机一个url路径，可指定域名
     *
     * @param domainName 指定域名
     */
    public static String url(String domainName) {
        StringBuilder sb = new StringBuilder();
        //url前半部分
        sb.append("http://").append(domainName).append("/").append(string());
        //每次有0.2的概率再追加一层路径
        while (RandomUtil.getProbability(0.2)) {
            sb.append("/").append(string());
        }
        return sb.toString();
    }

    /**
     * 随机一个url
     */
    public static String url() {
        return url(domain());
    }


    /* —————————————————————— 数字手机 —————————————————————— */

    /**
     * 随机生成一个电话号码
     */
    public static String telephone() {
        int index = integer(0, MOBILE.length - 1);
        String first = MOBILE[index];
        String second = String.valueOf(integer(1, 888) + 10000).substring(1);
        String thrid = String.valueOf(integer(1, 9100) + 10000).substring(1);
        return first + second + thrid;
    }

    /**
     * 随机生成一个省份
     */
    public static String province() {
        return PROVINCE[integer(0, PROVINCE.length - 1)];
    }

    /**
     * 随机生成一个城市
     */
    public static String city() {
        return CITY[integer(0, CITY.length - 1)];
    }

    /**
     * 随机生成一个地址
     */
    public static String address() {
        int index = integer(0, ROAD.length - 1);
        String first = ROAD[index];
        String second = integer(11, 150) + "号";
        String third = "-" + integer(1, 20) + "-" + integer(1, 10);
        return first + second + third;
    }

    /* —————————————————————— 构造 —————————————————————— */

    /**
     * 构造私有化
     */
    private MockUtil() {
    }


}
