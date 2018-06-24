;(function(window) {
  window.DocsifyCodefund = {
    create: function(codefundId) {
      return function(hook, vm) {
        hook.ready(function() {
          var style = document.createElement('style')
          var css = '#codefund_ad span.cf-wrapper { background-color: rgba(0,0,0,0) } #codefund_ad a.cf-text { color: #444; font-weight: 400; font-size: 10px }'

          style.type = 'text/css'
          if (style.styleSheet){
            style.styleSheet.cssText = css
          } else {
            style.appendChild(document.createTextNode(css))
          }
          document.head.appendChild(style)

          var script = document.createElement('script')
          script.src = "https://codesponsor.io/scripts/" + codefundId + "/embed.js"
          document.body.appendChild(script)
        })

        hook.doneEach(function () {
          // create a codefun container
          let nav = document.getElementsByClassName('sidebar-nav')
          let codefunContainer = document.createElement('div')
          codefunContainer.id = 'codefund_ad'
          nav[0].insertBefore(codefunContainer, nav[0].firstChild)

          if (document.getElementsByClassName('cf-wrapper').length === 0) {
            window._codefund && window._codefund.serve();
          }
        })
      }
    }
  }
})(window)
