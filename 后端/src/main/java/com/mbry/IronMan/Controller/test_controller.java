package com.mbry.IronMan.Controller;

// import com.mbry.IronMan.RequestBody.BaseRequestBody.LoginCode;
// import com.mbry.IronMan.RequestBody.WxMessageRequestBody.WxMessageRequestBody;
// import com.mbry.IronMan.Service.MessageService;
// import com.mbry.IronMan.Utils.WxMessageUtil;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// class test_controller{
    
//     @Autowired
//     private MessageService messageService;

//     @Autowired
//     private WxMessageUtil wxmessageUtil;
    
//     String helloworld(@RequestBody LoginCode loginCode){
//         return "hello world";
//     }

//     @GetMapping("/test/message/")
//     public void testMessage(){
//         WxMessageRequestBody wxMessageRequestBody = new WxMessageRequestBody();
//         WxMessageRequestBody.Data data = wxMessageRequestBody.new Data(0);
//         wxmessageUtil.sendMessage(data, "oU0C35PcTnicyc7y4uWQbjGa9Rgs");
//     }
// }