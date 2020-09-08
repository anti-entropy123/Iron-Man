<template>
    <div>
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
              @keyup.enter.native="goSearch(search_input_nickname,search_input_userId,search_input_mobileNumber)"
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
              @keyup.enter.native="goSearch(search_input_nickname,search_input_userId,search_input_mobileNumber)"
              clearable>
            </el-input>
          </el-col>
          <el-col :offset="1" :span="2">
            <div class="search_tag">手机号: </div>
          </el-col>
          <el-col :span="4">
            <el-input
              placeholder="请输入内容"
              v-model="search_input_nickname"
              @keyup.enter.native="goSearch(search_input_nickname,search_input_userId,search_input_mobileNumber)"
              clearable>
            </el-input>
          </el-col>
          <el-col :offset="1" :span="2" >
            <div class="search_btn" @click="goSearch(search_input_nickname,search_input_userId,search_input_mobileNumber)">搜索</div>
          </el-col>
        </el-row>

      </div>
      <div class="shadow content">
        <el-row class="inner-title">
          用户信息
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
              width="80">
            </el-table-column>
            <el-table-column
              prop="nickname"
              label="姓名"
              align="center"
              width="100">
            </el-table-column>
            <el-table-column
              align="center"
              prop="sex"
              label="性别"
              width="60">
            </el-table-column>

            <el-table-column
              prop="mobileNumber"
              label="手机号"
              align="center"
              width="150">
            </el-table-column>
            <el-table-column
              prop="introduction"
              label="介绍"
              align="center"
              width="350">
            </el-table-column>
            <el-table-column
              prop="introduction"
              label="介绍"
              align="center"
              width="350">
            </el-table-column>
            <el-table-column
              label="操作"
              width="100"
              align="center"
            >
              <template slot-scope="scope" >
                <el-button
                  size="mini"
                  type="danger"
                  @click="handleDeleteUser(scope.$index, scope.row)">删除</el-button>
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
              :total="100">
            </el-pagination>
          </div>
        </el-row>
      </div>
    </div>
</template>

<script>
  export default {
    name: "User",
    data () {
      return {
        search_input_nickname:'',
        search_input_userId:'',
        search_input_mobileNumber:'',

        currentPage: 1,

        options: [{
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

        tableData: [
          {
            "userId": "001",
            "nickname": "mxf",
            "sex": "Y",
            "introduction": "天津大学北洋园校区诚园8斋A115",
            "mobileNumber": "18722648040"
          },
          {
            "userId": "002",
            "nickname": "yjn",
            "sex": "Y",
            "introduction": "天津大学北洋园校区诚园8斋A114",
            "mobileNumber": "18722648040"
          },
          {
            "userId": "003",
            "nickname": "mxf",
            "sex": "Y",
            "introduction": "天津大学北洋园校区诚园8斋A115",
            "mobileNumber": "18722648040"
          },
          {
            "userId": "004",
            "nickname": "mxf",
            "sex": "Y",
            "introduction": "天津大学北洋园校区诚园8斋A115",
            "mobileNumber": "18722648040"
          },
          {
            "userId": "005",
            "nickname": "mxf",
            "sex": "Y",
            "introduction": "天津大学北洋园校区诚园8斋A115",
            "mobileNumber": "18722648040"
          },
          {
            "userId": "006",
            "nickname": "mxf",
            "sex": "Y",
            "introduction": "天津大学北洋园校区诚园8斋A115",
            "mobileNumber": "18722648040"
          },
          {
            "userId": "007",
            "nickname": "mxf",
            "sex": "Y",
            "introduction": "天津大学北洋园校区诚园8斋A115",
            "mobileNumber": "18722648040"
          },
          {
            "userId": "008",
            "nickname": "mxf",
            "sex": "Y",
            "introduction": "天津大学北洋园校区诚园8斋A115",
            "mobileNumber": "18722648040"
          },
        ],
        multipleSelection: [],

      }
    },
    methods: {
      changeChoose (data) {
        this.choose = data;
      },
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
        console.log(val)
      },
      handleView(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
      },

      handleDeleteUser(index, row) {
        console.log(index, row);
      },
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      },

    },
    computed : {

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
