<%-- Data type: json --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    out.clear();
    // Get action
    String action = request.getParameter("key_action");

    if (action != null) {
        if (action.equals("action_get_code")) { // Get the verification code
            out.print("{\"key_status\":1}");
        } else if (action.equals("action_login")) { // Login
            out.print("{\"key_status\":1,\"key_token\":\"tmp_test_token\"}");
        } else if (action.equals("action_get_dis_list")) { // Get discussion list
            out.print("{\"key_status\":1,\"key_page\":1,\"key_perpage\":20,\"key_dis_list\":[" +
                    "{\"key_discussion\":\"讨论课:现代世界的诞生\",\"key_phone\":\"user1\",\"key_discussionid\":\"1\",\"key_starttime\":\"15/12/23, 14:10\"}," +
                    "{\"key_discussion\":\"约球:晚上有人想打球么?\",\"key_phone\":\"user1\",\"key_discussionid\":\"2\",\"key_starttime\":\"15/12/23, 16:00\"}," +
                    "{\"key_discussion\":\"讲座:实验室宣讲会\",\"key_phone\":\"user3\",\"key_discussionid\":\"3\",\"key_starttime\":\"15/12/21, 12:25\"}," +
                    "{\"key_discussion\":\"讲座:留学经验分享\",\"key_phone\":\"user5\",\"key_discussionid\":\"4\",\"key_starttime\":\"15/12/22, 13:08\"}," +
                    "{\"key_discussion\":\"圣诞节有人想一起去长泰广场嘛?\",\"key_phone\":\"user7\",\"key_discussionid\":\"5\",\"key_starttime\":\"15/12/20, 09:56\"}," +
                    "{\"key_discussion\":\"活动:圣诞晚会\",\"key_phone\":\"user19\",\"key_discussionid\":\"6\",\"key_starttime\":\"15/12/22, 18:20\"}," +
                    "{\"key_discussion\":\"活动:KTV\",\"key_phone\":\"user6\",\"key_discussionid\":\"7\",\"key_starttime\":\"15/12/23, 16:09\"}," +
                    "{\"key_discussion\":\"活动:期末答疑\",\"key_phone\":\"user8\",\"key_discussionid\":\"8\",\"key_starttime\":\"15/12/20, 20:33\"}" +
                    "]}");
        } else if (action.equals("action_get_discussion")) { // Get detailed discussion content
            out.print("{\"key_status\":1,\"key_page\":1,\"key_perpage\":20,\"key_discussionid\":\"2\",\"key_contents\":[" +
                    "{\"key_sentence\":\"今晚有谁想打羽毛球嘛?\",\"key_phone\":\"user1\"}," +
                    "{\"key_sentence\":\"几点?\",\"key_phone\":\"user2\"}," +
                    "{\"key_sentence\":\"七点到九点都可以~\",\"key_phone\":\"user1\"}," +
                    "{\"key_sentence\":\"我有空,加我一个吧\",\"key_phone\":\"user2\"}," +
                    "{\"key_sentence\":\"我也有空.\",\"key_phone\":\"user3\"}," +
                    "{\"key_sentence\":\"那我到时候先去占场地\",\"key_phone\":\"user1\"}," +
                    "{\"key_sentence\":\"好的.\",\"key_phone\":\"user2\"}" +
                    "]}");
        } else if (action.equals("action_pub_comment")) {
            out.print("{\"key_status\":1}");
        }
    } else {
        out.print("No action!");
    }

%>
