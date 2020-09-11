(global["webpackJsonp"]=global["webpackJsonp"]||[]).push([["components/navigation/navigation"],{"3f56":function(t,n,e){},5025:function(t,n,e){"use strict";var u,o=function(){var t=this,n=t.$createElement;t._self._c},r=[];e.d(n,"b",(function(){return o})),e.d(n,"c",(function(){return r})),e.d(n,"a",(function(){return u}))},"5c35":function(t,n,e){"use strict";var u=e("3f56"),o=e.n(u);o.a},7494:function(t,n,e){"use strict";e.r(n);var u=e("5025"),o=e("fd45");for(var r in o)"default"!==r&&function(t){e.d(n,t,(function(){return o[t]}))}(r);e("5c35");var a,c=e("f0c5"),i=Object(c["a"])(o["default"],u["b"],u["c"],!1,null,null,null,!1,u["a"],a);n["default"]=i.exports},8364:function(t,n,e){"use strict";(function(t){Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0;var e={data:function(){return{}},props:{config:{type:Object,default:function(){return{type:0,title:"",back:!1}}}},computed:{height:function(){var n=t.getSystemInfoSync(),e=n.platform,u=n.statusBarHeight,o=u+4;return"android"==e.toLowerCase()&&(o+=4),o+38+"px"},marginTop:function(){var n=t.getSystemInfoSync(),e=n.platform,u=n.statusBarHeight,o=u+4;return"android"==e.toLowerCase()&&(o+=4),o+"px"}},methods:{conduct_:function(){this.$emit("customConduct")}}};n.default=e}).call(this,e("543d")["default"])},fd45:function(t,n,e){"use strict";e.r(n);var u=e("8364"),o=e.n(u);for(var r in u)"default"!==r&&function(t){e.d(n,t,(function(){return u[t]}))}(r);n["default"]=o.a}}]);
;(global["webpackJsonp"] = global["webpackJsonp"] || []).push([
    'components/navigation/navigation-create-component',
    {
        'components/navigation/navigation-create-component':(function(module, exports, __webpack_require__){
            __webpack_require__('543d')['createComponent'](__webpack_require__("7494"))
        })
    },
    [['components/navigation/navigation-create-component']]
]);
