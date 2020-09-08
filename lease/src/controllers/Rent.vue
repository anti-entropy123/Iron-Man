<template>
    <div>
      <div class="shadow search" >
        <div style="font-size: larger">查询</div>
        <el-row :gutter="10" style="margin-top: 10px;margin-bottom:10px">
          <el-col :span="5">
            <el-input
              placeholder="请输入内容"
              v-model="search_input_resource"
              clearable>
            </el-input>
          </el-col>
          <el-col :span="2">
            <div class="search_btn">搜索</div>
          </el-col>
          <el-col :span="8">
            房屋类型:
            <el-select v-model="value" multiple placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
            <div class="search_btn">搜索</div>
          </el-col>
        </el-row>
       </div>
        <div class="shadow content">
          <el-row class="inner-title">
            房屋信息
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
                prop="cardId"
                label="cardId"
                align="center"
                width="100">
              </el-table-column>
              <el-table-column
                prop="userId"
                label="userId"
                align="center"
                width="80">
              </el-table-column>
              <el-table-column
                align="center"
                label="type"
                prop="type"
                width="60">
                <template slot-scope="scope">
                  <div>{{types[type]}}</div>
                </template>
              </el-table-column>


              <el-table-column
                prop="date"
                label="日期"
                align="center"
                width="150">
              </el-table-column>
              <el-table-column
                prop="title"
                label="title"
                align="center"
                width="350">
              </el-table-column>
              <el-table-column
                label="操作"
                width="300"
                align="center"
              >
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click="handleView(scope.$index, scope.row)">查看</el-button>
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
                :total="100">
              </el-pagination>
            </div>
          </el-row>
      </div>
    </div>
</template>

<script>
  export default {
    name: "Rent",
    data () {
      return {
        search_input_resource:'',
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
        types:new Array('出租','出售','求租','求购','找室友'),
        value: [],

        multipleSelection: [],
        tableData: [{
          cardId: "001",
          type: 1,

          "userId": "001",
          "date": "2020/09/08",
          "title": "天津大学"
        },{
          "cardId": "001",
          "type": 1,
          "userId": "001",
          "date": "2020/09/08",
          "title": "天津大学"
        },{
          "cardId": "001",
          "type": 1,
          "userId": "001",
          "date": "2020/09/08",
          "title": "天津大学"
        }, {
          "cardId": "001",
          "type": 1,
          "userId": "001",
          "date": "2020/09/08",
          "title": "天津大学"
        }, {
          "cardId": "001",
          "type": 1,
          "userId": "001",
          "date": "2020/09/08",
          "title": "天津大学"
        }, {
          "cardId": "001",
          "type": 1,
          "userId": "001",
          "date": "2020/09/08",
          "title": "天津大学"
        }, {
          "cardId": "001",
          "type": 0,
          "userId": "001",
          "date": "2020/09/08",
          "title": "天津大学"
        }, {
          "cardId": "001",
          "type": 0,
          "userId": "001",
          "date": "2020/09/08",
          "title": "天津大学"
        }, ],
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
    background-color: #fdfdfd;
    opacity: 70%;
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
