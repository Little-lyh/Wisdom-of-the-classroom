// pages/prize/prize.js
const { $Message } = require('../../dist/base/index');
const { $Toast } = require('../../dist/base/index');
const request = require('../../utils/request.js');
let time = require('../../utils/util.js');
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    picurl:''

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      this.setData({
        picurl: app.globalData.url1,
      })
      console.log('向doex传来的id')
      console.log(options.id)
      var ii = options.id
      this.setData({
        id: options.id
      })
      console.log(ii)
      var self = this
      wx.request({
        url: app.globalData.url1 +'GetPrizeListAction_getPrize', //仅为示例，并非真实的接口地址
        data: {
          x: ii,
          y: 'fdsa'
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded;charset=utf-8' // 默认值
        },
        method: 'POST',
        success: function (res) {
          console.log(res.data.prize)
          self.setData({
            prize: res.data.prize,
          })
        },
        fail: function (e) {
          console.log('failed')
        },
      })
  },
  handleClose2() {
    this.setData({
      visible1: false
    });
    var self = this
    var p = getApp().globalData.point
    console.log(p)
    if (p < self.data.prize.point) {
      self.handleError()
    } else {
      wx.request({
        url: app.globalData.url1+'ChangePointAction', //仅为示例，并非真实的接口地址
        data: {
          username: getApp().globalData.username,
          point: self.data.prize.point,
          mode: '-',
          p_id: self.data.prize.id,
        },
        header: {
          'content-type': 'application/x-www-form-urlencoded;charset=utf-8' // 默认值
        },
        method: 'POST',
        success: function (res1) {
          console.log(res1)
          if (res1.data.result = true) {
            self.handleSuccess()
            setTimeout(function () {
              //要延时执行的代码
              wx.switchTab({
                url: '../prizes/prizes',
              })
            }, 1000) //延迟时间 这里是1秒
          } else {

          }
        },
        fail: function (e) {
          console.log('failed')
        },
      })
    }
  },
  handleSuccess() {
    $Toast({
      content: '兑换成功',
      type: 'success'
    });
  },
  handleClick: function () {
    this.setData({
      visible1: true
    });
  },
  handleClose1() {
    this.setData({
      visible1: false
    });
  },
  handleError() {
    $Toast({
      content: '积分不足',
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

  }
})