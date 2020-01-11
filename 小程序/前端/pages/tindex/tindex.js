// pages/tindex/tindex.js
//index.js
//获取应用实例
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
  data: {
    StatusBar: app.globalData.StatusBar,
    CustomBar: app.globalData.CustomBar,
    Custom: app.globalData.Custom,
    BlogName: app.globalData.BlogName,
    HaloUser: app.globalData.HaloUser,
    HaloPassword: app.globalData.HaloPassword,
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    userInfo: {},
    cardIdex: 1,
    randomNum: 0,
    animationTime: 1,
    moreFlag: false,
    pages: 0,
    cardCur: 0,
    TabCur: 0,
    scrollLeft: 0,
    openid: '',
    Role: '游客',
    roleFlag: false,
    adminOpenid: app.globalData.adminOpenid,
    bg: [{
      index: 1,
      back: 'images/bg1.jpg',
      info: '森林',
      info1: 'Forest'
    }, {
      index: 2,
      back: 'images/bg2.jpg',
      info: '教堂',
      info1: 'Cathedral'
    }, {
      index: 3,
      back: 'images/bg3.jpg',
      info: '山脉',
      info1: 'Mountain'

    }, {
      index: 4,
      back: 'images/bg4.jpg',
      info: '雪山',
      info1: 'Jokul'
    }, {
      index: 5,
      back: 'images/bg5.jpg',
      info: '夕阳',
      info1: 'Sunset'
    }
    ],
    url: '',
    colourList: [{
      colour: 'bg-red'
    }, {
      colour: 'bg-orange'
    }, {
      colour: 'bg-yellow'
    }, {
      colour: 'bg-olive'
    }, {
      colour: 'bg-green'
    }, {
      colour: 'bg-cyan'
    }, {
      colour: 'bg-blue'
    }, {
      colour: 'bg-purple'
    }, {
      colour: 'bg-mauve'
    }, {
      colour: 'bg-pink'
    }, {
      colour: 'bg-lightBlue'
    }],
    postList: [],
    username: 'yang',
    point: '90',
    address: '留和路288号',
    phone: '17857696989',

  },
  /**
   * 监听屏幕滚动 判断上下滚动
   */
  onPageScroll: function (event) {
    this.setData({
      scrollTop: event.detail.scrollTop
    })
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var self = this
    wx.request({
      url: app.globalData.url1 + 'StudentMessageAction_getStudentMessage', //仅为示例，并非真实的接口地址
      data: {
        username: getApp().globalData.username
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded;charset=utf-8' // 默认值
      },
      method: 'POST',
      success: function (res) {
        console.log(res.data.problem)
        getApp().globalData.point = res.data.student.point
        self.setData({
          student: res.data.student
        })
      },
      fail: function (e) {
        console.log('failed')
      },
    })
    wx.request({
      url: app.globalData.url1 + 'GetProblemListAction_getAllProblem', //仅为示例，并非真实的接口地址
      data: {
        x: '正在获取题目列表'
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded;charset=utf-8' // 默认值
      },
      method: 'POST',
      success: function (res) {
        console.log(res.data.problems)
        self.setData({
          postList: res.data.problems,
          username: app.globalData.username,
          address: app.globalData.address,
          phone: app.globalData.phone,
          point: app.globalData.point
        })
      },
      fail: function (e) {
        console.log('failed')
      },
    })
  },
  onLoad: function () {
    this.setData({
      url: app.globalData.url1
    })
    // 每日诗词
    jinrishici.load(result => {
      // 下面是处理逻辑示例
      console.log(result.data.content)
      this.setData({
        jinrishici: result.data.content
      });
    });
    var self = this
    wx.request({
      url: app.globalData.url1 + 'GetProblemListAction_getTeacherProblem', //仅为示例，并非真实的接口地址
      data: {
        x: '正在获取题目列表'
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded;charset=utf-8' // 默认值
      },
      method: 'POST',
      success: function (res) {
        console.log(res.data.problems)
        self.setData({
          postList: res.data.problems,
          username: app.globalData.username,
        })
      },
      fail: function (e) {
        console.log('failed')
      },
    })
    wx.request({
      url: app.globalData.url1 + 'TeacherMessageAction', //仅为示例，并非真实的接口地址
      data: {
        username: getApp().globalData.username
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded;charset=utf-8' // 默认值
      },
      method: 'POST',
      success: function (res) {
        self.setData({
          teacher: res.data.teacher
        })
      },
      fail: function (e) {
        console.log('failed')
      },
    })
    var urlPostList = app.globalData.url + '/api/content/posts';
    var token = app.globalData.token;
    var params = {
      page: 0,
      size: 10,
      sort: 'createTime,desc',
    };
    var paramBanner = {
      page: 0,
      size: 5,
      sort: 'visits,desc',
    };
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

  /**
   * 加载更多
   */
  loadMore: function () {

  },
  /**
   * 搜索文章模块
   */
  Search: function (e) {
    var content = e.detail.value.replace(/\s+/g, '');
    // console.log(content);
    var that = this;
    that.setData({
      SearchContent: content,
    });
  },
  //获取题目
  SearchSubmit: function (e) {
    console.log(this.data.SearchContent);
    console.log(e)
    var self = this;
    var search = this.data.SearchContent
    var regPos = / ^\d+$/;
    if (search == '' || search == null) {
      wx.request({
        url: app.globalData.url1 + 'GetProblemListAction_getAllProblem', //仅为示例，并非真实的接口地址
        data: {
          x: '正在获取题目列表'
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded;charset=utf-8' // 默认值
        },
        method: 'POST',
        success: function (res) {
          console.log(res.data.problems)
          self.setData({
            postList: res.data.problems,
            username: app.globalData.username,
            address: app.globalData.address,
            phone: app.globalData.phone,
            point: app.globalData.point
          })
        },
        fail: function (e) {
          console.log('failed')
        },
      })
    } else {
      if (!regPos.test(search) || search.toString().length > 11) {
        this.handleError3();
      } else {
        wx.request({
          url: app.globalData.url1 + 'GetProblemListAction_getProblem1', //仅为示例，并非真实的接口地址
          data: {
            x: search,
            y: 'fdsa'
          },
          header: {
            'content-type': 'application/x-www-form-urlencoded;charset=utf-8' // 默认值
          },
          method: 'POST',
          success: function (res) {
            console.log(res.data.problem)
            self.setData({
              postList: res.data.problem,
            })
          },
          fail: function (e) {
            console.log('failed')
          },
        })
      }
    }

  },
  handleError3: function () {
    $Toast({
      content: '题目编号必须是11以内的数字',
      type: 'error'
    });
  },
  successSearch: function (res, selfObj) {
    var that = this;
    // console.warn(res.data.content);
    var list = res.data.content;
    for (let i = 0; i < list.length; ++i) {
      list[i].createTime = util.customFormatTime(list[i].createTime, 'Y.M.D');
    }
    if (res.data.content != "") {
      that.setData({
        postList: res.data.content,
        moreFlag: false,
        pages: res.data.pages,
      });
    } else {
      that.setData({
        postList: res.data.content,
        moreFlag: true,
        pages: res.data.pages,
      });
    }
  },
  failSearch: function (res, selfObj) {
    console.error('failSearch', res)
  },

  /**
  * 用户点击右上角分享
  */
  onShareAppMessage: function () {
    return {
      title: this.data.jinrishici,
      path: '/pages/index/index',
      imageUrl: 'https://image.aquan.run/poster.jpg',
    }
  },
  exit: function () {
    wx.clearStorage()
    wx.navigateTo({
      url: '/pages/iindex/iindex',
    })
  }
})

