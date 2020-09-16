package com.mbry.IronMan.RequestBody.WxMessageRequestBody;

public class WxMessageRequestBody {

    // http://www.ionic.wang/weixin/api/notice.html#%E6%8E%A5%E5%8F%A3%E8%AF%B4%E6%98%8E
    
    public class Data {

        public Param character_string1;

        public Param phrase2;

        public Data(){}
        
        public Data(int type) {
            character_string1.setValue("消息提醒");
            character_string1.setColor("#173177");
            phrase2.setValue(Integer.toString(type));
            phrase2.setColor("#173177");
        }
    }

    public class Param {
        private String value;
        private String color;
        public Param() {}
        public Param(String value, String color) {
            this.setValue(value);
            this.setColor(color);
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        public String getColor() {
            return color;
        }
        public void setColor(String color) {
            this.color = color;
        }
    }
    
    /**
     * true
     * 接受者的openId
     */
    private String touser;

    /**
     * true
     * 所需下发的模板消息的id
     */
    private String template_id;

    /**
     * false
     * 点击查看详情跳转到的页面
     */
    private String page;

    /**
     * true
     */
    private String from_id;

    /**
     * true
     * 模板内容
     */
    private Data data;

    /**
     * false
     * 模板需要放大的关键词
     */
    private String emphasis_keyword;

    public WxMessageRequestBody() {}
    public WxMessageRequestBody(
        String touser,
        String template_id,
        String page,
        String from_id,
        Data data) {
        this.setTouser(touser);
        this.setTemplate_id(template_id);
        this.setPage(page);
        this.setFrom_id(from_id);
        this.setData(data);
    }

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getFrom_id() {
		return from_id;
	}

	public void setFrom_id(String from_id) {
		this.from_id = from_id;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getEmphasis_keyword() {
		return emphasis_keyword;
	}

	public void setEmphasis_keyword(String emphasis_keyword) {
		this.emphasis_keyword = emphasis_keyword;
	}
    



}