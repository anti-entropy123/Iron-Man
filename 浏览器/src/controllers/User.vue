<template>
    <div >
      <div class="shadow search" >
        <div style="font-size: larger">查询</div>
        <el-row :gutter="10" style="margin-top: 10px;margin-bottom:10px">
          <el-col :span="2">
            <div class="search_tag">nickname: </div>
          </el-col>
          <el-col :span="4">
            <el-input
              placeholder="请输入内容"
              v-model="search_input_nickname"
              @keyup.enter.native="getSearch(search_input_nickname,search_input_userId,search_input_mobileNumber)"
              clearable>
            </el-input>
          </el-col>
          <el-col :offset="1" :span="2">
            <div class="search_tag">userId: </div>
          </el-col>
          <el-col :span="4">
            <el-input
              placeholder="请输入内容"
              v-model="search_input_userId"
              @keyup.enter.native="getSearch()"
              clearable>
            </el-input>
          </el-col>
          <el-col :offset="1" :span="2">
            <div class="search_tag">手机号: </div>
          </el-col>
          <el-col :span="4">
            <el-input
              placeholder="请输入内容"
              v-model="search_input_mobileNumber"
              @keyup.enter.native="getSearch()"
              clearable>
            </el-input>
          </el-col>
          <el-col :offset="1" :span="2" >
            <div class="search_btn" @click="getSearch()">搜索</div>
          </el-col>
        </el-row>

      </div>
      <div class="shadow content">
        <el-row>
        <el-col :span="4" class="inner-title">用户信息</el-col>
        <el-col :offset="17" :span="2">
          <el-button type="danger" style="margin-top: -5px;" @click="handleDeletes">批量删除</el-button>
        </el-col>
        </el-row>
        <el-row>
          <el-table
            :data="tableData"
            border
            stripe
            tooltip-effect="dark"
            style="width: 100%"
            @selection-change="handleSelectionChange">
            >
            <el-table-column
              type="selection"
              width="40">
            </el-table-column>
            <el-table-column
              prop="userId"
              label="userId"
              align="center"
              width="250"
             :show-overflow-tooltip='true'>
            </el-table-column>
            <el-table-column
              prop="nickname"
              label="姓名"
              align="center"
              width="120">
            </el-table-column>
            <el-table-column
              align="center"
              prop="sex"
              label="性别"
              width="80">
              <template slot-scope="scope">
                <div > {{ scope.row.sex|filters1 }}</div>

              </template>
            </el-table-column>

            <el-table-column
              prop="mobileNumber"
              label="手机号"
              align="center"
              width="200">
            </el-table-column>
            <el-table-column
              prop="introduction"
              label="介绍"
              align="center"
              width="400"
              :show-overflow-tooltip='true'>
            </el-table-column>

            <el-table-column
              label="操作"
              width="150"
              align="center"
            >
              <template slot-scope="scope" >
                <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.$index, scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div class="block" style="margin-left:20%">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page.sync="currentPage"
              :page-size="8"
              layout="prev, pager, next, jumper"
              :total="totalPage">
            </el-pagination>
          </div>
        </el-row>
      </div>


    </div>
</template>

<script>
  export default {
    name: "User",
    mounted () {
     const token = sessionStorage.getItem('token')?true:false
      let _this = this
      if(token){
        this.$nextTick(function (){
          _this.getUser()
        })
      }else{
        _this.$message.warning(
          "请先登录后查看"
        )
        _this.$message(
          "3s后为您跳转到登陆界面"
        )
        setTimeout(()=>{
          this.$route.replace('/login')
        },3000)
      }
     },
    data () {
      return {
        search_input_nickname:'',
        search_input_userId:'',
        search_input_mobileNumber:'',
        currentPage: 1,
        options: [
          {
          value: '选项1',
          label: '出售'
        }, {
          value: '选项2',
          label: '出租'
        }, {
          value: '选项3',
          label: '求购'
        }, {
          value: '选项4',
          label: '求租'
        }
        ],
        totalPage:2,
        tableData: [],
        multipleSelection: [],
      }
    },
    methods: {
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      handleView(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        /**
         * todo 删除指定用户
           * /api/adm/deleteUser/
         */
        let _this = this
        this.$http.post('/api/adm/deleteUser/',{
          userIds:[_this.tableData[index].userId],
        }).then(res=>{
          console.log(res)
          _this.$message.success("删除成功")
        }).catch(err=>{
          console.log(err)
        })
        setTimeout(()=>{
          this.getUser()
        },3000)

      },
      handleDeletes() {

        console.log(this.multipleSelection)
        /**
         * 获取userId 数组然后批量删除
         * /api/adm/deleteUser/
         * */
        let arr = this.multipleSelection
        let _this = this
        let userIds=new Array()
        for ( let i = 0; i <arr.length; i++){
          userIds[i] = arr[i].userId
        }
        this.$http.post('/api/adm/deleteUser/',{
          userIds:userIds,
        }).then(res=>{
          console.log(res)
          _this.$message.success("删除成功")
        }).catch(err=>{
          console.log(err)
        })
        setTimeout(()=>{
          _this.getUser()
        },1000)
      },
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        this.getUser()
        this.$message.success("查询成功")
      },
      getSearch () {
        this.getUser()
        this.$message.success("查询成功")
      },
      getUser(){
        /**
         * todo 获取用户信息
         * /api/adm/getUser/
         */
        let _this = this
        this.$http.get(`/api/adm/getUser/`,{
          params:{
            nickname:_this.search_input_nickname,
            userId:_this.search_input_userId,
            mobileNumber:_this.search_input_mobileNumber,
            page:this.currentPage
          }
        }).then(res=>{
          console.log(res.data.totalPage)
          _this.tableData=res.data.data
          _this.totalPage=parseInt(res.data.totalPage)
        }).catch(err=>{
          console.log(err)
        })
      }
    },
    filters: {
      filters1: function(arg) {
        if (!arg && typeof arg != "undefined" && arg != 0)
        {
          return '保密';
        } else if (arg) {
          return '男';
        } else {
          return '女';
        }
      },
    },
    computed:{

    }
  }
</script>

<style scoped>
  .shadow {
    box-shadow: 5px 5px 5px #c0c0c0;
    border-radius: 10px;
  }
  .search{
    margin-left:20px;
    margin-top: 10px;
    margin-right: 15px;
    background-color:#fff;
    height: 95px;
    padding-left: 30px;
    padding-top: 15px;
  }
  .content {
    margin-top: 10px;
    margin-left:20px;
    margin-right: 15px;
    background-color:#fff;
    height: 470px;
    padding-top: 10px;
  }
  .inner-title{
    font-size: larger;
    margin-bottom: 10px;
    margin-left: 30px;
  }
  .search_btn {
    height: 32px;
    line-height:32px;
    cursor: pointer;
    background-color: #f0f0f0;
    border-radius: 6px;
    display: inline-block;
    padding-right: 10px;
    padding-left: 10px;
  }
  .search_tag{
    height: 32px;
    line-height:32px;
    text-align: center;
  }
</style>
