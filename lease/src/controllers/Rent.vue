<template>
    <div>

      <div class="shadow search" >
        <div style="font-size: larger">查询</div>
        <el-row :gutter="10" style="margin-top: 10px;margin-bottom:10px">
          <el-col :span="6">
            <el-input
              placeholder="请输入userId"
              v-model="search_input_userId"
              clearable>
            </el-input>
          </el-col>

          <el-col :offset="1" :span="8">
              <el-date-picker
                v-model="value2"
                type="daterange"
                align="right"
                value-format="timestamp"
                unlink-panels
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                :picker-options="pickerOptions">
              </el-date-picker>

          </el-col>
          <el-col :span="8">
            帖子类型:
            <el-select v-model="value"  placeholder="请选择">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
            <div class="search_btn"  @click="handleSearch">搜索</div>
          </el-col>
        </el-row>
       </div>
      <div class="shadow content">
        <el-row >
          <el-col :span="4" class="inner-title">帖子信息</el-col>
          <el-col :offset="17" :span="2">
            <el-button type="danger" style="margin-top: -5px;margin-left:-46px" @click="handleDeletes">批量删除</el-button>
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
              prop="cardId"
              label="cardId"
              align="center"
              width="200"
              :show-overflow-tooltip='true'>
            </el-table-column>
            <el-table-column
              prop="userId"
              label="userId"
              align="center"
              width="200"
              :show-overflow-tooltip='true'>
            </el-table-column>
            <el-table-column
              align="center"
              label="type"
              prop="type"
              width="80">
              <template slot-scope="scope">
                <div >{{scope.row.type|filter}}</div>

              </template>
            </el-table-column>
            <el-table-column
              prop="date"
              label="日期"
              align="center"
              width="180">
            </el-table-column>
            <el-table-column
              prop="title"
              label="title"
              align="center"
              width="290"
              :show-overflow-tooltip='true'>
            </el-table-column>
            <el-table-column
              label="操作"
              width="250"
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
              :total="totalPage">
            </el-pagination>
          </div>
        </el-row>
      </div>
        <el-dialog
          title="详情信息"
          :visible.sync="centerDialogVisible"
          width="50%"
          top="5vh"
          center>
          <div style="margin: 20px;"></div>

<!--          出租-->
          <el-form v-if="this.detail.type==1"
            :label-position="labelPosition" label-width="80px" :model="detail">
            <el-form-item label="帖子类型:">
              <div> {{this.detail.type|filter}} </div>
            </el-form-item>
            <el-form-item label="发布人id:">
              <div> {{this.detail.ownerId}} </div>
            </el-form-item>
            <el-form-item label="发布时间:">
              <div> {{this.detail.postDate}} </div>
            </el-form-item>
            <el-form-item label="现在状态:">
              <div> {{this.detail.status}} </div>
            </el-form-item>
            <el-form-item label="title:">
              <div> {{this.detail.title}} </div>
            </el-form-item>
            <el-form-item label="房屋图片:">
              <div class="block">
                <el-carousel height="150px" style="width: 300px">
                  <el-carousel-item v-for="item in detail.images" :key="item">
                    <div   class="small">
                      <img  :src="item">
                    </div>
                  </el-carousel-item>
                </el-carousel>
              </div>
            </el-form-item>
            <el-form-item label="介绍:">
              <div> {{this.detail.introduce}} </div>
            </el-form-item>
            <el-form-item label="价格:">
              <div> {{this.detail.prices[0]}} </div>
            </el-form-item>
            <el-form-item label="面积:">
              <div> {{this.detail.squares[0]}}</div>
            </el-form-item>
            <el-form-item label="户型:">
              <div> {{this.detail.unitType}} </div>
            </el-form-item>
            <el-form-item label="地点:">
              <div> {{this.detail.location}} </div>
            </el-form-item>
            <el-form-item label="合租人数:">
              <div> {{this.detail.unionNum}} </div>
            </el-form-item>
            <el-form-item label="要求:">
              <div> {{this.detail.requirement}} </div>
            </el-form-item>
          </el-form>
<!--出售-->
          <el-form v-if="this.detail.type==2"
                   :label-position="labelPosition" label-width="80px" :model="detail">
            <el-form-item label="帖子类型:">
              <div> {{this.detail.type}} </div>
            </el-form-item>
            <el-form-item label="发布人id:">
              <div> {{this.detail.ownerId}} </div>
            </el-form-item>
            <el-form-item label="发布时间:">
              <div> {{this.detail.postDate}} </div>
            </el-form-item>
            <el-form-item label="现在状态:">
              <div> {{this.detail.status}} </div>
            </el-form-item>
            <el-form-item label="title:">
              <div> {{this.detail.title}} </div>

            </el-form-item>
            <el-form-item label="房屋图片:">
              <div class="block">
                <el-carousel height="150px" style="width: 300px">
                  <el-carousel-item v-for="item in detail.images" :key="item">
                    <div   class="small">
                      <img  :src="item">
                    </div>
                  </el-carousel-item>
                </el-carousel>
              </div>
            </el-form-item>
            <el-form-item label="介绍:">
              <div> {{this.detail.introduce}} </div>

            </el-form-item>

            <el-form-item label="价格:">
              <div> {{this.detail.prices[0]}} </div>
            </el-form-item>
            <el-form-item label="面积:">
              <div> {{this.detail.squares[0]}}</div>
            </el-form-item>
            <el-form-item label="户型:">
              <div> {{this.detail.unitType}} </div>
            </el-form-item>
            <el-form-item label="地点:">
              <div> {{this.detail.location}} </div>
            </el-form-item>
            <el-form-item label="要求:">
              <div> {{this.detail.requirement}} </div>
            </el-form-item>
          </el-form>
