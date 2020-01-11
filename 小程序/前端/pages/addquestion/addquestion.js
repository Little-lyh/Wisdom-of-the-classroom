// pages/addquestion/addquestion.js
const app = getApp()
const jinrishici = require('../../utils/jinrishici.js')
const request = require('../../utils/request.js');
let util = require('../../utils/util.js');
let touchDotX = 0;//X按下时坐标
let touchDotY = 0;//y按下时坐标
let interval;//计时器
let time = 0;//从按下到松开共多少时间*100
const { $Toast } = require('../../dist/base/index');
Page({
  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var postId = options.postId;
    // console.log(postId);
    this.setData({
      postId: postId
    })
    var self = this
    wx.request({
      url: app.globalData.url1 + 'GetProblemListAction_getProblem', //仅为示例，并非真实的接口地址
      data: {
        x: postId,
        y: 'fdsa'
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded;charset=utf-8' // 默认值
      },
      method: 'POST',
      success: function (res) {
        console.log(res.data.problem)
        self.setData({
          problem: res.data.problem,
        })
      },
      fail: function (e) {
        console.log('failed')
      },
    })

  },
  Search1: function (e) {
    var content = e.detail.value.replace(/\s+/g, '');
    var that = this;
    that.setData({
      SearchContent1: content,
    });
  },
  Search2: function (e) {
    var content = e.detail.value.replace(/\s+/g, '');
    var that = this;
    that.setData({
      SearchContent2: content,
    });
  },
  Search3: function (e) {
    var content = e.detail.value.replace(/\s+/g, '');
    var that = this;
    that.setData({
      SearchContent3: content,
    });
  },
  Search4: function (e) {
    var content = e.detail.value.replace(/\s+/g, '');
    var that = this;
    that.setData({
      SearchContent4: content,
    });
  },
  Search5: function (e) {
    var content = e.detail.value.replace(/\s+/g, '');
    var that = this;
    that.setData({
      SearchContent5: content,
    });
  },
  Search6: function (e) {
    var content = e.detail.value.replace(/\s+/g, '');
    var that = this;
    that.setData({
      SearchContent6: content,
    });
  },
  Search7: function (e) {
    var content = e.detail.value.replace(/\s+/g, '');
    var that = this;
    that.setData({
      SearchContent7: content,
    });
  },
  AddSubmit: function (e) {
    console.log(this.data.SearchContent1);
    console.log(this.data.SearchContent2);
    console.log(e)
    var self = this;
    var question = this.data.SearchContent1
    var option_A = this.data.SearchContent2
    var option_B = this.data.SearchContent3
    var option_C = this.data.SearchContent4
    var option_D = this.data.SearchContent5
    var answer = this.data.SearchContent6
    var point = this.data.SearchContent7
    console.log(question)
    console.log(option_A)
    console.log(option_B)
    console.log(option_C)
    console.log(option_D)
    console.log(answer)
    console.log(point)
    var regPos = /^[0-9]+$/;
    if (question == '' || question == null || question.length > 45){
      this.handleError1("题干", 100)
    } else if (option_A == '' || option_A == null || option_A.length > 45){
      this.handleError1("选项A",45)
    } else if (option_B == '' || option_B == null || option_B.length > 45) {
      this.handleError1("选项B", 45)
    } else if (option_C == '' || option_C == null || option_C.length > 45) {
      this.handleError1("选项C", 45)
    } else if (option_D == '' || option_D == null || option_D.length > 45) {
      this.handleError1("选项D", 45)
    } else if (answer == '' || answer == null || answer.length > 45) {
      this.handleError1("答案", 45)
    } else if (point == '' || point == null) {
      this.handleError1("积分", 11)
    } else if (answer != option_A && answer != option_B && answer != option_C && answer != option_D) {
      this.handleError()
    } else if (!regPos.test(point)||point.toString().length>11){
      this.handleError3();
    }
    else{
      wx.request({
        url: app.globalData.url1 + 'AddQuestionAction_addQuestion', //仅为示例，并非真实的接口地址
        data: {
          problem: question,
          optionA: option_A,
          optionB: option_B,
          optionC: option_C,
          optionD: option_D,
          answer: answer,
          point: point,
          username: getApp().globalData.username
        },
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: function (res) {
          if (res.data.result == true) {
            wx.navigateTo({
              url: '../tindex/tindex',
            })
          } else {
            self.handleError()
          }
        },
        fail: function (e) {
          console.log('failed')
        },
      })
    }
  },
  handleError: function () {
    $Toast({
      content: '答案不再选项中',
      type: 'error'
    });
  },
  handleError1: function (name,num) {
    $Toast({
      content: name+'必须小于'+num+'且不能为空',
      type: 'error'
    });
  },
  handleError3: function () {
    $Toast({
      content: '积分必须11以内的数字',
      type: 'error'
    });
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  exit: function () {
    wx.clearStorage()
    wx.navigateTo({
      url: '/pages/login/login',
    })
  },
  DotStyle(e) {
    this.setData({
      DotStyle: e.detail.value
    })
  },
  // cardSwiper
  cardSwiper(e) {
    this.setData({
      cardCur: e.detail.current
    })
  },
  // towerSwiper
  // 初始化towerSwiper
  towerSwiper(name) {
    let list = this.data[name];
    for (let i = 0; i < list.length; i++) {
      list[i].zIndex = parseInt(list.length / 2) + 1 - Math.abs(i - parseInt(list.length / 2))
      list[i].mLeft = i - parseInt(list.length / 2)
    }
    this.setData({
      swiperList: list
    })
  },
  // towerSwiper触摸开始
  towerStart(e) {
    this.setData({
      towerStart: e.touches[0].pageX
    })
  },
  // towerSwiper计算方向
  towerMove(e) {
    this.setData({
      direction: e.touches[0].pageX - this.data.towerStart > 0 ? 'right' : 'left'
    })
  },
  // towerSwiper计算滚动
  towerEnd(e) {
    let direction = this.data.direction;
    let list = this.data.swiperList;
    if (direction == 'right') {
      let mLeft = list[0].mLeft;
      let zIndex = list[0].zIndex;
      for (let i = 1; i < list.length; i++) {
        list[i - 1].mLeft = list[i].mLeft
        list[i - 1].zIndex = list[i].zIndex
      }
      list[list.length - 1].mLeft = mLeft;
      list[list.length - 1].zIndex = zIndex;
      this.setData({
        swiperList: list
      })
    } else {
      let mLeft = list[list.length - 1].mLeft;
      let zIndex = list[list.length - 1].zIndex;
      for (let i = list.length - 1; i > 0; i--) {
        list[i].mLeft = list[i - 1].mLeft
        list[i].zIndex = list[i - 1].zIndex
      }
      list[0].mLeft = mLeft;
      list[0].zIndex = zIndex;
      this.setData({
        swiperList: list
      })
    }
  },
  showModal(e) {
    console.log(e);
    this.setData({
      modalName: e.currentTarget.dataset.target
    })
  },
  hideModal(e) {
    this.setData({
      modalName: null
    })
  },
  tabSelect(e) {
    this.randomNum();
    this.setData({
      postList: [],
    });
    var urlPostList = app.globalData.url + '/api/content/posts';
    var token = app.globalData.token;
    console.warn(e.currentTarget.dataset.id);
    var params = {
      page: e.currentTarget.dataset.id,
      size: 10,
      sort: 'createTime,desc',
    };


    //@todo 文章内容网络请求API数据
    request.requestGetApi(urlPostList, token, params, this, this.successPostList, this.failPostList);

    this.setData({
      TabCur: e.currentTarget.dataset.id,
      scrollLeft: (e.currentTarget.dataset.id - 1) * 60
    });
  },
  switchSex: function (e) {
    // console.warn(e.detail.value);
    app.globalData.skin = e.detail.value
    console.log(app.globalData.skin)
    this.setData({
      skin: e.detail.value
    });
  },
  // 触摸开始事件
  touchStart: function (e) {
    touchDotX = e.touches[0].pageX; // 获取触摸时的原点
    touchDotY = e.touches[0].pageY;
    // 使用js计时器记录时间    
    interval = setInterval(function () {
      time++;
    }, 100);
  },
  // 触摸结束事件
  touchEnd: function (e) {
    let touchMoveX = e.changedTouches[0].pageX;
    let touchMoveY = e.changedTouches[0].pageY;
    let tmX = touchMoveX - touchDotX;
    let tmY = touchMoveY - touchDotY;
    if (time < 20) {
      let absX = Math.abs(tmX);
      let absY = Math.abs(tmY);
      if (absX > 2 * absY) {
        if (tmX < 0) {
          this.setData({
            modalName: null
          })
        } else {
          this.setData({
            modalName: "viewModal"
          })
        }
      }
      if (absY > absX * 2 && tmY < 0) {
        console.log("上滑动=====")
      }
    }
    clearInterval(interval); // 清除setInterval
    time = 0;
  },
  // 关闭抽屉
  shutDownDrawer: function (e) {
    this.setData({
      modalName: null
    })
  },
  //冒泡事件
  maopao: function (e) {
    console.log("冒泡...")
  },
  showMask: function (e) {

    this.shutDownDrawer();
  },

  //获取随机数
  randomNum: function () {
    var num = Math.floor(Math.random() * 10);
    this.setData({
      randomNum: num
    });
  },


})