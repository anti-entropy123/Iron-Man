(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["pages/my/my","components/navigation/navigation"],{"030a":function(t,n,e){},"077b":function(t,n,e){"use strict";(function(t){e("849e");u(e("66fd"));var n=u(e("97b3"));function u(t){return t&&t.__esModule?t:{default:t}}t(n.default)}).call(this,e("543d")["createPage"])},"20fa":function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;u(e("7494"));function u(t){return t&&t.__esModule?t:{default:t}}var r={data:function(){return{config:{type:1,title:"首页",back:!1},msgList:[{type:"0",msg:"你的card有人申请了!",cardId:"1"},{type:"1",msg:"你的队伍有人申请加入了!",cardId:"1"},{type:"2",msg:"你的入队申请通过了!",cardId:"1"},{type:"3",msg:"你的队伍有人退出了!",cardId:"1"},{type:"4",msg:"你的队伍解散了!",cardId:"1"},{type:"5",msg:"你的card有人申请了!",cardId:"1"}]}},methods:{goEdit:function(){wx.navigateTo({url:"../my/editMine"})},goFinish:function(){wx.navigateTo({url:"../my/finish"})},goUnfinish:function(){wx.navigateTo({url:"../my/unfinish"})}}};n.default=r},"3f56":function(t,n,e){},"498c":function(t,n,e){"use strict";var u=e("030a"),r=e.n(u);r.a},5025:function(t,n,e){"use strict";var u,r=function(){var t=this,n=t.$createElement;t._self._c},a=[];e.d(n,"b",(function(){return r})),e.d(n,"c",(function(){return a})),e.d(n,"a",(function(){return u}))},"5c35":function(t,n,e){"use strict";var u=e("3f56"),r=e.n(u);r.a},"68f0":function(t,n,e){"use strict";e.r(n);var u=e("20fa"),r=e.n(u);for(var a in u)"default"!==a&&function(t){e.d(n,t,(function(){return u[t]}))}(a);n["default"]=r.a},7494:function(t,n,e){"use strict";e.r(n);var u=e("5025"),r=e("fd45");for(var a in r)"default"!==a&&function(t){e.d(n,t,(function(){return r[t]}))}(a);e("5c35");var c,i=e("f0c5"),o=Object(i["a"])(r["default"],u["b"],u["c"],!1,null,null,null,!1,u["a"],c);n["default"]=o.exports},8364:function(t,n,e){"use strict";(function(t){Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var e={data:function(){return{}},props:{config:{type:Object,default:function(){return{type:0,title:"",back:!1}}}},computed:{height:function(){var n=t.getSystemInfoSync(),e=n.platform,u=n.statusBarHeight,r=u+4;return"android"==e.toLowerCase()&&(r+=4),r+38+"px"},marginTop:function(){var n=t.getSystemInfoSync(),e=n.platform,u=n.statusBarHeight,r=u+4;return"android"==e.toLowerCase()&&(r+=4),r+"px"}},methods:{conduct_:function(){this.$emit("customConduct")}}};n.default=e}).call(this,e("543d")["default"])},"97b3":function(t,n,e){"use strict";e.r(n);var u=e("c377"),r=e("68f0");for(var a in r)"default"!==a&&function(t){e.d(n,t,(function(){return r[t]}))}(a);e("498c");var c,i=e("f0c5"),o=Object(i["a"])(r["default"],u["b"],u["c"],!1,null,"a2a44e38",null,!1,u["a"],c);n["default"]=o.exports},c377:function(t,n,e){"use strict";var u={navigation:function(){return Promise.resolve().then(e.bind(null,"7494"))}},r=function(){var t=this,n=t.$createElement;t._self._c},a=[];e.d(n,"b",(function(){return r})),e.d(n,"c",(function(){return a})),e.d(n,"a",(function(){return u}))},fd45:function(t,n,e){"use strict";e.r(n);var u=e("8364"),r=e.n(u);for(var a in u)"default"!==a&&function(t){e.d(n,t,(function(){return u[t]}))}(a);n["default"]=r.a}},[["077b","common/runtime","common/vendor"]]]);