<!--          求租-->
          <el-form v-if="this.detail.type==3"
                   :label-position="labelPosition" label-width="80px" :model="detail">
            <el-form-item label="帖子类型:">
              <div> {{this.detail.type}} </div>
            </el-form-item>
            <el-form-item label="发布人id:">
              <div> {{this.detail.ownerId}} </div>
            </el-form-item>
            <el-form-item label="发布时间:">
              <div> {{this.detail.postDate}} </div>
            </el-form-item>
            <el-form-item label="现在状态:">
              <div> {{this.detail.status}} </div>
            </el-form-item>
            <el-form-item label="title:">
              <div> {{this.detail.title}} </div>

            </el-form-item>
            <el-form-item label="介绍:">
              <div> {{this.detail.introduce}} </div>
            </el-form-item>
            <el-form-item label="价格:">
              <div> {{this.detail.prices[0]}}~{{this.detail.prices[1]}} </div>
            </el-form-item>
            <el-form-item label="面积:">
              <div> {{this.detail.squares[0]}}~ {{this.detail.squares[1]}}</div>
            </el-form-item>
            <el-form-item label="户型:">
              <div> {{this.detail.unitType}} </div>
            </el-form-item>
            <el-form-item label="地点:">
              <div> {{this.detail.location}} </div>
            </el-form-item>
            <el-form-item label="要求:">
              <div> {{this.detail.requirement}} </div>
            </el-form-item>
          </el-form>
<!--        求购  -->
          <el-form v-if="this.detail.type==4"
                   :label-position="labelPosition" label-width="80px" :model="detail">
            <el-form-item label="帖子类型:">
              <div> {{this.detail.type}} </div>
            </el-form-item>
            <el-form-item label="发布人id:">
              <div> {{this.detail.userId}} </div>
            </el-form-item>
            <el-form-item label="发布时间:">
              <div> {{this.detail.postDate}} </div>
            </el-form-item>
            <el-form-item label="现在状态:">
              <div> {{this.detail.status}} </div>
            </el-form-item>
            <el-form-item label="title:">
              <div> {{this.detail.title}} </div>

            </el-form-item>
            <el-form-item label="介绍:">
              <div> {{this.detail.introduce}} </div>

            </el-form-item>

            <el-form-item label="价格:">
              <div> {{this.detail.prices[0]}}~{{this.detail.prices[1]}} </div>
            </el-form-item>
            <el-form-item label="面积:">
              <div> {{this.detail.squares[0]}}~ {{this.detail.squares[1]}}</div>
            </el-form-item>
            <el-form-item label="户型:">
              <div> {{this.detail.unitType}} </div>
            </el-form-item>
            <el-form-item label="地点:">
              <div> {{this.detail.location}} </div>
            </el-form-item>
            <el-form-item label="要求:">
              <div> {{this.detail.requirement}} </div>
            </el-form-item>
          </el-form>
<!--        找室友 -->
          <el-form v-if="this.detail.type==5"
                   :label-position="labelPosition" label-width="80px" :model="detail">
            <el-form-item label="帖子类型:">
              <div> {{this.detail.type}} </div>
            </el-form-item>
            <el-form-item label="发布人id:">
              <div> {{this.detail.ownerId}} </div>
            </el-form-item>
            <el-form-item label="发布时间:">
              <div> {{this.detail.postDate}} </div>
            </el-form-item>
            <el-form-item label="现在状态:">
              <div> {{this.detail.status}} </div>
            </el-form-item>
            <el-form-item label="title:">
              <div> {{this.detail.title}} </div>

            </el-form-item>
            <el-form-item label="是否有房源:">
              <div> {{this.detail.hasHouseResourse | filter2}} </div>
            </el-form-item>
            <el-form-item v-if="this.detail.hasHouseResourse" label="房屋图片:">
              <div class="block">
                <el-carousel height="150px" style="width: 300px">
                  <el-carousel-item v-for="item in detail.images" :key="item">
                    <div   class="small">
                      <img  :src="item">
                    </div>
                  </el-carousel-item>
                </el-carousel>
              </div>
            </el-form-item>
            <el-form-item label="介绍:">
              <div> {{this.detail.introduce}} </div>

            </el-form-item>

            <el-form-item label="价格:">
              <div> {{this.detail.prices[0]}}~{{this.detail.prices[1]}} </div>
            </el-form-item>
            <el-form-item label="面积:">
              <div> {{this.detail.squares[0]}}~ {{this.detail.squares[1]}}</div>
            </el-form-item>
            <el-form-item label="户型:">
              <div> {{this.detail.unitType}} </div>
            </el-form-item>
            <el-form-item label="地点:">
              <div> {{this.detail.location}} </div>
            </el-form-item>
            <el-form-item label="要求:">
              <div> {{this.detail.requirement}} </div>
            </el-form-item>
          </el-form>
             <span slot="footer" class="dialog-footer">
              <el-button type="primary" @click="centerDialogVisible = false">确定</el-button>
              <el-button type="danger" @click="handleDetailDelete">删除</el-button>
             </span>
        </el-dialog>

    </div>
