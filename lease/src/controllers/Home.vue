<template>
  <div>
    <el-row >
      <el-col  :span="4" >
        <div class="left-side">
          <div class="administrator">
            <div class="img">
              <img style="height: 100%;width: 100%" src="static/avatar.png">
            </div>
            <div style="text-align: center;margin-top: 8px">管理员</div>
          </div>
          <div class="line"></div>
          <div class="menu">
            <div @click="changeChoose('user')">用户信息管理</div>
            <div @click="changeChoose('resource')">资源管理</div>
          </div>
        </div>
      </el-col>
      <el-col :span="20" >
        <div class="container" style="height: 100vh;">
          <div class="headline ">
            <div>北洋房屋租售信息管理系统</div>
            <div style="position: absolute;top:20px;right:15px;cursor: pointer" @click="Logout">登出</div>
          </div>
          <div v-if="isLogin">
            <div style="display: flex;justify-content: center;align-items: center;flex-direction: column">
              <img src="/static/yjn.png" class ="img">
              <el-input
                class="input"
                placeholder="请输入账号"
                v-model="inputUser"
                clearable>
              </el-input>
              <el-input
                placeholder="请输入密码"
                v-model="inputPassword"
                show-password
                class="input">
              </el-input>
              <el-button
                class="login_button"
                type="primary"
                @click="Login"
                @keydown.enter="Login"
                round>登陆</el-button>
            </div>
          </div>
          <div v-else-if="isShow">
            <div class="shadow search" >
              <div style="font-size: larger">查询</div>
              <el-row :gutter="10" style="margin-top: 10px;margin-bottom:10px">
                <el-col :span="5">
                  <el-input
                    placeholder="请输入内容"
                    v-model="input"
                    clearable>
                  </el-input>
                </el-col>
                <el-col :span="2">
                  <button>搜索</button>
                </el-col>
              </el-row>
              <div>
                房屋类型:
                <el-select v-model="value" multiple placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
                <button>搜索</button>
              </div>

            </div>
            <div class="shadow content">
              <el-row class="inner-title">
                房屋信息
              </el-row>
              <el-row>
                <el-table
                  ref="multipleTable"
                  :data="tableData"
                  tooltip-effect="dark"
                  style="width: 100%"
                  @selection-change="handleSelectionChange">
                  <el-table-column
                    type="selection"
                    width="55">
                  </el-table-column>
                  <el-table-column
                    label="日期"
                    width="120">
                    <template slot-scope="scope">{{ scope.row.date }}</template>
                  </el-table-column>
                  <el-table-column
                    prop="name"
                    label="姓名"
                    width="120">
                  </el-table-column>
                  <el-table-column
                    prop="address"
                    label="地址"
                    show-overflow-tooltip>
                  </el-table-column>
                </el-table>
              </el-row>
            </div>
          </div>
          <div v-else>
            <div class="shadow search" >
              <div style="font-size: larger">查询</div>
              <el-row :gutter="10" style="margin-top: 10px;margin-bottom:10px">
                <el-col :span="5">
                  <el-input
                    placeholder="请输入内容"
                    v-model="input"
                    clearable>
                  </el-input>
                </el-col>
                <el-col :span="2">
                  <button>搜索</button>
                </el-col>
              </el-row>
              <div>
                房屋类型:
                <el-select v-model="value" multiple placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>
                <button>搜索</button>
              </div>

            </div>
            <div class="shadow content">
              <el-row class="inner-title">
                用户信息
              </el-row>
              <el-row>
                <el-table
                  :data="tableData2"
                  border
                  style="width: 100%">
                  <el-table-column
                    prop="date"
                    label="日期"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="name"
                    label="姓名"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="address"
                    label="地址">
                  </el-table-column>
                  <el-table-column label="操作">
                    <template slot-scope="scope">
                      <el-button
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                      <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="block">
                  <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page.sync="currentPage"
                    :page-size="20"
                    layout="prev, pager, next, jumper"
                    :total="100">
                  </el-pagination>
                </div>
              </el-row>
            </div>
          </div>

        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "Home",
    data () {
      return {
        input:'',
        choose:'user',
        search: '',
        login:false,
        currentPage: 1,
        inputUser:'',
        inputPassword:'',
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
        value: [],
        tableData: [{
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-08',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-06',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-07',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }],
        multipleSelection: [],
        tableData2: [{
          date: '2016-05-03',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-04',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-01',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-08',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-06',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }, {
          date: '2016-05-07',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }],
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
      },
      handleEdit(index, row) {
        console.log(index, row);
      },
      handleDelete(index, row) {
        console.log(index, row);
      },
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      },
      Login (){
        this.login=true;
      },
      Logout (){
        this.login=false;
      }
    },
    computed : {
      isShow () {
        return (this.choose==='resource')&this.login;
      },
      isLogin() {
          return !this.login;
      }
    }
  }
</script>

<style scoped>
  .line {
    height: 1px;
    border-top: 1px solid #c7c7c7;
    opacity:0.5;
    margin:10px;
  }
  .left-side{
    min-height: 100vh;
    background-color: #2e2e2e;
    box-shadow: 2px 2px 2px #c0c0c0;
    color: #f0f0f0;
  }
  .img {
    height: 80px;
    width: 80px;
    border-radius: 50%;
    overflow: hidden;
    margin-top: 90px;
  }
  .administrator{
    height: 200px;
    display: flex;
    align-items: center;
    flex-direction: column;
  }
  .menu {
    display: flex;
    align-items: center;
    flex-direction: column;
    line-height: 35px
  }
  .shadow {
    box-shadow: 5px 5px 5px #c0c0c0;
    border-radius: 10px;
  }
  .headline{
    height: 80px;
    background-color:#fff;
    display: flex;
    justify-content: center;
    align-items: center;
    font-family: "Adobe 楷体 Std R";
    font-size: x-large;
  }
  .search{
    margin-left:20px;
    margin-top: 10px;
    margin-right: 15px;
    background-color:#fff;
    height: 120px;
    padding-left: 30px;
    padding-top: 15px;
  }
  .content {
    margin-top: 10px;
    margin-left:20px;
    margin-right: 15px;
    background-color:#fff;
    height: 420px;
    padding-top: 10px;
  }
  .inner-title{
    font-size: larger;
    margin-bottom: 10px;
    margin-left: 30px;
  }
  .input{
    padding-top: 10px;
    width: 30%
  }
  .login_button {
    margin-top: 25px;
    width: 20%;
    background-color: #3c3f41;
    opacity: 50%;
  }
</style>
