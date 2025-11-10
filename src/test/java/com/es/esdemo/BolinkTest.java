package com.es.esdemo;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.es.bolink.DesUtils;
import com.es.esdemo.pojo.ProfitAnalysisTb;
import com.es.pojo.PingAnSettDTO;
import com.es.pojo.ProfitNotifyDTO;
import com.es.pojo.UnionOrderProfitsharingTb;
import com.es.pojo.UnionParkTb;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class BolinkTest {

    private static HttpClient httpClient = null;

    private static final LinkedBlockingQueue<BlUserInfoDTO> BQUEUE = new LinkedBlockingQueue<>();

    static {
        BlUserInfoDTO b1 = BlUserInfoDTO.builder().userId(1).mobile("188").build();
        BlUserInfoDTO b2 = BlUserInfoDTO.builder().userId(2).mobile("189").build();
        BlUserInfoDTO b3 = BlUserInfoDTO.builder().userId(3).mobile("190").build();
        BQUEUE.add(b1);
        BQUEUE.add(b2);
        BQUEUE.add(b3);
    }

    public static void main(String[] args) {
        String subTableName = getSubTableName("21202511101833242590807314564");
        System.out.println(subTableName);

        System.out.println(203062 % 3);
        System.out.println(203062 % 30);

        System.out.println("M2025072600000004".hashCode() % 3);
        System.out.println("M2025072600000004".hashCode() % 10);

    }

    /** 截断微信 body（字节数，UTF-8，安全不乱码） */
    public static String truncateWeChatBody(String body) {
        if (body == null || body.isEmpty()) return "";
        try {
            byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
            if (bytes.length <= 128) return body;

            int byteCount = 0;
            int charIndex = 0;
            for (char c : body.toCharArray()) {
                int charBytes = String.valueOf(c).getBytes(StandardCharsets.UTF_8).length;
                if (byteCount + charBytes > 128) break;
                byteCount += charBytes;
                charIndex++;
            }
            return body.substring(0, charIndex);
        } catch (Exception e) {
            return "";
        }
    }

    public static String safeUtf8Truncate(String input, int maxBytes) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        byte[] allBytes = input.getBytes(StandardCharsets.UTF_8);
        if (allBytes.length <= maxBytes) {
            return input;
        }

        CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();
        ByteBuffer byteBuffer = ByteBuffer.allocate(maxBytes);
        CharBuffer charBuffer = CharBuffer.wrap(input);

        encoder.encode(charBuffer, byteBuffer, true);

        byteBuffer.flip();
        return StandardCharsets.UTF_8.decode(byteBuffer).toString();
    }

    public static String safeUtf8Truncate1(String input, int maxBytes) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        if (bytes.length <= maxBytes) {
            return input;
        }

        int cut = maxBytes;
        while (cut > 0) {
            int b = bytes[cut - 1] & 0xFF;

            if ((b & 0b1000_0000) == 0) {
                break;
            }

            // 找首字节
            int back = cut - 1;
            while (back >= 0 && (bytes[back] & 0b1100_0000) == 0b1000_0000) {
                back--;
            }

            int firstByte = bytes[back] & 0xFF;
            int expectedLen = 1;
            if ((firstByte & 0b1111_1000) == 0b1111_0000) expectedLen = 4;
            else if ((firstByte & 0b1111_0000) == 0b1110_0000) expectedLen = 3;
            else if ((firstByte & 0b1110_0000) == 0b1100_0000) expectedLen = 2;

            int actualLen = cut - back;
            if (actualLen == expectedLen) {
                break;
            } else {
                cut = back;
            }
        }

        return new String(bytes, 0, cut, StandardCharsets.UTF_8);
    }


    /**
     * 计算字符串在 UTF-8 下所占字节数
     *
     * @param input 输入字符串
     * @return 字节数
     */
    public static String getUtf8Bytes(String input) {
        String body = "";
        if (StrUtil.isEmpty(input)) {
            return body;
        }
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        if (bytes.length > 128) {
            System.out.println("超长截取");

            // 找到最后一个完整的UTF-8字符边界
            int safeLength = 128;
            while (safeLength > 0) {
                byte b = bytes[safeLength - 1];
                if ((b & 0x80) == 0) {
                    // ASCII字符，安全边界
                    break;
                } else if ((b & 0xC0) == 0xC0) {
                    // UTF-8字符开始字节，安全边界
                    break;
                } else {
                    // UTF-8字符中间字节，继续向前
                    safeLength--;
                }
            }

            byte[] truncatedBytes = Arrays.copyOf(bytes, safeLength);
            return new String(truncatedBytes, StandardCharsets.UTF_8);
        }

        return input;
    }

    public static boolean checkURL(String url) {
        Pattern exp = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", Pattern.CASE_INSENSITIVE);
        return exp.matcher(url).matches();
    }

    @Test
    public void ds() {
        ProfitAnalysisTb tb = new ProfitAnalysisTb();

    }

    public static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    @Test
    public void json() {
        List<String> lsTradeNos = Arrays.asList("2120231204204930151460731", "21202312041939502844434776");
        List<String> blTradeNos = Arrays.asList("21202312040953505847497957", "21202312041026452029272442");
    }

    @Test
    public void getOrderDb() throws Exception {
        int code = getHashCode(201033, "4gtest");
        System.out.println(code % 4);
        System.out.println(code % 200);

    }
    public int getHashCode(Integer unionId, String parkId) {
        int code = (unionId + "_" + parkId).hashCode();
        if (code < 0) {
            code = code * -1;
        }
        return code;
    }

    public String secondsToDateStr(Long milliSeconds, String formatStr) {
        if (milliSeconds == null)
            return "";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
            if (milliSeconds > 1) {
                GregorianCalendar gCalendar = new GregorianCalendar();
                gCalendar.setTimeInMillis(milliSeconds);
                return dateFormat.format(gCalendar.getTime());
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    @Test
    public void joins2() {
        ArrayList<UnionParkTb> parkTbs = new ArrayList<>();
        UnionParkTb tb = new UnionParkTb();
        tb.setId(1L);
        tb.setName("lili");
        parkTbs.add(tb);
        UnionParkTb tb1 = new UnionParkTb();
        tb1.setName("lucy");
        tb1.setId(2L);
        parkTbs.add(tb1);
        UnionParkTb tb2 = new UnionParkTb();
        tb2.setName("George");
        tb2.setId(3L);
        parkTbs.add(tb2);
        UnionParkTb tb4 = new UnionParkTb();
        tb4.setId(1L);
        tb4.setName("lili2");
        parkTbs.add(tb4);
        parkTbs.forEach(it -> it.setName("sss"));
        Map<Long, UnionParkTb> collect = parkTbs.stream().collect(Collectors.toMap(UnionParkTb::getId, x -> x, (k1, k2) -> k2));
        System.out.println(JSONObject.toJSONString(collect));
        Map<Long, UnionParkTb> tbMap = toMap(parkTbs, UnionParkTb::getId);
        System.out.println(JSONObject.toJSONString(tbMap));
        InheritableThreadLocal<Object> local = new InheritableThreadLocal<>();
    }

    /**
     * list转Map 数据格式为<String,E>
     * key为指定的字段，value 表示当前字段
     */
    public static <K, E> Map<K, E> toMap(Collection<E> list, Function<E, K> function) {
        return list.stream().collect(Collectors.toMap(function, a -> a, (k1, K2) -> k1));
    }

    @Test
    public void joins() throws Exception {
        String str = "hJwMuJVW0RU/McWVObtrSENNi7UIa19MKhEnKSSxikQw69kQgwNQoNLz57HDaY/8mVRv/vaAbjf8ckeD0VwVNwwKJgS7WK8TkQ0/0+VLZCBKk5yU3h0uL+XhWU4wkXuIetWmv3QIKH/jjESgmtYFknJ0E04i2yGeM0e97l1jWVh6LQfjWmxlk1lUsG2Tq7impVFa3c0m61VjTV4c2viYBSgzeg41UK1/tmBJuGVOH0F5hdcJ+Gkgsr9hMcuP/SAaN00NvhWA0rZIVt85nkiv4FMZJ9xeIJqUd42nfPOenk2udtXIXXDAgjfwZ4dNQhO6ATh67VtXG7+XaPH6i3PXUEWuRkHNGNsUqmhgHag7gilxT+GernJ8wSTtBZGeMq9FswaBtML91cxICybJoWNIJmYGtvF8X6lyZTIGtBlYVMj2cdEjCVv1et9zPIIVReXy4I7E2+QtTx+eyzmy3WWW9NoWAiPip464Ehahz6XxG5pN6KcvweIhDTvJCueOeynlcOjAWx022+nCNTyT2E7rboMHQNet9VacYPcjCoDbycjdUC3TedJPYYrZjLoIS0db5ZJPjNI0W7yDgQ60mnCsjNmYfMAC+KECHhyZwKaua7Y=";
        String decrypt = DesUtils.decrypt(str);
        JSONObject obj = JSONObject.parseObject(decrypt);
        System.out.println(obj);
        JSONArray parkAccount = obj.getJSONArray("park_account");
        System.out.println(parkAccount.size());
        JSONObject o = (JSONObject) parkAccount.get(0);
        Long unionId = o.getLong("unionId");
        System.out.println(unionId);
        System.out.println(unionId % 3);
        System.out.println(unionId % 30);
        System.out.println("park_account_tb_" + unionId % 30 + "_202306");
    }


    @Test
    public void cs() {
        String str = "{\"page\":1,\"total\":9,\"rows\": [{\"id\":314894,\"name\":\"车公庙停车场\",\"lng\":0,\"lat\":0,\"total_plot\":100,\"empty_plot\":100,\"union_id\":200729,\"ctime\":1682221050,\"utime\":1693391294,\"state\":1,\"balance\":0,\"address\":\"沙头街道天安社区深南大道6009号NEO绿景广场B座32C\",\"phone\":\"15820277442\",\"server_id\":null,\"park_id\":\"36491\",\"remark\":\"\",\"price_desc\":\"0元/次\",\"city_id\":null,\"pay_platform_id\":200001,\"mobile\":\"15820277442\",\"operator_id\":null,\"withdrawal_money\":null,\"withdrawal_cycle\":null,\"webank_walllet_id\":null,\"ys_shop_id\":null,\"ys_shop_name\":null,\"province_id\":null,\"area_id\":null,\"park_type\":null,\"score\":10,\"is_cloud_park\":1,\"last_pay_time\":null,\"first_pay_time\":null,\"hf_withdrawal_money\":null,\"hf_withdrawal_cycle\":null,\"huifu_wallet\":null,\"query_fee_from_cloud\":0,\"msg_reply\":null,\"cloud_platform\":0,\"device_code\":null,\"month_card\":null,\"match_order\":null,\"new_energy_duration\":null,\"account_note\":null,\"off_notify\":null,\"docking_type\":0,\"headurl\":null,\"nickname\":null,\"openid\":null,\"pay_notify_url\":null,\"charge_standard\":null,\"charge_standard_pic\":null,\"nolicence_push\":null,\"call_out_push\":null,\"is_match\":0,\"ls_withdrawal_money\":null,\"ls_withdrawal_cycle\":null,\"ls_wallet\":null,\"marketing_wallet\":null,\"marketing_wallet_balance\":0,\"etc_provider_no\":null,\"etc_secret\":null,\"invoice_auto\":0,\"reg_province_id\":440000,\"reg_city_id\":440300,\"reg_area_id\":440304,\"is_from_pay_then_inpark\":null,\"ls_withdrawal_day\":null,\"ls_jspay\":0,\"yn_etc_no\":null,\"mobile_collect\":1,\"add_account_time\":null,\"add_account_state\":null,\"tmobile\":\"13077561727\",\"use_state\":0,\"open_state\":0},{\"id\":314512,\"name\":\"车公庙车场b01\",\"lng\":0,\"lat\":0,\"total_plot\":123,\"empty_plot\":123,\"union_id\":200712,\"ctime\":1668138983,\"utime\":1693391109,\"state\":1,\"balance\":0,\"address\":\"沙头街道天安社区深南大道6009号NEO绿景广场B座32C\",\"phone\":\"17787418013\",\"server_id\":800663,\"park_id\":\"36313\",\"remark\":\"\",\"price_desc\":\"0元/次\",\"city_id\":null,\"pay_platform_id\":200001,\"mobile\":\"17787418013\",\"operator_id\":null,\"withdrawal_money\":null,\"withdrawal_cycle\":null,\"webank_walllet_id\":null,\"ys_shop_id\":null,\"ys_shop_name\":null,\"province_id\":null,\"area_id\":null,\"park_type\":null,\"score\":10,\"is_cloud_park\":1,\"last_pay_time\":null,\"first_pay_time\":null,\"hf_withdrawal_money\":null,\"hf_withdrawal_cycle\":null,\"huifu_wallet\":null,\"query_fee_from_cloud\":0,\"msg_reply\":null,\"cloud_platform\":0,\"device_code\":null,\"month_card\":1,\"match_order\":null,\"new_energy_duration\":null,\"account_note\":\"车公庙车场b01\",\"off_notify\":null,\"docking_type\":0,\"headurl\":null,\"nickname\":null,\"openid\":null,\"pay_notify_url\":null,\"charge_standard\":null,\"charge_standard_pic\":null,\"nolicence_push\":null,\"call_out_push\":null,\"is_match\":0,\"ls_withdrawal_money\":null,\"ls_withdrawal_cycle\":null,\"ls_wallet\":null,\"marketing_wallet\":null,\"marketing_wallet_balance\":0,\"etc_provider_no\":null,\"etc_secret\":null,\"invoice_auto\":0,\"reg_province_id\":440000,\"reg_city_id\":440300,\"reg_area_id\":440304,\"is_from_pay_then_inpark\":null,\"ls_withdrawal_day\":null,\"ls_jspay\":0,\"yn_etc_no\":null,\"mobile_collect\":1,\"add_account_time\":1679985956,\"add_account_state\":1,\"tmobile\":\"17787418013\",\"use_state\":1,\"open_state\":1},{\"id\":314501,\"name\":\"车公庙车场g05\",\"lng\":0,\"lat\":0,\"total_plot\":135,\"empty_plot\":135,\"union_id\":200712,\"ctime\":1667816277,\"utime\":1693808927,\"state\":1,\"balance\":0,\"address\":\"沙头街道天安社区深南大道6009号NEO绿景广场B座32C\",\"phone\":\"15986900548\",\"server_id\":800663,\"park_id\":\"36302\",\"remark\":\"\",\"price_desc\":\"0元/次\",\"city_id\":null,\"pay_platform_id\":200001,\"mobile\":\"15986900548\",\"operator_id\":null,\"withdrawal_money\":null,\"withdrawal_cycle\":null,\"webank_walllet_id\":null,\"ys_shop_id\":null,\"ys_shop_name\":null,\"province_id\":null,\"area_id\":null,\"park_type\":null,\"score\":10,\"is_cloud_park\":1,\"last_pay_time\":null,\"first_pay_time\":null,\"hf_withdrawal_money\":null,\"hf_withdrawal_cycle\":null,\"huifu_wallet\":null,\"query_fee_from_cloud\":0,\"msg_reply\":null,\"cloud_platform\":0,\"device_code\":null,\"month_card\":null,\"match_order\":null,\"new_energy_duration\":null,\"account_note\":null,\"off_notify\":null,\"docking_type\":0,\"headurl\":null,\"nickname\":null,\"openid\":null,\"pay_notify_url\":null,\"charge_standard\":null,\"charge_standard_pic\":null,\"nolicence_push\":null,\"call_out_push\":null,\"is_match\":0,\"ls_withdrawal_money\":null,\"ls_withdrawal_cycle\":null,\"ls_wallet\":null,\"marketing_wallet\":null,\"marketing_wallet_balance\":0,\"etc_provider_no\":null,\"etc_secret\":null,\"invoice_auto\":0,\"reg_province_id\":440000,\"reg_city_id\":440300,\"reg_area_id\":440304,\"is_from_pay_then_inpark\":null,\"ls_withdrawal_day\":null,\"ls_jspay\":0,\"yn_etc_no\":null,\"mobile_collect\":1,\"add_account_time\":null,\"add_account_state\":null,\"tmobile\":\"15986900548\",\"use_state\":0,\"open_state\":0},{\"id\":314499,\"name\":\"车公庙车场g04\",\"lng\":0,\"lat\":0,\"total_plot\":98,\"empty_plot\":98,\"union_id\":200712,\"ctime\":1667783938,\"utime\":1667783938,\"state\":1,\"balance\":0,\"address\":\"\",\"phone\":\"18312038403\",\"server_id\":800663,\"park_id\":\"36300\",\"remark\":\"\",\"price_desc\":\"0元/次\",\"city_id\":null,\"pay_platform_id\":200001,\"mobile\":\"18312038403\",\"operator_id\":null,\"withdrawal_money\":null,\"withdrawal_cycle\":null,\"webank_walllet_id\":null,\"ys_shop_id\":null,\"ys_shop_name\":null,\"province_id\":null,\"area_id\":null,\"park_type\":null,\"score\":10,\"is_cloud_park\":1,\"last_pay_time\":null,\"first_pay_time\":null,\"hf_withdrawal_money\":null,\"hf_withdrawal_cycle\":null,\"huifu_wallet\":null,\"query_fee_from_cloud\":0,\"msg_reply\":null,\"cloud_platform\":0,\"device_code\":null,\"month_card\":null,\"match_order\":null,\"new_energy_duration\":null,\"account_note\":null,\"off_notify\":null,\"docking_type\":0,\"headurl\":null,\"nickname\":null,\"openid\":null,\"pay_notify_url\":null,\"charge_standard\":null,\"charge_standard_pic\":null,\"nolicence_push\":null,\"call_out_push\":null,\"is_match\":0,\"ls_withdrawal_money\":null,\"ls_withdrawal_cycle\":null,\"ls_wallet\":null,\"marketing_wallet\":null,\"marketing_wallet_balance\":0,\"etc_provider_no\":null,\"etc_secret\":null,\"invoice_auto\":0,\"reg_province_id\":null,\"reg_city_id\":null,\"reg_area_id\":null,\"is_from_pay_then_inpark\":null,\"ls_withdrawal_day\":null,\"ls_jspay\":0,\"yn_etc_no\":null,\"mobile_collect\":1,\"add_account_time\":null,\"add_account_state\":null,\"tmobile\":\"18312038403\",\"use_state\":0,\"open_state\":0},{\"id\":314498,\"name\":\"车公庙车场g03\",\"lng\":110.669822,\"lat\":22.16619,\"total_plot\":162,\"empty_plot\":162,\"union_id\":200712,\"ctime\":1667783837,\"utime\":1679562353,\"state\":1,\"balance\":0,\"address\":\"龙华新区\",\"phone\":\"15230238478\",\"server_id\":800663,\"park_id\":\"36299\",\"remark\":\"\",\"price_desc\":\"0元/次\",\"city_id\":null,\"pay_platform_id\":200001,\"mobile\":\"15230238478\",\"operator_id\":null,\"withdrawal_money\":null,\"withdrawal_cycle\":null,\"webank_walllet_id\":null,\"ys_shop_id\":null,\"ys_shop_name\":null,\"province_id\":null,\"area_id\":null,\"park_type\":null,\"score\":10,\"is_cloud_park\":1,\"last_pay_time\":null,\"first_pay_time\":null,\"hf_withdrawal_money\":null,\"hf_withdrawal_cycle\":null,\"huifu_wallet\":null,\"query_fee_from_cloud\":0,\"msg_reply\":null,\"cloud_platform\":0,\"device_code\":null,\"month_card\":null,\"match_order\":null,\"new_energy_duration\":null,\"account_note\":null,\"off_notify\":null,\"docking_type\":0,\"headurl\":null,\"nickname\":null,\"openid\":null,\"pay_notify_url\":null,\"charge_standard\":null,\"charge_standard_pic\":null,\"nolicence_push\":null,\"call_out_push\":null,\"is_match\":0,\"ls_withdrawal_money\":null,\"ls_withdrawal_cycle\":null,\"ls_wallet\":null,\"marketing_wallet\":null,\"marketing_wallet_balance\":0,\"etc_provider_no\":null,\"etc_secret\":null,\"invoice_auto\":0,\"reg_province_id\":440000,\"reg_city_id\":440300,\"reg_area_id\":440309,\"is_from_pay_then_inpark\":null,\"ls_withdrawal_day\":null,\"ls_jspay\":0,\"yn_etc_no\":null,\"mobile_collect\":1,\"add_account_time\":null,\"add_account_state\":null,\"tmobile\":\"15230238478\",\"use_state\":0,\"open_state\":0},{\"id\":314497,\"name\":\"车公庙车场g02\",\"lng\":0,\"lat\":0,\"total_plot\":158,\"empty_plot\":158,\"union_id\":200712,\"ctime\":1667783790,\"utime\":1679552563,\"state\":1,\"balance\":0,\"address\":\"下沙街道\",\"phone\":\"13982901238\",\"server_id\":800663,\"park_id\":\"36298\",\"remark\":\"\",\"price_desc\":\"0元/次\",\"city_id\":440300,\"pay_platform_id\":200001,\"mobile\":\"13982901238\",\"operator_id\":null,\"withdrawal_money\":null,\"withdrawal_cycle\":null,\"webank_walllet_id\":null,\"ys_shop_id\":null,\"ys_shop_name\":null,\"province_id\":null,\"area_id\":null,\"park_type\":2,\"score\":10,\"is_cloud_park\":1,\"last_pay_time\":1668059675,\"first_pay_time\":null,\"hf_withdrawal_money\":null,\"hf_withdrawal_cycle\":null,\"huifu_wallet\":null,\"query_fee_from_cloud\":1,\"msg_reply\":null,\"cloud_platform\":0,\"device_code\":null,\"month_card\":1,\"match_order\":null,\"new_energy_duration\":null,\"account_note\":\"车公庙车场g02\",\"off_notify\":null,\"docking_type\":0,\"headurl\":null,\"nickname\":null,\"openid\":null,\"pay_notify_url\":null,\"charge_standard\":null,\"charge_standard_pic\":null,\"nolicence_push\":null,\"call_out_push\":null,\"is_match\":0,\"ls_withdrawal_money\":null,\"ls_withdrawal_cycle\":null,\"ls_wallet\":null,\"marketing_wallet\":null,\"marketing_wallet_balance\":0,\"etc_provider_no\":null,\"etc_secret\":null,\"invoice_auto\":0,\"reg_province_id\":440000,\"reg_city_id\":440300,\"reg_area_id\":440304,\"is_from_pay_then_inpark\":null,\"ls_withdrawal_day\":null,\"ls_jspay\":0,\"yn_etc_no\":null,\"mobile_collect\":1,\"add_account_time\":1667980750,\"add_account_state\":1,\"tmobile\":\"13982901238\",\"use_state\":1,\"open_state\":1},{\"id\":314490,\"name\":\"车公庙车场w01\",\"lng\":null,\"lat\":null,\"total_plot\":209,\"empty_plot\":\"-\",\"union_id\":200743,\"ctime\":1667370866,\"utime\":1693819041,\"state\":1,\"balance\":0,\"address\":\"车公庙\",\"phone\":\"15820277442\",\"server_id\":800664,\"park_id\":\"B2022110201\",\"remark\":null,\"price_desc\":null,\"city_id\":null,\"pay_platform_id\":200001,\"mobile\":\"15820277442\",\"operator_id\":1667370773113285,\"withdrawal_money\":null,\"withdrawal_cycle\":null,\"webank_walllet_id\":null,\"ys_shop_id\":null,\"ys_shop_name\":null,\"province_id\":null,\"area_id\":null,\"park_type\":null,\"score\":10,\"is_cloud_park\":0,\"last_pay_time\":null,\"first_pay_time\":null,\"hf_withdrawal_money\":null,\"hf_withdrawal_cycle\":null,\"huifu_wallet\":null,\"query_fee_from_cloud\":0,\"msg_reply\":null,\"cloud_platform\":0,\"device_code\":null,\"month_card\":null,\"match_order\":null,\"new_energy_duration\":null,\"account_note\":\"车公庙车场w01\",\"off_notify\":null,\"docking_type\":0,\"headurl\":null,\"nickname\":null,\"openid\":null,\"pay_notify_url\":null,\"charge_standard\":null,\"charge_standard_pic\":null,\"nolicence_push\":null,\"call_out_push\":null,\"is_match\":0,\"ls_withdrawal_money\":null,\"ls_withdrawal_cycle\":null,\"ls_wallet\":null,\"marketing_wallet\":null,\"marketing_wallet_balance\":0,\"etc_provider_no\":null,\"etc_secret\":null,\"invoice_auto\":0,\"reg_province_id\":440000,\"reg_city_id\":440300,\"reg_area_id\":440304,\"is_from_pay_then_inpark\":null,\"ls_withdrawal_day\":null,\"ls_jspay\":0,\"yn_etc_no\":null,\"mobile_collect\":1,\"add_account_time\":null,\"add_account_state\":null,\"tmobile\":\"15820277442\",\"use_state\":0,\"open_state\":0},{\"id\":314489,\"name\":\"车公庙车场g01\",\"lng\":114.030204,\"lat\":22.535943,\"total_plot\":231,\"empty_plot\":231,\"union_id\":200712,\"ctime\":1667370472,\"utime\":1693552108,\"state\":1,\"balance\":0,\"address\":\"广东省深圳市福田区车公庙泰然工贸园(深南大道南)\",\"phone\":\"15820277444\",\"server_id\":800691,\"park_id\":\"36286\",\"remark\":\"\",\"price_desc\":\"0元/次\",\"city_id\":440300,\"pay_platform_id\":200001,\"mobile\":\"15932012732\",\"operator_id\":null,\"withdrawal_money\":null,\"withdrawal_cycle\":null,\"webank_walllet_id\":null,\"ys_shop_id\":null,\"ys_shop_name\":null,\"province_id\":440000,\"area_id\":440304,\"park_type\":null,\"score\":10,\"is_cloud_park\":1,\"last_pay_time\":1693551751,\"first_pay_time\":null,\"hf_withdrawal_money\":null,\"hf_withdrawal_cycle\":null,\"huifu_wallet\":null,\"query_fee_from_cloud\":1,\"msg_reply\":null,\"cloud_platform\":0,\"device_code\":null,\"month_card\":1,\"match_order\":null,\"new_energy_duration\":null,\"account_note\":\"车公庙车场g01\",\"off_notify\":null,\"docking_type\":0,\"headurl\":null,\"nickname\":null,\"openid\":null,\"pay_notify_url\":null,\"charge_standard\":null,\"charge_standard_pic\":null,\"nolicence_push\":null,\"call_out_push\":null,\"is_match\":0,\"ls_withdrawal_money\":0,\"ls_withdrawal_cycle\":0,\"ls_wallet\":2,\"marketing_wallet\":null,\"marketing_wallet_balance\":0,\"etc_provider_no\":null,\"etc_secret\":null,\"invoice_auto\":0,\"reg_province_id\":440000,\"reg_city_id\":440300,\"reg_area_id\":440304,\"is_from_pay_then_inpark\":null,\"ls_withdrawal_day\":0,\"ls_jspay\":0,\"yn_etc_no\":\"\",\"mobile_collect\":1,\"add_account_time\":1670208595,\"add_account_state\":2,\"tmobile\":\"15932012732\",\"use_state\":0,\"open_state\":2},{\"id\":314482,\"name\":\"车公庙车场001\",\"lng\":0,\"lat\":0,\"total_plot\":238,\"empty_plot\":238,\"union_id\":200729,\"ctime\":1666856778,\"utime\":1693818532,\"state\":1,\"balance\":0,\"address\":\"沙头街道天安社区深南大道6009号NEO绿景广场B座32C\",\"phone\":\"17787418019\",\"server_id\":800657,\"park_id\":\"36277\",\"remark\":\"\",\"price_desc\":\"0元/次\",\"city_id\":null,\"pay_platform_id\":200001,\"mobile\":\"17787418019\",\"operator_id\":null,\"withdrawal_money\":null,\"withdrawal_cycle\":null,\"webank_walllet_id\":null,\"ys_shop_id\":null,\"ys_shop_name\":null,\"province_id\":null,\"area_id\":null,\"park_type\":null,\"score\":10,\"is_cloud_park\":1,\"last_pay_time\":null,\"first_pay_time\":null,\"hf_withdrawal_money\":null,\"hf_withdrawal_cycle\":null,\"huifu_wallet\":null,\"query_fee_from_cloud\":0,\"msg_reply\":null,\"cloud_platform\":0,\"device_code\":null,\"month_card\":null,\"match_order\":null,\"new_energy_duration\":null,\"account_note\":null,\"off_notify\":null,\"docking_type\":0,\"headurl\":null,\"nickname\":null,\"openid\":null,\"pay_notify_url\":null,\"charge_standard\":null,\"charge_standard_pic\":null,\"nolicence_push\":null,\"call_out_push\":null,\"is_match\":0,\"ls_withdrawal_money\":null,\"ls_withdrawal_cycle\":null,\"ls_wallet\":null,\"marketing_wallet\":null,\"marketing_wallet_balance\":0,\"etc_provider_no\":null,\"etc_secret\":null,\"invoice_auto\":0,\"reg_province_id\":440000,\"reg_city_id\":440300,\"reg_area_id\":440304,\"is_from_pay_then_inpark\":null,\"ls_withdrawal_day\":null,\"ls_jspay\":0,\"yn_etc_no\":null,\"mobile_collect\":1,\"add_account_time\":null,\"add_account_state\":null,\"tmobile\":\"17787418019\",\"use_state\":0,\"open_state\":0}]}\n";
        JSONObject object = JSON.parseObject(str);
        JSONArray rows = object.getJSONArray("rows");
        HashMap<Long, Integer> map = new HashMap<>();
        rows.forEach(it -> {
            JSONObject jsonObject = (JSONObject) it;
            Long phone = jsonObject.getLong("phone");
            map.merge(phone, 1, Integer::sum);
        });
        System.out.println(map);
    }

    @Test
    public void compare() {
        BigDecimal b1 = BigDecimal.valueOf(12);
        BigDecimal b2 = BigDecimal.valueOf(2);
        System.out.println(b1.compareTo(b2));//1
        System.out.println(b2.compareTo(b1));//-1
    }

    @Test
    public void forEach() {
        Map<Long, Integer> accountMap = new HashMap<>();
        accountMap.put(1L, 2);
        accountMap.put(2L, 2);
        accountMap.put(3L, 2);
        Map<Long, Integer> serverMap = new HashMap<>();
        serverMap.put(3L, 2);
        serverMap.put(4L, 2);
        Map<Long, Integer> platfromMap = new HashMap<>();
        platfromMap.put(5L, 2);
        platfromMap.put(6L, 2);
        Set<Long> collect = new HashSet<>(accountMap.keySet());
        collect.addAll(serverMap.keySet());
        collect.addAll(platfromMap.keySet());
        System.out.println(collect);
    }

    public static String getSubTableName(String tradeNo) {
        int code = tradeNo.hashCode();
        if (code < 0) {
            code = code * -1;
        }
        return "union_unified_order_tb_" + code % 10;
    }


    @Test
    public void mai2() {
        BigDecimal totalAmount = BigDecimal.valueOf(4250);

        BigDecimal fee = totalAmount.multiply(BigDecimal.valueOf(6)).divide(BigDecimal.valueOf(1000), RoundingMode.HALF_UP)
                .setScale(0, RoundingMode.HALF_UP);
        BigDecimal fee1 = totalAmount.multiply(BigDecimal.valueOf(6)).divide(BigDecimal.valueOf(1000), RoundingMode.HALF_UP);
        System.out.println(fee);
        System.out.println(fee1);
        String reference1 = new String("a");
        reference1.intern();
        String reference2 = "a";
        System.out.println(reference1 == reference2);
        String reference3 = new String("a") + new String("a");
        reference3.intern();
        String reference4 = "aa";
        System.out.println(reference3 == reference4);
    }

    @Test
    public void internTest() {
        // 字符串常量池创建对象abc和def, 堆中创建对象abc、def、abcdef, str1指向堆对象abcdef
        String str1 = new String("abc") + new String("def");
        // 字符串常量池不存在对象abcdef, 返回堆对象abcdef引用(将“abcdef”堆对象引用放入字符串常量池)
//        str1.intern();
        // 字符串常量池有堆对象abcdef引用
        String str2 = "abcdef";
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str1 == str2);
    }

    @Test
    public void md5() {
        String KEY = "zldboink20170613";
        String dbpwd = "aec147bd3bb94407f52ca16a28c224b1";
        String md5Pwd = MD5("193490681" + KEY);
        System.out.println(md5Pwd);
        System.out.println(dbpwd.equals(md5Pwd));
    }

    public static String MD5(String s) {
        //System.err.println(s);
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("MD5");
            messagedigest.reset();
            byte[] abyte0 = messagedigest.digest(s.getBytes("utf-8"));
            return byteToString(abyte0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String byteToString(byte[] abyte0) {
        int i = abyte0.length;
        char[] ac = new char[i * 2];
        int j = 0;
        for (byte byte0 : abyte0) {
            ac[j++] = hexDigits[byte0 >>> 4 & 0xf];
            ac[j++] = hexDigits[byte0 & 0xf];
        }
        return new String(ac);
    }

    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    @Test
    public void sanXia() throws Exception {
        try {

            List<UnionOrderProfitsharingTb> profitsharingTbs = readListFromFileBuffered("/Users/xugan/work/project/ESDemo/src/main/resources/未命名15.json", UnionOrderProfitsharingTb.class);

            System.out.println(profitsharingTbs.size());

            for (UnionOrderProfitsharingTb statusQuery : profitsharingTbs) {

                String subOrders = statusQuery.getSubOrders();
                if (StrUtil.isNotEmpty(subOrders) && JSONUtil.isTypeJSON(subOrders)) {
                    PingAnSettDTO pingAnSettDTO = JSON.parseObject(subOrders, PingAnSettDTO.class);
                    if (pingAnSettDTO.getProfitDetails() != null) {
                        airportNotify(pingAnSettDTO, statusQuery);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static <T> List<T> readListFromFileBuffered(String filePath, Class<T> clazz) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String jsonStr = br.lines().collect(Collectors.joining(System.lineSeparator()));
            return JSON.parseArray(jsonStr, clazz);
        }
    }


    private void airportNotify(PingAnSettDTO pingAnSettDTO, UnionOrderProfitsharingTb statusQuery) {
        String outTradeNo = statusQuery.getOutTradeNo();
        try {
            ProfitNotifyDTO notifyDTO = new ProfitNotifyDTO();
            notifyDTO.setOutTradeNo(outTradeNo);
            notifyDTO.setThirdOutTradeNo(pingAnSettDTO.getThirdTradeNo());
            notifyDTO.setUnionId(statusQuery.getUnionId());
            notifyDTO.setParkId(statusQuery.getParkId());
            notifyDTO.setAppId(pingAnSettDTO.getAppid());
            notifyDTO.setProfitDetails(pingAnSettDTO.getProfitDetails());

            String airportNotifyUrl = "https://yun.bolink.club/zld/airport-parking/handle-share-done";
            String res = HttpUtil.post(airportNotifyUrl, JSON.toJSONString(notifyDTO));
            System.out.println(outTradeNo + "预约停车分账成功通知云平台返回" + res);
        } catch (Exception e) {
            System.out.println(outTradeNo + "{} 预约停车分账成功通知云平台异常" + e);
            e.printStackTrace();
        }

    }


}


//'21202402211111058193650885','21202402212026330181114544','21202402212030318426519751','21202402212030577619920220','21202402212032308455252672','21202402212032408921430533','21202402212036116323531045','21202402212037111049388029','2120240221203823570336740','21202402212038556321544966','21202402212040500267373761','21202402212041255918462361','21202402212042246177523301','21202402212043218131676444','21202402212044528011955149','21202402212046341413316763','21202402212047137036348026','21202402212047266856376534','2120240221204737262944497','21202402212052247156382167','21202402212058293041659664','21202402212059123474633923','21202402212104187187138605','2120240221210443838347953','21202402212105077339876919','21202402212106443318938133','21202402212108585418926698','21202402212117129274725145','2120240221211744984758877','21202402212120131398491927','21202402212122373513072774','21202402212129219406601831','21202402212138208816926923','21202402212139067756662985','21202402212144133837633007','21202402212145393828415530','21202402212148362229144889','21202402212151250861210363','21202402212201108203435094','21202402212211389827972579','21202402212214515661374031','21202402212216259325129384','21202402212225127881747096','21202402212244523973124548','2120240221224734441846982','21202402212254329957430517','21202402212325063649356602','21202402212337142084636554','21202402212357402539490584'


//        21202402211111058193650885 21202402212026330181114544 21202402212030318426519751 21202402212030577619920220 21202402212032308455252672 21202402212032408921430533 21202402212036116323531045 21202402212037111049388029 2120240221203823570336740 21202402212038556321544966 21202402212040500267373761 21202402212041255918462361 21202402212042246177523301 21202402212043218131676444 21202402212044528011955149 21202402212046341413316763 21202402212047137036348026 21202402212047266856376534 2120240221204737262944497 21202402212052247156382167 21202402212058293041659664 21202402212059123474633923 21202402212104187187138605 2120240221210443838347953 21202402212105077339876919 21202402212106443318938133 21202402212108585418926698 21202402212117129274725145 2120240221211744984758877 21202402212120131398491927 21202402212122373513072774 21202402212129219406601831 21202402212138208816926923 21202402212139067756662985 21202402212144133837633007 21202402212145393828415530 21202402212148362229144889 21202402212151250861210363 21202402212201108203435094 21202402212211389827972579 21202402212214515661374031 21202402212216259325129384 21202402212225127881747096 21202402212244523973124548 2120240221224734441846982 21202402212254329957430517 21202402212325063649356602 21202402212337142084636554 21202402212357402539490584
//        9001052966224052 9001137266224052 9001229466224052 9001226366224052 9001225966224052 9001225766224052 9001044766224052 9001129366224052 9001222666224052 9001128366224052 9001219566224052 9001040166224052 9001039766224052 9001123466224052 9001216666224052 9001214766224052 9001121366224052 9001035766224052 9001118166224052 9001033566224052 9001210966224052 9001117266224052 9001208966224052 9001114566224052 9001030466224052 9001208066224052 9001207966224052 9001112066224052 9001027766224052 9001205566224052 9001108266224052 9001024466224052 9001204366224052 9001204166224052 9001023166224052 9001022666224052 9001202266224052 9001106566224052 9001106266224052 9001021466224052 9001105566224052 9001105366224052 9001020266224052 9001103666224052 9001019066224052 9000999766224052 9001018366224052 9001016866224052 9000071666224052

