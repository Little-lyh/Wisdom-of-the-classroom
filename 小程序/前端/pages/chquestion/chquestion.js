// pages/chquestion/chquestion.js
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
    id:'',

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var postId = options.postId;
    // console.log(postId);
    this.setData({
      id: postId
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
          SearchContent1: res.data.problem.question,
          SearchContent2: res.data.problem.option_A,
          SearchContent3: res.data.problem.option_B,
          SearchContent4: res.data.problem.option_C,
          SearchContent5: res.data.problem.option_D,
          SearchContent6: res.data.problem.answer,
          SearchContent7: res.data.problem.point,
        })
      },
      fail: function (e) {
        console.log('failed')
      },
    })

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
  DelSubmit:function(){
    var self=this
    wx.request({
      url: app.globalData.url1 + 'AddQuestionAction_delQuestion', //仅为示例，并非真实的接口地址
      data: {
        id: self.data.id,
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
          self.handleError2()
        }
      },
      fail: function (e) {
        console.log('failed')
      },
    })
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
    if (question == '' || question == null || question.length > 45) {
      this.handleError1("题干", 100)
    } else if (option_A == '' || option_A == null || option_A.length > 45) {
      this.handleError1("选项A", 45)
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
    } else if (!regPos.test(point) || point.toString().length > 11) {
      this.handleError3();
    }
    else {
      wx.request({
        url: app.globalData.url1 + 'AddQuestionAction_changeQuestion', //仅为示例，并非真实的接口地址
        data: {
          id: self.data.problem.id,
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
            self.handleError2()
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
  handleError1: function (name, num) {
    $Toast({
      content: name + '必须小于' + num + '且不能为空',
      type: 'error'
    });
  },
  handleError3: function () {
    $Toast({
      content: '积分必须11以内的数字',
      type: 'error'
    });
  },

})