</template>

<script>
  export default {
    name: "Rent",
    data () {
      return {
        centerDialogVisible:false,
        labelPosition: 'right',
        detail:{
        },
        search_input_userId:'',
        currentPage: 1,
        totalPage:2,
        options: [
          {
          value: '1',
          label: '出租'
        }, {
          value: '2',
          label: '出售'
        }, {
          value: '3',
          label: '求租'
        }, {
          value: '4',
          label: '求售'
        }, {
          value: '5',
          label: '找室友'
        }
        ],
        value: '',
        multipleSelection: [],
        tableData: [],
        pickerOptions: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        value2: '',
        detail_cardId:''
      }
    },
    mounted() {
      const token = sessionStorage.getItem('token')?true:false
      let _this = this
      if(token){
        this.$nextTick(function (){
          _this.getCard()
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
         /**
          *  获取帖子详情
          *  api/adm/getCardDetail/
          */
         let _this = this
          this.detail_cardId=_this.tableData[index].cardId
         this.$http.get('/api/detail/getCardDetail/',{
           params:{
             cardId:_this.tableData[index].cardId
           }
         }).then(res=>{
           console.log(res.data)
           _this.detail=res.data.data
           _this.$message.success("获取详情成功")
         }).catch(err=>{
           console.log(err)
         })
        this.centerDialogVisible = true
      },
      handleDeletes() {
        /**
         * todo 批量删除帖子
         * /api/adm/deleteCard/
         */
        let arr = this.multipleSelection
        let cardIds=new Array()
        for ( let i = 0; i <arr.length; i++){
          cardIds[i] = arr[i].cardId
        }
        let _this = this
        this.$http.post('/api/adm/deleteCard/',{
          cardIds:cardIds,
        }).then(res=>{
          console.log(res)
          _this.$message.success("删除成功")
        }).catch(err=>{
          console.log(err)
        })
        setTimeout(()=>{
          _this.getCard()
        },3000)
      },

      handleDelete(index, row) {
        /**
         * todo 删除帖子
         * /api/adm/deleteCard/
         */
        let _this = this
        console.log(this.tableData[index].cardId)
        this.$http.post('/api/adm/deleteCard/',{
          cardIds:[this.tableData[index].cardId],
        }).then(res=>{
          console.log(res)
          _this.$message.success("删除成功")
        }).catch(err=>{
          console.log(err)
        })
        setTimeout(()=>{
          _this.getCard()
        },1000)
      },
      handleDetailDelete(){


        let _this = this
        this.$http.post('/api/adm/deleteCard/',{
          cardIds:[this.detail_cardId],
        }).then(res=>{
          console.log(res)
          _this.$message.success("删除成功")
        }).catch(err=>{
          console.log(err)
        })
        _this.detail_cardId=''
        setTimeout(()=>{
          _this.getCard()
        },1000)
        this.centerDialogVisible = false
      },
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
        this.getCard()
        this.$message.success("查询成功")
      },
      getCard(){
        /**
         * 查询符合条件的帖子
         * /api/adm/getCard/
         */
        let _this = this
        this.$http.get('/api/adm/getCard/',{
          params:{
            type:_this.value===''?0:_this.value,
            userId:_this.search_input_userId,
            minDate:_this.value2===''?0:_this.value2[0],
            maxDate:_this.value2===''?0:_this.value2[1],
            page:_this.currentPage
          }
        }).then(res=>{
          console.log(res.data.totalPage )
          _this.tableData=res.data.data
          _this.totalPage=parseInt(res.data.totalPage)
        }).catch(err=>{
          console.log(err)
        })
      },
      handleSearch() {
        this.getCard()
        this.$message.success("查询成功")
      }
    },
    computed : {
        isLogin (){
         return sessionStorage.getItem('token')?true:false
        }
    },
    filters: {
      filter: function (arg) {
        if (arg == 1) {
          return '出租';
        } else if (arg == 2) {
          return '出售';
        } else if (arg == 3) {
          return '求租';
        } else if (arg == 4) {
          return '求购';
        } else if (arg == 5) {
          return '找室友';
        }
      },
      filter2: function(arg){
        return arg ? '有': '无'
      }
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
    margin-left: 10px;
  }

  .small {
  height: 150px;
   width: 300px;
    overflow: hidden;
    position: relative;

  }
  .small img{
    position: absolute;
    height: 100%;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
  }
</style>
